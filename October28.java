/*
 * Task: The Phone Call
 *
 * Description:
 * You are given a large decimal number representing a phone number.
 * Your goal is to simulate entering it into a binary-based 32-bit phone system.
 * The system can only handle unsigned 32-bit integers (values from 0 to 4,294,967,295).
 *
 * If the number exceeds this maximum value, it causes an overflow.
 * Each overflow occurs when the number surpasses the 32-bit limit,
 * and the value "wraps around" by subtracting the 32-bit range (2^32).
 *
 * Rules:
 * 1. Calculate how many times the number exceeds the 32-bit range (overflows).
 * 2. After adjusting for overflows, convert the remaining value to binary.
 * 3. Remove any leading zeros from the binary result.
 * 4. Output the number of overflows and the binary number, separated by a comma.
 *
 * Example:
 * If the input number is 5,170,322,083:
 * - The 32-bit range is 4,294,967,296 (2^32).
 * - 5170322083 - 4,294,967,296 = 822,655,787 (1 overflow)
 * - Binary of 822,655,787 = 1,100,010,000,100,110,101,110,001,011
 *
 * Output:
 * 1,1100010000100110101110001011
 *
 * Steps:
 * 1. Define the input number (phone number in decimal).
 * 2. Compute the 32-bit maximum (2 * (Integer.MAX_VALUE + 1L)).
 * 3. While the number exceeds this value, subtract it and count overflows.
 * 4. Convert the remaining value to binary and print a result as:
 *    <overflows>,<binary_string>
 */

void main() {
    long number = 5170322083L;
    long modulo = 2L * (Integer.MAX_VALUE + 1L);
    long maxValue = modulo - 1;
    int overflows = 0;

    while (number > maxValue) {
        number = number - modulo;
        overflows++;
        System.out.println("Number: " + number + " Overflows: " + overflows);
    }

    String binary = Long.toBinaryString(number);

    System.out.println("Binary: " + binary);
    System.out.println(overflows + "," + binary);
}