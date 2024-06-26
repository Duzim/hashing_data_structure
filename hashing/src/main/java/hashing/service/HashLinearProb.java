package hashing.service;

import hashing.model.HashTable;
import hashing.model.Node;
import hashing.model.NodeStatus;

public class HashLinearProb<T extends Comparable<T>> extends Hash<T> {

  private int getIndex(HashTable<T> hashTable, T value, int m, int i) {
    return ((value.hashCode() % m) + i) % m;
  }

  @Override
  public boolean insert(HashTable<T> hashTable, T value) {
    int tableSize = hashTable.getItems().size();

    for (int i = 0; i < tableSize - 1; i++) {
      int position = getIndex(hashTable, value, tableSize, i);

      Node<T> element = hashTable.getItems().get(position);
      if (!element.getStatus().equals(NodeStatus.BUSY)) {
        element.setValue(value);
        element.setStatus(NodeStatus.BUSY);

        return true;
      }
    }

    return false;
  }

  @Override
  public boolean remove(HashTable<T> hashTable, T value) {
    int tableSize = hashTable.getItems().size();

    for (int i = 0; i < tableSize - 1; i++) {
      int position = getIndex(hashTable, value, tableSize, i);

      Node<T> element = hashTable.getItems().get(position);
      if (element.getStatus().equals(NodeStatus.BUSY) && value.equals(element.getValue())) {
        element.setStatus(NodeStatus.REMOVED);

        return true;
      }
    }

    return false;
  }

  @Override
  public T find(HashTable<T> hashTable, T value) {
    int tableSize = hashTable.getItems().size();

    for (int i = 0; i < tableSize - 1; i++) {
      int position = getIndex(hashTable, value, tableSize, i);

      Node<T> element = hashTable.getItems().get(position);
      if (element.getStatus().equals(NodeStatus.BUSY) && value.equals(element.getValue())) {
        return element.getValue();
      }
    }

    return null;
  }

}
