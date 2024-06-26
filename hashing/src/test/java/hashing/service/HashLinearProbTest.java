package hashing.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import hashing.model.HashTable;

public class HashLinearProbTest {
  @Test
  void createANewHashTable() {
    IHashing<Integer> hashingService = new HashLinearProb<Integer>();
    HashTable<Integer> hashTable = hashingService.createHashTable(8);

    assertEquals(hashTable.getItems().size(), 8);

    assertEquals(hashingService.toString(hashTable),
        "[0]FREE:,[1]FREE:,[2]FREE:,[3]FREE:,[4]FREE:,[5]FREE:,[6]FREE:,[7]FREE:");
  }

  @Test
  void insertElements() {
    IHashing<Integer> hashingService = new HashLinearProb<Integer>();
    HashTable<Integer> hashTable = hashingService.createHashTable(8);

    Integer[] elements = new Integer[] { 16, 23, 41, 25, 39 };

    assertEquals(hashTable.getItems().size(), 8);

    hashingService.insert(hashTable, elements);

    assertEquals(hashingService.toString(hashTable),
        "[0]BUSY:16,[1]BUSY:41,[2]BUSY:25,[3]BUSY:39,[4]FREE:,[5]FREE:,[6]FREE:,[7]BUSY:23");
  }

  @Test
  void insertAndRemoveElements() {
    IHashing<Integer> hashingService = new HashLinearProb<Integer>();
    HashTable<Integer> hashTable = hashingService.createHashTable(8);

    assertEquals(hashTable.getItems().size(), 8);

    assertEquals(hashingService.toString(hashTable),
        "[0]FREE:,[1]FREE:,[2]FREE:,[3]FREE:,[4]FREE:,[5]FREE:,[6]FREE:,[7]FREE:");

    assertTrue(hashingService.insert(hashTable, 16));
    assertEquals(hashingService.toString(hashTable),
        "[0]BUSY:16,[1]FREE:,[2]FREE:,[3]FREE:,[4]FREE:,[5]FREE:,[6]FREE:,[7]FREE:");

    assertTrue(hashingService.insert(hashTable, 23));
    assertEquals(hashingService.toString(hashTable),
        "[0]BUSY:16,[1]FREE:,[2]FREE:,[3]FREE:,[4]FREE:,[5]FREE:,[6]FREE:,[7]BUSY:23");

    assertTrue(hashingService.insert(hashTable, 41));
    assertEquals(hashingService.toString(hashTable),
        "[0]BUSY:16,[1]BUSY:41,[2]FREE:,[3]FREE:,[4]FREE:,[5]FREE:,[6]FREE:,[7]BUSY:23");

    assertTrue(hashingService.insert(hashTable, 25));
    assertEquals(hashingService.toString(hashTable),
        "[0]BUSY:16,[1]BUSY:41,[2]BUSY:25,[3]FREE:,[4]FREE:,[5]FREE:,[6]FREE:,[7]BUSY:23");

    assertTrue(hashingService.insert(hashTable, 39));
    assertEquals(hashingService.toString(hashTable),
        "[0]BUSY:16,[1]BUSY:41,[2]BUSY:25,[3]BUSY:39,[4]FREE:,[5]FREE:,[6]FREE:,[7]BUSY:23");

    assertTrue(hashingService.remove(hashTable, 41));
    assertEquals(hashingService.toString(hashTable),
        "[0]BUSY:16,[1]REMOVED:41,[2]BUSY:25,[3]BUSY:39,[4]FREE:,[5]FREE:,[6]FREE:,[7]BUSY:23");

    assertTrue(hashingService.remove(hashTable, 23));
    assertEquals(hashingService.toString(hashTable),
        "[0]BUSY:16,[1]REMOVED:41,[2]BUSY:25,[3]BUSY:39,[4]FREE:,[5]FREE:,[6]FREE:,[7]REMOVED:23");

    assertTrue(hashingService.remove(hashTable, 25));
    assertEquals(hashingService.toString(hashTable),
        "[0]BUSY:16,[1]REMOVED:41,[2]REMOVED:25,[3]BUSY:39,[4]FREE:,[5]FREE:,[6]FREE:,[7]REMOVED:23");

    assertTrue(hashingService.insert(hashTable, 34));
    assertEquals(hashingService.toString(hashTable),
        "[0]BUSY:16,[1]REMOVED:41,[2]BUSY:34,[3]BUSY:39,[4]FREE:,[5]FREE:,[6]FREE:,[7]REMOVED:23");
  }

  @Test
  void findElements() {
    IHashing<Integer> hashingService = new HashLinearProb<Integer>();
    HashTable<Integer> hashTable = hashingService.createHashTable(8);

    Integer[] elements = new Integer[] { 16, 23, 41, 25, 39 };

    assertEquals(hashTable.getItems().size(), 8);

    hashingService.insert(hashTable, elements);

    assertTrue(hashingService.remove(hashTable, 41));
    assertTrue(hashingService.remove(hashTable, 23));
    assertTrue(hashingService.remove(hashTable, 25));
    assertTrue(hashingService.insert(hashTable, 34));

    assertEquals(hashingService.toString(hashTable),
        "[0]BUSY:16,[1]REMOVED:41,[2]BUSY:34,[3]BUSY:39,[4]FREE:,[5]FREE:,[6]FREE:,[7]REMOVED:23");

    Integer value = hashingService.find(hashTable, 16);
    assertEquals(value, 16);

    value = hashingService.find(hashTable, 41);
    assertNull(value);

    value = hashingService.find(hashTable, 34);
    assertEquals(value, 34);

    value = hashingService.find(hashTable, 39);
    assertEquals(value, 39);

    value = hashingService.find(hashTable, 23);
    assertNull(value);
  }
}
