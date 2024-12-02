import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class RedNosedReports {
    /**
     * Check if report is safe
     *
     * @param report Report
     * @return Whether report is safe or not
     */
    static boolean isSafe(int[] report) {
        if (report.length <= 1) {
            return true;
        }

        boolean isIncreasing = report[1] > report[0];
        for (int i = 1; i < report.length; i++) {
            int diff = report[i] - report[i - 1];
            if (diff == 0 || Math.abs(diff) > 3 || (diff > 0) != isIncreasing) {
                return false;
            }
        }
        return true;
    }

    /**
     * How many reports are safe?
     *
     * Brute Force - Time: O(n*m), Space: O(1)
     * 
     * @param reports List of report
     * @return Number of safe reports
     */
    static int countSafeReports(List<int[]> reports) {
        int safeCount = 0;
        for (int[] report : reports) {
            if (isSafe(report)) {
                safeCount++;
            }
        }
        return safeCount;
    }

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        List<int[]> reports = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            int[] report = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
            reports.add(report);
        }
        scanner.close();

        int safeCount = countSafeReports(reports);
        System.out.println(safeCount);
    }
}
