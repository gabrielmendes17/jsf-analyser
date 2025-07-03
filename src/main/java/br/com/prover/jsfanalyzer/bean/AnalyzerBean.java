package br.com.prover.jsfanalyzer.bean;

import br.com.prover.jsfanalyzer.model.AnalyzerResult;
import br.com.prover.jsfanalyzer.service.AnalyzerService;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

@Named
@RequestScoped
public class AnalyzerBean {

    private String sentence;
    private AnalyzerResult result;

    @Inject
    private AnalyzerService analyzerService;

    public void analyze() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (sentence == null || sentence.trim().isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Warning", "Please enter a sentence before analyzing."));
            return;
        }

        try {
            Map<String, Integer> frequencies = analyzerService.analyzeText(sentence);
            result = new AnalyzerResult(frequencies);

            if (frequencies.isEmpty()) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Info", "No valid words were found in the sentence."));
            }

        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "An error occurred while processing the sentence: " + e.getMessage()));
        }
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public AnalyzerResult getResult() {
        return result;
    }
}