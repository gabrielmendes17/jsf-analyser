package br.com.prover.jsfanalyzer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AnalyzerServiceTest {

    private AnalyzerService analyzerService;

    @BeforeEach
    void setUp() {
        analyzerService = new AnalyzerService();
    }

    @Test
    void shouldReturnEmptyMapWhenSentenceIsNull() {
        Map<String, Integer> result = analyzerService.analyzeText(null);
        assertTrue(result.isEmpty(), "Expected empty map for null input");
    }

    @Test
    void shouldReturnEmptyMapWhenSentenceIsEmpty() {
        Map<String, Integer> result = analyzerService.analyzeText("   ");
        assertTrue(result.isEmpty(), "Expected empty map for empty input");
    }

    @Test
    void shouldCountSingleWordCorrectly() {
        Map<String, Integer> result = analyzerService.analyzeText("Test");
        assertEquals(1, result.size());
        assertEquals(1, result.get("test"));
    }

    @Test
    void shouldCountMultipleWordsCorrectly() {
        Map<String, Integer> result = analyzerService.analyzeText("hello world hello");
        assertEquals(2, result.size());
        assertEquals(2, result.get("hello"));
        assertEquals(1, result.get("world"));
    }

    @Test
    void shouldIgnorePunctuationAndBeCaseInsensitive() {
        Map<String, Integer> result = analyzerService.analyzeText("Hello, HELLO. World!");
        assertEquals(2, result.size());
        assertEquals(2, result.get("hello"));
        assertEquals(1, result.get("world"));
    }

    @Test
    void shouldIgnoreExtraSpacesAndBlankWords() {
        Map<String, Integer> result = analyzerService.analyzeText("   space   space  ");
        assertEquals(1, result.size());
        assertEquals(2, result.get("space"));
    }
}