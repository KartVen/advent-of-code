package pl.kartven;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/* TODO: Fix small bug */
public class Task10 {

    public static void main(String[] args) throws IOException {
        List<String> content = Files.readAllLines(Path.of(Resource.getPath("task10.txt")), StandardCharsets.UTF_8);
        int signalStrength = 0;
        int spritePosition = 0;

        while (Instruction.i <= content.size() - 1) {
            String[] lnSplit = content.get(Instruction.i).split(" ");
            if (Instruction.during == 0) {
                CPU.x += Instruction.value;
                switch (lnSplit[0]) {
                    case "noop" -> {
                        Instruction.during = 1;
                        Instruction.value = 0;
                    }
                    case "addx" -> {
                        Instruction.during = 2;
                        Instruction.value = Integer.parseInt(lnSplit[1]);
                    }
                    default -> {
                    }
                }
                Instruction.i++;
                if (Picture.row >= 0) spritePosition = CPU.x;
            }
            if (CPU.tick + 1 == 20 || ((CPU.tick + 1 - 20) % 40 == 0 && CPU.tick < 220)) {
                signalStrength += (CPU.tick + 1) * CPU.x;
            }
            if (CPU.tick % 40 == 0) {
                Picture.content.add(new ArrayList<>());
                Picture.row++;
            }
            CPU.tick++;
            Instruction.during--;

            if (CPU.tick >= spritePosition && CPU.tick <= spritePosition + 2 || CPU.tick % 40 >= spritePosition && CPU.tick % 40 <= spritePosition + 2) {
                Picture.content.get(Picture.row).add('#');
            } else {
                Picture.content.get(Picture.row).add('.');
            }
        }
        System.out.println(signalStrength);
        Picture.print();
    }

    private static class Instruction {
        static int i = 0, during = 0, value = 0;
    }

    private static class Picture {
        static List<List<Character>> content = new ArrayList<>();
        static int row = -1;

        static void print() {
            for (List<Character> characters : content) {
                characters.forEach(System.out::print);
                System.out.println();
            }
        }
    }

    private static class CPU {
        static int tick = 0, x = 1;
    }
}
