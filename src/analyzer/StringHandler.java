package analyzer;

class StringHandler {
    // Clean a text to have only letters, while keeping the spaces
    static String removeNonLettersAndSpaces(String unparsedText) {
        unparsedText = unparsedText.toLowerCase();
        String regexLS = "[^\\p{L}\\s]+";
        String lettersSpaces = unparsedText.replaceAll(regexLS, "");
        lettersSpaces = lettersSpaces.replaceAll("[\\s]{2,}"," ");
        return lettersSpaces;
    }

    // Cleans the text to only contain letters
    static String removeNonLetters(String unparsedText) {
        unparsedText = unparsedText.toLowerCase();
        String regexL = "[^\\p{L}]+";
        regexL = regexL.replaceAll("\\s","");
        return unparsedText.replaceAll(regexL, "");
    }
}
