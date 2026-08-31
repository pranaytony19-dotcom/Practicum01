import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.JFileChooser;

public class ProductReader {

    public static void main(String[] args) {

        JFileChooser chooser = new JFileChooser();

        File workingDirectory =
                new File(System.getProperty("user.dir"));

        chooser.setCurrentDirectory(workingDirectory);

        System.out.println("Please select a product data file.");

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

            File selectedFile = chooser.getSelectedFile();
            Path file = selectedFile.toPath();

            try {

                BufferedReader reader = Files.newBufferedReader(file);

                String header = String.format(
                        "%-10s %-15s %-30s %10s",
                        "ID#", "Name", "Description", "Cost");

                System.out.println(header);

                System.out.println(
                        "----------------------------------------------------------------------");

                String record;

                while ((record = reader.readLine()) != null) {

                    String[] fields = record.split(",");

                    if (fields.length == 4) {

                        String id = fields[0].trim();
                        String name = fields[1].trim();
                        String description = fields[2].trim();

                        double cost =
                                Double.parseDouble(fields[3].trim());

                        String output = String.format(
                                "%-10s %-15s %-30s %10.1f",
                                id, name, description, cost);

                        System.out.println(output);

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
    }
}