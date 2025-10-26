/*
 * Task: The Piano Puzzle
 *
 * Description:
 * In this challenge, you are given a long string of musical notes (A–G),
 * and you must find the shortest substring that contains all seven unique notes.
 * If multiple substrings of the same shortest length exist, you must return
 * the one that appears first in the dataset.
 *
 * Example:
 * For input: ADCEEFAGABDC
 * The correct output is: EFAGABDC
 * (because it’s the shortest sequence containing all notes A–G).
 *
 * Steps:
 * 1. Read the dataset (a single line of characters, each representing a note).
 * 2. Use a sliding window technique to iterate through all possible substrings.
 * 3. Check which substring includes all seven distinct notes (A–G).
 * 4. Keep track of the first and shortest valid sequence found.
 * 5. Print the resulting substring containing all notes.
 *
 * Output:
 * A string representing the shortest sequence that includes all notes A–G,
 * using only capital letters without spaces (e.g., "EFAGABDC").
 */

void main() throws FileNotFoundException {

    final File datasetFile = new File("October26_dataset.txt");
    final List<Character> notes = new ArrayList<>(List.of('A', 'B', 'C', 'D', 'E', 'F', 'G'));

    int notesSize = notes.size();

    int notesSum = 0;

    for (char note : notes) {
        notesSum += note;
    }


    char[] dataset;
    try (Scanner scanner = new Scanner(datasetFile)) {
        dataset = scanner.nextLine().trim().toCharArray();
    }

    List<Character> datasetList = new ArrayList<>();
    for (char note : dataset) {
        datasetList.add(note);
    }


    int currentWindowSum = 0;
    int index;

    for (index = 0; index <= datasetList.size() - notesSize; index++) {

        if (index == 0) {
            for (int j = 0; j < notesSize; j++) {
                currentWindowSum += dataset[j];
            }
        } else {
            currentWindowSum = currentWindowSum - datasetList.get(index - 1) + datasetList.get(index + notesSize - 1);
        }

        if (currentWindowSum == notesSum) {
            List<Character> tempList = new LinkedList<>(datasetList.subList(index, index + notesSize));
            if (new HashSet<>(tempList).containsAll(notes)) {
                break;
            }
        }
    }

    if (index <= datasetList.size() - notesSize) {
        for (int i = index; i < index + notesSize; i++) {
            System.out.print(dataset[i]);
        }
    } else {
        System.out.println("No matching substring found.");
    }
}
