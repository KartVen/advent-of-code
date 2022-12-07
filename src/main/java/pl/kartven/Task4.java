package pl.kartven;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Task4 {

    static List<String> content;
    final static List<List<List<Integer>>> pairs = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        content = Files.readAllLines(Paths.get(Resource.getPath("task4.txt")), StandardCharsets.UTF_8);
        for (String ln : content) {
            List<List<Integer>> pair = Arrays.stream(ln.split(",")).map(
                    elf -> Arrays.stream(elf.split("-")).map(Integer::parseInt).toList()
            ).toList();
            pairs.add(pair);
        }

        part1();
        part2();
    }


    private static void part1() {
        int counter = 0;

        for (List<List<Integer>> pair : pairs) {

            BiFunction<Integer, Integer, Boolean> checkInclude =
                    (elf1, elf2) -> (pair.get(elf1).get(0) >= pair.get(elf2).get(0) && pair.get(elf1).get(1) <= pair.get(elf2).get(1));

            if (checkInclude.apply(0, 1)) counter++;
            else if (checkInclude.apply(1, 0)) counter++;

        }
        System.out.println(counter);
    }

    private static void part2() {
        int counter = 0;

        for (List<List<Integer>> pair : pairs) {

            Function<List<Integer>, Set<Integer>> getSet =
                    elf1 -> IntStream.range(elf1.get(0), elf1.get(1) + 1).boxed().collect(Collectors.toSet());

            Set<Integer> elf1 = getSet.apply(pair.get(0));
            Set<Integer> elf2 = getSet.apply(pair.get(1));

            for (Integer section : elf1) {
                if (elf2.contains(section)) {
                    counter++;
                    break;
                }
            }
        }
        System.out.println(counter);
    }
}
