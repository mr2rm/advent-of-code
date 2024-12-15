import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

record IntPair(int first, int second) {
}


class WarehouseWoes {
    // @formatter:off
    final static Map<Character, IntPair> directionMap = Map.of(
        '>', new IntPair(0, 1),
        '<', new IntPair(0, -1),
        '^', new IntPair(-1, 0),
        'v', new IntPair(1, 0)
    );
    // @formatter:on

    private static boolean move(List<char[]> matrix, IntPair position, IntPair direction) {
        int n = matrix.size();
        int m = matrix.get(0).length;
        int x = position.first();
        int y = position.second();

        int nextX = x + direction.first();
        int nextY = y + direction.second();
        if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) {
            return false;
        }

        char[] nextRow = matrix.get(nextX);
        if (nextRow[nextY] == '#') {
            return false;
        }

        IntPair nextPosition = new IntPair(nextX, nextY);
        if (nextRow[nextY] == 'O') {
            move(matrix, nextPosition, direction);
        }

        char[] currentRow = matrix.get(x);
        if (nextRow[nextY] != '.') {
            return false;
        }

        nextRow[nextY] = currentRow[y];
        currentRow[y] = '.';
        return true;
    }

    private static IntPair moveRobot(List<char[]> matrix, IntPair position, char directionChar) {
        IntPair direction = directionMap.get(directionChar);

        boolean isMoved = move(matrix, position, direction);
        if (!isMoved) {
            return position;
        }

        int newX = position.first() + direction.first();
        int newY = position.second() + direction.second();
        return new IntPair(newX, newY);
    }

    private static int calculateGPSCoordinate(List<char[]> matrix) {
        int n = matrix.size();
        int m = matrix.get(0).length;

        int gpsCoordinate = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix.get(i)[j] == 'O') {
                    gpsCoordinate += (100 * i) + j;
                }
            }
        }
        return gpsCoordinate;
    }

    public static void main(String[] args) throws IOException {
        List<char[]> matrix = new ArrayList<>();
        IntPair robotPosition = null;

        File inputFile = new File("input.in");
        Scanner scanner = new Scanner(inputFile);

        // Read matrix
        while (scanner.hasNextLine()) {
            String row = scanner.nextLine();
            if (row.isBlank()) {
                break;
            }

            matrix.add(row.toCharArray());
            if (robotPosition != null) {
                continue;
            }

            // Find robot initial position
            for (int j = 0; j < row.length(); j++) {
                if (row.charAt(j) == '@') {
                    robotPosition = new IntPair(matrix.size() - 1, j);
                }
            }
        }

        // Read commands
        while (scanner.hasNextLine()) {
            String commands = scanner.nextLine();
            for (char directionChar : commands.toCharArray()) {
                robotPosition = moveRobot(matrix, robotPosition, directionChar);
            }
        }
        scanner.close();

        int gpsCoordinate = calculateGPSCoordinate(matrix);
        System.out.println(gpsCoordinate);
    }
}
