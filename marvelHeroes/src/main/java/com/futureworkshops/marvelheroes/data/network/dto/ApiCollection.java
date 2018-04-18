/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.data.network.dto;

import com.google.gson.annotations.SerializedName;

/**
 * DTO used to parse the response from the Marvel API and extract only the relevant information
 * without polluting all the DTOs with redundant information.
 *
 * Created by stelian on 03/04/2018.
 */

public class ApiCollection<T> {
    
    @SerializedName("offset")
    private int offset;
    
    @SerializedName("limit")
    private int limit;
    
    @SerializedName("total")
    private int total;
    
    @SerializedName("count")
    private int count;
    
    @SerializedName("results")
    private T response;
    
    public ApiCollection() {
    }
    
    public ApiCollection(ApiCollection apiCollection) {
        this.offset = apiCollection.getOffset();
        this.limit = apiCollection.getLimit();
        this.total = apiCollection.getTotal();
        this.count = apiCollection.getCount();
    }
    
    
    public int getOffset() {
        return offset;
    }
    
    public void setOffset(int offset) {
        this.offset = offset;
    }
    
    public int getLimit() {
        return limit;
    }
    
    public void setLimit(int limit) {
        this.limit = limit;
    }
    
    public int getTotal() {
        return total;
    }
    
    public void setTotal(int total) {
        this.total = total;
    }
    
    public int getCount() {
        return count;
    }
    
    public void setCount(int count) {
        this.count = count;
    }
    
    public T getResponse() {
        return response;
    }
    
    public void setResponse(T response) {
        this.response = response;
    }
}
