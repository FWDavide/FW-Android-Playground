/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.domain.model;


import java.io.Serializable;

/**
 * Created by stelian on 03/04/2018.
 */

public class Character implements Serializable {
    
    private String id;
    
    private String name;
    
    private String description;
    
    private String modified;
    
    private String resourceUri;
    
    private String thumbnailUrl;
    
    private String imageUrl;
    
    private String landscapeImageUrl;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getModified() {
        return modified;
    }
    
    public void setModified(String modified) {
        this.modified = modified;
    }
    
    public String getResourceUri() {
        return resourceUri;
    }
    
    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }
    
    /**
     * Get an URL pointing to a portrait thumbnail image of the character.
     * @return
     */
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
    
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
    
    /**
     * Return a URL pointing to a full resolution image of the character.
     * @return
     */
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    /**
     * Return an URL pointing to a landscape image of the character.
     * @return
     */
    public String getLandscapeImageUrl() {
        return landscapeImageUrl;
    }
    
    public void setLandscapeImageUrl(String landscapeImageUrl) {
        this.landscapeImageUrl = landscapeImageUrl;
    }
}
