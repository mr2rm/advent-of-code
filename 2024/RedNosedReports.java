import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class RedNosedReports {
    /**
     * @param report Report
     * @return Whether the report is increasing
     */
    static boolean isIncreasing(List<Integer> report) {
        for (int i = 0; i < report.size() - 1; i++) {
            if (report.get(i) >= report.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param report Report
     * @return Whether the report is decreasing
     */
    static boolean isDecreasing(List<Integer> report) {
        for (int i = 0; i < report.size() - 1; i++) {
            if (report.get(i) <= report.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param report Report
     * @return Whether the difference between each number is between 1 and 3
     */
    static boolean checkDifference(List<Integer> report) {
        for (int i = 0; i < report.size() - 1; i++) {
            int diff = Math.abs(report.get(i) - report.get(i + 1));
            if (diff < 1 || diff > 3) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param report Report
     * @return Whether report is safe
     */
    static boolean isSafe(List<Integer> report) {
        if (!isIncreasing(report) && !isDecreasing(report)) {
            return false;
        }
        return checkDifference(report);
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
