import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class MullItOver {
    static long multiply(String s) {
        Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
        Matcher matcher = pattern.matcher(s);

        long result = 0;
        while (matcher.find()) {
            long a = Long.parseLong(matcher.group(1));
            long b = Long.parseLong(matcher.group(2));
            result += a * b;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        File input = new File("input.in");
        final Scanner scanner = new Scanner(input);

        long result = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            result += multiply(line);
        }
        scanner.close();

        System.out.println(result);
    }
}
