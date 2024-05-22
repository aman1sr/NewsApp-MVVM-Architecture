package com.aman.mynewsmvvm_cleanarch.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Language")
data class LanguageEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String = "",
    @ColumnInfo(name = "name") val name: String = ""
)

fun LanguageEntity.asLanguage() = Language(
    id = id, name = name
)
