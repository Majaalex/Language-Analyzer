package analyzer;

import java.util.HashMap;

class Calculation {
    // Calculates the occurrence of each letter in a text
    static HashMap<String, Double> letterOccurrence(String text){
        HashMap<String, Double> letterOccurrences = new HashMap<>();
        String letter;
        double addOccurrence = 1.0 / text.length();
        // Loop through the text
        for (int i = 0; i < text.length(); i++){
            // pick out each letter
            letter = text.substring(i, i + 1);
            // Add it to the HashMap
            putOccurrence(letterOccurrences, letter, addOccurrence);
        }
        return letterOccurrences;
    }

    // Calculates the occurrence of each first letter of a word in a text
    static HashMap<String, Double> firstLetterOccurrence(String text){
        HashMap<String, Double> firstLetterOccurrences = new HashMap<>();
        String firstLetter;
        // Split the text
        String[] split = text.split(" ");
        double addOccurrence = 1.0 / split.length;
        for (String value : split){
            // Get the first letter
            firstLetter = String.valueOf(value.charAt(0));
            // Add it to the HashMap
            putOccurrence(firstLetterOccurrences, firstLetter, addOccurrence);
        }
        return firstLetterOccurrences;
    }

    // Calculates how many times each unique window of 3 characters occurs
    static HashMap<String, Double> windowOccurrence(String text){
        HashMap<String, Double> windowOccurrences = new HashMap<>();
        String window;
        // Start at first letter
        int firstPos = 0;
        // Get the following 2 letters for a window of 3 total
        int secondPos = 3;
        // Used to find how many windows of size X there are based on since the last X - 1 letters in the text will only ever be in a single window of X
        int N = secondPos - 2;
        double totalWindows = text.length() - N;
        double addOccurrence = 1.0 / totalWindows;
        while (secondPos < text.length()){
            window = text.substring(firstPos++, secondPos++);
            putOccurrence(windowOccurrences, window, addOccurrence);
        }
        return windowOccurrences;
    }

    // Puts the occurence into the Temp HashMap and calculates
    private static void putOccurrence(HashMap<String, Double> occurrenceTemp, String section, double addOccurrence) {
        // If the HashMap already contains the value, account for that value
        if (occurrenceTemp.get(section) != null) {
            double priorValue = occurrenceTemp.get(section);
            occurrenceTemp.put(section, addOccurrence + priorValue);
        } else { // Add the new value to the key that hasn't been created yet
            occurrenceTemp.put(section, addOccurrence);
        }
    }
}
