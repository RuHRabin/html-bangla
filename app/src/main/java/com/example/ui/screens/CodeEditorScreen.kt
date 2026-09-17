package com.example.ui.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.text.method.LinkMovementMethod
import android.view.View
import android.view.ViewGroup
import android.webkit.RenderProcessGoneDetail
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.outlined.Style
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.model.HscLessonsData
import com.example.ui.theme.CodeEditorDarkBg
import com.example.ui.theme.HtmlOrange
import com.example.ui.theme.PrimaryBlue
import com.example.ui.util.HtmlSyntaxVisualTransformation
import com.example.viewmodel.EditorViewMode
import com.example.viewmodel.MainViewModel
import kotlinx.coroutines.delay

@Composable
fun CodeEditorScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val code by viewModel.currentHtmlCode.collectAsStateWithLifecycle()
    val viewMode by viewModel.editorViewMode.collectAsStateWithLifecycle()
    val useCleanCss by viewModel.useCleanCss.collectAsStateWithLifecycle()
    val refreshKey by viewModel.previewRefreshCounter.collectAsStateWithLifecycle()
    val isFullscreen by viewModel.isEditorFullscreen.collectAsStateWithLifecycle()

    CodeEditorScreen(
        viewModel = viewModel,
        code = code,
        viewMode = viewMode,
        useCleanCss = useCleanCss,
        refreshKey = refreshKey,
        isFullscreen = isFullscreen,
        modifier = modifier
    )
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun CodeEditorScreen(
    viewModel: MainViewModel,
    code: String,
    viewMode: EditorViewMode,
    useCleanCss: Boolean,
    refreshKey: Int,
    isFullscreen: Boolean = false,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

    var saveDialogOpen by remember { mutableStateOf(false) }
    var snippetTitle by remember { mutableStateOf("") }
    var templateMenuExpanded by remember { mutableStateOf(false) }
    var showCopiedToast by remember { mutableStateOf(false) }

    if (showCopiedToast) {
        LaunchedEffect(Unit) {
            android.widget.Toast.makeText(context, "কোড কপি করা হয়েছে!", android.widget.Toast.LENGTH_SHORT).show()
            showCopiedToast = false
        }
    }

    // Tag shortcuts to insert quickly covering the entire HSC ICT Chapter 4
    val quickTags = remember {
        listOf(
            "<p></p>", "<h1></h1>", "<h6></h6>", "<b></b>", "<i></i>", "<u></u>",
            "<sub></sub>", "<sup></sup>", "<br>", "<hr>",
            "<a href=\"\" target=\"_blank\"></a>", "<img src=\"\" alt=\"\">",
            "<table border=\"1\"></table>", "<tr></tr>", "<th></th>", "<td></td>",
            "colspan=\"2\"", "rowspan=\"2\"",
            "<ol type=\"1\"></ol>", "<ul type=\"square\"></ul>", "<li></li>",
            "<form method=\"post\"></form>", "<input type=\"text\">", "<input type=\"password\">",
            "<input type=\"radio\" name=\"grp\">", "<input type=\"checkbox\">", "<input type=\"submit\">",
            "<select><option></option></select>", "<textarea></textarea>",
            "<audio controls></audio>", "<video controls></video>", "<iframe></iframe>"
        )
    }

    Column(modifier = modifier.fillMaxSize()) {
        if (isFullscreen) {
            // Ultra-compact single-row header in Fullscreen Mode for maximum code real estate
            Surface(
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 4.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Left: Fullscreen badge & View mode toggles
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Surface(
                            color = HtmlOrange,
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                "⛶ ফুলস্ক্রিন",
                                fontSize = 10.5.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                            )
                        }

                        // Compact view mode buttons
                        FilterChip(
                            selected = viewMode == EditorViewMode.SPLIT,
                            onClick = { viewModel.setEditorViewMode(EditorViewMode.SPLIT) },
                            label = { Text("উভয়", fontSize = 11.sp) },
                            modifier = Modifier.height(28.dp)
                        )
                        FilterChip(
                            selected = viewMode == EditorViewMode.EDITOR_ONLY,
                            onClick = { viewModel.setEditorViewMode(EditorViewMode.EDITOR_ONLY) },
                            label = { Text("কোড", fontSize = 11.sp) },
                            modifier = Modifier.height(28.dp)
                        )
                        FilterChip(
                            selected = viewMode == EditorViewMode.PREVIEW_ONLY,
                            onClick = { viewModel.setEditorViewMode(EditorViewMode.PREVIEW_ONLY) },
                            label = { Text("ভিউ", fontSize = 11.sp) },
                            modifier = Modifier.height(28.dp)
                        )
                    }

                    // Right: Run, Template & Exit Fullscreen Actions
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        // Templates menu
                        Box {
                            IconButton(
                                onClick = { templateMenuExpanded = true },
                                modifier = Modifier.size(30.dp)
                            ) {
                                Icon(Icons.Default.Layers, contentDescription = "টেমপ্লেট", Modifier.size(16.dp), tint = PrimaryBlue)
                            }
                            DropdownMenu(
                                expanded = templateMenuExpanded,
                                onDismissRequest = { templateMenuExpanded = false }
                            ) {
                                HscLessonsData.starterTemplates.forEach { (name, templateCode) ->
                                    DropdownMenuItem(
                                        text = { Text(name) },
                                        onClick = {
                                            viewModel.loadTemplate(templateCode)
                                            templateMenuExpanded = false
                                        }
                                    )
                                }
                            }
                        }

                        // Run / Refresh Button
                        Button(
                            onClick = { viewModel.refreshPreview() },
                            colors = ButtonDefaults.buttonColors(containerColor = HtmlOrange),
                            shape = RoundedCornerShape(6.dp),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                            modifier = Modifier
                                .height(28.dp)
                                .testTag("run_preview_button")
                        ) {
                            Icon(Icons.Default.PlayArrow, contentDescription = "রান", Modifier.size(14.dp))
                            Spacer(Modifier.width(2.dp))
                            Text("রান", fontWeight = FontWeight.Bold, fontSize = 11.5.sp)
                        }

                        // Exit Fullscreen Button
                        OutlinedButton(
                            onClick = { viewModel.setEditorFullscreen(false) },
                            shape = RoundedCornerShape(6.dp),
                            border = BorderStroke(1.dp, HtmlOrange),
                            contentPadding = PaddingValues(horizontal = 7.dp, vertical = 2.dp),
                            modifier = Modifier
                                .height(28.dp)
                                .testTag("exit_fullscreen_banner_button")
                        ) {
                            Text(
                                "✕ স্বাভাবিক",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = HtmlOrange
                            )
                        }
                    }
                }
            }
        } else {
            // Standard / Normal View Toolbar
            Surface(
                tonalElevation = 2.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 6.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Split / View Mode Toggle Chips
                        Row(
                            modifier = Modifier
                                .weight(1f, fill = false)
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            FilterChip(
                                selected = viewMode == EditorViewMode.SPLIT,
                                onClick = { viewModel.setEditorViewMode(EditorViewMode.SPLIT) },
                                label = { Text("উভয় (Split)", fontSize = 11.5.sp) },
                                modifier = Modifier.testTag("mode_split")
                            )
                            FilterChip(
                                selected = viewMode == EditorViewMode.EDITOR_ONLY,
                                onClick = { viewModel.setEditorViewMode(EditorViewMode.EDITOR_ONLY) },
                                label = { Text("এডিটর", fontSize = 11.5.sp) },
                                leadingIcon = { Icon(Icons.Default.Code, contentDescription = null, Modifier.size(13.dp)) },
                                modifier = Modifier.testTag("mode_editor")
                            )
                            FilterChip(
                                selected = viewMode == EditorViewMode.PREVIEW_ONLY,
                                onClick = { viewModel.setEditorViewMode(EditorViewMode.PREVIEW_ONLY) },
                                label = { Text("প্রিভিউ", fontSize = 11.5.sp) },
                                leadingIcon = { Icon(Icons.Default.Visibility, contentDescription = null, Modifier.size(13.dp)) },
                                modifier = Modifier.testTag("mode_preview")
                            )
                        }

                        Spacer(Modifier.width(6.dp))

                        // Action Controls: Fullscreen Toggle & Run Button
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            // Fullscreen Toggle Button
                            OutlinedButton(
                                onClick = { viewModel.toggleEditorFullscreen() },
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = if (isFullscreen) HtmlOrange.copy(alpha = 0.15f) else Color.Transparent
                                ),
                                border = BorderStroke(
                                    1.dp,
                                    if (isFullscreen) HtmlOrange else MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
                                ),
                                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                                modifier = Modifier
                                    .height(34.dp)
                                    .testTag("toggle_fullscreen_button")
                            ) {
                                Text(
                                    if (isFullscreen) "✕ প্রস্থান" else "⛶ ফুলস্ক্রিন",
                                    fontSize = 11.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isFullscreen) HtmlOrange else MaterialTheme.colorScheme.onSurface
                                )
                            }

                            // Run / Refresh Button
                            Button(
                                onClick = { viewModel.refreshPreview() },
                                colors = ButtonDefaults.buttonColors(containerColor = HtmlOrange),
                                shape = RoundedCornerShape(8.dp),
                                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                                modifier = Modifier
                                    .height(34.dp)
                                    .testTag("run_preview_button")
                            ) {
                                Icon(Icons.Default.PlayArrow, contentDescription = "রান", Modifier.size(16.dp))
                                Spacer(Modifier.width(3.dp))
                                Text("রান", fontWeight = FontWeight.Bold, fontSize = 12.5.sp)
                            }
                        }
                    }

                    // Secondary actions row: Templates, Save, Copy, Clean CSS Toggle
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            // Templates menu
                            Box {
                                TextButton(
                                    onClick = { templateMenuExpanded = true },
                                    modifier = Modifier.testTag("templates_button")
                                ) {
                                    Text("টেমপ্লেট ▾", fontSize = 12.sp, color = MaterialTheme.colorScheme.primary)
                                }
                                DropdownMenu(
                                    expanded = templateMenuExpanded,
                                    onDismissRequest = { templateMenuExpanded = false }
                                ) {
                                    HscLessonsData.starterTemplates.forEach { (name, templateCode) ->
                                        DropdownMenuItem(
                                            text = { Text(name) },
                                            onClick = {
                                                viewModel.loadTemplate(templateCode)
                                                templateMenuExpanded = false
                                            }
                                        )
                                    }
                                }
                            }

                            // Save Snippet
                            IconButton(
                                onClick = { saveDialogOpen = true },
                                modifier = Modifier.size(36.dp).testTag("save_code_button")
                            ) {
                                Icon(Icons.Default.Save, contentDescription = "সংরক্ষণ", Modifier.size(18.dp))
                            }

                            // Copy Code
                            IconButton(
                                onClick = {
                                    clipboardManager.setText(AnnotatedString(code))
                                    showCopiedToast = true
                                },
                                modifier = Modifier.size(36.dp).testTag("copy_code_button")
                            ) {
                                Icon(Icons.Default.ContentCopy, contentDescription = "কপি", Modifier.size(18.dp))
                            }

                            // Clear Code
                            IconButton(
                                onClick = { viewModel.updateHtmlCode("") },
                                modifier = Modifier.size(36.dp).testTag("clear_code_button")
                            ) {
                                Icon(Icons.Default.DeleteSweep, contentDescription = "পরিষ্কার", Modifier.size(18.dp))
                            }
                        }

                        // CSS Styling indicator
                        FilterChip(
                            selected = useCleanCss,
                            onClick = { viewModel.toggleCleanCss() },
                            label = {
                                Text(
                                    if (useCleanCss) "স্টাইলযুক্ত" else "র' HTML",
                                    fontSize = 11.sp
                                )
                            },
                            leadingIcon = { Icon(Icons.Outlined.Style, contentDescription = null, Modifier.size(12.dp)) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primaryContainer
                            )
                        )
                    }
                }
            }
        }

        // Main Work Area: Split or Single
        if (isLandscape && viewMode == EditorViewMode.SPLIT) {
            // Horizontal Split (side-by-side for tablets or landscape)
            Row(modifier = Modifier.weight(1f).fillMaxWidth()) {
                Box(modifier = Modifier.weight(1f).fillMaxHeight()) {
                    EditorPanel(
                        code = code,
                        onCodeChange = { viewModel.updateHtmlCode(it) },
                        quickTags = quickTags
                    )
                }
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .fillMaxHeight()
                        .background(MaterialTheme.colorScheme.outline.copy(alpha = 0.4f))
                )
                Box(modifier = Modifier.weight(1f).fillMaxHeight()) {
                    PreviewPanel(
                        code = code,
                        useCleanCss = useCleanCss,
                        refreshKey = refreshKey
                    )
                }
            }
        } else {
            // Portrait / Vertical Layout
            when (viewMode) {
                EditorViewMode.SPLIT -> {
                    Column(modifier = Modifier.weight(1f).fillMaxWidth()) {
                        Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
                            EditorPanel(
                                code = code,
                                onCodeChange = { viewModel.updateHtmlCode(it) },
                                quickTags = quickTags
                            )
                        }
                        // Visual Divider
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(2.dp)
                                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                        )
                        Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
                            PreviewPanel(
                                code = code,
                                useCleanCss = useCleanCss,
                                refreshKey = refreshKey
                            )
                        }
                    }
                }
                EditorViewMode.EDITOR_ONLY -> {
                    Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
                        EditorPanel(
                            code = code,
                            onCodeChange = { viewModel.updateHtmlCode(it) },
                            quickTags = quickTags
                        )
                    }
                }
                EditorViewMode.PREVIEW_ONLY -> {
                    Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
                        PreviewPanel(
                            code = code,
                            useCleanCss = useCleanCss,
                            refreshKey = refreshKey
                        )
                    }
                }
            }
        }
    }

    // Save Code Dialog
    if (saveDialogOpen) {
        AlertDialog(
            onDismissRequest = { saveDialogOpen = false },
            title = { Text("কোড সংরক্ষণ করুন") },
            text = {
                Column {
                    Text("এই কোড স্নিপেটটির একটি নাম দিন:", fontSize = 14.sp)
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value = snippetTitle,
                        onValueChange = { snippetTitle = it },
                        placeholder = { Text("যেমন: HSC টেবিল সমাধান") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth().testTag("snippet_title_input")
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.saveCurrentCode(snippetTitle)
                        snippetTitle = ""
                        saveDialogOpen = false
                    },
                    modifier = Modifier.testTag("confirm_save_button")
                ) {
                    Text("সংরক্ষণ")
                }
            },
            dismissButton = {
                TextButton(onClick = { saveDialogOpen = false }) {
                    Text("বাতিল")
                }
            }
        )
    }
}

