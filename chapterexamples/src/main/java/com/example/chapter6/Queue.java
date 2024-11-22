package com.example.chapter6;

public class Queue {
    
    private Item[] items;
    private int maxCount;
    private int queueCount = 0;

    public Queue(int numItems) {
        this.items = new Item[numItems];
        this.maxCount = numItems;
    }

    public Queue() {
        this(10);
    }

    private void resizeItemsArray() {
        // increase de max number of items by 5
        maxCount = maxCount + 5;

        // instantiate larger items array
        Item[] newArray = new Item[maxCount];

        // copy current items array to new
        for (int count = 0; count < queueCount; count++) {
            newArray[count] = items[count];
        }

        // override current items array with new array
        items = newArray;
    }

    public Item getFront() {
        if (queueCount > 0) {
            return items[0];
        } else {
            return null;
        }
    }

    public Item getBack() {
        if (queueCount > 0) {
            return items[queueCount - 1];
        } else {
            return null;
        }
    }

    public void enqueue(Item item) {
        if (queueCount + 1 > maxCount) {
            resizeItemsArray();
        }

        queueCount++;
        items[queueCount - 1] = item;
    }

    public Item dequeue() {
        if (queueCount == 0) {
            return null;
        } else {
            Item returnVal = getFront();

            // move all other items down
            for (int counter = 1; counter < queueCount; counter++) {
                items[counter - 1] = items[counter];
            }

            queueCount--;

            return returnVal;
        }
    }

    public int getQueueCount() {
        return queueCount;
    }

    public String toString() {
        StringBuilder returnVal = new StringBuilder("\n");

        for (int counter = 0; counter < queueCount; counter++) {
            returnVal.append(counter);
            returnVal.append(" - ");
            returnVal.append(items[counter].getName());
            returnVal.append("\n");
        }

        return returnVal.toString();
    }
}
