package pl.kartven;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;

public class Task9 {
    private static List<String> content;

    public static void main(String[] args) throws IOException {
        content = Files.readAllLines(Path.of(Resource.getPath("task9.txt")), StandardCharsets.UTF_8);

        System.out.println("Part 1: " + part(1));
        System.out.println("Part 2: " + part(9));
    }
    private static int part(int knots) {
        Rope rope = new Rope(knots);

        for (String ln : content) {
            String[] lnSplit = ln.split(" ");
            int moveValue = Integer.parseInt(lnSplit[1]);
            for (int i = 0; i < moveValue; i++) {
                switch (lnSplit[0]) {
                    case "U" -> rope.knots.get(0).y++;
                    case "D" -> rope.knots.get(0).y--;
                    case "L" -> rope.knots.get(0).x--;
                    case "R" -> rope.knots.get(0).x++;
                }
                for (int k = 1; k < rope.knots.size(); k++) {
                    Point preKnot = rope.knots.get(k - 1);
                    Point currentKnot = rope.knots.get(k);
                    if (Math.abs(preKnot.x - currentKnot.x) > 1 || Math.abs(preKnot.y - currentKnot.y) > 1) {
                        if (preKnot.x > currentKnot.x && preKnot.y > currentKnot.y) {
                            currentKnot.x++;
                            currentKnot.y++;
                        }
                        if (preKnot.x > currentKnot.x && preKnot.y < currentKnot.y) {
                            currentKnot.x++;
                            currentKnot.y--;
                        }
                        if (preKnot.x < currentKnot.x && preKnot.y > currentKnot.y) {
                            currentKnot.x--;
                            currentKnot.y++;
                        }
                        if (preKnot.x < currentKnot.x && preKnot.y < currentKnot.y) {
                            currentKnot.x--;
                            currentKnot.y--;
                        }
                        if (preKnot.x == currentKnot.x) currentKnot.y += (preKnot.y - currentKnot.y) / 2;
                        if (preKnot.y == currentKnot.y) currentKnot.x += (preKnot.x - currentKnot.x) / 2;
                    }
                }
                rope.saveTail();
            }
        }
        return rope.tailPositions.size();
    }

    private static class Rope {
        final List<Point> knots;
        final Set<Point> tailPositions;
        final Point tail;

        Rope(int knots) {
            this.knots = new ArrayList<>() {{
                for (int i = 0; i < knots + 1; i++) add(new Point());
            }};
            this.tail = this.knots.get(this.knots.size() - 1);
            this.tailPositions = new HashSet<>() {{
                add(new Point());
            }};
        }

        void saveTail() {
            tailPositions.add(new Point(this.tail));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Rope rope = (Rope) o;
            return tail.equals(rope.tail);
        }

        @Override
        public int hashCode() {
            return Objects.hash(tail);
        }
    }
}
