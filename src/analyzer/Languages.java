package analyzer;

import java.util.HashMap;

class Languages {
    static HashMap<String, Language> languages = new HashMap<>();

    static void create(){
        // Creates all the language objects
        initiateLanguages();
        // Reads the corresponding file for each language and pulls the data
        languageModifications();
    }

    // Create each language and add it to the language hashmap
    private static void initiateLanguages(){
        Language english = new Language("eng", "eng_English");
        languages.put(english.getKey(), english);
        Language estonian = new Language("est", "est_Estonian");
        languages.put(estonian.getKey(), estonian);
        Language finnish = new Language("fin", "fin_Finnish");
        languages.put(finnish.getKey(), finnish);
        Language french = new Language("fre", "fre_French");
        languages.put(french.getKey(), french);
        Language german = new Language("ger", "ger_German");
        languages.put(german.getKey(), german);
        Language italian = new Language("ita", "ita_Italian");
        languages.put(italian.getKey(), italian);
        Language nynorsk = new Language("nno", "nno_Nynorsk");
        languages.put(nynorsk.getKey(), nynorsk);
        Language swedish = new Language("swe", "swe_Swedish");
        languages.put(swedish.getKey(), swedish);
    }

    // Modify the necessary values for each language
    private static void languageModifications(){
        for (Language lang : languages.values()){
            // Adds the 2 text version to the language object
            addTextsToLanguage(lang);
            // Calculate the 3 different stats for the language
            calculateTextStatistics(lang);
        }
    }

    // Reads each languages relevant txt file and adds it the corresponding language
    private static void addTextsToLanguage(Language lang){
        // Read the text
        String unSanitizedText = FileHandling.readFileByLines(lang.getFileName());
        // Clean the text to have 2 versions
        String letters = StringHandler.removeNonLetters(unSanitizedText);
        String lettersAndSpaces = StringHandler.removeNonLettersAndSpaces(unSanitizedText);
        // Add to their corresponding hashMap
        lang.setTextLettersOnly(letters);
        lang.setTextLettersSpaces(lettersAndSpaces);
    }

    // Finds the frequency of different stats in the text
    private static void calculateTextStatistics(Language lang){
        // Letter frequency
        lang.setLetters(
                Calculation.letterOccurrence(lang.getTextLettersOnly())
        );
        // First letter frequency
        lang.setFirstLetter(
                Calculation.firstLetterOccurrence(lang.getTextLettersSpaces())
        );
        // Window frequency
        lang.setWindows(
                Calculation.windowOccurrence(lang.getTextLettersOnly())
        );
    }
}
