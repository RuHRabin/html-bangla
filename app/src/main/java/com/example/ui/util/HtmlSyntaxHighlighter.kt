package com.example.ui.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

// Pre-compiled regular expressions to prevent repeated recompilation on every keystroke
private val COMMENT_REGEX = Regex("<!--[\\s\\S]*?(-->|$)")
private val DOCTYPE_REGEX = Regex("<!DOCTYPE[\\s\\S]*?(>|$)", RegexOption.IGNORE_CASE)
private val TAG_REGEX = Regex("<(/?[a-zA-Z0-9\\-]+)([^>]*)(/?>|$)")
private val ATTR_REGEX = Regex("([a-zA-Z0-9_\\-:]+)(\\s*=\\s*)?(\"[^\"]*\"|'[^']*'|[^\\s\"'>]+)?")
private val ENTITY_REGEX = Regex("&[a-zA-Z0-9#]+;")

/**
 * VisualTransformation that applies real-time, highly-optimized syntax highlighting to HTML code.
 * Includes in-memory memoization to eliminate CPU overhead during cursor movement and recomposition.
 */
class HtmlSyntaxVisualTransformation(
    private val tagColor: Color = Color(0xFF38BDF8),       // Sky Blue for tags (<h1, <table, </p>, etc.)
    private val attrNameColor: Color = Color(0xFFFBBF24),  // Amber for attribute names (href, src, border, etc.)
    private val attrValColor: Color = Color(0xFF4ADE80),   // Mint/Emerald Green for values ("...", '...')
    private val commentColor: Color = Color(0xFF64748B),   // Muted Slate for <!-- comments -->
    private val doctypeColor: Color = Color(0xFFF472B6),   // Rose/Pink for <!DOCTYPE html>
    private val entityColor: Color = Color(0xFFA78BFA),    // Lavender for &nbsp;, &lt;, etc.
    private val operatorColor: Color = Color(0xFF94A3B8)   // Slate for '=' and operators
) : VisualTransformation {

    private var cachedInput: String? = null
    private var cachedOutput: TransformedText? = null

    override fun filter(text: AnnotatedString): TransformedText {
        val raw = text.text
        if (raw == cachedInput && cachedOutput != null) {
            return cachedOutput!!
        }

        val highlighted = highlightHtml(
            rawCode = raw,
            tagColor = tagColor,
            attrNameColor = attrNameColor,
            attrValColor = attrValColor,
            commentColor = commentColor,
            doctypeColor = doctypeColor,
            entityColor = entityColor,
            operatorColor = operatorColor
        )
        val result = TransformedText(highlighted, OffsetMapping.Identity)
        cachedInput = raw
        cachedOutput = result
        return result
    }
}

/**
 * Efficiently applies syntax highlighting styles to the given HTML string using precompiled regexes.
 */
fun highlightHtml(
    rawCode: String,
    tagColor: Color = Color(0xFF38BDF8),
    attrNameColor: Color = Color(0xFFFBBF24),
    attrValColor: Color = Color(0xFF4ADE80),
    commentColor: Color = Color(0xFF64748B),
    doctypeColor: Color = Color(0xFFF472B6),
    entityColor: Color = Color(0xFFA78BFA),
    operatorColor: Color = Color(0xFF94A3B8)
): AnnotatedString {
    if (rawCode.isEmpty()) return AnnotatedString("")
    if (rawCode.length > 50_000) return AnnotatedString(rawCode) // Guard for performance

    return buildAnnotatedString {
        append(rawCode)

        // 1. Comments: <!-- ... -->
        val commentRanges = mutableListOf<IntRange>()
        for (match in COMMENT_REGEX.findAll(rawCode)) {
            val start = match.range.first
            val end = (match.range.last + 1).coerceAtMost(rawCode.length)
            addStyle(
                SpanStyle(color = commentColor, fontStyle = FontStyle.Italic),
                start,
                end
            )
            commentRanges.add(start until end)
        }

        fun isInsideComment(idx: Int): Boolean {
            if (commentRanges.isEmpty()) return false
            for (range in commentRanges) {
                if (idx in range) return true
            }
            return false
        }

        // 2. DOCTYPE: <!DOCTYPE ...>
        for (match in DOCTYPE_REGEX.findAll(rawCode)) {
            val start = match.range.first
            if (!isInsideComment(start)) {
                val end = (match.range.last + 1).coerceAtMost(rawCode.length)
                addStyle(
                    SpanStyle(color = doctypeColor, fontWeight = FontWeight.Bold),
                    start,
                    end
                )
            }
        }

        // 3. HTML Tags: <(/?[a-zA-Z0-9\-]+)([^>]*)(/?>|$)
        for (tagMatch in TAG_REGEX.findAll(rawCode)) {
            val tagStart = tagMatch.range.first
            if (isInsideComment(tagStart)) continue

            // Ignore DOCTYPE or comments that start with <!
            if (tagMatch.value.startsWith("<!", ignoreCase = true)) continue

            // Brackets and Tag Name
            val tagNameGroup = tagMatch.groups[1]
            if (tagNameGroup != null) {
                // Bracket '<' or '</'
                addStyle(
                    SpanStyle(color = tagColor, fontWeight = FontWeight.Medium),
                    tagStart,
                    tagNameGroup.range.first
                )
                // Tag Name (h1, table, tr, td, a, div, body, etc.)
                addStyle(
                    SpanStyle(color = tagColor, fontWeight = FontWeight.Bold),
                    tagNameGroup.range.first,
                    tagNameGroup.range.last + 1
                )
            }

            // Closing Bracket '>' or '/>'
            val closeGroup = tagMatch.groups[3]
            if (closeGroup != null && closeGroup.value.isNotEmpty()) {
                addStyle(
                    SpanStyle(color = tagColor, fontWeight = FontWeight.Medium),
                    closeGroup.range.first,
                    closeGroup.range.last + 1
                )
            }

            // Tag Attributes: name="value" or name='value'
            val attrGroup = tagMatch.groups[2]
            if (attrGroup != null && attrGroup.value.isNotBlank()) {
                val attrStr = attrGroup.value
                val offset = attrGroup.range.first

                for (attrMatch in ATTR_REGEX.findAll(attrStr)) {
                    val nameG = attrMatch.groups[1]
                    val eqG = attrMatch.groups[2]
                    val valG = attrMatch.groups[3]

                    if (nameG != null) {
                        val nStart = offset + nameG.range.first
                        val nEnd = offset + nameG.range.last + 1
                        addStyle(
                            SpanStyle(color = attrNameColor, fontWeight = FontWeight.SemiBold),
                            nStart,
                            nEnd
                        )
                    }

                    if (eqG != null) {
                        val eqStart = offset + eqG.range.first
                        val eqEnd = offset + eqG.range.last + 1
                        addStyle(
                            SpanStyle(color = operatorColor),
                            eqStart,
                            eqEnd
                        )
                    }

                    if (valG != null) {
                        val vStart = offset + valG.range.first
                        val vEnd = offset + valG.range.last + 1
                        addStyle(
                            SpanStyle(color = attrValColor),
                            vStart,
                            vEnd
                        )
                    }
                }
            }
        }

        // 4. HTML Character Entities: &nbsp;, &lt;, &gt;, &copy;, &#...;
        for (entityMatch in ENTITY_REGEX.findAll(rawCode)) {
            val start = entityMatch.range.first
            if (!isInsideComment(start)) {
                val end = entityMatch.range.last + 1
                addStyle(
                    SpanStyle(color = entityColor, fontWeight = FontWeight.Medium),
                    start,
                    end
                )
            }
        }
    }
}
