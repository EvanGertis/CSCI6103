import java.util.*;

/*
    name: Evan Getis
    date: 04/02
    program: clone and equals
*/
/*
    implement clone and equals for the Heap class.
    input: 
    heap1: 6
    heap2: 6
    equals? true
    heap1: 5
    heap2: 6
    equals? false
*/
public class Heap<E> implements Cloneable {
  private java.util.ArrayList<E> list = new java.util.ArrayList<>();
  private java.util.Comparator<? super E> c;

  /** Create a default heap using a natural order for comparison */
  public Heap() {
    this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
  }

  /** Create a heap with a specified comparator */
  public Heap(java.util.Comparator<E> c) {
    this.c = c;
  }

  /** Create a heap from an array of objects */
  public Heap(E[] objects) {
    this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
    for (int i = 0; i < objects.length; i++)
      add(objects[i]);
  }

  /** Add a new object into the heap */
  public void add(E newObject) {
    list.add(newObject); // Append to the heap
    int currentIndex = list.size() - 1; // The index of the last node

    while (currentIndex > 0) {
      int parentIndex = (currentIndex - 1) / 2;
      // Swap if the current object is greater than its parent
      if (c.compare(list.get(currentIndex),
          list.get(parentIndex)) > 0) {
        E temp = list.get(currentIndex);
        list.set(currentIndex, list.get(parentIndex));
        list.set(parentIndex, temp);
      } else
        break; // the tree is a heap now

      currentIndex = parentIndex;
    }
  }

  /** Remove the root from the heap */
  public E remove() {
    if (list.size() == 0)
      return null;

    E removedObject = list.get(0);
    list.set(0, list.get(list.size() - 1));
    list.remove(list.size() - 1);

    int currentIndex = 0;
    while (currentIndex < list.size()) {
      int leftChildIndex = 2 * currentIndex + 1;
      int rightChildIndex = 2 * currentIndex + 2;

      // Find the maximum between two children
      if (leftChildIndex >= list.size())
        break; // The tree is a heap
      int maxIndex = leftChildIndex;
      if (rightChildIndex < list.size()) {
        if (c.compare(list.get(maxIndex),
            list.get(rightChildIndex)) < 0) {
          maxIndex = rightChildIndex;
        }
      }

      // Swap if the current node is less than the maximum
      if (c.compare(list.get(currentIndex),
          list.get(maxIndex)) < 0) {
        E temp = list.get(maxIndex);
        list.set(maxIndex, list.get(currentIndex));
        list.set(currentIndex, temp);
        currentIndex = maxIndex;
      } else
        break; // The tree is a heap
    }

    return removedObject;
  }

  /** Get the number of nodes in the tree */
  public int getSize() {
    return list.size();
  }

  /** Return true if heap is empty */
  public boolean isEmpty() {
    return list.size() == 0;
  }

// BEGIN REVEL SUBMISSION
@Override /** Override the proctected clone method defined in
the Object class, and stregthen its accessibility */
public Object clone() {
    // Method one of performing clone
    // Heap<E> heap = new Heap();
    // heap.list = new java.util.ArrayList(list);
    // return heap;
    try {
      Heap<E> newHeap = (Heap<E>)(super.clone());
      // cast to an ArrayList
      newHeap.list    = (java.util.ArrayList<E>)(list.clone());
      return new Heap<>();
    }
    catch (CloneNotSupportedException ex) {
      return null;
    }
}

@Override /** Override the equals method in the Object cl */
public boolean equals(Object other) {
    // Alternative method
    // if (list.size() != ((Heap<E>) (other)).getSize())
    //     return false;

    // for (int i = 0; i < list.size(); i++) {
    //     if (list.get(i) != ((Heap<E>) (other)).list.get(i))
    //         return false;
    // }
    // return true;
    // Cast to Heap
    return this.list.equals(((Heap<E>)other).list);
}
// END REVEL SUBMISSION
}
