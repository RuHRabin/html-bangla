package com.example.ui.screens

import android.app.Activity
import android.content.ContextWrapper
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.theme.HtmlOrange
import com.example.ui.theme.SuccessGreen
import com.example.viewmodel.AppTab
import com.example.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier
) {
    val selectedTab by viewModel.selectedTab.collectAsStateWithLifecycle()
    val selectedLesson by viewModel.selectedLesson.collectAsStateWithLifecycle()
    val allProgress by viewModel.allProgress.collectAsStateWithLifecycle()
    val savedSnippets by viewModel.savedSnippets.collectAsStateWithLifecycle()

    val lessonFilter by viewModel.lessonFilter.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val isEditorFullscreen by viewModel.isEditorFullscreen.collectAsStateWithLifecycle()

    val isFullscreenPractice = selectedTab == AppTab.EDITOR && isEditorFullscreen

    val context = LocalContext.current
    val activity = remember(context) {
        var ctx = context
        while (ctx is ContextWrapper) {
            if (ctx is Activity) return@remember ctx
            ctx = ctx.baseContext
        }
        null
    }

    // True Immersive Fullscreen Controller: Hides system status bar & nav bar for 100% full screen
    DisposableEffect(isFullscreenPractice, activity) {
        val window = activity?.window
        if (window != null) {
            val insetsController = WindowCompat.getInsetsController(window, window.decorView)
            if (isFullscreenPractice) {
                insetsController.systemBarsBehavior =
                    WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                insetsController.hide(WindowInsetsCompat.Type.systemBars())
            } else {
                insetsController.systemBarsBehavior =
                    WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
                insetsController.show(WindowInsetsCompat.Type.systemBars())
            }
        }
        onDispose {
            val win = activity?.window
            if (win != null) {
                val insetsController = WindowCompat.getInsetsController(win, win.decorView)
                insetsController.systemBarsBehavior =
                    WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
                insetsController.show(WindowInsetsCompat.Type.systemBars())
            }
        }
    }

    // Handle back button when editor is in fullscreen mode
    BackHandler(enabled = isFullscreenPractice) {
        viewModel.setEditorFullscreen(false)
    }

    // Handle back button when a lesson detail is open
    BackHandler(enabled = selectedLesson != null) {
        viewModel.closeLesson()
    }

    if (selectedLesson != null) {
        val currentLesson = selectedLesson!!
        val progressMap = remember(allProgress) { allProgress.associateBy { it.lessonId } }
        val isCompleted = progressMap[currentLesson.id]?.isCompleted == true
        val isBookmarked = progressMap[currentLesson.id]?.isBookmarked == true

        LessonDetailScreen(
            viewModel = viewModel,
            lesson = currentLesson,
            isCompleted = isCompleted,
            isBookmarked = isBookmarked,
            onBack = { viewModel.closeLesson() }
        )
    } else {
        Scaffold(
            contentWindowInsets = if (isFullscreenPractice) WindowInsets(0, 0, 0, 0) else ScaffoldDefaults.contentWindowInsets,
            topBar = {
                if (!isFullscreenPractice) {
                    TopAppBar(
                        title = {
                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        "এইচটিএমএল শিক্ষা",
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Spacer(Modifier.width(6.dp))
                                    Surface(
                                        color = HtmlOrange.copy(alpha = 0.15f),
                                        shape = RoundedCornerShape(4.dp)
                                    ) {
                                        Text(
                                            "HSC ICT",
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = HtmlOrange,
                                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                                        )
                                    }
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        Icons.Default.WifiOff,
                                        contentDescription = null,
                                        tint = SuccessGreen,
                                        modifier = Modifier.size(12.dp)
                                    )
                                    Spacer(Modifier.width(4.dp))
                                    Text(
                                        "১০০% অফলাইন লার্নিং",
                                        fontSize = 11.sp,
                                        color = SuccessGreen,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                        },
                        actions = {
                            // Dark Mode Toggle
                            IconButton(
                                onClick = { viewModel.toggleDarkMode() },
                                modifier = Modifier.testTag("dark_mode_toggle")
                            ) {
                                Icon(
                                    imageVector = if (isDarkTheme) Icons.Default.LightMode else Icons.Default.DarkMode,
                                    contentDescription = "ডার্ক মোড টগল",
                                    tint = if (isDarkTheme) HtmlOrange else MaterialTheme.colorScheme.onSurface
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        )
                    )
                }
            },
            bottomBar = {
                if (!isFullscreenPractice) {
                    NavigationBar(
                        containerColor = MaterialTheme.colorScheme.surface,
                        tonalElevation = 6.dp
                    ) {
                        NavigationBarItem(
                            selected = selectedTab == AppTab.LESSONS,
                            onClick = { viewModel.setTab(AppTab.LESSONS) },
                            icon = { Icon(Icons.Default.MenuBook, contentDescription = null) },
                            label = { Text(AppTab.LESSONS.titleBn, fontSize = 11.sp) },
                            modifier = Modifier.testTag("nav_lessons")
                        )
                        NavigationBarItem(
                            selected = selectedTab == AppTab.EDITOR,
                            onClick = { viewModel.setTab(AppTab.EDITOR) },
                            icon = { Icon(Icons.Default.Code, contentDescription = null) },
                            label = { Text(AppTab.EDITOR.titleBn, fontSize = 11.sp) },
                            modifier = Modifier.testTag("nav_editor")
                        )
                        NavigationBarItem(
                            selected = selectedTab == AppTab.QUIZ,
                            onClick = { viewModel.setTab(AppTab.QUIZ) },
                            icon = { Icon(Icons.Default.Quiz, contentDescription = null) },
                            label = { Text(AppTab.QUIZ.titleBn, fontSize = 11.sp) },
                            modifier = Modifier.testTag("nav_quiz")
                        )
                        NavigationBarItem(
                            selected = selectedTab == AppTab.SAVED,
                            onClick = { viewModel.setTab(AppTab.SAVED) },
                            icon = { Icon(Icons.Default.Bookmark, contentDescription = null) },
                            label = { Text(AppTab.SAVED.titleBn, fontSize = 11.sp) },
                            modifier = Modifier.testTag("nav_saved")
                        )
                    }
                }
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .then(
                        if (isFullscreenPractice) {
                            Modifier
                        } else {
                            Modifier.padding(innerPadding)
                        }
                    )
            ) {
                when (selectedTab) {
                    AppTab.LESSONS -> {
                        LessonsListScreen(
                            viewModel = viewModel,
                            progressList = allProgress,
                            filter = lessonFilter,
                            searchQuery = searchQuery
                        )
                    }
                    AppTab.EDITOR -> {
                        CodeEditorScreen(
                            viewModel = viewModel
                        )
                    }
                    AppTab.QUIZ -> {
                        QuizScreen(
                            viewModel = viewModel
                        )
                    }
                    AppTab.SAVED -> {
                        SavedScreen(
                            viewModel = viewModel,
                            progressList = allProgress,
                            savedSnippets = savedSnippets
                        )
                    }
                }
            }
        }
    }
}
