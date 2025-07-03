package br.com.prover.jsfanalyzer.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

public class AnalyzerResult implements Serializable {

    private final Map<String, Integer> frequencies;

    public AnalyzerResult(Map<String, Integer> frequencies) {
        this.frequencies = frequencies != null ? frequencies : Collections.emptyMap();
    }

    public Map<String, Integer> getFrequencies() {
        return Collections.unmodifiableMap(frequencies);
    }

    public int getDistinctWordCount() {
        return frequencies.size();
    }
}