package hashing.service;

import hashing.model.HashTable;
import hashing.model.Node;
import hashing.model.NodeStatus;

public class HashQuadraticProb<T extends Comparable<T>> extends Hash<T> {

  @Override
  public boolean insert(HashTable<T> hashTable, T value) {
    int tableSize = hashTable.getItems().size();

    int position = value.hashCode() % tableSize;

    for (int k = 0; k < tableSize - 1; k++) {
      position = (position + k) % tableSize;

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

    int position = value.hashCode() % tableSize;

    for (int k = 0; k < tableSize - 1; k++) {
      position = (position + k) % tableSize;

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

    int position = value.hashCode() % tableSize;

    for (int k = 0; k < tableSize - 1; k++) {
      position = (position + k) % tableSize;

      Node<T> element = hashTable.getItems().get(position);
      if (element.getStatus().equals(NodeStatus.BUSY) && value.equals(element.getValue())) {
        return element.getValue();
      }
    }

    return null;
  }

}
