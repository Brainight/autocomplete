

package com.brainache.autocomplete.nodes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 *
 * @author Brainight
 */
public class LinkedListNode implements Node{

    private LinkedList<LinkedListNode> children;
    private char key;
    private boolean endWord;
    
    public LinkedListNode(char c){
        this.key = c;
        this.children = new LinkedList<>();
    }

    @Override
    public LinkedListNode computeIfAbsent(Character c, NodeComputeFunction func) {
        LinkedListNode n = getNode(c);
        if(n == null){
            n = (LinkedListNode)func.computeFunction(c);
            this.children.add(n);
        }
        return n;
    }

    @Override
    public LinkedListNode getNode(Character c) {
        for(LinkedListNode n : this.children){
            if(n.key == c.charValue()){
                return n;
            }
        }
        return null;
    }

    @Override
    public Collection<Character> getChildrenNodeKeys() {
        return this.children.stream().map(node -> node.key).sorted().collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public boolean isEndWord() {
        return this.endWord;
    }

    @Override
    public void setEndWord(boolean endWord) {
        this.endWord = endWord;
    }
}
