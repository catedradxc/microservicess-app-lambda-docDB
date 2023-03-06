package com.lambda.order;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.lambda.order.model.Order;
import com.lambda.order.model.Request;
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
        MongoDatabase db = mongoClient.getDatabase("orders");
        MongoCollection<Order> orders = db.getCollection("orders", Order.class);

        Order o = null;

        switch (request.getHttpMethod()){
            case "GET":
                if(request.getId() == null){
                    List<Order> orderList = new ArrayList<>();
                    List<Order> customerList = new ArrayList<>();
                    FindIterable<Order> iterable = orders.find();
                    iterable.into(orderList);
                    mongoClient.close();
                    return orderList;
                }else {
                    o = orders.find(eq("_id", request.getId())).first();
                    mongoClient.close();
                    return o;
                }
            case "POST":
                o = request.getOrder();
                if(o != null) {
                    o.setId(new ObjectId().toString());
                    orders.insertOne(o);
                }
                return o;
            case "DELETE":
                if (request.getId() != null)
                    orders.deleteOne(eq("_id", request.getId()));
                mongoClient.close();
                return "deleted";
        }
        return null;
    }
}
