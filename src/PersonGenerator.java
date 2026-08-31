import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonGenerator {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        ArrayList<String> records = new ArrayList<>();

        boolean keepGoing = true;

        while (keepGoing) {

            String id = SafeInput.getNonZeroLenString(in,"Enter ID");
            String firstName = SafeInput.getNonZeroLenString(in, "Enter First Name");
            String lastName = SafeInput.getNonZeroLenString(in, "Enter Last Name");
            String title = SafeInput.getNonZeroLenString(in, "Enter Title");

            int yearOfBirth = SafeInput.getInt(in, "Enter Year of Birth");

            String record = String.format("%s, %s, %s, %s, %d",
                    id, firstName, lastName, title, yearOfBirth);
            records.add(record);

            System.out.println("\nRecord added:");
            System.out.println(record);

            keepGoing = SafeInput.getYNConfirm(in, "Do you want to enter another person?");
        }

        String fileName = SafeInput.getNonZeroLenString(
                in, "Enter the name of the output file");
        Path file = Paths.get(fileName);

        try {
            BufferedWriter Writer = Files.newBufferedWriter(file);

            for (String record : records) {
                Writer.write(record);
                Writer.newLine();
            }

            Writer.close();

            System.out.println("\nFile written successfully!");
            System.out.println("File: " + file.toAbsolutePath());

        } catch (IOException e) {
            System.out.println("Error writing the file.");
            e.printStackTrace();
        }

        in.close();
    }
}
