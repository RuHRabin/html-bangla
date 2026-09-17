package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lesson_progress")
data class LessonProgress(
    @PrimaryKey val lessonId: String,
    val isCompleted: Boolean = false,
    val isBookmarked: Boolean = false,
    val lastUpdated: Long = System.currentTimeMillis()
)

@Entity(tableName = "saved_snippets")
data class SavedSnippet(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val htmlCode: String,
    val createdAt: Long = System.currentTimeMillis()
)
