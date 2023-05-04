package com.example.youtubeapp.data.db

import com.example.youtubeapp.model.Item
import com.example.youtubeapp.model.PageInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object TypeConverter {

    val gson = Gson()

    @androidx.room.TypeConverter
    fun jsonToItems(data: String): List<Item> {
        val listType: Type = object : TypeToken<List<Item>>() {}.type
        return gson.fromJson(data, listType)
    }

    @androidx.room.TypeConverter
    fun itemsToString(items: List<Item>): String {
        return gson.toJson(items)
    }

    @androidx.room.TypeConverter
    fun pageToString(pageInfo: PageInfo): String {
        return gson.toJson(pageInfo)
    }

    @androidx.room.TypeConverter
    fun jsonToPageInfo(data: String): PageInfo {
        return gson.fromJson(data, PageInfo::class.java)
    }
}