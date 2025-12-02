import java.util.Scanner;

public class SecretEntrance {
    static final int DIAL_SIZE = 100;
    private int position;
    private int zeroCount;

    public SecretEntrance(int pointingAt) {
        this.position = pointingAt;
    }

    public int getPosition() {
        return position;
    }

    public int getZeroCount() {
        return zeroCount;
    }

    public void move(String rotation) {
        char direction = rotation.charAt(0);
        int distance = Integer.parseInt(rotation.substring(1));

        zeroCount += distance / DIAL_SIZE;
        distance %= DIAL_SIZE;
        if (distance == 0) {
            return;
        }

        int finalPosition = direction == 'L' ? position - distance : position + distance;
        if (finalPosition >= DIAL_SIZE || (finalPosition <= 0 && position > 0)) {
            zeroCount++;
        }
        position = (finalPosition + DIAL_SIZE) % DIAL_SIZE;
    }

    public static void main(String[] args) {
        int finalZeroCount = 0;
        SecretEntrance secretEnterance = new SecretEntrance(50);
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                String rotation = scanner.next();
                secretEnterance.move(rotation);
                if (secretEnterance.getPosition() == 0) {
                    finalZeroCount++;
                }
            }
        }
        System.out.println(finalZeroCount);
        System.out.println(secretEnterance.getZeroCount());
    }
}
