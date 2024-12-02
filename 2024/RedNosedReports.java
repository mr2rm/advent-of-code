import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class RedNosedReports {
    /**
     * Check if report is safe
     *
     * @param report Report
     * @return Whether report is safe or not
     */
    static boolean isSafe(List<Integer> report) {
        if (report.size() <= 1) {
            return true;
        }

        boolean isIncreasing = report.get(1) > report.get(0);
        for (int i = 1; i < report.size(); i++) {
            int diff = report.get(i) - report.get(i - 1);
            if (diff == 0 || Math.abs(diff) > 3 || (diff > 0) != isIncreasing) {
                return false;
            }
        }
        return true;
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
