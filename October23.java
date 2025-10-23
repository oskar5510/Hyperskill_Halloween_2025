/*
 * Task: The White Noise (Part 2)
 *
 * Description:
 * This program processes a list of antenna rotation angles (in degrees),
 * which can include both positive (clockwise) and negative (counterclockwise) values.
 * It calculates the total rotation and then normalizes it to a value
 * between 0 and 359 degrees using modulo 360 arithmetic.
 *
 * Steps:
 * 1. Split the dataset into individual angles.
 * 2. Convert each angle to an integer and sum them all.
 * 3. Normalize the final result (sum % 360) to represent the antenna's final position.
 *
 * Output:
 * A single integer between 0 and 359 — the final antenna angle.
 */

void main() {
    String dataset = "90,-64,36,12,29,67,73,55,-40,72,-47,46,13,9,54,35,89,-12,85,-73,-55"; //sample dataset
    long sum = Arrays.stream(dataset.split(","))
            .mapToInt(Integer::parseInt)
            .sum();
    System.out.println(sum % 360);
}