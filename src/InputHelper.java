import java.util.Scanner;

public class InputHelper {

    /*
     *Loops through until the user enters a valid int value
     *Displays error message and asks user to try again until valid input received
     */

    public static String getNonZeroLenString(Scanner scan, String prompt){
        String string;
        boolean done = false;

        System.out.println(prompt);
        do {
            string = scan.nextLine();
            if (string.isEmpty()) {
                System.out.println("String can't be blank. Please try again.");
            } else {
                done = true;
            }
        } while (!done);
        return string;
    }


    public static int getInt(Scanner scan, String prompt){
        int num = 0; //eventually returned to user after validated
        boolean done = false;

        System.out.println(prompt);
        do {
            if (scan.hasNextInt()) {
                num = scan.nextInt();
                done = true;
            } else {
                System.out.println("Invalid input. Please try again.");
                scan.nextLine();
            }
        } while (!done);
        return num;
    }

    public static double getDouble(Scanner scan, String prompt){
        double num = 0;
        boolean done = false;

        System.out.println(prompt);
        do {
            if (scan.hasNextDouble()) {
                num = scan.nextDouble();
                done = true;
            } else {
                System.out.println("Invalid input. Please try again.");
                scan.nextLine();
            }
        } while (!done);
        return num;
    }

    public static int getRangedInt(Scanner scan, String prompt, int min, int max){
        int num = 0;
        boolean done = false;

        System.out.println(prompt);
        do {
            if (scan.hasNextInt()) {
                num = scan.nextInt();
                if (num >= min && num <= max) {
                    done = true;
                } else {
                    System.out.println("Int has to be within the range. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please try again.");
            }
            scan.nextLine();
        } while (!done);
        return num;
    }

    public static double getRangedDouble(Scanner scan, String prompt, double min, double max){
        double num = 0;
        boolean done = false;

        System.out.println(prompt);
        do {
            if (scan.hasNextDouble()) {
                num = scan.nextDouble();
                if (num >= min && num <= max) {
                    done = true;
                } else {
                    System.out.println("Double has to be within the range. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please try again.");
            }
            scan.nextLine();
        } while (!done);
        return num;
    }

    public static int getPositiveNonZeroInt(Scanner scan, String prompt){
        int num = 0; //eventually returned to user after validated
        boolean done = false;

        System.out.println(prompt);
        do {
            if (scan.hasNextInt()) {
                num = scan.nextInt();
                if (num > 0) {
                    done = true;
                } else {
                    System.out.println("Please enter a positive, non-zero integer value.");
                }
            } else {
                System.out.println("Invalid input. Please try again.");
                scan.nextLine();
            }
        } while (!done);
        return num;
    }

    public static boolean getYNConfirm(Scanner scan, String prompt){
        String answer;
        boolean yn = false;
        boolean done = false;

        System.out.println(prompt);
        do {
            answer = scan.nextLine();
            if (answer.equalsIgnoreCase("y")) {
                yn = true;
                done = true;
            } else if (answer.equalsIgnoreCase("n")) {
                yn = false;
                done = true;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        } while (!done);
        return yn;
    }

    public static String getRegExString(Scanner scan, String prompt, String regEx){
        String response;
        boolean valid = false;

        do {
            System.out.println(prompt);
            response = scan.nextLine();
            if (response.matches(regEx)) {
                valid = true;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        } while (!valid);
        return response;
    }

    public static void prettyHeader(String msg){
        int msgLength = msg.length();
        int starLength = 60;
        int centeredLength = (starLength - 6 - msgLength) / 2;

        for (int i = 0; i < starLength; i++) {
            System.out.printf("%1s", "*");
        }

        System.out.print("\n***");
        for (int i = 0; i < centeredLength; i++) {
            System.out.printf("%1s", " ");
        }
        System.out.print(msg);
        for (int i = 0; i < centeredLength; i++) {
            System.out.printf("%1s", " ");
        }
        System.out.print("***");

        System.out.println();
        for (int i = 0; i < 60; i++) {
            System.out.printf("%1s", "*");
        }
    }
}