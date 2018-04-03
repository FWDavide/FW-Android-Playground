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

public class ApiResponse<T> {
    
    @SerializedName("code")
    private int code;
    
    @SerializedName("status")
    private String status;
    
    @SerializedName("copyright")
    private String copyright;
    
    @SerializedName("attributionText")
    private String attributionText;
    
    @SerializedName("attributionHTML")
    private String getAttributionHtml;
    
    @SerializedName("etag")
    private String etag;
    
    @SerializedName("data")
    private T response;
    
    public ApiResponse() {
    }
    
    public ApiResponse(ApiResponse apiResponse) {
        this.code = apiResponse.getCode();
        this.status = apiResponse.getStatus();
        this.copyright = apiResponse.getCopyright();
        this.attributionText = apiResponse.getAttributionText();
        this.getAttributionHtml = apiResponse.getGetAttributionHtml();
        this.etag = apiResponse.getEtag();
    }
    
    public int getCode() {
        return code;
    }
    
    public void setCode(int code) {
        this.code = code;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getCopyright() {
        return copyright;
    }
    
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
    
    public String getAttributionText() {
        return attributionText;
    }
    
    public void setAttributionText(String attributionText) {
        this.attributionText = attributionText;
    }
    
    public String getGetAttributionHtml() {
        return getAttributionHtml;
    }
    
    public void setGetAttributionHtml(String getAttributionHtml) {
        this.getAttributionHtml = getAttributionHtml;
    }
    
    public String getEtag() {
        return etag;
    }
    
    public void setEtag(String etag) {
        this.etag = etag;
    }
    
    public T getResponse() {
        return response;
    }
    
    public void setResponse(T response) {
        this.response = response;
    }
}
