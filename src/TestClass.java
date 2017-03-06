import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class TestClass {
    public static void main(String[] args) {
        // Setup resources
        File input = new File(args[0]);
        File output = new File(args[1]);
        Calculator calculator = new Calculator();

        try {
            // Access resources
            Scanner sc = new Scanner(input);
            FileOutputStream fos = new FileOutputStream(output);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            // Iterate over inputs
            while(sc.hasNextLine()) {
                // Evaluate expression
                String expression = sc.nextLine();
                String result = calculator.evaluate(expression);
                System.out.println(expression + " => " + result);

                // Write to output file
                bw.write(result);
                bw.newLine();
            }

            // Close resources
            sc.close();
            bw.close();
        } catch (Exception e) {
            // Handle FileNotFoundExceptions and IOExceptions
            e.printStackTrace();
        }
    }
}
