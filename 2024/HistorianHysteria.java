import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class HistorianHysteria {
    /**
     * Part 1: What is the total distance between lists?
     *
     * Adhoc - Time: O(n) Space: O(1)
     *
     * @param leftColumn Left list
     * @param rightColumn Right list
     * @return Total distance between lists
     */
    static int getTotalDistance(List<Integer> leftColumn, List<Integer> rightColumn) {
        Collections.sort(leftColumn);
        Collections.sort(rightColumn);

        int totalDistance = 0;
        for (int i = 0; i < leftColumn.size(); i++) {
            totalDistance += Math.abs(leftColumn.get(i) - rightColumn.get(i));
        }

        return totalDistance;
    }

    /**
     * Part 2: What is their similarity score?
     *
     * Hash Table - Time: O(n) Space: O(n)
     * 
     * @param leftColumn Left list
     * @param rightColumn Right list
     * @return Calculated similarity score
     */
    static int getSimilarityScore(List<Integer> leftColumn, List<Integer> rightColumn) {
        Map<Integer, Integer> freqencyCount = new HashMap<>();
        for (int number : rightColumn) {
            int count = freqencyCount.getOrDefault(number, 0);
            freqencyCount.put(number, count + 1);
        }

        int similarityScore = 0;
        for (int number : leftColumn) {
            int count = freqencyCount.getOrDefault(number, 0);
            similarityScore += number * count;
        }

        return similarityScore;
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
        int similarityScore = getSimilarityScore(leftColumn, rightColumn);
        System.out.println(String.format("Total Distance: %d\nSimilarity Score: %d", totalDistance,
                similarityScore));
    }
}
