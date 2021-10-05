package com.narae.collection.list;

import java.util.Optional;

public class MyLinkedList<E> {
    /**
     * Return a String of all the data in the linked list
     *
     * @return
     */
    @Override
    public String toString() {
        String allData = "";
        LinkedNode<E> node = firstNode;
        for (int i = 0; i < size; i++) {
            String dataString = (node.data == null) ? "" : node.data.toString();
            allData += String.format("%d-th data: %s \n", i, dataString);
            node = node.nextNode;
        }
        return allData;
    }

    private class LinkedNode<E> {
        E data;
        LinkedNode<E> prevNode;
        LinkedNode<E> nextNode;

        LinkedNode(E data) {
            this.data = data;
        }
    }

    private LinkedNode<E> firstNode;
    private LinkedNode<E> lastNode;
    private int size;

    /**
     * Return the size of the linked list
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * Add new data at the end of the linked list
     *
     * @param data
     */
    public void add(E data) {
        LinkedNode<E> newData = new LinkedNode<E>(data);
        if (firstNode == null) {
            firstNode = newData;
            lastNode = newData;
        } else {
            lastNode.nextNode = newData;
            LinkedNode<E> prevLastNode = lastNode;
            lastNode = newData;
            lastNode.prevNode = prevLastNode;
        }
        size++;
    }

    /**
     * Add new data at the index-th node in the linked list
     * Data not added if the index < 0
     *
     * @param data
     * @param index
     */
    public void add(E data, int index) {
        if (index >= size) {
            while (index > size) {
                add(null);
            }
            add(data);
        } else {
            LinkedNode<E> newNode = new LinkedNode<E>(data);
            LinkedNode<E> indexthNode = firstNode;
            for (int i = 0; i <= index; i++) {
                if (index == 0) {
                    newNode.nextNode = indexthNode;
                    firstNode = newNode;
                    size++;
                } else if (i == index - 1) {
                    LinkedNode<E> prevIndexthNode = indexthNode;
                    LinkedNode<E> prevNextNode = indexthNode.nextNode;
                    indexthNode.nextNode = newNode;
                    newNode.prevNode = prevIndexthNode;
                    newNode.nextNode = prevNextNode;
                    size++;
                }
                indexthNode = indexthNode.nextNode;
            }
        }
    }

    /**
     * Optimised add new data at the index-th node in the linked list by checking if the index is close to 0 or the last one
     * Data not added if the index < 0
     *
     * @param data
     * @param index
     */
    public void optimisedAdd(E data, int index) {
        if (index == size) {
            add(data);
        } else if (index > size) {
            add(data, index);
        } else if ((size - 2 * index) >= 0) { // (size -1 - index) + (0 - index) > 0 then closer from the first node
            LinkedNode<E> newNode = new LinkedNode<E>(data);
            LinkedNode<E> indexthNode = firstNode;
            for (int i = 0; i <= index; i++) {
                if (index == 0) {
                    newNode.nextNode = indexthNode;
                    firstNode = newNode;
                    size++;
                } else if (i == index - 1) {
                    LinkedNode<E> prevNextNode = indexthNode.nextNode;
                    newNode.prevNode = indexthNode;
                    indexthNode.nextNode = newNode;
                    newNode.nextNode = prevNextNode;
                    prevNextNode.prevNode = newNode;
                    size++;
                }
                indexthNode = indexthNode.nextNode;
            }
        } else {
            LinkedNode<E> newNode = new LinkedNode<E>(data);
            LinkedNode<E> indexthNode = lastNode;
            for (int i = size - 1; i >= index; i--) {
                if (i == index) {
                    LinkedNode<E> prevIndexthNode = indexthNode.prevNode;
                    newNode.prevNode = prevIndexthNode;
                    prevIndexthNode.nextNode = newNode;
                    indexthNode.prevNode = newNode;
                    newNode.nextNode = indexthNode;
                    size++;
                }
                indexthNode = indexthNode.prevNode;
            }
        }
    }

