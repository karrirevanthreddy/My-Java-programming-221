package record;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PatternFrequency {
    public static void main(String[] args) {
        String fileName = "sample.txt";
        String content = "Peter Piper picked a peck of pickled peppers\n"
                       + "A peck of pickled peppers Peter Piper picked\n"
                       + "If Peter Piper picked a peck of pickled peppers\n"
                       + "Where’s the peck of pickled peppers Peter Piper picked?";

        // Step 1: Write the text to sample.txt
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(content);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            return;
        }

        // Step 2: Read from sample.txt and count pattern occurrences
        int peCount = 0;
        int piCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Convert to lowercase to match both 'Pe'/'pe' and 'Pi'/'pi'
                String lowerLine = line.toLowerCase();
                peCount += countOccurrences(lowerLine, "pe");
                piCount += countOccurrences(lowerLine, "pi");
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
            return;
        }

        // Step 3: Print the output
        System.out.println("‘pe’ – no of occurrences - " + peCount);
        System.out.println("‘pi’ – no of occurrences – " + piCount);
    }

    private static int countOccurrences(String text, String pattern) {
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(pattern, index)) != -1) {
            count++;
            index += pattern.length();
        }
        return count;
    }
}



