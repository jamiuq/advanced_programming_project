import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Paths;

public class spellChecker
{

    ArrayList<String> words;

    public spellChecker()
    {
        words = new ArrayList<>();

        File wordFile = new File("./resources/words_alpha.txt");
        Scanner fileReader;
        try {
            fileReader = new Scanner(wordFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (fileReader.hasNextLine())
        {
            String nextWord = fileReader.nextLine();
            words.add(nextWord);
        }

    }

    private int binarySearchWords(String needle, int l, int r)
    {
        int mid = (l + r) / 2;
        String compareString = words.get(mid);

        int comparison = compareString.compareTo(needle);

        if (l > r) return -1;
        if (needle.equals(compareString)) return mid;
        if (comparison > 0) return binarySearchWords(needle, l, mid - 1);
        if (comparison < 0) return binarySearchWords(needle, mid + 1, r);
        return -1;
    }

    private int getDist(String s1, String s2, int a, int b)
    {
        if (a == 0) {
            return b;
        }

        if (b == 0) {
            return a;
        }
        if (s1.charAt(a - 1) == s2.charAt(b - 1)) {
            return getDist(s1, s2, a - 1, b - 1);
        }
        return 1 + Math.min(
                getDist(s1, s2, a, b - 1),
                Math.min(
                        getDist(s1, s2, a - 1, b),

                        getDist(s1, s2, a - 1, b - 1)
                )
        );
    }

    public void checkSentence(String sentence)
    {
        System.out.println("Beginning analysis.");

        String[] sentenceWords = sentence.split(" ");

        int numErrors = 0;

        for (String sentenceWord : sentenceWords) // remove non-alphabetical characters
        {

            sentenceWord = sentenceWord.replaceAll("[^\\p{Alpha}]", ""); // get rid of all special symbols
            sentenceWord = sentenceWord.toLowerCase();

            int searchResult = binarySearchWords(sentenceWord, 0, words.size() - 1); // search wordlist for word
            if (searchResult != -1) continue; // word found in wordlist, move on.

            // find closest word
            int closestIndex = 0;
            int closestCompValue = getDist(sentenceWord, words.getFirst(), sentenceWord.length(), words.getFirst().length());
            for (int i = 1; i < words.size(); i++)
            {
                String thisWord = words.get(i);
                if (Math.abs(thisWord.length() - sentenceWord.length()) > 2) continue;
                int localCompValue = getDist(sentenceWord, thisWord, sentenceWord.length(), thisWord.length());
                if (localCompValue < closestCompValue) {
                    closestIndex = i;
                    closestCompValue = localCompValue;
                }
            }

            if (sentenceWord.equals(words.get(closestIndex))) {continue;}

            System.out.println(sentenceWord + "? Did you mean: " + words.get(closestIndex));
            numErrors++;
        }

        System.out.println(numErrors + " errors found in total.");

    }
}
