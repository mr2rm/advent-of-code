import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class RedNosedReports {
    /**
     * @param report Report
     * @return Whether report is safe
     */
    static boolean isSafe(List<Integer> report) {
        boolean isIncreasing = true, isDecreasing = true;
        for (int i = 0; i < report.size() - 1; i++) {
            int diff = report.get(i + 1) - report.get(i);
            if (diff > 0) {
                isDecreasing = false;
            } else if (diff < 0) {
                isIncreasing = false;
            }

            int absDiff = Math.abs(diff);
            if (absDiff < 1 || absDiff > 3) {
                return false;
            }
        }
        return isIncreasing || isDecreasing;
    }

    /**
     * Part 1: How many reports are safe?
     *
     * Brute Force - Time: O(n*m), Space: O(1)
     * 
     * @param reports List of report
     * @return Number of safe reports
     */
    static int countSafeReports(List<List<Integer>> reports) {
        int safeCount = 0;
        for (List<Integer> report : reports) {
            if (isSafe(report)) {
                safeCount++;
            }
        }
        return safeCount;
    }

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        List<List<Integer>> reports = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            List<Integer> report = Arrays.stream(line.split(" ")).map(Integer::valueOf)
                    .collect(Collectors.toList());
            reports.add(report);
        }
        scanner.close();

        int safeCount = countSafeReports(reports);
        System.out.println(safeCount);
    }
}
