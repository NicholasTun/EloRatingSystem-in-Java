import java.util.Scanner;

public class Game {
    private static Scanner scanner = new Scanner(System.in);

    private static Object[][] quizzes = {
            {"\nWhich country is the largest by land area?",
                    "A. Russia", "B. United States", "C. China" , 0},
            {"\nWhich one is responsible for carrying oxygen in the blood?",
                    "A. Platelets", "B. White blood cells", "C. Red blood cells", 2},
            {"\nWhat is the approximate value of Euler's constant (e)?",
                    "A. 3.14159", "B. 2.71828", "C. 1.61803", 1}
    };
    public static void start() {

        System.out.println("\nWelcome to QuizMinia!");

        System.out.print("\nEnter Player 1 name: ");
        String name1 = scanner.nextLine();
        System.out.print("Enter Player 2 name: ");
        String name2 = scanner.nextLine();

        Player p1 = new Player(name1);
        Player p2 = new Player(name2);
        p2.setRating(1400);

        System.out.println();
        System.out.println(p1.getName() + "'s rating: " + p1.getRating());
        System.out.println(p2.getName() + "'s rating: " + p2.getRating());

        while (true) {
            System.out.print("\nPress\n1 to Play \n0 to Quit: " );
            String input = scanner.next();

            if(input.equals("1")) {
                duel(p1, p2);
            } else if (input.equals("0")) {
                break;
            } else {
                System.out.println("Please type an invalid input.");
                continue;
            }

            System.out.println();
            System.out.println(p1.getName() + "'s rating: " + p1.getRating());
            System.out.println(p2.getName() + "'s rating: " + p2.getRating());
            System.out.print("\nPress 1 to Play Again: ");
            String playAgain = scanner.next();
            if(playAgain.equals("1")) {
                continue;
            }
            break;
        }

    }

    private static void duel(Player p1, Player p2) {
        for (var quiz : quizzes) {
            askQThenCount(quiz, p1, p2);
        }

        double E1 = Elo.expectedScore(p1.getRating(), p2.getRating());
        double E2 = Elo.expectedScore(p2.getRating(), p1.getRating());

        if(p1.getCount() > p2.getCount()) {
            p1.setRating(Elo.newRating(p1.getRating(), E1, 1));
            p2.setRating(Elo.newRating(p2.getRating(), E2, 0));
        } else if (p1.getCount() < p2.getCount()) {
            p1.setRating(Elo.newRating(p1.getRating(), E1, 0));
            p2.setRating(Elo.newRating(p2.getRating(), E2, 1));
        } else {
            p1.setRating(Elo.newRating(p1.getRating(), E1, 0.5));
            p2.setRating(Elo.newRating(p2.getRating(), E2, 0.5));
        }
        p1.setCount(0);
        p2.setCount(0);
        System.out.println(E1);
        System.out.println(E2);
    }
    private static void askQThenCount (Object[] quiz, Player p1, Player p2) {

        var str = String.valueOf((char) ('A' + (int)(quiz[quiz.length-1])));

        for(int i=0; i < quiz.length - 1; i++) {
            System.out.println(quiz[i]);
        }

        System.out.print(p1.getName() + ": ");
        String answer = scanner.next().toUpperCase();
        System.out.print(p2.getName() + ": ");
        String answer2 = scanner.next().toUpperCase();

        if(answer.equals(str) && answer2.equals(str)) {
            p1.setCount(p1.getCount() + 1);
            p2.setCount(p2.getCount() + 1);
        }
        else if (answer.equals(str) || answer2.equals(str)){
            if(!answer.equals(str)) {
                p2.setCount(p2.getCount() + 1);
            }
            if(!answer2.equals(str)) {
                p1.setCount(p1.getCount() + 1);
            }
        }

        System.out.println("Correct Answer: " + str);
        System.out.println(p1.getCount());
        System.out.println(p2.getCount());

    }
    
}
