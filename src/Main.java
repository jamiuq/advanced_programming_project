import java.util.Scanner;

class Main
{


    public static void main(String[] args)
    {
        spellChecker sp = new spellChecker();
        System.out.print("\nPlease enter a sentence to be spell-checked: ");
        Scanner input = new Scanner(System.in);
        String sentence = input.nextLine(); // take input
        sp.checkSentence(sentence); // check sentence and print results
    }
}