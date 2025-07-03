package br.com.prover.jsfanalyzer.bean;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class ThemeBean implements Serializable {
    private boolean darkMode = false;

    public boolean isDarkMode() {
        return darkMode;
    }

    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
    }

    public void toggleTheme() {
        // trigger update if needed
    }
}