import java.util.Random;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите ваше имя: ");
        String playerName = scanner.nextLine();

        System.out.print("Введите ваш выбор (ROCK, PAPER, SCISSORS): ");
        String playerChoiceStr = scanner.nextLine().toUpperCase();

        VARIANTS playerChoice;
        try {
            playerChoice = VARIANTS.valueOf(playerChoiceStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Неправильный ввод. Выберите ROCK, PAPER или SCISSORS.");
            scanner.close();
            return;
        }

        Player bot = new Player();
        Player player = new Player(playerChoice, playerName);

        System.out.println(bot.whoWins(bot, player));

        scanner.close();
    }
}

enum VARIANTS {
    ROCK, PAPER, SCISSORS
}

class Player {
    private VARIANTS variant;
    private String name;

    public Player() {
        this.variant = getRandomVariant();
        this.name = "Bot";
    }

    public Player(VARIANTS variant, String name) {
        this.variant = variant;
        this.name = name;
    }

    private VARIANTS getRandomVariant() {
        VARIANTS[] variants = VARIANTS.values();
        int randomIndex = new Random().nextInt(variants.length);
        return variants[randomIndex];
    }

    public String whoWins(Player p1, Player p2) {
        if (p1.variant == p2.variant) {
            return "Ничья!";
        }

        if ((p1.variant == VARIANTS.ROCK && p2.variant == VARIANTS.SCISSORS) ||
            (p1.variant == VARIANTS.SCISSORS && p2.variant == VARIANTS.PAPER) ||
            (p1.variant == VARIANTS.PAPER && p2.variant == VARIANTS.ROCK)) {
            return "Победил игрок с именем: " + p1.name;
        } else {
            return "Победил игрок с именем: " + p2.name;
        }
    }
}
