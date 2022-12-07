package pl.kartven;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Task7 {
    private static Map<String, Long> dirSize;

    public static void main(String[] args) throws IOException {
        List<String> content = Files.readAllLines(Path.of(Resource.getPath("task7.txt")), StandardCharsets.UTF_8);
        dirSize = new HashMap<>();

        Map<String, List<File>> structure = new HashMap<>();
        List<String> currentPath = new ArrayList<>() {{
            add("");
        }};

        for (String ln : content) {
            String[] lnSplit = ln.split(" ");
            if (lnSplit[0].equals("$")) {
                if (lnSplit[1].equals("cd")) switch (lnSplit[2]) {
                    case "/":
                        break;
                    case "..":
                        currentPath.remove(currentPath.size() - 1);
                        break;
                    default:
                        currentPath.add(lnSplit[2]);
                }
            } else if (!lnSplit[0].equals("dir")) {
                String dirPath = String.join("/", currentPath.toArray(new String[0]));//pwd(currentPath);
                if (!structure.containsKey(dirPath)) structure.put(dirPath, new ArrayList<>());
                structure.get(dirPath).add(new File(lnSplit[1], Long.parseLong(lnSplit[0])));
            }
        }

        structure.forEach((key, value) -> {
            String[] dirs = key.split("/");
            for (int nestingCounter = 0; nestingCounter < dirs.length; nestingCounter++) {
                String parentDirs = String.join("/", Arrays.copyOfRange(dirs, 0, nestingCounter + 1));
                dirSize.put(parentDirs,
                        (dirSize.containsKey(parentDirs) ? dirSize.get(parentDirs) : 0) + value.stream().mapToLong(el -> el.size).sum()
                );
            }
        });

        part1();
        part2();
    }

    private static void part1() {
        System.out.println(dirSize.values().stream().filter(el -> el <= 100_000).mapToLong(el -> el).sum());
    }

    private static void part2() {
        Long neededSize = 30000000 - (70_000_000 - dirSize.get(""));
        System.out.println(dirSize.values().stream().filter(el -> el >= neededSize).min(Long::compare).orElse(0L));
    }

    private static class File {
        protected String name;
        protected long size;

        public File(String name, long size) {
            this.name = name;
            this.size = size;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }
}
