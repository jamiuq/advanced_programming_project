import java.util.Scanner;

class Main
{


    public static void main(String[] args)
    {
        spellChecker sp = new spellChecker();
        System.out.print("\nPlease enter a sentence to be spell-checked: ");
        Scanner input = new Scanner(System.in);
        String sentence = input.nextLine();
        sp.checkSentence(sentence);
//        sp.checkSentence("Helo, my friond. Hw are yuo diong tdoay?");
    }
}