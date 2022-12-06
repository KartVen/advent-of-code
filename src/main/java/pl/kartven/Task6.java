package pl.kartven;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Task6 {

    private static String content;

    public static void main(String[] args) throws IOException {
        content = Files.readAllLines(Path.of("src/main/resources/task6.txt"), StandardCharsets.UTF_8).get(0);
        part(4);
        part(14);
    }

    private static void part(int packetLen) {
        Queue<Character> markers = new ArrayDeque<>();
        int counter = 0;
        for (char c : content.toCharArray()) {
            while (markers.contains(c) && markers.size() != packetLen)
                markers.poll();
            if (markers.size() < packetLen) {
                markers.add(c);
            } else break;
            counter++;
        }
        markers.forEach(System.out::print);
        System.out.println(" " + counter);
    }
}
