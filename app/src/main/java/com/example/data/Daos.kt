package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LessonProgressDao {
    @Query("SELECT * FROM lesson_progress")
    fun getAllProgress(): Flow<List<LessonProgress>>

    @Query("SELECT * FROM lesson_progress WHERE lessonId = :lessonId")
    suspend fun getProgressForLesson(lessonId: String): LessonProgress?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertProgress(progress: LessonProgress)

    @Query("UPDATE lesson_progress SET isCompleted = :isCompleted, lastUpdated = :timestamp WHERE lessonId = :lessonId")
    suspend fun setCompleted(lessonId: String, isCompleted: Boolean, timestamp: Long = System.currentTimeMillis())

    @Query("UPDATE lesson_progress SET isBookmarked = :isBookmarked, lastUpdated = :timestamp WHERE lessonId = :lessonId")
    suspend fun setBookmarked(lessonId: String, isBookmarked: Boolean, timestamp: Long = System.currentTimeMillis())
}

@Dao
interface SavedSnippetDao {
    @Query("SELECT * FROM saved_snippets ORDER BY createdAt DESC")
    fun getAllSnippets(): Flow<List<SavedSnippet>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSnippet(snippet: SavedSnippet): Long

    @Query("DELETE FROM saved_snippets WHERE id = :id")
    suspend fun deleteSnippet(id: Long)
}
