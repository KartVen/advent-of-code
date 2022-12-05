package pl.kartven;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Task5 {

    private static List<String> content;

    public static void main(String[] args) throws IOException {
        content = Files.readAllLines(Path.of("src/main/resources/task5.txt"), StandardCharsets.UTF_8);
        part1();
        part2();
    }

    private static void part1() {
        List<List<Character>> stacks = new ArrayList<>();
        boolean movement = false;

        for (String ln : content) {
            if (ln.equals("")) {
                movement = true;
                continue;
            }
            if (!movement) {
                int col = 2;
                for (char c : ln.toCharArray()) {
                    if (++col % 4 == 0) {
                        if (stacks.size() < col / 4) stacks.add(new ArrayList<>());
                        if (c >= 'A' && c <= 'Z') stacks.get(col / 4 - 1).add(0, c);
                    }
                }
            } else {
                String[] moves = ln.split(" ");
                for (int i = 0; i < Integer.parseInt(moves[1]); i++) {
                    List<Character> stack = stacks.get(Integer.parseInt(moves[3]) - 1);
                    stacks.get(Integer.parseInt(moves[5]) - 1).add(stack.remove(stack.size() - 1));
                }
            }
        }

        StringBuilder topOfStacks = new StringBuilder();
        for (List<Character> stack : stacks) topOfStacks.append(stack.get(stack.size() - 1));
        System.out.println(topOfStacks);
    }

    private static void part2() {
        List<List<Character>> stacks = new ArrayList<>();
        boolean movement = false;

        for (String ln : content) {
            if (ln.equals("")) {
                movement = true;
                continue;
            }
            if (!movement) {
                int col = 2;
                for (char c : ln.toCharArray()) {
                    if (++col % 4 == 0) {
                        if (stacks.size() < col / 4) stacks.add(new ArrayList<>());
                        if (c >= 'A' && c <= 'Z') stacks.get(col / 4 - 1).add(0, c);
                    }
                }
            } else {
                String[] moves = ln.split(" ");
                for (int i = Integer.parseInt(moves[1]); i > 0; i--) {
                    List<Character> stack = stacks.get(Integer.parseInt(moves[3]) - 1);
                    stacks.get(Integer.parseInt(moves[5]) - 1).add(stack.remove(stack.size() - i));
                }
            }
        }

        StringBuilder topOfStacks = new StringBuilder();
        for (List<Character> stack : stacks) topOfStacks.append(stack.get(stack.size() - 1));
        System.out.println(topOfStacks);
    }
}
