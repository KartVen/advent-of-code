package pl.kartven;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Task2 {

    private final static HashMap<String, Integer> shapeVal = new HashMap<>() {{
        put("X", 1); //rock
        put("Y", 2); //paper
        put("Z", 3); //scissors
    }};

    private final static HashMap<String, String> comparer = new HashMap<>() {{
        put("A", "X"); //rock
        put("B", "Y"); //paper
        put("C", "Z"); //scissors
    }};

    public static void main(String[] args) throws IOException {
        BufferedReader file = new BufferedReader(new FileReader(Resource.getPath("task2.txt")));
        List<String> content = new ArrayList<>();
        String ln;
        while ((ln = file.readLine()) != null) content.add(ln);

        System.out.println("Part 1: " + part1(content));
        System.out.println("Part 2: " + part2(content));

        file.close();
    }

    private static int part1(List<String> content) {
        int sum = 0;

        for (String ln : content) {
            String[] ln2 = ln.split(" ");
            switch (ln2[1]) {
                case "X":
                    sum += shapeVal.get("X");
                    sum += switch (comparer.get(ln2[0])) {
                        case "X" -> 3;
                        case "Z" -> 6;
                        default -> 0;
                    };
                    break;
                case "Y":
                    sum += shapeVal.get("Y");
                    sum += switch (comparer.get(ln2[0])) {
                        case "X" -> 6;
                        case "Y" -> 3;
                        default -> 0;
                    };
                    break;
                case "Z":
                    sum += shapeVal.get("Z");
                    sum += switch (comparer.get(ln2[0])) {
                        case "Y" -> 6;
                        case "Z" -> 3;
                        default -> 0;
                    };
                    break;
            }
        }
        return sum;
    }

    private static int part2(List<String> content) {
        int sum = 0;

        for (String ln : content) {
            String[] ln2 = ln.split(" ");
            switch (ln2[1]) {
                case "X":
                    sum += 0;
                    sum += switch (comparer.get(ln2[0])) {
                        case "X" -> shapeVal.get("Z");
                        case "Y" -> shapeVal.get("X");
                        case "Z" -> shapeVal.get("Y");
                        default -> 0;
                    };
                    break;
                case "Y":
                    sum += 3;
                    sum += switch (comparer.get(ln2[0])) {
                        case "X" -> shapeVal.get("X");
                        case "Y" -> shapeVal.get("Y");
                        case "Z" -> shapeVal.get("Z");
                        default -> 0;
                    };
                    break;
                case "Z":
                    sum += 6;
                    sum += switch (comparer.get(ln2[0])) {
                        case "X" -> shapeVal.get("Y");
                        case "Y" -> shapeVal.get("Z");
                        case "Z" -> shapeVal.get("X");
                        default -> 0;
                    };
                    break;
            }
        }
        return sum;
    }
}