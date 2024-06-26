package hashing.service;

import hashing.model.HashTable;

public interface IHashing<T extends Comparable<T>> {

  HashTable<T> createHashTable(int n);

  boolean insert(HashTable<T> hashTable, T value);

  void insert(HashTable<T> hashTable, T[] values);

  boolean remove(HashTable<T> hashTable, T value);

  T find(HashTable<T> hashTable, T value);

  String toString(HashTable<T> hashTable);

}
