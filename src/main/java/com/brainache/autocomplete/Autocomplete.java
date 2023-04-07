package com.brainache.autocomplete;

import com.brainache.autocomplete.nodes.LinkedListNode;
import com.brainache.autocomplete.nodes.NodeFactoryProvider;
import com.brainache.autocomplete.nodes.TreeMapNode;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Brainight
 */
public class Autocomplete {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Trie trie = new Trie(NodeFactoryProvider.getNodeFactory(LinkedListNode.class));

    public interface CodeProfiler {

        void execute();

        public static long profile(CodeProfiler profilable) {
            long s, e;
            s = System.nanoTime();
            profilable.execute();
            e = System.nanoTime();
            return e - s;
        }
    }

    public static void main(String[] args) throws IOException {
        boolean exit = false;
        loadDataFromFile(Path.of(System.getProperty("user.dir") + "/src/main/resources/rockyou.txt"));
        do {
            System.out.println("Autocomplete: ");
            final String command = br.readLine();
            final List<String> result = new LinkedList<>();
            if (command.equals("!exit")) {
                exit = true;
                break;
            }
            long time = CodeProfiler.profile(() -> trie.autoComplete(command, result));
            System.out.println("Executed in: " + time + "ns");
            result.stream().forEach(e -> System.out.println(e));

        } while (!exit);
    }

    public static void loadDataFromFile(Path p) throws IOException {
        System.out.println("Loading file " + p.toString() + " ...");
        BufferedReader br = new BufferedReader(new FileReader(p.toFile()));
        String line;
        while ((line = br.readLine()) != null) {
            trie.insertWord(line);
        }
        System.out.println("Finished");
    }
}
