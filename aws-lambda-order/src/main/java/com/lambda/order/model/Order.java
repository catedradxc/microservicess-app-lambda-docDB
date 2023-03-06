package com.lambda.order.model;

import com.lambda.order.clients.CatalogClient;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "orders")
public class Order {

    @Id
    @BsonId
    private String id;

    @BsonProperty(value = "customerId")
    private String customerId;

    @BsonProperty(value = "orderLines")
    private List<OrderLine> orderLines;

    public Order() {
        super();
        orderLines = new ArrayList<OrderLine>();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public Order(String customerId) {
        super();
        this.customerId = customerId;
        this.orderLines = new ArrayList<OrderLine>();
    }

    public void setOrderLines(List<OrderLine> orderLine) {
        this.orderLines = orderLine;
    }

    public void addLine(int count, String itemId) {
        this.orderLines.add(new OrderLine(count, itemId));
    }


    public int getNumberOfLines() {
        return orderLines.size();
    }

    public double totalPrice(CatalogClient itemClient) {
        return orderLines.stream()
                .map((ol) -> ol.getCount() * itemClient.price(ol.getItemId()))
                .reduce(0.0, (d1, d2) -> d1 + d2);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);

    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
