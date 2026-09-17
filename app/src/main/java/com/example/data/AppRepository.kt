package com.example.data

import kotlinx.coroutines.flow.Flow

class AppRepository(
    private val lessonProgressDao: LessonProgressDao,
    private val savedSnippetDao: SavedSnippetDao
) {
    val allProgress: Flow<List<LessonProgress>> = lessonProgressDao.getAllProgress()
    val allSavedSnippets: Flow<List<SavedSnippet>> = savedSnippetDao.getAllSnippets()

    suspend fun toggleCompleted(lessonId: String, currentStatus: Boolean) {
        val existing = lessonProgressDao.getProgressForLesson(lessonId)
        if (existing == null) {
            lessonProgressDao.upsertProgress(
                LessonProgress(
                    lessonId = lessonId,
                    isCompleted = !currentStatus,
                    isBookmarked = false
                )
            )
        } else {
            lessonProgressDao.setCompleted(lessonId, !currentStatus)
        }
    }

    suspend fun toggleBookmark(lessonId: String, currentStatus: Boolean) {
        val existing = lessonProgressDao.getProgressForLesson(lessonId)
        if (existing == null) {
            lessonProgressDao.upsertProgress(
                LessonProgress(
                    lessonId = lessonId,
                    isCompleted = false,
                    isBookmarked = !currentStatus
                )
            )
        } else {
            lessonProgressDao.setBookmarked(lessonId, !currentStatus)
        }
    }

    suspend fun saveSnippet(title: String, code: String): Long {
        return savedSnippetDao.insertSnippet(
            SavedSnippet(
                title = title.ifBlank { "আমার কোড" },
                htmlCode = code
            )
        )
    }

    suspend fun deleteSnippet(id: Long) {
        savedSnippetDao.deleteSnippet(id)
    }
}
