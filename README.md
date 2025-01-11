# SortedLinkedList

A custom implementation of a LinkedList that maintains a sorted order of elements. The sorting order is determined at the time of creation and can either be ascending or descending.

## Features:

Automatically sorts elements when they are added to the list.

### Sorting Order:

Supports both ascending and descending sorting orders.
The sorting order is specified during the creation of the list using the Sort enum (`ASCENDING` or `DESCENDING`).

### Overrides:
Methods that could break the sorted order (`addFirst`, `addLast`, `add(index, element)`) are or redirected to `add(T)` for consistency.

Methods that add multiple elements (`addAll(collection)`, `addAll(index, collection)`) call `add(T)` for each element to ensure sorted order.

### Complexity:
The `add(T)` method uses iterators for efficient traversal, ensuring elements are added in the correct position without redundant operations.

The `add(T)` method has a time complexity of `O(n)`, where `n` is the size of the list, as it iterates through the list to find the correct position for insertion.

## Supported Operations:

| Method                                       | Behavior
|----------------------------------------------|---
| add(T element)	                           | Adds an element while maintaining the sorted order.
| addFirst(T element)	                       | Redirects to add(T) to preserve order.
| addLast(T element)	                       | Redirects to add(T) to preserve order.
| add(int index, T element)	                   | Redirects to add(T) to preserve order.
| addAll(Collection<? extends T> c)	           | Adds all elements from the collection while maintaining the sorted order.
| addAll(int index, Collection<? extends T> c) | Redirects to addAll(Collection) to preserve order.

## Usage:
1. Create a SortedLinkedList:
   ```java
   SortedLinkedList<Integer> ascendingList = new SortedLinkedList<>(SortedLinkedList.Sort.ASCENDING);
   SortedLinkedList<Integer> descendingList = new SortedLinkedList<>(SortedLinkedList.Sort.DESCENDING);
   ```
2. Add Elements:
    ```java
    ascendingList.add(10);
    ascendingList.add(5);
    ascendingList.add(15);
    System.out.println(ascendingList); // Output: [5, 10, 15]
    
    descendingList.add(10);
    descendingList.add(5);
    descendingList.add(15);
    System.out.println(descendingList); // Output: [15, 10, 5]
    ```
3. Add Multiple Elements:

   ```java
   ascendingList.addAll(List.of(8, 3, 12));
   System.out.println(ascendingList); // Output: [3, 5, 8, 10, 12, 15]
   ```
