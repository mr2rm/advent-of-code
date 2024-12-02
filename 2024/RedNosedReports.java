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
     * @param report Report
     * @param index Index to remove
     * @return Whether report is safe without index
     */
    static boolean isSafeWithout(List<Integer> report, int index) {
        List<Integer> newReport = new ArrayList<>(report);
        newReport.remove(index);
        return isSafe(newReport);
    }

    /**
     * @param report Report
     * @return Whether report is safe with tolerance of 1
     */
    static boolean isSafeWithTolerance(List<Integer> report) {
        if (isSafeWithout(report, 0)) {
            return true;
        }

        for (int i = 0; i < report.size() - 1; i++) {
            int diff = report.get(i + 1) - report.get(i);
            if (i + 2 < report.size()) {
                int nextDiff = report.get(i + 2) - report.get(i + 1);
                if ((diff > 0) != (nextDiff > 0)) {
                    return isSafeWithout(report, i) || isSafeWithout(report, i + 1)
                            || isSafeWithout(report, i + 2);
                }
            }

            int absDiff = Math.abs(diff);
            if (absDiff < 1 || absDiff > 3) {
                return isSafeWithout(report, i) || isSafeWithout(report, i + 1);
            }
        }
        return true;
    }

    /**
     * Part 2: How many reports are safe with tolerance of 1?
     *
     * Brute Force - Time: O(n*m), Space: O(1)
     * 
     * @param reports List of reports
     * @return Number of safe reports with tolerance of 1
     */
    static int countSafeReportsWithTolerance(List<List<Integer>> reports) {
        int safeCount = 0;
        for (List<Integer> report : reports) {
            if (isSafeWithTolerance(report)) {
                safeCount++;
            }
        }
        return safeCount;
    }

    /**
     * Part 1: How many reports are safe?
     *
     * Brute Force - Time: O(n*m), Space: O(1)
     * 
     * @param reports List of reports
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
        int safeCountWithTolerance = countSafeReportsWithTolerance(reports);
        System.out.println("Safe Count: %d\nSafe Count with Tolerance: %d".formatted(safeCount,
                safeCountWithTolerance));
    }
}
