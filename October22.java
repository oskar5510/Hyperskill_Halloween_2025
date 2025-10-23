/*
 * Task: The Wrong (re)Turn
 *
 * Description:
 * This program analyzes a numeric sequence to determine the correct 4-digit gate code.
 * The code is composed of the digits that appear most frequently in the sequence:
 * - 1st most common digit
 * - 2nd most common digit
 * - 3rd most common digit
 * - 4th most common digit
 *
 * If multiple digits have the same frequency, the **higher digit** takes priority.
 *
 * Steps:
 * 1. Count occurrences of each digit (0–9) in the dataset.
 * 2. Sort digits by frequency (descending) and by value (descending) when frequencies match.
 * 3. Take the top four digits in that order and combine them into a 4-digit code.
 *
 * Output:
 * The final 4-digit gate code as a string of numbers.
 */

void main() {

    String dataset = "45184564154545450564201"; //sample number

    int[] numbers = new int[10];

    for (char ch : dataset.toCharArray()) {
        int number = Integer.parseInt(String.valueOf(ch));
        numbers[number]++;
    }

    Map<Integer, Integer> numberMap = new LinkedHashMap<>();

    for (int i = 0; i < numbers.length; i++) {
        numberMap.put(i, numbers[i]);
    }

    List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(numberMap.entrySet());
    entries.sort(
            Comparator.<Map.Entry<Integer, Integer>>comparingInt(Map.Entry::getValue)
                    .thenComparing(Map.Entry::getKey)
                    .reversed()
    );

    StringBuilder out = new StringBuilder();

    entries.stream()
            .map(Map.Entry::getKey)
            .limit(4)
            .forEach(out::append);

    System.out.println(out);

}