@Composable
private fun EditorPanel(
    code: String,
    onCodeChange: (String) -> Unit,
    quickTags: List<String>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CodeEditorDarkBg)
    ) {
        // VS Code simulated tabs
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF252526))
                .padding(top = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(CodeEditorDarkBg, RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                    .padding(horizontal = 16.dp, vertical = 6.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Code, contentDescription = null, tint = Color(0xFF38BDF8), modifier = Modifier.size(14.dp))
                    Spacer(Modifier.width(6.dp))
                    Text("index.html", color = Color(0xFFE2E8F0), fontSize = 12.sp, fontFamily = FontFamily.Monospace)
                }
            }
        }

        // Quick Tag Helper Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(CodeEditorDarkBg)
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 6.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "ট্যাগ শর্টকাট:",
                fontSize = 11.sp,
                color = Color(0xFF94A3B8),
                modifier = Modifier.padding(end = 4.dp)
            )
            quickTags.forEach { tag ->
                Surface(
                    onClick = {
                        val newCode = if (code.contains("</body>")) {
                            code.replace("</body>", "    $tag\n</body>")
                        } else {
                            "$code\n$tag"
                        }
                        onCodeChange(newCode)
                    },
                    color = Color(0xFF334155),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = tag,
                        color = Color(0xFFE2E8F0),
                        fontFamily = FontFamily.Monospace,
                        fontSize = 11.sp,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp)
                    )
                }
            }
        }

        // Lightweight Syntax Guide Legend for Learners
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1E1E1E))
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 8.dp, vertical = 3.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "কালার গাইড:",
                fontSize = 10.sp,
                color = Color(0xFF64748B),
                fontWeight = FontWeight.Medium
            )
            SyntaxLegendPill("<ট্যাগ>", Color(0xFF38BDF8))
            SyntaxLegendPill("অ্যাট্রিবিউট", Color(0xFFFBBF24))
            SyntaxLegendPill("\"মান\"", Color(0xFF4ADE80))
            SyntaxLegendPill("<!--মন্তব্য-->", Color(0xFF94A3B8))
        }

        // Code Input Area with Real-Time Syntax Highlighting & Dynamic Gutter
        Row(modifier = Modifier.fillMaxSize().background(CodeEditorDarkBg)) {
            val lineCount = remember(code) { (code.count { it == '\n' } + 1).coerceAtLeast(1) }
            val lineNumbersText = remember(lineCount) {
                (1..lineCount).joinToString("\n")
            }

            // Dynamic line numbers gutter (single composable instead of 50 separate elements)
            Box(
                modifier = Modifier
                    .width(36.dp)
                    .fillMaxHeight()
                    .background(Color(0xFF1E1E1E))
                    .border(BorderStroke(0.5.dp, Color(0xFF2D2D2D)))
                    .padding(top = 16.dp, end = 6.dp),
                contentAlignment = Alignment.TopEnd
            ) {
                Text(
                    text = lineNumbersText,
                    color = Color(0xFF6E7681),
                    fontSize = 13.sp,
                    lineHeight = 20.sp,
                    fontFamily = FontFamily.Monospace
                )
            }
            
            val syntaxTransformation = remember { HtmlSyntaxVisualTransformation() }

            OutlinedTextField(
                value = code,
                onValueChange = onCodeChange,
                visualTransformation = syntaxTransformation,
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("html_code_input"),
                textStyle = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 13.5.sp,
                    color = Color(0xFFD4D4D4),
                    lineHeight = 20.sp
                ),
                placeholder = {
                    Text(
                        "এখানে আপনার HTML কোড লিখুন...",
                        color = Color(0xFF6A9955), // VS Code comment green
                        fontFamily = FontFamily.Monospace,
                        fontSize = 13.5.sp
                    )
                },
                colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedContainerColor = CodeEditorDarkBg,
                    unfocusedContainerColor = CodeEditorDarkBg
                )
            )
        }
    }
}

