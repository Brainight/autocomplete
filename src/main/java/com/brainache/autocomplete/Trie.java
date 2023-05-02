package com.brainache.autocomplete;

import com.brainache.autocomplete.nodes.Node;
import com.brainache.autocomplete.nodes.Node.NodeFactory;
import com.brainache.autocomplete.nodes.NodeFactoryProvider;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Brainight
 */
public class Trie {

    public static final int DEFAULT_AUTOCOMPLETE_SIZE = 4;
    private Node root;
    private NodeFactory factory;

    public Trie() {
        this.factory = NodeFactoryProvider.getNodeFactory(Node.NodeType.TreeMapNode);
        this.root = this.factory.create('*');
    }

    public Trie(NodeFactory n) {
        this.factory = n;
        this.root = this.factory.create('*');
    }

    public void insertWord(String word) {
        Node curr = this.root;
        String lcword = word.toLowerCase();
        for (char c : lcword.toCharArray()) {
            curr = curr.computeIfAbsent(c, k -> this.factory.create(c));
        }
        curr.setEndWord(true);
    }

    /**
     * Fills the provided list with a max of 4 results. The results are the next
     * possible word in alphanumeric order.
     *
     * @param input
     * @param result
     */
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

    /**
     * Returns a list with a max of 4 results. The results are the next possible
     * word in alphanumeric order.
     *
     * @param input
     * @param result
     */
    public List<String> autoComplete(String input) {
        List<String> result = new LinkedList();
        Node curr = this.root;
        for (char c : input.toCharArray()) {
            curr = curr.getNode(c);
            if (curr == null) {
                return result;
            }
        }
        findNextWord(curr, result, input, DEFAULT_AUTOCOMPLETE_SIZE);
        return result;
    }

    /**
     * Return a list containing a maximum of {@code resultLength} results.
     *
     * @param input
     * @param resultLength
     * @return a {@code List<String>}
     */
    public List<String> autoComplete(String input, int resultLength) {
        List<String> result = new LinkedList();
        Node curr = this.root;
        for (char c : input.toCharArray()) {
            curr = curr.getNode(c);
            if (curr == null) {
                return result;
            }
        }
        findNextWord(curr, result, input, resultLength > 0 ? resultLength : DEFAULT_AUTOCOMPLETE_SIZE);
        return result;
    }

    /**
     * Fills the provied list with a maximum of @code resultLength results.
     *
     * @param input
     * @param resultLength
     * @return
     */
    public void autoComplete(String input, int resultLength, List<String> result) {
        Node curr = this.root;
        for (char c : input.toCharArray()) {
            curr = curr.getNode(c);
            if (curr == null) {
                return;
            }
        }
        findNextWord(curr, result, input, resultLength > 0 ? resultLength : DEFAULT_AUTOCOMPLETE_SIZE);
    }

    private void findNextWord(Node node, List<String> data, String acword, int maxWords) {
        Node curr = null;
        if (data.size() >= maxWords) {
            return;
        }
        String prefix = acword;
        for (char c : node.getChildrenNodeKeys()) {
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
