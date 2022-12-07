package pl.kartven;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Task5 {

    private final static List<List<Character>> stacks = new ArrayList<>();
    private final static List<String[]> moves = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        List<String> content = Files.readAllLines(Path.of(Resource.getPath("task5.txt")), StandardCharsets.UTF_8);
        boolean movement = false;
        for (String ln : content) {
            if (ln.equals("")) {
                movement = true;
                continue;
            }
            if (!movement) {
                int col = 2;
                for (char c : ln.toCharArray())
                    if (++col % 4 == 0) {
                        if (stacks.size() < col / 4) stacks.add(new ArrayList<>());
                        if (c >= 'A' && c <= 'Z') stacks.get(col / 4 - 1).add(0, c);
                    }
            } else moves.add(ln.split(" "));
        }
        part1();
        part2();
    }

    private static void part1() {
        List<ArrayList<Character>> stacksCopy = stacks.stream().map(ArrayList::new).toList();

        for (String[] move : moves)
            for (int i = 0; i < Integer.parseInt(move[1]); i++) {
                List<Character> stack = stacksCopy.get(Integer.parseInt(move[3]) - 1);
                stacksCopy.get(Integer.parseInt(move[5]) - 1).add(stack.remove(stack.size() - 1));
            }

        StringBuilder topOfStacks = new StringBuilder();
        for (List<Character> stack : stacksCopy) topOfStacks.append(stack.get(stack.size() - 1));
        System.out.println(topOfStacks);
    }

    private static void part2() {
        List<ArrayList<Character>> stacksCopy = stacks.stream().map(ArrayList::new).toList();

        for (String[] move : moves)
            for (int i = Integer.parseInt(move[1]); i > 0; i--) {
                List<Character> stack = stacksCopy.get(Integer.parseInt(move[3]) - 1);
                stacksCopy.get(Integer.parseInt(move[5]) - 1).add(stack.remove(stack.size() - i));
            }

        StringBuilder topOfStacks = new StringBuilder();
        for (List<Character> stack : stacksCopy) topOfStacks.append(stack.get(stack.size() - 1));
        System.out.println(topOfStacks);
    }
}
