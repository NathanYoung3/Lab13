//Allows us to use the FileChooser wizard GUI to pick files
import javax.swing.*;
//Needed imports for working w/ IO (input/output)
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;


/*
 * These class incorporates the ReadFiles and WriteFiles programs we worked with previously.
 * These two programs have been rewritten as methods with the IOHelper class.
 * You can call the openFile method in your Main class by calling IOHelper.openFile().
 * Repeat for all methods you want to use in this clas.
 */

public class IOHelper {
    //Creates a JFileChooser object that will open the JFileChooser Wizard GUI
    //Allows user to search for files that they want read by the program
    //Much easier for the user than typing in a file directory manually
    private static JFileChooser chooser = new JFileChooser();

    /*
        Method Summary:
        * Allows the user to pick a file from the JFileChooser Wizard GUI
        * Returns selected file name back to the user
        * Stores each line of the selected file in the ArrayList passed in by the user as a parameter
        * This method DOES NOT read the file - it stores the lines from the file in the ArrayList
        * The file can be read by looping through and printing the ArrayList in your Main class
    */
    public static String openFile(ArrayList <String> list) throws IOException{
        try {
            //This variable will hold the users current working directory (program folder)
            //"user.dir" is shorthand for current working directory (project folder)
            File workingDirectory = new File(System.getProperty("user.dir"));

            //This will make the JFileChooser GUI default to look in the workingDirectory first
            //User can still navigate out of this folder if desired
            chooser.setCurrentDirectory(workingDirectory);

            //Checks to see if the user picks a file in the file chooser wizard
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                //Stores user selected file
                File selectedFile = chooser.getSelectedFile();
                //Holds the path to the selected file
                Path file = selectedFile.toPath();

                //InputStream is needed in order to create our Buffered Reader
                //InputStream allows bytes of data to be read from a file
                //BufferedReader is our actual "reader" tool that will be used to read characters from file
                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                //Rec holds what the reader finds on the line
                String rec = "";
                //Moving through file, reading, and printing each line of the selected file
                while (reader.ready()) {
                    rec = reader.readLine();
                    //Adds each line from txt file to ArrayList for storage in Main
                    list.add(rec);
                }
                reader.close(); // must close the file to seal it and clear buffer
                System.out.println("\n\nData file opened!"); //Success message
                return file.getFileName().toString();
            } else {
                //This else statement is hit when the user closes the JFileChooser Wizard without selecting file
                System.out.println("File not selected. Please restart program.");
                System.exit(0); //Shuts down program
            }
            return "";
        }
        //This catch block is hit when the user file the user attempts to open a file that can not be found
        catch (FileNotFoundException e) {
            System.out.println("File not found!");
            //Prints the error along with additional info related to the error
            e.printStackTrace();
        }
        //This catch block is hit for all other IO Exceptions
        catch (IOException e) {
            //Prints the error along with additional info related to the error
            e.printStackTrace();
        }
        return null;
    }

    /*
        Method Summary:
        * Allows the user to store the contents of the ArrayList in a txt file
        * User must pass in the ArrayList that is being written to the txt file as a parameter
        * User must pass in the name of file that they are writing
        * You can think of this method as "saving" your data
    */
    public static void writeFile(ArrayList<String> list, String name) throws IOException{
        //Sample data that is being added to an ArrayList named recs
        ArrayList<String> recs = list;
        String fileName = name;

        //This variable will hold the users current working directory (program folder)
        //"user.dir" is shorthand for current working directory (project folder)
        File workingDirectory = new File(System.getProperty("user.dir"));
        //Path is automatically set for user
        //In this case, the file will be stored in the src folder and the name is already chosen
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\" + name);

        //The try block will attempt to write a new txt file
        //If an error occurs in this block, the catch block will handle the IO Exception
        try {
            //OutputStream is needed in order to create our Buffered Writer
            //OutputStream allows bytes of data to be written to a file
            //BufferedWriter is our actual writing tool that will be used to write characters to file
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE,TRUNCATE_EXISTING));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            //Actually writing data from recs to new file
            for (String r : recs) {
                //r is the String being written
                //0 is the index of the String the writer starts writing at
                //r.length is the index of the String the writer stops writing at
                writer.write(r, 0, r.length());
                //need new line added before we write more data - ensures next bit of data is put on own line
                writer.newLine();
            }
            writer.close(); //closes file and clears buffer
            System.out.println("Data file written!");
        }
        //This catch block is hit for a variety of IO Exceptions
        catch (IOException e) {
            //Prints the error along with additional info related to the error
            e.printStackTrace();
        }
    }
}