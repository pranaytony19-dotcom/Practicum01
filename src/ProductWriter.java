import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductWriter {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> records = new ArrayList<>();
        boolean keepGoing = true;
        while (keepGoing) {

            String id = SafeInput.getNonZeroLenString(
                    in, "Enter Product  ID");
            String name = SafeInput.getNonZeroLenString(
                    in, "Enter Product Name");
            String description = SafeInput.getNonZeroLenString(
                    in, "Enter Product Description");
            double cost = SafeInput.getDouble(
                    in, "Enter Product Cost");
            String record = String.format(
                    ":%s, %s, %s, %.1f",
                    id, name, description, cost);

            records.add(record);

            System.out.println("\nRecord added:");
            System.out.println(record);

            keepGoing = SafeInput.getYNConfirm(
                    in, "Do you want to enter another product?");
        }

        String fileName = SafeInput.getNonZeroLenString(
                 in, "Enter the name of the output file");
        Path file = Paths.get(fileName);
        try {
            BufferedWriter writer = Files.newBufferedWriter(file);
            for (String record : records) {
                writer.write(record);
                writer.newLine();
            }

            writer.close();

            System.out.println("\nFile written successfully!");
            System.out.println("File: " + file.toAbsolutePath());

        } catch (IOException e) {
            System.out.println("Error writing the file");
            e.printStackTrace();
        }
        in.close();
    }
}
