/*
 * Task: The Eldritch Horror
 *
 * Description:
 * You are given a dataset containing many words written in the mysterious language of the Old Gods.
 * Among all these words, only one does **not** have an anagram — that word is the true name of the creature.
 *
 * Your task is to find and print the word that has no anagrams in the dataset.
 *
 * Approach:
 * 1. Read all words from the input file.
 * 2. For each word, generate its *pattern* by sorting its letters alphabetically (e.g., "evil" → "eilv").
 * 3. Use two maps:
 *    - `patternToWord`: stores one representative word for each unique pattern.
 *    - `patternCount`: counts how many times each pattern appears.
 * 4. After processing all words:
 *    - Find the pattern that appears exactly once (`count == 1`).
 *    - Retrieve and print the corresponding word from `patternToWord`.
 *
 * Example:
 * Input: "god dog cat tac act"
 * Patterns:
 *   "dgo" → 2 words ("god", "dog")
 *   "act" → 3 words ("cat", "tac", "act")
 * No word without an anagram → none found.
 *
 * Input: "stone tones apple"
 * Patterns:
 *   "enost" → 2 words ("stone", "tones")
 *   "aelpp" → 1 word ("apple")
 * Output: apple
 *
 * Key Points:
 * - Sorting characters creates a reliable signature for detecting anagrams.
 * - Efficiently tracks both word patterns and occurrence counts using HashMaps.
 * - Stops after finding the first word with a unique pattern (no anagrams).
 *
 * Output:
 * Prints the only word in the dataset that does not have any anagram.
 */

void main() throws FileNotFoundException {
    File dataset = new File("October29_dataset.txt");

    Map<String, String> patternToWord = new HashMap<>();
    Map<String, Integer> patternCount = new HashMap<>();

    try (Scanner scanner = new Scanner(dataset)) {
        while (scanner.hasNext()) {
            String word = scanner.next();
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String pattern = new String(chars);

            patternToWord.putIfAbsent(pattern, word);
            patternCount.merge(pattern, 1, Integer::sum);
        }
    }

    for (Map.Entry<String, Integer> entry : patternCount.entrySet()) {
        if (entry.getValue() == 1) {
            System.out.println(patternToWord.get(entry.getKey()));
            break;
        }
    }
}