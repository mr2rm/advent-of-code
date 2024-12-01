import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class HistorianHysteria {
    static int getTotalDistance(List<Integer> leftColumn, List<Integer> rightColumn) {
        Collections.sort(leftColumn);
        Collections.sort(rightColumn);

        int totalDistance = 0;
        for (int i = 0; i < leftColumn.size(); i++) {
            totalDistance += Math.abs(leftColumn.get(i) - rightColumn.get(i));
        }
        return totalDistance;
    }

    public static void main(String[] args) {
        List<Integer> leftColumn = new ArrayList<>();
        List<Integer> rightColumn = new ArrayList<>();

        final Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int leftNumber = scanner.nextInt();
            leftColumn.add(leftNumber);

            int rightNumber = scanner.nextInt();
            rightColumn.add(rightNumber);
        }
        scanner.close();

        int totalDistance = getTotalDistance(leftColumn, rightColumn);
        System.out.println(totalDistance);
    }
}
