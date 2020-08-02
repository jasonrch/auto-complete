package com.example.autocomplete;

import java.util.ArrayList;
import java.util.List;

public class Trie {
    private TrieNode root = new TrieNode(' ');

    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            if (!current.hasChild(ch))
                current.addChild(ch);
            current = current.getChild(ch);
        }

        current.setIsEndOfWord(true);
    }

    public boolean contains(String word) {
        if (word == null)
            return false;

        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            if (!current.hasChild(ch))
                return false;
            current = current.getChild(ch);
        }

        return current.isEndOfWord();
    }

    public boolean containsRecursive(String word) {
        if (word == null)
            return false;

        return containsRecursive(root, word, 0);
    }

    private boolean containsRecursive(TrieNode node, String word, int index) {
        if (index == word.length())
            return node.isEndOfWord();

        if (node == null)
            return false;

        char ch = word.charAt(index);
        TrieNode child = node.getChild(ch);

        if (child == null)
            return false;

        return containsRecursive(child, word, index + 1);
    }


    public void traverse() {
        traverse(root);
    }

    private void traverse(TrieNode root) {
        //Pre-order: visit the root first. Post-order visit the root last.
        for (TrieNode child : root.getChildren())
            traverse(child);

        System.out.println(root.getValue());
    }

    public void remove(String word) {
        if (word == null)
            return;

        remove(root, word, 0);
    }

    private void remove(TrieNode root, String word, int index) {
        if (index == word.length()) {
            root.setIsEndOfWord(false);
            return;
        }

        char ch = word.charAt(index);
        TrieNode child = root.getChild(ch);
        if (child == null)
            return;

        remove(child, word, index + 1);

        if (!child.hasChildren() && !child.isEndOfWord())
            root.removeChild(ch);
    }

    public List<String> findWords(String prefix) {
        if (prefix == null)
            throw new IllegalArgumentException();

        List<String> wordsList = new ArrayList<>();
        TrieNode node = findLastNodeOf(prefix);
        findWords(node, prefix, wordsList);

        return wordsList;
    }


    private void findWords(TrieNode node, String prefix, List<String> words) {
        if (node == null)
            return;

        if (node.isEndOfWord())
            words.add(prefix);

        for (TrieNode child : node.getChildren())
            findWords(child, prefix + child.getValue(), words);

    }

    private TrieNode findLastNodeOf(String prefix) {
        TrieNode current = root;
        for (char ch : prefix.toCharArray()) {
            TrieNode child = current.getChild(ch);
            if (child == null)
                return null;

            current = child;
        }
        return current;
    }

    public int countWords() {
        return countWords(root);
    }

    private int countWords(TrieNode root) {
        int total = 0;
        if (root.isEndOfWord())
            total++;

        for (TrieNode child : root.getChildren())
            total += countWords(child);

        return total;
    }
}
