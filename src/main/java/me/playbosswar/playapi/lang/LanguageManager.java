package me.playbosswar.playapi.lang;

import java.util.ArrayList;
import java.util.List;

public class LanguageManager {
    private Language loadedLanguage;
    private final List<Language> availableLanguages;

    public LanguageManager(Language defaultLanguage) {
        this.loadedLanguage = defaultLanguage;
        this.availableLanguages = new ArrayList<>();
    }
}
