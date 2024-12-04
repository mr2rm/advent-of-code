import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class CeresSearch {
    private static final int[][] dir =
            {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};


    private static int searchAround(List<String> matrix, int x, int y) {
        int n = matrix.size();
        int m = matrix.get(0).length();
        int xmasCounter = 0;

        for (int k = 0; k < dir.length; k++) {
            int tx = x, ty = y;
            boolean isMatched = true;

            for (char c : "XMAS".toCharArray()) {
                if (tx < 0 || tx >= n || ty < 0 || ty >= m || matrix.get(tx).charAt(ty) != c) {
                    isMatched = false;
                    break;
                }

                tx += dir[k][0];
                ty += dir[k][1];
            }

            if (isMatched) {
                xmasCounter++;
            }
        }
        return xmasCounter;
    }

    private static int countXmas(List<String> matrix) {
        int xmasCounter = 0;

        for (int i = 0; i < matrix.size(); i++) {
            String row = matrix.get(i);
            for (int j = 0; j < row.length(); j++) {
                xmasCounter += searchAround(matrix, i, j);
            }
        }
        return xmasCounter;
    }

    public static void main(String[] args) throws IOException {
        File inputFile = new File("input.in");
        final Scanner scanner = new Scanner(inputFile);

        List<String> matrix = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String row = scanner.nextLine();
            matrix.add(row);
        }
        scanner.close();

        int result = countXmas(matrix);
        System.out.println(result);
    }
}
