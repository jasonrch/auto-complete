package com.example.autocomplete;

import java.util.HashMap;

public class TrieNode {
    private char value;
    private HashMap<Character, TrieNode> children = new HashMap<>();
    private boolean isEndOfWord;

    public TrieNode(char value) {
        this.value = value;
    }

    //Abstraction
    public boolean hasChild(char ch) {
        return children.containsKey(ch);
    }

    public void addChild(char ch) {
        children.put(ch, new TrieNode(ch));
    }

    public TrieNode getChild(char ch) {
        return children.get(ch);
    }

    public char getValue(){
        return value;
    }

    public boolean hasChildren() {
        return !children.isEmpty();
    }

    public TrieNode[] getChildren() {
        return children.values().toArray(new TrieNode[0]);
    }

    public void setIsEndOfWord(Boolean bool) {
        isEndOfWord = bool;
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public void removeChild(char ch) {
        children.remove(ch);
    }

    @Override
    public String toString() {
        return "value=" + value;
    }
}
