package com.lambda.example.customer;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.lambda.example.customer.model.Customer;
import com.lambda.example.customer.model.Request;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


public class LambdaHandler implements RequestHandler<Request, Object> {

    @Override
    public Object handleRequest(Request request, Context context) {

        String mongouri = "mongodb://userdemo:password@clusterdemo.cluster-c83aobj9axfu.eu-central-1.docdb.amazonaws.com:27017/?replicaSet=rs0&readPreference=secondaryPreferred&retryWrites=false";
        ConnectionString connectionString = new ConnectionString(mongouri);
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(codecRegistry)
                .build();
        MongoClient mongoClient = MongoClients.create(clientSettings);
        MongoDatabase db = mongoClient.getDatabase("customers");
        MongoCollection<Customer> customers = db.getCollection("customers", Customer.class);

        Customer c = null;

        switch (request.getHttpMethod()){
            case "GET":
                if(request.getId() == null){
                    List<Customer> customerList = new ArrayList<>();
                    FindIterable<Customer> iterable = customers.find();
                    iterable.into(customerList);
                    mongoClient.close();
                    return customerList;
                }else {
                    c = customers.find(eq("_id", request.getId())).first();
                    mongoClient.close();
                    return c;
                }
            case "POST":
                c = request.getCustomer();
                if(c != null) {
                    c.setId(new ObjectId().toString());
                    customers.insertOne(c);
                }
                return c;
            case "DELETE":
                if (request.getId() != null)
                    customers.deleteOne(eq("_id", request.getId()));
                mongoClient.close();
                return "deleted";
        }
        return null;
    }

}
