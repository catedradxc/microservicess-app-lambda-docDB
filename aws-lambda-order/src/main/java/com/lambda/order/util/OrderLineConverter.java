package com.lambda.order.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambda.order.model.OrderLine;

import java.util.ArrayList;
import java.util.List;

public class OrderLineConverter /*implements DynamoDBTypeConverter<List<String>, List<OrderLine>> */{

   /* @Override
    public List<String> convert(List<OrderLine> orderLine) {
        List<OrderLine> orderLines = (List<OrderLine>) orderLine;
        List<String> lines = new ArrayList<>();
        try {
            if (orderLines != null) {
                for(OrderLine o : orderLines)
                    lines.add(String.format("{\"count\": \"%s\", \"itemId\": \"%s\"}", o.getCount()+"", o.getItemId()+""));

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    @Override
    public List<OrderLine> unconvert(List<String> strings) {
        List<OrderLine> lines = new ArrayList<>();
        try {
            if (strings != null ) {
                for(String s : strings){
                    ObjectMapper mapper = new ObjectMapper();
                    lines.add(mapper.readValue(s, OrderLine.class));
                }

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return lines;
    }*/
}
