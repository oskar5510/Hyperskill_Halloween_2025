/*
 * Task: The Runtime Warp
 *
 * Description:
 * You are given a list of four-dimensional vectors, each represented by four integers (x, y, z, a).
 * These vectors describe points in 4D space. Your goal is to calculate the total distance
 * traveled through these points in sequence.
 *
 * Rules:
 * 1. Calculate the Euclidean distance between each consecutive pair of points.
 * 2. If the calculated distance is not an integer, round it UP to the nearest whole number.
 * 3. Sum all the rounded distances to get the total travel distance.
 *
 * Formula:
 * For two 4D points (x1, y1, z1, a1) and (x2, y2, z2, a2):
 * distance = √((x2 - x1)² + (y2 - y1)² + (z2 - z1)² + (a2 - a1)²)
 *
 * Steps:
 * 1. Read the dataset line by line — each line contains four comma-separated integers.
 * 2. Convert each line into a 4D point.
 * 3. Iterate through all consecutive pairs of points and calculate their distances.
 * 4. Round up each distance and add it to the total.
 * 5. Print the final total distance as an integer value.
 *
 * Output:
 * A single integer representing the total rounded-up Euclidean distance traveled
 * through all points in the dataset.
 */

void main() throws FileNotFoundException {

    final File datasetFile = new File("October27_dataset.txt");

    List<Point> points = new ArrayList<>();

    try (Scanner scanner = new Scanner(datasetFile)) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isBlank()) break;
            points.add(new Point(line));
        }
    }

    long totalDistance = 0;
    long distanceBetweenPoints;

    for (int i = 0; i < points.size() - 1; i++) {
        distanceBetweenPoints = Point.calculateDistance(points.get(i), points.get(i + 1));
        totalDistance += distanceBetweenPoints;
    }

    System.out.printf("Euclidean distance for %d pairs is %d\n", points.size(), totalDistance);

}

private record Point(double x, double y, double z, double a) {
    public Point(String line) {
        int[] numbers;
        try {
            numbers = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Failed to parse line as integers: '" + line + "'", e);
        }
        if (numbers.length != 4) {
            throw new IllegalArgumentException("Expected 4 values, got " + numbers.length + " in line: '" + line + "'");
        }
        this(numbers[0], numbers[1], numbers[2], numbers[3]);
    }

    public static long calculateDistance(Point p1, Point p2) {
        double xSquared = (p2.x - p1.x) * (p2.x - p1.x);
        double ySquared = (p2.y - p1.y) * (p2.y - p1.y);
        double zSquared = (p2.z - p1.z) * (p2.z - p1.z);
        double aSquared = (p2.a - p1.a) * (p2.a - p1.a);

        double sum = xSquared + ySquared + zSquared + aSquared;

        double distance = Math.sqrt(sum);
        return (long) Math.ceil(distance);

    }


}


