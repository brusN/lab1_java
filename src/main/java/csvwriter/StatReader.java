package csvwriter;

import java.io.*;
import java.util.*;

public class StatReader {
    private HashMap<String, Integer> words;
    private int wordСount;

    public StatReader() {
        words = new HashMap<>();
        wordСount = 0;
    }

    public HashMap<String, Integer> getMap() {
        return words;
    }

    public int getCount() {
        return wordСount;
    }

    public void reset() {
        words.clear();
        wordСount = 0;
    }

    public void fillMap(FileInputStream file) {
        wordСount = 0;
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file))) {
            String fileLine = fileReader.readLine();
            while (fileLine != null) {
                String[] lineWords = fileLine.split(" ");
                for (int i = 0; i < lineWords.length; ++i) {
                    words.put(lineWords[i], words.getOrDefault(lineWords[i], 0) + 1);
                    ++wordСount;
                }
                fileLine = fileReader.readLine();
            }
        } catch (IOException exception) {
            System.err.println("Error while reading file: " + exception.getLocalizedMessage());
        }
    }

    public void sortByValueMap() {
        List<HashMap.Entry<String, Integer>> list = new ArrayList<>(words.entrySet());
        list.sort(HashMap.Entry.comparingByValue(Comparator.reverseOrder()));

        HashMap <String, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        words = new LinkedHashMap<>(result);
    }

}
