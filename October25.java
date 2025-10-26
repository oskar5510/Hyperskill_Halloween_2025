/*
 * Task: The Kitchen Secret
 *
 * Description:
 * You discover a 4x4 keypad with letters (A–P) and a mysterious note containing
 * movement instructions like UP, DOWN, LEFT, and RIGHT. Each line in the dataset
 * represents a separate sequence of movements, starting from the top-left corner (A).
 *
 * Rules:
 * - Movement outside the keypad boundaries is ignored (you stay in place).
 * - After finishing each line of instructions, you record the final letter reached.
 * - Then you reset your position back to 'A' for the next sequence.
 *
 * Steps:
 * 1. Read the dataset line by line (each line is a sequence of movements separated by commas).
 * 2. For each instruction, move within the 4x4 keypad if possible.
 * 3. Collect the final letter reached after processing each line.
 * 4. Concatenate all resulting letters to form the final code word.
 *
 * Output:
 * A string in uppercase containing all letters from the final keypad positions,
 * without spaces or separators (e.g., "CFJKL...").
 */

void main() throws IOException {

    final char[][] KEYPAD = {
            {'A', 'B', 'C', 'D'},
            {'E', 'F', 'G', 'H'},
            {'I', 'J', 'K', 'L'},
            {'M', 'N', 'O', 'P'}
    };

    File file = new File("October25_dataset.txt");
    StringBuilder sb = new StringBuilder();

    try (Scanner input = new Scanner(file)) {
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] lineSplit = line.split(",");

            Direction[] moveSequence = new Direction[lineSplit.length];

            for (int i = 0; i < lineSplit.length; i++) {

                if (!lineSplit[i].isEmpty()) {
                    moveSequence[i] = Direction.valueOf(lineSplit[i]);
                } else {
                    moveSequence[i] = Direction.STOP;
                }
            }

            Position position = new Position();

            for (Direction step : moveSequence) {
                position.move(step);
            }

            sb.append(KEYPAD[position.row][position.col]);

        }

    }
    System.out.println(sb);
}

private enum Direction {
    UP(new Step(-1, 0)),
    DOWN(new Step(1, 0)),
    RIGHT(new Step(0, 1)),
    LEFT(new Step(0, -1)),
    STOP(new Step(0, 0));

    private final Step step;

    Direction(Step step) {
        this.step = step;
    }

    public Step getStep() {
        return step;
    }
}

private record Step(int row, int col) {
}


private static class Position {
    private static final int KEYPAD_SIZE = 4;
    private static final int MIN_INDEX = 0;
    private static final int MAX_INDEX = KEYPAD_SIZE - 1;
    private int row = 0;
    private int col = 0;

    public void move(Direction direction) {
        Step step = direction.getStep();
        int newRow = this.row + step.row;
        int newCol = this.col + step.col;

        if (isValidIndex(newRow)) this.row = newRow;
        if (isValidIndex(newCol)) this.col = newCol;
    }

    private boolean isValidIndex(int index) {
        return index >= MIN_INDEX && index <= MAX_INDEX;
    }

}

