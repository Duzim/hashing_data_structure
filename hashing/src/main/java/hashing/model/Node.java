package hashing.model;

public class Node<T extends Comparable<T>> {

  private NodeStatus status;
  private T value;
  private Node<T> next;

  public Node() {
    this.status = NodeStatus.FREE;
  }

  public NodeStatus getStatus() {
    return status;
  }

  public void setStatus(NodeStatus status) {
    this.status = status;
  }

  public T getValue() {
    return value;
  }

  public void setValue(T value) {
    this.value = value;
  }

  public Node<T> getNext() {
    return next;
  }

  public void setNext(Node<T> next) {
    this.next = next;
  }

  @Override
  public String toString() {
    if (this.value == null) {
      return String.format("%s:", this.status);
    } else {
      return String.format("%s:%s", this.status, this.value.toString());
    }
  }

}
