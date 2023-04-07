package com.brainache.autocomplete.nodes;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Brainight
 */
public class TreeMapNode implements Node{
    
    private Map<Character, TreeMapNode> children;
    private boolean endWord;

    public TreeMapNode() {
        this.children = new TreeMap<>();
    }

    public TreeMapNode(Map<Character, TreeMapNode> children, boolean endWord) {
        this.children = children;
        this.endWord = endWord;
    }

    public Map<Character, TreeMapNode> getChildren() {
        return children;
    }

    public void setChildren(Map<Character, TreeMapNode> children) {
        this.children = children;
    }

    @Override
    public boolean isEndWord() {
        return endWord;
    }

    @Override
    public void setEndWord(boolean endWord) {
        this.endWord = endWord;
    }

    @Override
    public TreeMapNode computeIfAbsent(Character c, NodeComputeFunction func) {
        TreeMapNode n = getNode(c);
        if(n == null){
            n = (TreeMapNode)func.computeFunction(c);
            this.children.put(c, n);
        }
        return n;
    }

    @Override
    public TreeMapNode getNode(Character c) {
        return this.children.get(c);
    }

    @Override
    public Collection<Character> getChildrenNodeKeys() {
        return this.children.keySet();
    }

}
