package hashing.service;

import java.util.List;
import java.util.StringJoiner;

import hashing.model.HashTable;
import hashing.model.Node;

public abstract class Hash<T extends Comparable<T>> implements IHashing<T> {
  @Override
  public HashTable<T> createHashTable(int n) {
    return new HashTable<T>(n);
  }

  @Override
  public void insert(HashTable<T> hashTable, T[] values) {
    for (T value : values) {
      insert(hashTable, value);
    }
  }

  @Override
  public String toString(HashTable<T> hashTable) {
    List<Node<T>> items = hashTable.getItems();

    StringJoiner joiner = new StringJoiner(",");

    for (int i = 0; i < items.size(); i++) {
      Node<T> item = items.get(i);
      String message = String.format("[%d]%s", i, item.toString());

      joiner.add(message);
    }

    return joiner.toString();
  }

}
