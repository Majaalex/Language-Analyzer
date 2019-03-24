package analyzer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class FileHandling {

    // Reads the filecontent and imports the text as a string
    static String readFileByLines(String fileName){
        StringBuilder text = new StringBuilder();
        String str;
        try {
            // Create the path to the relevant file
            Path path = Paths.get("src/resources/" + fileName + ".txt");
            // Start a buffered reader
            BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8);
            // Append text until the file ending has been reached
            while ((str = br.readLine()) != null){
                text.append(str);
                // Account for line separators (chars 13 and 10)
                text.append(System.lineSeparator());
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("No file found.");
        } catch (IOException e) {
            e.getMessage();
        }
        return text.toString();
    }

    static String readFileByChar(String fileName){
        StringBuilder text = new StringBuilder();
        String txt = "";
        int value;
        char val;
        try {
            Path path = Paths.get("src/resources/" + fileName + ".txt");
            BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8);
            while ((value = br.read()) != -1){
                if (value == 13){
                    val = '\n';
                } else {
                    val = (char) value;
                }

                text.append(val);
            }

            br.close();
        }catch (FileNotFoundException e) {
            System.out.println("No file found.");
        } catch (IOException e) {
            e.getMessage();
        }
        return text.toString();
    }
}

