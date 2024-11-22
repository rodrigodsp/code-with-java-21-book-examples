package com.example.chapter6;

public class LinkedList {
    
    private Item firstItem;
    private Item lastItem;
    private int listCount = 0;

    public LinkedList() {        
    }

    public LinkedList(Item item) {
        addItem(item);
    }

    private void setWithOneItem(Item newItem) {
        firstItem = newItem;
        lastItem = newItem;
    }

    public void addItem(Item newItem) {
        
        if(listCount == 0) {
            setWithOneItem(newItem);
        } else {
            newItem.setNextItem(firstItem);
            firstItem.setPrevItem(newItem);
            firstItem = newItem;
        }

        listCount++;
    }

    public Item findItemByName(String name) {

        Item currentItem = firstItem;

        while (currentItem != null) {
            if (currentItem.getName().equals(name)) {
                // found!
                return currentItem;
            }

            currentItem = currentItem.getNextItem();
        }

        return null;
    }

    public boolean removeItemByName(String name) {

        Item itemFound = findItemByName(name);

        if (itemFound != null) {
            Item previous = itemFound.getPrevItem();
            Item next = itemFound.getNextItem();

            previous.setNextItem(next);
            next.setPrevItem(previous);

            listCount--;
            return true;
        }
        
        return false;
    }

    public Item getFirstItem() {
        return firstItem;
    }

    public Item getLastItem() {
        return lastItem;
    }

    public int getListCount() {
        return listCount;
    }

    public String toString() {

        StringBuilder returnVal = new StringBuilder("\n");

        Item item = firstItem;

        while (item != null) {
            returnVal.append(item.getName());
            returnVal.append("\n");
            item = item.getNextItem();
        }

        return returnVal.toString();
    }
}
