package hashing.model;

import java.util.ArrayList;
import java.util.List;

public class HashTable<T extends Comparable<T>> {
  private List<Node<T>> items;

  public HashTable(int n) {
    this.items = new ArrayList<Node<T>>(n);

    for (int i = 0; i < n; i++) {
      this.items.add(new Node<>());
    }
  }

  public List<Node<T>> getItems() {
    return items;
  }

}