    /**
     * Optimised remove data at the index-th node in the linked list by checking if the index is close to 0 or the last one
     *
     * @param index
     */
    public void optimisedRemove(int index) {
        if (index >= 0 && index < size) {
            if ((size - 2 * index) >= 0) { // (size -1 - index) + (0 - index) > 0 then closer from the first node
                LinkedNode<E> indexthNode = firstNode;
                for (int i = 0; i <= index; i++) {
                    if (index == 0) {
                        firstNode = indexthNode.nextNode;
                        if (size > 1) {
                            firstNode.nextNode = indexthNode.nextNode.nextNode;
                        }
                        size--;
                    } else if (i == index - 1) {
                        LinkedNode<E> prevPrevNode = indexthNode;
                        LinkedNode<E> prevNextNode = indexthNode.nextNode.nextNode;
                        indexthNode.nextNode = prevNextNode;
                        prevNextNode.prevNode = prevPrevNode;
                        size--;
                    }
                    indexthNode = indexthNode.nextNode;
                }
            } else {
                LinkedNode<E> indexthNode = lastNode;
                for (int i = size - 1; i >= index; i--) {
                    if (i == index) {
                        LinkedNode<E> prevPrevNode = indexthNode.prevNode.prevNode;
                        LinkedNode<E> prevNextNode = indexthNode;
                        indexthNode.prevNode = prevPrevNode;
                        prevPrevNode.nextNode = prevNextNode;
                        size--;
                    }
                    indexthNode = indexthNode.prevNode;
                }
            }
        }
    }

    /**
     * Get an index-th data in the linked list
     * Return type is the generic type with java.util.Optional
     *
     * @param index
     * @return
     */
    public Optional<E> get(int index) { // slow when index goes high as it starts from the first element to the index-th
        if (index >= size || index < 0) {
            return Optional.empty();
        }
        LinkedNode<E> indexthNode = firstNode;
        for (int i = 0; i <= index; i++) {
            if (i == index) {
                return Optional.ofNullable(indexthNode.data); //index-th data
            }
            indexthNode = indexthNode.nextNode;
        }
        return Optional.empty();
    }

    /**
     * Optimised get an index-th data in the linked list by checking if the index is close to 0 or the last one
     * Return type is the generic type with java.util.Optional
     *
     * @param index
     * @return
     */
    public Optional<E> optimisedGet(int index) {
        if (index >= size || index < 0) {
            return Optional.empty();
        }
        if ((size - 2 * index) >= 0) { // (size -1 - index) + (0 - index) > 0 then closer from the first node
            LinkedNode<E> indexthNode = firstNode;
            for (int i = 0; i <= index; i++) {
                if (i == index) {
                    return Optional.ofNullable(indexthNode.data); //index-th data
                }
                indexthNode = indexthNode.nextNode;
            }
        } else {
            LinkedNode<E> indexthNode = lastNode;
            for (int i = size - 1; i >= index; i--) {
                if (i == index) {
                    return Optional.ofNullable(indexthNode.data); //index-th data
                }
                indexthNode = indexthNode.prevNode;
            }
        }
        return Optional.empty();
    }

    /**
     * Get a middle data in the linked list when the size and the final node are unknown
     * Return type is the generic type with java.util.Optional
     *
     * @return
     */
    public Optional<E> getMiddel() {
        LinkedNode<E> step1Node = firstNode;
        LinkedNode<E> step2Node = firstNode;
        while (Optional.ofNullable(step2Node.nextNode).isPresent()) {
            step1Node = step1Node.nextNode;
            step2Node = step2Node.nextNode;
            if (Optional.ofNullable(step2Node.nextNode).isPresent()) {
                step2Node = step2Node.nextNode;
            } else {
                break;
            }
        }
        return Optional.ofNullable(step1Node.data);
    }
}
