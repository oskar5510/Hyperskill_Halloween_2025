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
