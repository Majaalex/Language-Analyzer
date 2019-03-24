package analyzer;

import java.util.HashMap;

class Language {
    private String key;
    private String fileName;
    private String textLettersOnly;
    private String textLettersSpaces;
    private HashMap<String, Double> letters = new HashMap<>();
    private HashMap<String, Double> windows = new HashMap<>();
    private HashMap<String, Double> firstL = new HashMap<>();

    Language(String key, String fileName){
        setKey(key);
        setFileName(fileName);

    }
    //---------
    // Getters
    String getKey() {
        return key;
    }
    String getFileName() {
        return fileName;
    }
    String getTextLettersOnly() {
        return textLettersOnly;
    }
    String getTextLettersSpaces() {
        return textLettersSpaces;
    }
    HashMap<String, Double> getLetters() {
        return letters;
    }
    HashMap<String, Double> getWindows() {
        return windows;
    }
    HashMap<String, Double> getFirstLetter() {
        return firstL;
    }

    //---------
    // Setters
    void setTextLettersSpaces(String textLettersSpaces) {
        this.textLettersSpaces = textLettersSpaces;
    }
    void setTextLettersOnly(String textLettersOnly) {
        this.textLettersOnly = textLettersOnly;
    }
    private void setFileName(String fileName) {
        this.fileName = fileName;
    }
    private void setKey(String key) {
        this.key = key;
    }
    void setLetters(HashMap<String, Double> letters) {
        this.letters = letters;
    }
    void setWindows(HashMap<String, Double> windows) {
        this.windows = windows;
    }
    void setFirstLetter(HashMap<String, Double> firstL) {
        this.firstL = firstL;
    }
}

