# autocomplete
Simple Trie based autocomplete engine with different Node implementations.

Usage:
```java
// Create the Trie
Trie trie = new Trie();

// Populate the Trie
trie.insertWord("Hello World");
trie.insertWord("github");
trie.insertWord("potato");
//...

// Getting autocomplete options
List<String> result = new ArrayList<>();
trie.autoComplete("potat", result);

// or 
List<String> result2 = trie.autoComplete("potat", 10);

```
