package com.example.ui.util

import androidx.compose.ui.text.AnnotatedString
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class HtmlSyntaxHighlighterTest {

    @Test
    fun testHighlightEmptyCode() {
        val result = highlightHtml("")
        assertEquals("", result.text)
        assertTrue(result.spanStyles.isEmpty())
    }

    @Test
    fun testHighlightBasicTagAndAttribute() {
        val code = "<a href=\"https://example.com\" target=\"_blank\">Visit</a>"
        val result = highlightHtml(code)

        assertEquals(code, result.text)
        assertTrue("Span styles should be generated for tags and attributes", result.spanStyles.isNotEmpty())
    }

    @Test
    fun testHighlightTableAndHeaders() {
        val code = """
            <table border="1">
                <tr>
                    <th>Roll</th>
                    <th>Name</th>
                </tr>
                <tr>
                    <td>101</td>
                    <td>Rahim</td>
                </tr>
            </table>
        """.trimIndent()
        val result = highlightHtml(code)

        assertEquals(code, result.text)
        assertTrue(result.spanStyles.size >= 10)
    }

    @Test
    fun testHighlightCommentsAndDoctype() {
        val code = "<!DOCTYPE html>\n<!-- Sample Comment -->\n<h1>Heading</h1>"
        val result = highlightHtml(code)

        assertEquals(code, result.text)
        assertTrue(result.spanStyles.isNotEmpty())
    }

    @Test
    fun testVisualTransformationOffsetMapping() {
        val transformation = HtmlSyntaxVisualTransformation()
        val original = AnnotatedString("<h1 class=\"title\">Hello</h1>")
        val transformed = transformation.filter(original)

        assertEquals(original.text, transformed.text.text)
        assertEquals(0, transformed.offsetMapping.originalToTransformed(0))
        assertEquals(5, transformed.offsetMapping.originalToTransformed(5))
        assertEquals(original.text.length, transformed.offsetMapping.originalToTransformed(original.text.length))
    }
}
