import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class PersonReader {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        JFileChooser chooser = new JFileChooser();

        File workingDirectory =
                new File(System.getProperty("user.dir"));

        chooser.setCurrentDirectory(workingDirectory);

        System.out.println("Please select a Person data file.");

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

            File selectedFile = chooser.getSelectedFile();

            Path file = selectedFile.toPath();

            try {

                BufferedReader reader = Files.newBufferedReader(file);

                System.out.printf("%-10s %-15s %-15s %-10s %6s%n",
                        "ID#", "Firstname", "Lastname", "Title", "YOB");

                System.out.println(
                        "------------------------------------------------------------");

                String record;

                while ((record = reader.readLine()) != null) {

                    String[] fields = record.split(",");

                    if (fields.length == 5) {

                        String id = fields[0].trim();
                        String firstName = fields[1].trim();
                        String lastName = fields[2].trim();
                        String title = fields[3].trim();
                        int yob = Integer.parseInt(fields[4].trim());

                        System.out.printf(
                                "%-10s %-15s %-15s %-10s %6d%n",
                                id, firstName, lastName, title, yob);

                    } else {

                        System.out.println(
                                "Invalid record: " + record);
                    }
                }

                reader.close();

                System.out.println("\nFile read successfully!");

            } catch (IOException e) {

                System.out.println("Error reading file.");
                e.printStackTrace();
            }

        } else {

            System.out.println("No file was selected.");
        }

        in.close();
    }
}