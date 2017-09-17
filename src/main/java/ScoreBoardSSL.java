import java.util.Random;

public class ScoreBoardDLL {

    public static void main (String[] args) {

        Random random = new Random();

        // Create 20 scores and store them in an array
        System.out.println("Here is a list of 20 scores:");
        DoubleLinkedList scores = new DoubleLinkedList();
        for (int count = 0; count < 20; count++) {
            int score = random.nextInt(100) + 1;
            System.out.println("Score " + (count + 1) + ": " + score);
            scores.addNode(new Score(score));
        }

        System.out.println("\nThe top 10 scores are:");
        scores.printLinkedList();
    }
}
