package com.lambda.order.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.bson.codecs.pojo.annotations.BsonProperty;



public class OrderLine {

    @BsonProperty(value = "F_COUNT")
    private int count;

    @BsonProperty(value = "itemId")
    private String itemId;

    public void setCount(int count) {
        this.count = count;
    }

    public void setItemId(String item) {
        this.itemId = item;
    }

    public OrderLine() {
    }

    public OrderLine(int count, String item) {
        this.count = count;
        this.itemId = item;
    }

    public int getCount() {
        return count;
    }

    public String getItemId() {
        return itemId;
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
