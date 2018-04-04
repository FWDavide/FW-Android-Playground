/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.domain.repositories.character;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class used for creating queries that match the Marvel API.
 *
 * <p>Inspired from <a href="https://github.com/Karumi/MarvelApiClientAndroid/blob/master/MarvelApiClient/src/main/java/com/karumi/marvelapiclient/model/java">Marvel API Client repository</a></p>
 *
 * Created by stelian on 04/04/2018.
 */

public class CharacterQuery {
    
    private static final String QUERY_NAME = "name";
    private static final String QUERY_NAME_START_WITH = "nameStartsWith";
    private static final String QUERY_MODIFIED_SINCE = "modifiedSince";
    private static final String QUERY_COMICS = "comics";
    private static final String QUERY_SERIES = "series";
    private static final String QUERY_EVENTS = "events";
    private static final String QUERY_STORIES = "stories";
    private static final String QUERY_ORDER_BY = "orderBy";
    private static final String QUERY_LIMIT = "limit";
    private static final String QUERY_OFFSET = "offset";
    
    private String name;
    private String nameStartWith;
    private String modifiedSince;
    private String comics;
    private String series;
    private String events;
    private String stories;
    private String orderBy;
    private int limit;
    private int offset;
    
    public CharacterQuery(String name, String nameStartWith, String modifiedSince, String comics,
                          String series, String events, String stories,
                          String orderBy, int limit, int offset) {
        this.name = name;
        this.nameStartWith = nameStartWith;
        this.modifiedSince = modifiedSince;
        this.comics = comics;
        this.series = series;
        this.events = events;
        this.stories = stories;
        this.orderBy = orderBy;
        this.limit = limit;
        this.offset = offset;
    }
    
    
    public Map<String, Object> toMap() {
        Map<String, Object> returnValues = new HashMap<>();
        
        if (name != null) {
            returnValues.put(QUERY_NAME, name);
        }
        
        if (nameStartWith != null) {
            returnValues.put(QUERY_NAME_START_WITH, nameStartWith);
        }
        
        if (modifiedSince != null) {
            returnValues.put(QUERY_MODIFIED_SINCE, modifiedSince);
        }
        
        if (comics != null) {
            returnValues.put(QUERY_COMICS, comics);
        }
        
        if (series != null) {
            returnValues.put(QUERY_SERIES, series);
        }
        
        if (events != null) {
            returnValues.put(QUERY_EVENTS, events);
        }
        
        if (stories != null) {
            returnValues.put(QUERY_STORIES, stories);
        }
        
        if (orderBy != null) {
            returnValues.put(QUERY_ORDER_BY, orderBy);
        }
        
        if (limit > 0) {
            returnValues.put(QUERY_LIMIT, limit);
        }
        
        if (offset > 0) {
            returnValues.put(QUERY_OFFSET, offset);
        }
        
        return returnValues;
    }
    
    /**
     * Builder used for simplifying the Character querying process.
     */
    public static final class Builder {
        
        public final static int MAX_SIZE = 100;
        public static final String LIST_SEPARATOR = ",";
        
        private static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZ";
        private static final String ORDER_BY_NAME = "name";
        private static final String ORDER_BY_MODIFIED = "modified";
        
        private String name;
        private String nameStartWith;
        private Date modifiedSince;
        private List<Integer> comics = new ArrayList<>();
        private List<Integer> series = new ArrayList<>();
        private List<Integer> events = new ArrayList<>();
        private List<Integer> stories = new ArrayList<>();
        private String orderBy;
        private boolean orderAsscending = false;
        private int limit;
        private int offset;
        
        
        public Builder() {
        }
        
        public Builder name(String val) {
            name = val;
            return this;
        }
        
        public Builder nameStartWith(String val) {
            nameStartWith = val;
            return this;
        }
        
        public Builder modifiedSince(Date val) {
            modifiedSince = val;
            return this;
        }
        
        public Builder comic(int comic) {
            comics.add(comic);
            return this;
        }
        
        public Builder comics(List<Integer> comics) {
            checkNull(comics);
            this.comics.addAll(comics);
            return this;
        }
        
        public Builder serie(int serie) {
            series.add(serie);
            return this;
        }
        
        public Builder series(List<Integer> series) {
            checkNull(series);
            this.series.addAll(series);
            return this;
        }
        
        public Builder event(int event) {
            events.add(event);
            return this;
        }
        
        public Builder events(List<Integer> events) {
            checkNull(events);
            this.events.addAll(events);
            return this;
        }
        
        public Builder story(int story) {
            stories.add(story);
            return this;
        }
        
        public Builder stories(List<Integer> stories) {
            checkNull(stories);
            this.stories.addAll(stories);
            return this;
        }
        
        public Builder orderByName(boolean asscending) {
            orderBy = ORDER_BY_NAME;
            orderAsscending = asscending;
            return this;
        }
        
        public Builder orderByLastModified(boolean asscending) {
            orderBy = ORDER_BY_MODIFIED;
            orderAsscending = asscending;
            return this;
        }
        
        public Builder limit(int val) {
            checkLimit(val);
            limit = val;
            return this;
        }
        
        public Builder offset(int val) {
            if (val < 0) {
                throw new IllegalArgumentException("offset must be bigger or equals than zero");
            }
            
            this.offset = val;
            return this;
        }
        
        public CharacterQuery build() {
            String plainModifedSince = convertDate(modifiedSince);
            String plainComics = convertToList(comics);
            String plainEvents = convertToList(events);
            String plainSeries = convertToList(series);
            String plainStories = convertToList(stories);
            String plainOrderBy = convertOrderBy(orderBy, orderAsscending);
            
            return new CharacterQuery(this.name, this.nameStartWith, plainModifedSince,
                plainComics, plainSeries, plainEvents, plainStories, plainOrderBy,
                this.limit, this.offset);
        }
        
        private String convertDate(Date date) {
            if (date == null) {
                return null;
            }
            
            SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
            return format.format(date);
        }
        
        private void checkLimit(int limit) {
            if (limit <= 0) {
                throw new IllegalArgumentException("limit must be bigger than zero");
            }
            
            if (limit > MAX_SIZE) {
                throw new IllegalArgumentException("limit must be smaller than 100");
            }
        }
        
        private void checkNull(List<Integer> list) {
            if (list == null) {
                throw new IllegalArgumentException("the collection can not be null");
            }
        }
        
        private String convertToList(List<Integer> list) {
            String plainList = "";
            for (int i = 0; i < list.size(); i++) {
                plainList += Integer.toString(list.get(i));
                if (i < list.size() - 1) {
                    plainList += LIST_SEPARATOR;
                }
            }
            return (plainList.isEmpty()) ? null : plainList;
        }
        
        private String convertOrderBy(String orderBy, boolean ascendant) {
            if (orderBy == null) {
                return null;
            }
            
            String plainOrderBy = orderBy.toString();
            return (ascendant) ? plainOrderBy : "-" + plainOrderBy;
        }
    }
}
