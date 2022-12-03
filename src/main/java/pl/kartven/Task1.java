package pl.kartven;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Task1 {

    public static void main() throws IOException {
        BufferedReader file = new BufferedReader(new FileReader("src/main/resources/task1.txt"));

        ArrayList<Integer> elfFeed = new ArrayList<>();
        ArrayList<Integer> summedElfFeed = new ArrayList<>();

        String ln = "";
        while(ln != null){
            ln = file.readLine();
            if(ln == null || ln.isEmpty()){
                summedElfFeed.add(elfFeed.stream().mapToInt(Integer::intValue).sum());
                elfFeed.clear();
                continue;
            }
            elfFeed.add(Integer.parseInt(ln));
        }
        int[] topElf = {0,0,0};
        for (int i = 0; i < topElf.length; i++) {
            topElf[i] = Collections.max(summedElfFeed);
            summedElfFeed.remove((Integer) topElf[i]);
        }
        System.out.println(Arrays.stream(topElf).sum());
        file.close();
    }
}
