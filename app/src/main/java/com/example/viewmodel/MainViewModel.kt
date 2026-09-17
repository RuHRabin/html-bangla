package com.example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.AppDatabase
import com.example.data.AppRepository
import com.example.data.LessonProgress
import com.example.data.SavedSnippet
import com.example.model.HscLessonsData
import com.example.model.LessonTopic
import com.example.model.QuizQuestion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class AppTab(val titleBn: String) {
    LESSONS("পাঠসমূহ"),
    EDITOR("কোড এডিটর"),
    QUIZ("কুইজ টেস্ট"),
    SAVED("বুকমার্ক ও কোড")
}

enum class EditorViewMode {
    SPLIT,
    EDITOR_ONLY,
    PREVIEW_ONLY
}

enum class LessonFilter {
    ALL,
    BOOKMARKED,
    COMPLETED
}

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AppRepository

    init {
        val db = AppDatabase.getDatabase(application)
        repository = AppRepository(db.lessonProgressDao(), db.savedSnippetDao())
    }

    val allProgress: StateFlow<List<LessonProgress>> = repository.allProgress
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val savedSnippets: StateFlow<List<SavedSnippet>> = repository.allSavedSnippets
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Theme Mode: null = follow system, true = dark, false = light
    private val _isDarkMode = MutableStateFlow<Boolean?>(null)
    val isDarkMode: StateFlow<Boolean?> = _isDarkMode.asStateFlow()

    fun toggleDarkMode() {
        _isDarkMode.value = when (_isDarkMode.value) {
            null -> true
            true -> false
            false -> null
        }
    }

    // Active Navigation Tab
    private val _selectedTab = MutableStateFlow(AppTab.LESSONS)
    val selectedTab: StateFlow<AppTab> = _selectedTab.asStateFlow()

    fun setTab(tab: AppTab) {
        _selectedTab.value = tab
    }

    // Selected Lesson Detail View
    private val _selectedLesson = MutableStateFlow<LessonTopic?>(null)
    val selectedLesson: StateFlow<LessonTopic?> = _selectedLesson.asStateFlow()

    fun openLesson(lesson: LessonTopic) {
        _selectedLesson.value = lesson
    }

    fun closeLesson() {
        _selectedLesson.value = null
    }

    // Lessons filter
    private val _lessonFilter = MutableStateFlow(LessonFilter.ALL)
    val lessonFilter: StateFlow<LessonFilter> = _lessonFilter.asStateFlow()

    fun setLessonFilter(filter: LessonFilter) {
        _lessonFilter.value = filter
    }

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    // Code Editor State
    private val defaultCode = HscLessonsData.lessons[0].practiceCode
    private val _currentHtmlCode = MutableStateFlow(defaultCode)
    val currentHtmlCode: StateFlow<String> = _currentHtmlCode.asStateFlow()

    private val _editorViewMode = MutableStateFlow(EditorViewMode.SPLIT)
    val editorViewMode: StateFlow<EditorViewMode> = _editorViewMode.asStateFlow()

    private val _useCleanCss = MutableStateFlow(true)
    val useCleanCss: StateFlow<Boolean> = _useCleanCss.asStateFlow()

    private val _previewRefreshCounter = MutableStateFlow(0)
    val previewRefreshCounter: StateFlow<Int> = _previewRefreshCounter.asStateFlow()

    // Fullscreen Practice Mode
    private val _isEditorFullscreen = MutableStateFlow(false)
    val isEditorFullscreen: StateFlow<Boolean> = _isEditorFullscreen.asStateFlow()

    fun toggleEditorFullscreen() {
        _isEditorFullscreen.value = !_isEditorFullscreen.value
    }

    fun setEditorFullscreen(fullscreen: Boolean) {
        _isEditorFullscreen.value = fullscreen
    }

    fun updateHtmlCode(code: String) {
        _currentHtmlCode.value = code
    }

    fun setEditorViewMode(mode: EditorViewMode) {
        _editorViewMode.value = mode
    }

    fun toggleCleanCss() {
        _useCleanCss.value = !_useCleanCss.value
        refreshPreview()
    }

    fun refreshPreview() {
        _previewRefreshCounter.value++
    }

    fun loadLessonPracticeCode(lesson: LessonTopic) {
        _currentHtmlCode.value = lesson.practiceCode
        _selectedTab.value = AppTab.EDITOR
        _selectedLesson.value = null
        _isEditorFullscreen.value = true
        refreshPreview()
    }

    fun loadTemplate(code: String) {
        _currentHtmlCode.value = code
        refreshPreview()
    }

    // Database Actions
    fun toggleCompleted(lessonId: String) {
        viewModelScope.launch {
            val progressMap = allProgress.value.associateBy { it.lessonId }
            val current = progressMap[lessonId]?.isCompleted ?: false
            repository.toggleCompleted(lessonId, current)
        }
    }

    fun toggleBookmark(lessonId: String) {
        viewModelScope.launch {
            val progressMap = allProgress.value.associateBy { it.lessonId }
            val current = progressMap[lessonId]?.isBookmarked ?: false
            repository.toggleBookmark(lessonId, current)
        }
    }

    fun saveCurrentCode(title: String) {
        viewModelScope.launch {
            repository.saveSnippet(title, _currentHtmlCode.value)
        }
    }

    fun deleteSavedSnippet(id: Long) {
        viewModelScope.launch {
            repository.deleteSnippet(id)
        }
    }

    // Quiz State
    private val _quizQuestions = MutableStateFlow(HscLessonsData.quizList)
    val quizQuestions: StateFlow<List<QuizQuestion>> = _quizQuestions.asStateFlow()

    private val _userAnswers = MutableStateFlow<Map<Int, Int>>(emptyMap())
    val userAnswers: StateFlow<Map<Int, Int>> = _userAnswers.asStateFlow()

    private val _quizSubmitted = MutableStateFlow(false)
    val quizSubmitted: StateFlow<Boolean> = _quizSubmitted.asStateFlow()

    fun selectQuizAnswer(questionId: Int, optionIndex: Int) {
        if (!_quizSubmitted.value) {
            val updated = _userAnswers.value.toMutableMap()
            updated[questionId] = optionIndex
            _userAnswers.value = updated
        }
    }

    fun submitQuiz() {
        _quizSubmitted.value = true
    }

    fun resetQuiz() {
        _userAnswers.value = emptyMap()
        _quizSubmitted.value = false
    }
}
