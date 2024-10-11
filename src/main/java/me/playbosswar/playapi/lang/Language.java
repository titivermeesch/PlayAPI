package me.playbosswar.playapi.lang;

import java.io.File;
import java.util.HashMap;

// TODO: Take system from CommandTimer
public class Language {
    private final String label;
    private final File file;

    public Language(String label, File file) {
        this.label = label;
        this.file = file;
    }

    public HashMap<String, String> getValues() {
        // TODO: Read file and return key
        return null;
    }

    public String getValue(String key) {
        return null;
    }
}
