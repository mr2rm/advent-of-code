import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class MullItOver {
    private static int multiply(String memory) {
        Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
        Matcher matcher = pattern.matcher(memory);

        int result = 0;
        while (matcher.find()) {
            int a = Integer.parseInt(matcher.group(1));
            int b = Integer.parseInt(matcher.group(2));
            result += a * b;
        }
        return result;
    }

    private static int multiplyEnabledInstructions(String memory) {
        Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
        Matcher matcher = pattern.matcher(memory);

        int result = 0;
        boolean enabled = true;
        while (matcher.find()) {
            String firstPart = memory.substring(0, matcher.start());
            int lastDoIndex = firstPart.lastIndexOf("do()");
            int lastDontIndex = firstPart.lastIndexOf("don't()");
            if (lastDoIndex > lastDontIndex) {
                enabled = true;
            } else if (lastDontIndex > lastDoIndex) {
                enabled = false;
            }

            if (enabled) {
                int a = Integer.parseInt(matcher.group(1));
                int b = Integer.parseInt(matcher.group(2));
                result += a * b;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        File inputFile = new File("input.in");
        final Scanner scanner = new Scanner(inputFile);
        StringBuilder builder = new StringBuilder();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            builder.append(line);
        }
        scanner.close();

        String memory = builder.toString();
        int part1 = multiply(memory);
        int part2 = multiplyEnabledInstructions(memory);

        System.out.println("Part 1: %d\nPart 2: %d".formatted(part1, part2));
    }
}
