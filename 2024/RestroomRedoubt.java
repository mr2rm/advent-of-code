import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

record Pair(int a, int b) {
}


public class RestroomRedoubt {
    final static int rowSize = 103;
    final static int colSize = 101;

    private static int getFinalDimension(int initial, int speed, int seconds, int size) {
        int finalPosition = initial + (speed * seconds);
        finalPosition %= size;
        if (finalPosition < 0) {
            finalPosition += size;
        }
        return finalPosition;
    }

    /**
     * Get the final position of the robot after 100 seconds
     *
     * Brute Force - Time: O(1), Space: O(1)
     * 
     * @param position Position pair
     * @param speed Speed pair
     * @return Final position of the robot after 100 seconds
     */
    private static Pair getFinalPositionAfter100Seconds(Pair position, Pair speed) {
        int finalRow = getFinalDimension(position.a(), speed.a(), 100, rowSize);
        int finalCol = getFinalDimension(position.b(), speed.b(), 100, colSize);
        return new Pair(finalRow, finalCol);
    }

    private static int[] countEachQuadrant(List<Pair> positions) {
        int midRow = rowSize / 2, midCol = colSize / 2;
        int[] quadrantCounter = new int[4];

        for (Pair position : positions) {
            int row = position.a();
            int col = position.b();
            if (row == midRow || col == midCol) {
                continue;
            }

            if (row <= midRow) {
                if (col <= midCol) {
                    quadrantCounter[0]++;
                } else {
                    quadrantCounter[1]++;
                }
            } else {
                if (col <= midCol) {
                    quadrantCounter[2]++;
                } else {
                    quadrantCounter[3]++;
                }
            }
        }
        return quadrantCounter;
    }

    /**
     * What will the safety factor be after exactly 100 seconds have elapsed?
     * 
     * Brute Force - Time: O(n), Space: O(1)
     * 
     * @param positions Final positions of the robots
     * @return Calculated Safety score
     */
    private static int calculateSafetyFactor(List<Pair> positions) {
        int[] quadrantCounter = countEachQuadrant(positions);
        int safetyFactor = 1;
        for (int count : quadrantCounter) {
            safetyFactor *= count;
        }
        return safetyFactor;
    }

    public static void main(String[] args) throws IOException {
        List<Pair> finalPositions = new ArrayList<>();
        final String numberRegex = "(-?\\d+)";
        final String inputRegex =
                "p=%s,%s\\s+v=%s,%s".formatted(numberRegex, numberRegex, numberRegex, numberRegex);

        File inputFile = new File("input.in");
        final Scanner scanner = new Scanner(inputFile);
        while (scanner.hasNextLine()) {
            Pattern pattern = Pattern.compile(inputRegex);
            Matcher matcher = pattern.matcher(scanner.nextLine());
            if (matcher.find()) {
                int row = Integer.parseInt(matcher.group(2));
                int col = Integer.parseInt(matcher.group(1));
                Pair position = new Pair(row, col);

                int rowSpeed = Integer.parseInt(matcher.group(4));
                int colSpeed = Integer.parseInt(matcher.group(3));
                Pair speed = new Pair(rowSpeed, colSpeed);

                Pair finalPosition = getFinalPositionAfter100Seconds(position, speed);
                finalPositions.add(finalPosition);
            }
        }
        scanner.close();

        int securityFactor = calculateSafetyFactor(finalPositions);
        System.out.println(securityFactor);
    }
}
