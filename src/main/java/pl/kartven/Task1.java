package pl.kartven;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Task1 {

    public static void main(String[] args) throws IOException {
        BufferedReader file = new BufferedReader(new FileReader(Resource.getPath("task1.txt")));

        ArrayList<Integer> elfFeed = new ArrayList<>();
        ArrayList<Integer> summedElfFeed = new ArrayList<>();

        String ln = "";
        do {
            if (ln.isEmpty()) {
                summedElfFeed.add(elfFeed.stream().mapToInt(Integer::intValue).sum());
                elfFeed.clear();
                continue;
            }
            elfFeed.add(Integer.parseInt(ln));
        } while ((ln = file.readLine()) != null);

        System.out.println("Part 1: " + Collections.max(summedElfFeed));

        /*
        int[] topElf = {0, 0, 0};
        for (int i = 0; i < topElf.length; i++) {
            topElf[i] = Collections.max(summedElfFeed);
            summedElfFeed.remove((Integer) topElf[i]);
        }
        System.out.println(Arrays.stream(topElf).sum());
        */

        System.out.println("Part 2: " + summedElfFeed.stream().sorted(Comparator.reverseOrder()).limit(3).reduce(0, Integer::sum));

        file.close();
    }
}
