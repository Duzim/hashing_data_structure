package hashing.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import hashing.model.HashTable;

public class HashQuadraticProbTest {
  @Test
  void createANewHashTable() {
    IHashing<Integer> hashingService = new HashQuadraticProb<Integer>();
    HashTable<Integer> hashTable = hashingService.createHashTable(8);

    assertEquals(hashTable.getItems().size(), 8);

    assertEquals(hashingService.toString(hashTable),
        "[0]FREE:,[1]FREE:,[2]FREE:,[3]FREE:,[4]FREE:,[5]FREE:,[6]FREE:,[7]FREE:");
  }

  @Test
  void insertElements() {
    IHashing<Integer> hashingService = new HashQuadraticProb<Integer>();
    HashTable<Integer> hashTable = hashingService.createHashTable(8);

    Integer[] elements = new Integer[] { 12, 20, 28, 36 };

    assertEquals(hashTable.getItems().size(), 8);

    hashingService.insert(hashTable, elements);

    assertEquals(hashingService.toString(hashTable),
        "[0]FREE:,[1]FREE:,[2]BUSY:36,[3]FREE:,[4]BUSY:12,[5]BUSY:20,[6]FREE:,[7]BUSY:28");
  }

  @Test
  void insertAndRemoveElements() {
    IHashing<Integer> hashingService = new HashQuadraticProb<Integer>();
    HashTable<Integer> hashTable = hashingService.createHashTable(8);

    assertEquals(hashTable.getItems().size(), 8);

    assertEquals(hashingService.toString(hashTable),
        "[0]FREE:,[1]FREE:,[2]FREE:,[3]FREE:,[4]FREE:,[5]FREE:,[6]FREE:,[7]FREE:");

    assertTrue(hashingService.insert(hashTable, 12));
    assertEquals(hashingService.toString(hashTable),
        "[0]FREE:,[1]FREE:,[2]FREE:,[3]FREE:,[4]BUSY:12,[5]FREE:,[6]FREE:,[7]FREE:");

    assertTrue(hashingService.insert(hashTable, 20));
    assertEquals(hashingService.toString(hashTable),
        "[0]FREE:,[1]FREE:,[2]FREE:,[3]FREE:,[4]BUSY:12,[5]BUSY:20,[6]FREE:,[7]FREE:");

    assertTrue(hashingService.insert(hashTable, 28));
    assertEquals(hashingService.toString(hashTable),
        "[0]FREE:,[1]FREE:,[2]FREE:,[3]FREE:,[4]BUSY:12,[5]BUSY:20,[6]FREE:,[7]BUSY:28");

    assertTrue(hashingService.insert(hashTable, 36));
    assertEquals(hashingService.toString(hashTable),
        "[0]FREE:,[1]FREE:,[2]BUSY:36,[3]FREE:,[4]BUSY:12,[5]BUSY:20,[6]FREE:,[7]BUSY:28");

    assertTrue(hashingService.remove(hashTable, 12));
    assertEquals(hashingService.toString(hashTable),
        "[0]FREE:,[1]FREE:,[2]BUSY:36,[3]FREE:,[4]REMOVED:12,[5]BUSY:20,[6]FREE:,[7]BUSY:28");

    assertTrue(hashingService.remove(hashTable, 20));
    assertEquals(hashingService.toString(hashTable),
        "[0]FREE:,[1]FREE:,[2]BUSY:36,[3]FREE:,[4]REMOVED:12,[5]REMOVED:20,[6]FREE:,[7]BUSY:28");

    assertTrue(hashingService.insert(hashTable, 44));
    assertEquals(hashingService.toString(hashTable),
        "[0]FREE:,[1]FREE:,[2]BUSY:36,[3]FREE:,[4]BUSY:44,[5]REMOVED:20,[6]FREE:,[7]BUSY:28");
  }

  @Test
  void findElements() {
    IHashing<Integer> hashingService = new HashQuadraticProb<Integer>();
    HashTable<Integer> hashTable = hashingService.createHashTable(8);

    Integer[] elements = new Integer[] { 12, 20, 28, 36 };

    assertEquals(hashTable.getItems().size(), 8);

    hashingService.insert(hashTable, elements);

    assertTrue(hashingService.remove(hashTable, 12));
    assertTrue(hashingService.remove(hashTable, 20));
    assertTrue(hashingService.insert(hashTable, 44));

    assertEquals(hashingService.toString(hashTable),
        "[0]FREE:,[1]FREE:,[2]BUSY:36,[3]FREE:,[4]BUSY:44,[5]REMOVED:20,[6]FREE:,[7]BUSY:28");

    Integer value = hashingService.find(hashTable, 36);
    assertEquals(value, 36);

    value = hashingService.find(hashTable, 44);
    assertEquals(value, 44);

    value = hashingService.find(hashTable, 20);
    assertNull(value);

    value = hashingService.find(hashTable, 28);
    assertEquals(value, 28);
  }
}
