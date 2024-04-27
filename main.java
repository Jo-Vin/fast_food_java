// I'M USING A SCANNER WHICH IS A "simple text scanner which can parse primitive types and strings using regular expressions"
import java.util.Scanner;

public class MyFastFoodChatbot {

    // HERE'S WHERE I DEFINE THE MENU OPTIONS, MADE SURE THAT ALL THE PRICES MATCH THE MENU POSITION FOR MY GET PRICE FUNCTION
    private static String[] mainCourses = {"Veggie Burger", "Chicken Burger", "Beef Burger"};
    private static double[] mainPrices = {10.00, 12.00, 13.00};
    private static String[] sides = {"Chips", "Wedges", "Salad"};
    private static double[] sidePrices = {2.00, 3.00, 4.00};
    private static String[] drinks = {"Coke", "Water", "Coffee"};
    private static double[] drinkPrices = {1.20, 1.00, 1.50};

    // MAIN FUNCTION FOR THE ORDERING SYSTEM
    public static void main(String[] args) {

        // THIS IS TO FORMAT THE OUTPUT TO UTF-8 FOR NON ENGLISH CHARS
        Scanner scanner = new Scanner(System.in, "UTF-8");

        // INTRODUCTION TEXT
        System.out.println("Welcome to Tasty Bytes! My name's Armaan and I'll be looking after you today :)");
        System.out.println("MENU:");
        displayMenu();

        // FIRST MESSAGE 
        System.out.println("\nPlease press 1 to place an order or 0 to quit");
        int choice = scanner.nextInt();
        scanner.nextLine(); // ACCEPT THE USER INPUT AND PROCESS

        // IF THEY SAY 1 AKA YES OR NO I EXIT OUT
        if(choice == 1) {
            placeOrder(scanner);
        } else {
            System.out.println("Thank you for visiting Tasty Bytes. Goodbye!");
        }
    }

    // FUNCTION DISPLAY THE MENU 
    private static void displayMenu() {
        System.out.println("Main Courses:");
        for(int i = 0; i < mainCourses.length; i++) {
            System.out.println(mainCourses[i] + " - £" + mainPrices[i]);
        }
        System.out.println("\nSides:");
        for(int i = 0; i < sides.length; i++) {
            System.out.println(sides[i] + " - £" + sidePrices[i]);
        }
        System.out.println("\nDrinks:");
        for(int i = 0; i < drinks.length; i++) {
            System.out.println(drinks[i] + " - £" + drinkPrices[i]);
        }
    }

    // FUNCTION TO PLACE THE ORDER
    private static void placeOrder(Scanner scanner) {
        System.out.println("What main will you like to order?");
        String mainChoice = scanner.nextLine();

        // MY CHECK TO SEE IF THE MAIN ON THE MENU BY CHECKING AGAINST IT
        if(!isValidChoice(mainChoice, mainCourses)) {
            System.out.println("Invalid choice. Please choose from the menu.");
            placeOrder(scanner);
            return;
        }
        
        // CHECK HOW MANY THEY WANT
        System.out.println("How many " + mainChoice + "(s) would you like?");
        int mainQuantity = scanner.nextInt();
        scanner.nextLine();

        // MY CHECK TO SEE IF THE SIDES ON THE MENU BY CHECKING AGAINST IT
        System.out.println("What side will you like to order?");
        String sideChoice = scanner.nextLine();

        // CHECK VALID SIDE
        if(!isValidChoice(sideChoice, sides)) {
            System.out.println("Invalid choice. Please choose from the menu.");
            placeOrder(scanner);
            return;
        }

        // ASK HOW MANY SIDES
        System.out.println("How many " + sideChoice + "(s) would you like?");
        int sideQuantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        // FINALLY THE DRINK ORDER WITH QUANTITY & CHECKS
        System.out.println("What drink will you like to order?");
        String drinkChoice = scanner.nextLine();
        if(!isValidChoice(drinkChoice, drinks)) {
            System.out.println("Invalid choice. Please choose from the menu.");
            placeOrder(scanner);
            return;
        }

        System.out.println("How many " + drinkChoice + "(s) would you like?");
        int drinkQuantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        // SET THE VARIABLE FOR TOTAL COST AS A DOUBLE AND THEN ADD THE PRICES UP TO BE USED IN THE FINAL SYSTEM OUT
        double totalCost = (getPrice(mainChoice, mainPrices) * mainQuantity) +
                          (getPrice(sideChoice, sidePrices) * sideQuantity) +
                          (getPrice(drinkChoice, drinkPrices) * drinkQuantity);

        // FINAL OUTPUT MESSAGE
        System.out.println("Great! I've placed your order for " + mainQuantity + " " + mainChoice +
                           "(s), " + sideQuantity + " " + sideChoice + "(s), and " +
                           drinkQuantity + " " + drinkChoice + "(s), which will be £" + totalCost + ".");
    }

    // DEFINE FUNCTION FOR CHECKING IF MENU IS ON THE ITEM WHICH CHECKS AGAINST THE MENU ARRAY
    private static boolean isValidChoice(String choice, String[] array) {
        for(String item : array) {
            if(item.equalsIgnoreCase(choice)) {
                return true;
            }
        }
        return false;
    }

    // DEFINE FUNCTION WHICH RETURNS A DOUBLE FOR THE GETTING OF PRICES BY INDEXING AGAINST MY MENU VS MENU_PRICES
    private static double getPrice(String item, double[] prices) {
        for(int i = 0; i < prices.length; i++) {
            if(item.equalsIgnoreCase(mainCourses[i]) || 
               item.equalsIgnoreCase(sides[i]) || 
               item.equalsIgnoreCase(drinks[i])) {
                return prices[i];
            }
        }
        // IF NO MATCH RETURN £0
        return 0.0;
    }
}
