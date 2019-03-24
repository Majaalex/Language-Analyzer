package analyzer;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

class Analyzer {
    // Queue used to sort the ComparedObjects after making the comparison between usertext and each language
    private PriorityQueue<ComparedObject> pq = new PriorityQueue<>(new AvgComparator());

    void run(String userInput){
        analyzeUserInput(userInput);
        returnResults();
    }

    // Print out the results
    private void returnResults() {
        DecimalFormat df = new DecimalFormat("#.###");
        System.out.println();
        String format = "%10s%10s%10s%15s%10s%8s";
        System.out.printf(format, "Language", "Average", "Letter", "First Letter", "Window", "Order");
        int order = 1;
        // Polls the queue until it is empty, printing out all the values of the ComparedObject
        while (!pq.isEmpty()){
            System.out.println();
            ComparedObject current = pq.poll();
            System.out.printf(format,
                    current.getName(),
                    df.format(current.getAverage()),
                    df.format(current.getLetter()),
                    df.format(current.getFirstLetter()),
                    df.format(current.getWindow()),
                    Integer.toString(order++));

        }
        System.out.println();
    }

    private void analyzeUserInput(String userInput) {
        // Cleans the text into the 2 necessary formats
        String letterText = StringHandler.removeNonLetters(userInput);
        String letterAndSpaceText = StringHandler.removeNonLettersAndSpaces(userInput);

        // Go through each language and setup the comparison to the user text.
        for (Language lang : Languages.languages.values()){
            String langName = lang.getKey();
            // Compare the user text and the language text for each of the relevant stats
            double letterComparison = compareOccurrenceHashMaps(Calculation.letterOccurrence(letterText), lang.getLetters());
            double firstLetterComparison = compareOccurrenceHashMaps(Calculation.firstLetterOccurrence(letterAndSpaceText), lang.getFirstLetter());
            double windowComparison = compareOccurrenceHashMaps(Calculation.windowOccurrence(letterText), lang.getWindows());
            // Calculate the average of the 3 comparisons which is then used for the ComparedObject sorting
            double average = calculateAverage(letterComparison, firstLetterComparison, windowComparison);
            // Create a temporary object which is then added to the queue for polling
            ComparedObject temp =
                    new ComparedObject(
                            langName,
                            letterComparison,
                            firstLetterComparison,
                            windowComparison,
                            average);
            pq.add(temp);
        }
    }

    // Compares the user text HashMap to the language text HashMap
    private double compareOccurrenceHashMaps(HashMap<String, Double> userCalculation, HashMap<String, Double> langCalculation) {
        double similarities = 0;
        // Size of the user text HashMap
        int size = userCalculation.size();
        // For each key in the usertext
        for (String key : userCalculation.keySet()){
            // Only consider similarities if the language text HashMap contains the same key
            if (langCalculation.get(key) != null){
                // Adds a similarity of 1 if the 2 HashMaps have the same value for the specific key
                // The closer to 1, the more "similar" it is to the language
                similarities += 1 - Math.abs(userCalculation.get(key) - langCalculation.get(key));
            }
        }
        // Divide by size to normalize the values for differently sized HashMaps IE letters has less unique values compared to windows
        return similarities/size;
    }

    private double calculateAverage(double letter, double firstLetter, double window){
        return (letter + firstLetter + window) / 3;
    }
}

// A comparator for the PriorityQueue so that it sorts based on the average
class AvgComparator implements Comparator<ComparedObject>{
    @Override
    public int compare(ComparedObject o1, ComparedObject o2) {
        if (o1.getAverage() < o2.getAverage()){
            return 1;
        } else if (o1.getAverage() > o2.getAverage()){
            return -1;
        }
        return 0;
    }
}

class ComparedObject{
    private String name;
    private double letter;
    private double firstLetter;
    private double window;
    private double average;
    ComparedObject(String name, double letter, double firstLetter, double window, double average){
        setName(name);
        setLetter(letter);
        setFirstLetter(firstLetter);
        setWindow(window);
        setAverage(average);
    }
    String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    double getLetter() {
        return letter;
    }

    private void setLetter(double letter) {
        this.letter = letter;
    }

    double getFirstLetter() {
        return firstLetter;
    }

    private void setFirstLetter(double firstLetter) {
        this.firstLetter = firstLetter;
    }

    double getWindow() {
        return window;
    }

    private void setWindow(double window) {
        this.window = window;
    }

    double getAverage() {
        return average;
    }

    private void setAverage(double average) {
        this.average = average;
    }
}