import java.io.FileReader;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;

public class levenshteinDistance {



    public int getDist(String s1, String s2, int a, int b)
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

    public int spellChecker(String sentence)
    {
        Scanner in = new Scanner(new FileReader(".\\words_alpha.txt"));

        return -1;
    }
}
