package br.com.prover.jsfanalyzer.service;

import javax.enterprise.context.ApplicationScoped;
import java.util.LinkedHashMap;
import java.util.Map;

@ApplicationScoped
public class AnalyzerService {

    public Map<String, Integer> analyzeText(String sentence) {
        Map<String, Integer> result = new LinkedHashMap<>();

        if (sentence == null || sentence.trim().isEmpty()) {
            return result;
        }

        String[] words = sentence.toLowerCase()
                .replaceAll("[^\\p{L}\\p{Nd} ]+", "") // remove punctuation
                .split("\\s+");

        for (String word : words) {
            if (word != null && !word.trim().isEmpty()) {
                result.put(word, result.getOrDefault(word, 0) + 1);
            }
        }

        return result;
    }
}