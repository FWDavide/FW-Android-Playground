/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.domain.repositories.character

import java.text.SimpleDateFormat
import java.util.*

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
        return HashMap<String, Any>()
                .apply {
                    name?.let { this[QUERY_NAME] = it }
                    nameStartWith?.let { this[QUERY_NAME_START_WITH] = it }
                    modifiedSince?.let { this[QUERY_MODIFIED_SINCE] = it }
                    comics?.let { this[QUERY_COMICS] = it }
                    series?.let { this[QUERY_SERIES] = it }
                    events?.let { this[QUERY_EVENTS] = it }
                    stories?.let { this[QUERY_STORIES] = it }
                    orderBy?.let { this[QUERY_ORDER_BY] = it }
                    limit.takeIf { it > 0 }?.let { this[QUERY_LIMIT] = limit }
                    offset.takeIf { it > 0 }?.let { this[QUERY_OFFSET] = offset }
                }
    }
    
    
    /**
     * Builder used for simplifying the MarvelCharacter querying process.
     */
    class Builder(var name: String? = null,
                  var nameStartWith: String? = null,
                  var modifiedSince: Date? = null,
                  var comics: MutableList<Int> = mutableListOf(),
                  var series: MutableList<Int> = mutableListOf(),
                  var events: MutableList<Int> = mutableListOf(),
                  var stories: MutableList<Int> = mutableListOf(),
                  var orderBy: String? = null,
                  var orderAscending: Boolean = false,
                  var limit: Int = 0,
                  var offset: Int = 0) {
        
        fun name(name: String): Builder {
            this.name = name
            return this
        }
        
        fun nameStartWith(nameStartWith: String): Builder {
            this.nameStartWith = nameStartWith
            return this
        }
        
        fun modifiedSince(modifiedSince: Date): Builder {
            this.modifiedSince = modifiedSince
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
            orderAscending = asscending
            return this
        }
        
        fun orderByLastModified(asscending: Boolean): Builder {
            orderBy = ORDER_BY_MODIFIED
            orderAscending = asscending
            return this
        }
        
        fun limit(limit: Int): Builder {
            checkLimit(limit)
            this.limit = limit
            return this
        }
        
        fun offset(offset: Int): Builder {
            if (offset < 0) {
                throw IllegalArgumentException("offset must be bigger or equals than zero")
            }
            
            this.offset = offset
            return this
        }
        
        fun build(): CharacterQuery {
            val plainModifedSince = convertDate(modifiedSince)
            val plainComics = convertToList(comics)
            val plainEvents = convertToList(events)
            val plainSeries = convertToList(series)
            val plainStories = convertToList(stories)
            val plainOrderBy = convertOrderBy(orderBy, orderAscending)
            
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