@Composable
private fun SyntaxLegendPill(text: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(6.dp)
                .background(color, shape = RoundedCornerShape(3.dp))
        )
        Spacer(Modifier.width(3.dp))
        Text(
            text = text,
            fontFamily = FontFamily.Monospace,
            fontSize = 9.5.sp,
            color = color,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
private fun PreviewPanel(
    code: String,
    useCleanCss: Boolean,
    refreshKey: Int
) {
    var webViewRef by remember { mutableStateOf<WebView?>(null) }
    var useNativePreview by rememberSaveable { mutableStateOf(false) }
    var renderCrashedNotice by rememberSaveable { mutableStateOf(false) }

    val formattedHtml = remember(code, useCleanCss) {
        buildPreparedHtml(code, useCleanCss)
    }

    // Clean up WebView resources on dispose
    DisposableEffect(Unit) {
        onDispose {
            try {
                webViewRef?.stopLoading()
                webViewRef?.destroy()
                webViewRef = null
            } catch (_: Exception) {}
        }
    }

    // Debounce updates when typing so we don't bombard WebView/renderer process
    LaunchedEffect(formattedHtml, refreshKey, useNativePreview) {
        if (useNativePreview) return@LaunchedEffect
        delay(500)
        try {
            webViewRef?.loadDataWithBaseURL(
                null,
                formattedHtml,
                "text/html",
                "UTF-8",
                null
            )
        } catch (_: Exception) {}
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Engine switch & status bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF1F5F9))
                .padding(horizontal = 12.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(
                            if (useNativePreview) Color(0xFF0284C7) else Color(0xFF16A34A),
                            shape = RoundedCornerShape(4.dp)
                        )
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    text = if (useNativePreview) "নেটিভ ইঞ্জিন (ক্র্যাশ-প্রুফ)" else "ওয়েবভিউ লাইভ ইঞ্জিন",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF334155)
                )
            }

            // Engine switch button
            TextButton(
                onClick = {
                    useNativePreview = !useNativePreview
                    if (!useNativePreview) {
                        renderCrashedNotice = false
                    }
                },
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
                modifier = Modifier.height(26.dp)
            ) {
                Icon(
                    if (useNativePreview) Icons.Default.Language else Icons.Default.Layers,
                    contentDescription = null,
                    modifier = Modifier.size(13.dp),
                    tint = PrimaryBlue
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = if (useNativePreview) "ওয়েবভিউ চালান" else "নেটিভ মোড",
                    fontSize = 11.sp,
                    color = PrimaryBlue,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        if (renderCrashedNotice && useNativePreview) {
            Surface(
                color = Color(0xFFFEF3C7),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "ভার্চুয়াল পরিবেশে জিপিইউ সমস্যার কারণে স্বয়ংক্রিয়ভাবে নিরাপদ নেটিভ প্রিভিউ চালু হয়েছে।",
                        fontSize = 11.sp,
                        color = Color(0xFF92400E),
                        lineHeight = 15.sp,
                        modifier = Modifier.weight(1f)
                    )
                    TextButton(
                        onClick = { renderCrashedNotice = false },
                        contentPadding = PaddingValues(horizontal = 6.dp, vertical = 0.dp),
                        modifier = Modifier.height(24.dp)
                    ) {
                        Text("ঠিক আছে", fontSize = 10.sp, color = Color(0xFFB45309))
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            if (useNativePreview) {
                SafeNativeHtmlPreview(
                    code = code,
                    useCleanCss = useCleanCss,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                AndroidView(
                    factory = { ctx ->
                        WebView(ctx).apply {
                            // Hardware acceleration for 60/120fps smooth scrolling
                            setLayerType(View.LAYER_TYPE_HARDWARE, null)

                            webViewClient = object : WebViewClient() {
                                override fun onRenderProcessGone(
                                    view: WebView?,
                                    detail: RenderProcessGoneDetail?
                                ): Boolean {
                                    try {
                                        (view?.parent as? ViewGroup)?.removeView(view)
                                        view?.destroy()
                                    } catch (_: Exception) {}
                                    webViewRef = null
                                    useNativePreview = true
                                    renderCrashedNotice = true
                                    return true
                                }
                            }
                            webChromeClient = WebChromeClient()
                            settings.apply {
                                javaScriptEnabled = true
                                domStorageEnabled = true
                                allowFileAccess = false
                                allowContentAccess = false
                                useWideViewPort = true
                                loadWithOverviewMode = true
                                setSupportZoom(true)
                                builtInZoomControls = true
                                displayZoomControls = false
                                cacheMode = WebSettings.LOAD_DEFAULT
                            }
                            setBackgroundColor(android.graphics.Color.WHITE)
                            webViewRef = this
                        }
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag("webview_preview")
                )
            }
        }
    }
}

/**
 * Safe Native HTML Previewer that renders HTML formatted text, tables, forms, and lists
 * without crashing even in headless or GPU-less Android emulator environments.
 */
@Composable
private fun SafeNativeHtmlPreview(
    code: String,
    useCleanCss: Boolean,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    // Extract body content or clean code
    val cleanCode = remember(code) {
        var processed = code
        // Remove style and script blocks for cleaner native rendering
        processed = processed.replace(Regex("(?s)<style.*?>.*?</style>"), "")
        processed = processed.replace(Regex("(?s)<script.*?>.*?</script>"), "")
        // Extract body if exists
        val bodyMatch = Regex("(?s)<body.*?>(.*?)</body>").find(processed)
        if (bodyMatch != null) {
            bodyMatch.groupValues[1].trim()
        } else {
            processed.trim()
        }
    }

    // Split into HTML text blocks and Table blocks
    val contentBlocks = remember(cleanCode) {
        parseHtmlBlocks(cleanCode)
    }

    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        if (cleanCode.isBlank()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "HTML কোড লিখুন প্রিভিউ দেখার জন্য",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        } else {
            contentBlocks.forEach { block ->
                when (block) {
                    is HtmlBlock.TableBlock -> {
                        NativeTableComponent(block)
                        Spacer(Modifier.height(12.dp))
                    }
                    is HtmlBlock.TextBlock -> {
                        if (block.html.isNotBlank()) {
                            AndroidView(
                                factory = { ctx ->
                                    TextView(ctx).apply {
                                        movementMethod = LinkMovementMethod.getInstance()
                                        setTextIsSelectable(true)
                                        textSize = if (useCleanCss) 17f else 16f
                                        setTextColor(android.graphics.Color.parseColor("#1E293B"))
                                        setLineSpacing(0f, if (useCleanCss) 1.35f else 1.2f)
                                    }
                                },
                                update = { tv ->
                                    tv.text = HtmlCompat.fromHtml(
                                        block.html,
                                        HtmlCompat.FROM_HTML_MODE_LEGACY
                                    )
                                },
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    }
}

private sealed class HtmlBlock {
    data class TextBlock(val html: String) : HtmlBlock()
    data class TableBlock(val rows: List<List<NativeTableCell>>) : HtmlBlock()
}

private data class NativeTableCell(
    val content: String,
    val isHeader: Boolean
)

private fun parseHtmlBlocks(html: String): List<HtmlBlock> {
    val tableRegex = Regex("(?si)<table[^>]*>(.*?)</table>")
    val blocks = mutableListOf<HtmlBlock>()
    var lastIndex = 0

    for (match in tableRegex.findAll(html)) {
        val start = match.range.first
        if (start > lastIndex) {
            val textSegment = html.substring(lastIndex, start).trim()
            if (textSegment.isNotEmpty()) {
                blocks.add(HtmlBlock.TextBlock(textSegment))
            }
        }

        // Parse Table content
        val tableContent = match.groupValues[1]
        val rowRegex = Regex("(?si)<tr[^>]*>(.*?)</tr>")
        val rows = mutableListOf<List<NativeTableCell>>()

        for (rowMatch in rowRegex.findAll(tableContent)) {
            val rowContent = rowMatch.groupValues[1]
            val cellRegex = Regex("(?si)<(th|td)[^>]*>(.*?)</\\1>")
            val cells = mutableListOf<NativeTableCell>()

            for (cellMatch in cellRegex.findAll(rowContent)) {
                val tagType = cellMatch.groupValues[1].lowercase()
                val cellInner = cellMatch.groupValues[2].trim()
                cells.add(
                    NativeTableCell(
                        content = cellInner,
                        isHeader = tagType == "th"
                    )
                )
            }
            if (cells.isNotEmpty()) {
                rows.add(cells)
            }
        }

        if (rows.isNotEmpty()) {
            blocks.add(HtmlBlock.TableBlock(rows))
        } else {
            blocks.add(HtmlBlock.TextBlock(match.value))
        }

        lastIndex = match.range.last + 1
    }

    if (lastIndex < html.length) {
        val remaining = html.substring(lastIndex).trim()
        if (remaining.isNotEmpty()) {
            blocks.add(HtmlBlock.TextBlock(remaining))
        }
    }

    if (blocks.isEmpty() && html.isNotBlank()) {
        blocks.add(HtmlBlock.TextBlock(html))
    }

    return blocks
}

@Composable
private fun NativeTableComponent(table: HtmlBlock.TableBlock) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(6.dp),
        border = BorderStroke(1.dp, Color(0xFFCBD5E1)),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            table.rows.forEachIndexed { rowIndex, row ->
                val hasHeader = row.any { it.isHeader }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            if (hasHeader) Color(0xFFF1F5F9)
                            else if (rowIndex % 2 == 1) Color(0xFFF8FAFC)
                            else Color.White
                        )
                ) {
                    row.forEach { cell ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .border(0.5.dp, Color(0xFFE2E8F0))
                                .padding(horizontal = 10.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = HtmlCompat.fromHtml(cell.content, HtmlCompat.FROM_HTML_MODE_LEGACY).toString(),
                                fontWeight = if (cell.isHeader) FontWeight.Bold else FontWeight.Normal,
                                fontSize = 15.sp,
                                color = if (cell.isHeader) Color(0xFF0F172A) else Color(0xFF334155)
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * Prepares HTML content with offline viewport and optional clean styling
 */
private fun buildPreparedHtml(rawCode: String, useCleanCss: Boolean): String {
    val cleanCssSnippet = if (useCleanCss) {
        """
        <style>
            * { box-sizing: border-box; }
            body { 
                margin: 0; 
                padding: 16px; 
                font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif; 
                font-size: 18px;
                line-height: 1.6; 
                color: #1e293b; 
                word-wrap: break-word;
            }
            table { 
                border-collapse: collapse; 
                margin: 10px 0; 
                width: 100%; 
                font-size: 16px;
            }
            th, td { 
                border: 1px solid #94a3b8; 
                padding: 10px 12px; 
            }
            th { 
                background-color: #f1f5f9; 
            }
            input[type=text], input[type=password], select, textarea {
                padding: 8px 10px;
                border: 1px solid #cbd5e1;
                border-radius: 4px;
                font-size: 16px;
            }
            input[type=submit], button {
                padding: 8px 16px;
                border-radius: 4px;
                border: none;
                background-color: #0284c7;
                color: white;
                font-size: 16px;
                cursor: pointer;
            }
        </style>
        """.trimIndent()
    } else ""

    val metaViewport = "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"

    return if (rawCode.contains("<head>", ignoreCase = true)) {
        rawCode.replaceFirst("<head>", "<head>\n$metaViewport\n$cleanCssSnippet", ignoreCase = true)
    } else if (rawCode.contains("<html>", ignoreCase = true)) {
        rawCode.replaceFirst("<html>", "<html>\n<head>\n$metaViewport\n$cleanCssSnippet\n</head>", ignoreCase = true)
    } else {
        """
        <!DOCTYPE html>
        <html>
        <head>
            $metaViewport
            $cleanCssSnippet
        </head>
        <body>
            $rawCode
        </body>
        </html>
        """.trimIndent()
    }
}
