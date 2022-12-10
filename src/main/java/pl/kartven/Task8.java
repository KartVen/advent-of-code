package pl.kartven;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task8 {

    private static int[] gridSize;
    private static List<List<Integer>> grid;

    public static void main(String[] args) throws IOException {
        List<String> content = Files.readAllLines(Path.of(Resource.getPath("task8.txt")), StandardCharsets.UTF_8);

        gridSize = new int[]{0, content.size()};
        grid = new ArrayList<>();
        for (String ln : content) {
            List<Integer> lnSplit = Arrays.stream(ln.split("")).mapToInt(Integer::parseInt).boxed().toList();
            gridSize[0] = lnSplit.size();
            grid.add(lnSplit);
        }

        System.out.println("Part 1: " + part1());
        System.out.println("Part 2: " + part2());
    }

    private static int part1() {
        int unhiddenTree = gridSize[0] * 2 + gridSize[1] * 2 - 4;
        for (int y = 1; y < gridSize[0] - 1; y++) {
            for (int x = 1; x < gridSize[1] - 1; x++) {
                if (!isHide(y, x)) unhiddenTree++;
            }
        }
        return unhiddenTree;
    }

    private static int part2() {
        int scenicScore = 0;
        for (int y = 0; y < gridSize[0]; y++) {
            for (int x = 0; x < gridSize[1]; x++) {
                int currentScenicScore = getScenicScore(y, x);
                if (currentScenicScore > scenicScore) scenicScore = currentScenicScore;
            }
        }
        return scenicScore;
    }

    private static boolean isHide(int y, int x) {
        int currentHeight = grid.get(y).get(x);
        boolean hide = false;

        for (int k = 0; k < gridSize[1]; k++) {
            if (k == x) {
                if (!hide) return hide;
                else {
                    hide = false;
                    continue;
                }
            }
            if (grid.get(y).get(k) >= currentHeight) hide = true;
            if (k == gridSize[1] - 1 && !hide) return hide;
        }

        hide = false;
        for (int v = 0; v < gridSize[0]; v++) {
            if (v == y) {
                if (!hide) return hide;
                else {
                    hide = false;
                    continue;
                }
            }
            if (grid.get(v).get(x) >= currentHeight) hide = true;
            if (v == gridSize[0] - 1 && !hide) return hide;
        }
        return true;
    }

    private static int getScenicScore(int y, int x) {
        if (y == 0 || y == gridSize[0] - 1 || x == 0 || x == gridSize[1] - 1) return 0;

        int currentHeight = grid.get(y).get(x);
        int scenicScore = 1;

        int tempScore = 0;
        for (int k = 0; k < gridSize[1]; k++) {
            int iterateHeight = grid.get(y).get(k);
            if (k == x) {
                scenicScore *= tempScore;
                tempScore = 0;
                if (k == gridSize[1] - 1) break;
            } else if (k < x) {
                if (iterateHeight < currentHeight) tempScore++;
                else if (iterateHeight == currentHeight) tempScore = 1;
                else tempScore = 0;
            } else if (iterateHeight < currentHeight) {
                tempScore++;
            } else {
                tempScore++;
                scenicScore *= tempScore;
                break;
            }

            if (k == gridSize[1] - 1) scenicScore *= tempScore;
        }
        tempScore = 0;
        for (int l = 0; l < gridSize[0]; l++) {
            int iterateHeight = grid.get(l).get(x);
            if (l == y) {
                scenicScore *= tempScore;
                tempScore = 0;
                if (l == gridSize[0] - 1) break;
            } else if (l < y) {
                if (iterateHeight < currentHeight) tempScore++;
                else if (iterateHeight == currentHeight) tempScore = 1;
                else tempScore = 0;
            } else if (iterateHeight < currentHeight) {
                tempScore++;
            } else {
                tempScore++;
                scenicScore *= tempScore;
                break;
            }
            if (l == gridSize[0] - 1) scenicScore *= tempScore;
        }
        return scenicScore;
    }
}
