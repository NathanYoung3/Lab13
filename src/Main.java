import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        boolean quit = false;
        boolean needsToBeSaved = false;
        boolean fileOpened = false;
        String fileName = "";
        String action;
        ArrayList <String> list = new ArrayList<String>();


        do {
            View(list);
            action = InputHelper.getRegExString(scan, "A - Add an item to the list \n" + "D – Delete an item from the list \n" +
                    "V – View the list\n" + "O - Open a list file from PC\n" + "S - Save the current list file to PC\n" + "C - Clear the current list\n" + "Q – Quit the program\n", "[AaDdVvQqOoSsCc]");
            if (action.equalsIgnoreCase("A")) {
                Add(scan, list);
                needsToBeSaved = true;
            } else if (action.equalsIgnoreCase("D")) {
                Delete(scan, list);
                needsToBeSaved = true;
            } else if (action.equalsIgnoreCase("V")) {
                View(list);
            } else if (action.equalsIgnoreCase("Q")) {
                if (needsToBeSaved && InputHelper.getYNConfirm(scan, "You have unsaved changes, do you want to save first?")) {
                    Save(scan, list, fileOpened, fileName);
                    needsToBeSaved = false;
                }
                Quit(scan, quit);
            } else if (action.equalsIgnoreCase("O")) {
                if (needsToBeSaved && InputHelper.getYNConfirm(scan, "You have unsaved changes, do you want to save first?")) {
                    Save(scan, list, fileOpened, fileName);
                    needsToBeSaved = false;
                    fileOpened = true;
                }
                fileName = IOHelper.openFile(list);
            } else if (action.equalsIgnoreCase("S")) {
                Save(scan, list, fileOpened, fileName);
                needsToBeSaved = false;
            } else if (action.equalsIgnoreCase("C")) {
                if (needsToBeSaved && InputHelper.getYNConfirm(scan, "You have unsaved changes, do you want to save first?")) {
                    Save(scan, list, fileOpened, fileName);
                    needsToBeSaved = false;
                }
                list.clear();
                needsToBeSaved = false;
                fileOpened = false;
            }
        } while (!quit);
    }

    public static void Add(Scanner scan, ArrayList<String> list){
        System.out.println("What do you want to add to the list?");
        String newItem = scan.nextLine();
        int indexNum = InputHelper.getRangedInt(scan,"Where do you want to add this to the list?", 0, list.size()); //wanted to do a little extra here
        list.add(indexNum, newItem);
    }

    public static void Delete(Scanner scan, ArrayList<String> list){
        list.remove(InputHelper.getRangedInt(scan, "What is the index of the list item would you like to delete? (0 - " + (list.size() - 1) + ")", 0, list.size() - 1));
    }

    public static void View(ArrayList<String> list){
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + " - " + list.get(i));
        }
        System.out.println();
    }

    public static void Quit(Scanner scan, boolean quit){
        quit = InputHelper.getYNConfirm(scan, "Are you sure you want to quit? [Y/N]");

        if (quit) {
            System.exit(0);
        }
    }

    public static void Save(Scanner scan, ArrayList<String> list, boolean fileOpened, String fileName) throws IOException {
        if (fileOpened) {
            IOHelper.writeFile(list, fileName);
        } else {
            fileName = InputHelper.getNonZeroLenString(scan, "What would you like to name the file?");
            IOHelper.writeFile(list, fileName);
        }
    }
}