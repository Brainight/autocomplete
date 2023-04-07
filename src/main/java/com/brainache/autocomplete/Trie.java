package com.brainache.autocomplete;

import com.brainache.autocomplete.nodes.Node;
import com.brainache.autocomplete.nodes.Node.NodeFactory;
import java.util.List;

/**
 *
 * @author Brainight
 */
public class Trie {

    public static final int DEFAULT_AUTOCOMPLETE_SIZE = 4;
    private Node root;
    private NodeFactory factory;

    public Trie(NodeFactory n) {
        this.root = n.create('*');
        this.factory = n;
    }

    public void insertWord(String word) {
        Node curr = this.root;
        String lcword = word.toLowerCase();
        for (char c : lcword.toCharArray()) {
            curr = curr.computeIfAbsent(c, k -> this.factory.create(c));
        }
        curr.setEndWord(true);
    }

    public void autoComplete(String input, List<String> result) {
        Node curr = this.root;
        for (char c : input.toCharArray()) {
            curr = curr.getNode(c);
            if (curr == null) {
                return;
            }
        }
        findNextWord(curr, result, input, DEFAULT_AUTOCOMPLETE_SIZE);
    }

    public void findNextWord(Node node, List<String> data, String acword, int maxWords) {
        Node curr = null;
        if (data.size() >= maxWords) {
            return;
        }
        String prefix = acword;
        for (char c :  node.getChildrenNodeKeys()) {
            curr = node.getNode(c);
            acword = prefix + c;
            if (curr.isEndWord()) {
                if (data.size() >= maxWords) {
                    return;
                }
                data.add(acword);
            }
            findNextWord(curr, data, acword, maxWords);
        }
    }

}
