package pl.kartven;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Task3 {

    public static void main(String[] args) throws IOException {
        BufferedReader file = new BufferedReader(new FileReader(Resource.getPath("task3.txt")));
        List<String> content = new ArrayList<>();
        String ln; while ((ln = file.readLine()) != null) content.add(ln);

        System.out.println("Part 1: " + part1(content));
        System.out.println("Part 2: " + part2(content));

        file.close();
    }


    private static int part1(List<String> content) {

        int summedPrior = 0;

        for (String ln : content) {
            int len = ln.length() / 2;
            Set<Character> first = ln.substring(0, len).chars().mapToObj(e -> (char) e).collect(Collectors.toSet());
            Set<Character> second = ln.substring(len).chars().mapToObj(e -> (char) e).collect(Collectors.toSet());
            for (Character letter : first) {
                if (second.contains(letter)) {
                    if (letter >= 'a' && letter <= 'z') {
                        summedPrior += letter - 'a' + 1;
                    } else {
                        summedPrior += letter - 'A' + 27;
                    }
                }
            }
        }
        return summedPrior;
    }

    private static int part2(List<String> content) {

        int summedPrior = 0;
        List<Set<Character>> rugsackGroup = new ArrayList<>();

        for (String ln : content) {
            Set<Character> rugsack = ln.chars().mapToObj(e -> (char) e).collect(Collectors.toSet());
            if (rugsackGroup.size() > 0 && rugsackGroup.get(0).size() < rugsack.size()) {
                    rugsackGroup.add(0, rugsack);
            } else {
                rugsackGroup.add(rugsack);
            }
            if (rugsackGroup.size() == 3) {
                for (Character letter : rugsackGroup.get(0)) {
                    if (rugsackGroup.get(1).contains(letter) && rugsackGroup.get(2).contains(letter)) {
                        if (letter >= 'a' && letter <= 'z') {
                            summedPrior += letter - 'a' + 1;
                        } else {
                            summedPrior += letter - 'A' + 27;
                        }
                        break;
                    }
                }
                rugsackGroup.clear();
            }
        }
        return summedPrior;
    }
}
