/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.domain.repositories.character

import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Date
import java.util.HashMap

/**
 * Utility class used for creating queries that match the Marvel API.
 *
 *
 * Inspired from [Marvel API Client repository](https://github.com/Karumi/MarvelApiClientAndroid/blob/master/MarvelApiClient/src/main/java/com/karumi/marvelapiclient/model/java)
 *
 * Created by stelian on 04/04/2018.
 */

class CharacterQuery(private val name: String?, private val nameStartWith: String?, private val modifiedSince: String?, private val comics: String?,
                     private val series: String?, private val events: String?, private val stories: String?,
                     private val orderBy: String?, private val limit: Int, private val offset: Int) {
    
    
    fun toMap(): Map<String, Any> {
        val returnValues = HashMap<String, Any>()
        
        if (name != null) {
            returnValues[QUERY_NAME] = name
        }
        
        if (nameStartWith != null) {
            returnValues[QUERY_NAME_START_WITH] = nameStartWith
        }
        
        if (modifiedSince != null) {
            returnValues[QUERY_MODIFIED_SINCE] = modifiedSince
        }
        
        if (comics != null) {
            returnValues[QUERY_COMICS] = comics
        }
        
        if (series != null) {
            returnValues[QUERY_SERIES] = series
        }
        
        if (events != null) {
            returnValues[QUERY_EVENTS] = events
        }
        
        if (stories != null) {
            returnValues[QUERY_STORIES] = stories
        }
        
        if (orderBy != null) {
            returnValues[QUERY_ORDER_BY] = orderBy
        }
        
        if (limit > 0) {
            returnValues[QUERY_LIMIT] = limit
        }
        
        if (offset > 0) {
            returnValues[QUERY_OFFSET] = offset
        }
        
        return returnValues
    }
    
    
    /**
     * Builder used for simplifying the Character querying process.
     */
    class Builder {
        
        private var name: String? = null
        private var nameStartWith: String? = null
        private var modifiedSince: Date? = null
        private val comics = ArrayList<Int>()
        private val series = ArrayList<Int>()
        private val events = ArrayList<Int>()
        private val stories = ArrayList<Int>()
        private var orderBy: String? = null
        private var orderAsscending = false
        private var limit: Int = 0
        private var offset: Int = 0
        
        fun name(`val`: String): Builder {
            name = `val`
            return this
        }
        
        fun nameStartWith(`val`: String): Builder {
            nameStartWith = `val`
            return this
        }
        
        fun modifiedSince(`val`: Date): Builder {
            modifiedSince = `val`
            return this
        }
        
        fun comic(comic: Int): Builder {
            comics.add(comic)
            return this
        }
        
        fun comics(comics: List<Int>): Builder {
            checkNull(comics)
            this.comics.addAll(comics)
            return this
        }
        
        fun serie(serie: Int): Builder {
            series.add(serie)
            return this
        }
        
        fun series(series: List<Int>): Builder {
            checkNull(series)
            this.series.addAll(series)
            return this
        }
        
        fun event(event: Int): Builder {
            events.add(event)
            return this
        }
        
        fun events(events: List<Int>): Builder {
            checkNull(events)
            this.events.addAll(events)
            return this
        }
        
        fun story(story: Int): Builder {
            stories.add(story)
            return this
        }
        
        fun stories(stories: List<Int>): Builder {
            checkNull(stories)
            this.stories.addAll(stories)
            return this
        }
        
        fun orderByName(asscending: Boolean): Builder {
            orderBy = ORDER_BY_NAME
            orderAsscending = asscending
            return this
        }
        
        fun orderByLastModified(asscending: Boolean): Builder {
            orderBy = ORDER_BY_MODIFIED
            orderAsscending = asscending
            return this
        }
        
        fun limit(`val`: Int): Builder {
            checkLimit(`val`)
            limit = `val`
            return this
        }
        
        fun offset(`val`: Int): Builder {
            if (`val` < 0) {
                throw IllegalArgumentException("offset must be bigger or equals than zero")
            }
            
            this.offset = `val`
            return this
        }
        
        fun build(): CharacterQuery {
            val plainModifedSince = convertDate(modifiedSince)
            val plainComics = convertToList(comics)
            val plainEvents = convertToList(events)
            val plainSeries = convertToList(series)
            val plainStories = convertToList(stories)
            val plainOrderBy = convertOrderBy(orderBy, orderAsscending)
            
            return CharacterQuery(this.name, this.nameStartWith, plainModifedSince,
                    plainComics, plainSeries, plainEvents, plainStories, plainOrderBy,
                    this.limit, this.offset)
        }
        
        private fun convertDate(date: Date?): String? {
            if (date == null) {
                return null
            }
            
            val format = SimpleDateFormat(DATE_PATTERN)
            return format.format(date)
        }
        
        private fun checkLimit(limit: Int) {
            if (limit <= 0) {
                throw IllegalArgumentException("limit must be bigger than zero")
            }
            
            if (limit > MAX_SIZE) {
                throw IllegalArgumentException("limit must be smaller than 100")
            }
        }
        
        private fun checkNull(list: List<Int>?) {
            if (list == null) {
                throw IllegalArgumentException("the collection can not be null")
            }
        }
        
        private fun convertToList(list: List<Int>): String? {
            var plainList = ""
            for (i in list.indices) {
                plainList += Integer.toString(list[i])
                if (i < list.size - 1) {
                    plainList += LIST_SEPARATOR
                }
            }
            return if (plainList.isEmpty()) null else plainList
        }
        
        private fun convertOrderBy(orderBy: String?, ascendant: Boolean): String? {
            if (orderBy == null) {
                return null
            }
            
            val plainOrderBy = orderBy.toString()
            return if (ascendant) plainOrderBy else "-$plainOrderBy"
        }
        
        companion object {
            
            val MAX_SIZE = 100
            val LIST_SEPARATOR = ","
            
            private val DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZ"
            private val ORDER_BY_NAME = "name"
            private val ORDER_BY_MODIFIED = "modified"
        }
    }
    
    companion object {
        
        private val QUERY_NAME = "name"
        private val QUERY_NAME_START_WITH = "nameStartsWith"
        private val QUERY_MODIFIED_SINCE = "modifiedSince"
        private val QUERY_COMICS = "comics"
        private val QUERY_SERIES = "series"
        private val QUERY_EVENTS = "events"
        private val QUERY_STORIES = "stories"
        private val QUERY_ORDER_BY = "orderBy"
        private val QUERY_LIMIT = "limit"
        private val QUERY_OFFSET = "offset"
    }
}
