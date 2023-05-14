package com.example.newsapp.db

import androidx.room.TypeConverters
import com.example.newsapp.model.Source

class TypeConverter {

    @androidx.room.TypeConverter
    fun fromSource(source: Source) : String {
        return source.name
    }

    @androidx.room.TypeConverter
    fun toSource(name: String) : Source {
        return Source(name, name)
    }
}