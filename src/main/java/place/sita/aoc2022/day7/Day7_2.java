package place.sita.aoc2022.day7;

import pl.ksitarski.quickparse.QuickParse;
import place.sita.aoc2022.utils.Utils;

import java.util.*;

public class Day7_2 {

    public static void main(String[] args) {
        List<Directory> allDirectories = new ArrayList<>();
        Stack<Directory> directoryStack = new Stack<>();

        List<String> dirOutput = new ArrayList<>();

        Utils.readFile("day7.txt").forEach(line -> {

            if (!line.startsWith("$")) {
                dirOutput.add(line);
            } else {
                if (directoryStack.size() != 0) {
                    fillDirectoryWithInfo(directoryStack.peek(), dirOutput);
                }
                if (line.equals("$ cd ..")) {
                    directoryStack.pop();
                    return;
                }

                if (line.startsWith("$ cd ")) {
                    String dirName = line.substring(5);
                    Directory directory;
                    if (directoryStack.size() != 0 && directoryStack.peek().directories.containsKey(dirName)) {
                        directory = directoryStack.peek().directories.get(dirName);
                    } else {
                        directory = new Directory();
                        directory.name = dirName;
                        if (directoryStack.size() != 0) {
                            directoryStack.peek().directories.put(dirName, directory);
                        }
                        allDirectories.add(directory);
                    }
                    directoryStack.add(directory);
                    return;
                }

                if (line.startsWith("$ ls")) {
                    dirOutput.clear();
                    return;
                }

            }

        });

        fillDirectoryWithInfo(directoryStack.peek(), dirOutput);

        long total = 70000000;
        long needed = 30000000;
        long taken = allDirectories.stream()
            .filter(directory -> directory.name.equals("/"))
            .findFirst()
            .get()
            .dirSize();
        long freeCurrently = total - taken;
        long neededToFree = needed - freeCurrently;

        PriorityQueue<Directory> priorityQueue = new PriorityQueue<>(Comparator.comparingLong(Directory::dirSize));

        priorityQueue.addAll(allDirectories);

        Directory checked = null;

        while ((checked = priorityQueue.poll()) != null) {
            if (checked.dirSize() > neededToFree) {
                System.out.println(checked.dirSize());
                return;
            }
        }
    }

    private static void fillDirectoryWithInfo(Directory current, List<String> dirOutput) {
        dirOutput.forEach(
            dir -> {
                if (dir.startsWith("dir")) {
                    return;
                }
                FileName fileName = QuickParse.parseToObject("${size}(int)$ ${name}$", dir, new FileName(), FileName.class);
                current.files.put(
                    fileName.name, fileName.size
                );
            }
        );

        dirOutput.clear();
    }

    public static class FileName {
        public String name;
        public long size;
    }

    public static class Directory {
        String name;
        Map<String, Directory> directories = new HashMap<>();
        Map<String, Long> files = new HashMap<>();
        long size;


        public long dirSize() {
            if (size != 0) {
                return size;
            }

            directories.values().forEach(
                directory -> size += directory.dirSize()
            );

            files.values().forEach(fileSize -> {
                size += fileSize;
            });

            return size;
        }

    }


}
