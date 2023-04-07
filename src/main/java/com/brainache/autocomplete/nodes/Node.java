package com.brainache.autocomplete.nodes;

import java.util.Collection;

/**
 *
 * @author Brainight
 */
public interface Node {

    @FunctionalInterface
    public static interface NodeComputeFunction {

        Node computeFunction(char c);
    }

    @FunctionalInterface
    public static interface NodeFactory {

        Node create(Object...args);
    }

    Node computeIfAbsent(Character c, NodeComputeFunction func);

    Node getNode(Character c);

    Collection<Character> getChildrenNodeKeys();

    boolean isEndWord();

    void setEndWord(boolean endWord);

}
