package com.lambda.catalog;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.lambda.catalog.model.Item;
import com.lambda.catalog.model.Request;
import com.mongodb.ConnectionString;
import com.mongodb.client.*;
import com.mongodb.MongoClientSettings;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        MongoDatabase db = mongoClient.getDatabase("catalogdb");
        MongoCollection<Item> items = db.getCollection("items", Item.class);

        Item i = null;

        switch (request.getHttpMethod()){
            case "GET":
                if(request.getId() == null && request.getName() == null){
                    List<Item> itemList = new ArrayList<>();
                    FindIterable<Item> iterable = items.find();
                    iterable.into(itemList);
                    mongoClient.close();
                    return itemList;
                }else {
                    if(request.getName() != null){
                        List<Item> itemList = new ArrayList<>();
                        FindIterable<Item> iterable = items.find();
                        iterable.into(itemList);
                        mongoClient.close();
                        return itemList.stream().filter(c -> c.getName().equals(request.getName()))
                                .collect(Collectors.toList());
                    }else{
                        i = items.find(eq("_id", request.getId())).first();
                        mongoClient.close();
                        return i;
                    }
                }
            case "POST":
                i = request.getItem();
                if(i != null){
                    i.setId(new ObjectId().toString());
                    items.insertOne(i);
                }
                mongoClient.close();
                return i;
            case "DELETE":
                if (request.getId() != null)
                    items.deleteOne(eq("_id", request.getId()));
                mongoClient.close();
                return "deleted";
        }
        mongoClient.close();
        return null;
    }

}
