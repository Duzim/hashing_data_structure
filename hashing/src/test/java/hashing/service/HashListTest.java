package hashing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import hashing.model.HashTable;

public class HashListTest {
  @Test
  void createANewHashTable() {
    IHashing<Integer> hashingService = new HashList<Integer>();
    HashTable<Integer> hashTable = hashingService.createHashTable(8);

    assertEquals(hashTable.getItems().size(), 8);

    assertEquals(hashingService.toString(hashTable),
        "[0]->null,[1]->null,[2]->null,[3]->null,[4]->null,[5]->null,[6]->null,[7]->null");
  }

  @Test
  void insertElements() {
    IHashing<Integer> hashingService = new HashList<Integer>();
    HashTable<Integer> hashTable = hashingService.createHashTable(8);

    Integer[] elements = new Integer[] { 14, 20, 17, 36, 22, 4 };

    assertEquals(hashTable.getItems().size(), 8);

    hashingService.insert(hashTable, elements);

    assertEquals(hashingService.toString(hashTable),
        "[0]->null,[1]->17->null,[2]->null,[3]->null,[4]->4->36->20->null,[5]->null,[6]->22->14->null,[7]->null");
  }

  @Test
  void insertAndRemoveElements() {
    IHashing<Integer> hashingService = new HashList<Integer>();
    HashTable<Integer> hashTable = hashingService.createHashTable(8);

    assertEquals(hashTable.getItems().size(), 8);

    assertEquals(hashingService.toString(hashTable),
        "[0]->null,[1]->null,[2]->null,[3]->null,[4]->null,[5]->null,[6]->null,[7]->null");

    assertTrue(hashingService.insert(hashTable, 14));
    assertEquals(hashingService.toString(hashTable),
        "[0]->null,[1]->null,[2]->null,[3]->null,[4]->null,[5]->null,[6]->14->null,[7]->null");

    assertTrue(hashingService.insert(hashTable, 20));
    assertEquals(hashingService.toString(hashTable),
        "[0]->null,[1]->null,[2]->null,[3]->null,[4]->20->null,[5]->null,[6]->14->null,[7]->null");

    assertTrue(hashingService.insert(hashTable, 17));
    assertEquals(hashingService.toString(hashTable),
        "[0]->null,[1]->17->null,[2]->null,[3]->null,[4]->20->null,[5]->null,[6]->14->null,[7]->null");

    assertTrue(hashingService.insert(hashTable, 36));
    assertEquals(hashingService.toString(hashTable),
        "[0]->null,[1]->17->null,[2]->null,[3]->null,[4]->36->20->null,[5]->null,[6]->14->null,[7]->null");

    assertTrue(hashingService.insert(hashTable, 22));
    assertEquals(hashingService.toString(hashTable),
        "[0]->null,[1]->17->null,[2]->null,[3]->null,[4]->36->20->null,[5]->null,[6]->22->14->null,[7]->null");

    assertTrue(hashingService.insert(hashTable, 4));
    assertEquals(hashingService.toString(hashTable),
        "[0]->null,[1]->17->null,[2]->null,[3]->null,[4]->4->36->20->null,[5]->null,[6]->22->14->null,[7]->null");

    assertTrue(hashingService.remove(hashTable, 17));
    assertEquals(hashingService.toString(hashTable),
        "[0]->null,[1]->null,[2]->null,[3]->null,[4]->4->36->20->null,[5]->null,[6]->22->14->null,[7]->null");

    assertTrue(hashingService.remove(hashTable, 20));
    assertEquals(hashingService.toString(hashTable),
        "[0]->null,[1]->null,[2]->null,[3]->null,[4]->4->36->null,[5]->null,[6]->22->14->null,[7]->null");

    assertTrue(hashingService.remove(hashTable, 14));
    assertEquals(hashingService.toString(hashTable),
        "[0]->null,[1]->null,[2]->null,[3]->null,[4]->4->36->null,[5]->null,[6]->22->null,[7]->null");

    assertTrue(hashingService.remove(hashTable, 22));
    assertEquals(hashingService.toString(hashTable),
        "[0]->null,[1]->null,[2]->null,[3]->null,[4]->4->36->null,[5]->null,[6]->null,[7]->null");

    assertTrue(hashingService.insert(hashTable, 20));
    assertEquals(hashingService.toString(hashTable),
        "[0]->null,[1]->null,[2]->null,[3]->null,[4]->20->4->36->null,[5]->null,[6]->null,[7]->null");
  }

  @Test
  void findElements() {
    IHashing<Integer> hashingService = new HashList<Integer>();
    HashTable<Integer> hashTable = hashingService.createHashTable(8);

    Integer[] elements = new Integer[] { 14, 20, 17, 36, 22, 4 };

    assertEquals(hashTable.getItems().size(), 8);

    hashingService.insert(hashTable, elements);

    assertTrue(hashingService.remove(hashTable, 17));
    assertTrue(hashingService.remove(hashTable, 20));

    assertEquals(hashingService.toString(hashTable),
        "[0]->null,[1]->null,[2]->null,[3]->null,[4]->4->36->null,[5]->null,[6]->22->14->null,[7]->null");

    Integer value = hashingService.find(hashTable, 4);
    assertEquals(value, 4);

    value = hashingService.find(hashTable, 36);
    assertEquals(value, 36);

    value = hashingService.find(hashTable, 22);
    assertEquals(value, 22);

    value = hashingService.find(hashTable, 14);
    assertEquals(value, 14);

    value = hashingService.find(hashTable, 17);
    assertNull(value);

    value = hashingService.find(hashTable, 20);
    assertNull(value);
  }

}
