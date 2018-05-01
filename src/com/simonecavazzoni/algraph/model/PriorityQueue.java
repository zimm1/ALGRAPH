package com.simonecavazzoni.algraph.model;

import java.util.ArrayList;

/** This class is a PriorityQueue implemented with unordered ArrayList.
 *  This class expose the methods to insert/delete/update/search PriorityItem&ltT&gt
 * @param <T>
 */
public class PriorityQueue<T> {

    private ArrayList<PriorityItem<T>> list;

    public PriorityQueue(){
        list = new ArrayList<>();
    }

    /**
     * @param object    This is the generic item to insert in the queue
     * @param priority   This is the priority of the new item
     */
    public void push(T object,int priority){
        PriorityItem<T> priorityItem = new PriorityItem<>(object,priority);
        this.list.add(priorityItem);
    }

    /**
     * @return  PriorityItem&ltT&gt  This returns the PriorityItem with the lower priority
     */
    public PriorityItem<T> pop(){
        PriorityItem<T> min = searchMin();
        remove(min);
        return min;
    }

    /**
     * @param item  This is the item to search in the queue
     * @return  PriorityItem&ltT&gt    This is the object searched
     */
    public PriorityItem<T> read(T item){
        PriorityItem<T> priorityItem = new PriorityItem<>(item);
        return searchItem(priorityItem);
    }

    /**
     * @param item  This is the item to search in the queue
     * @return  boolean This returns true only if the item is in the queue
     */
    public boolean existItem(T item){
        PriorityItem<T> priorityItem = new PriorityItem<>(item);
        return searchItem(priorityItem) != null;
    }

    /**
     * @param priorityItem  The priorityItem to remove in the queue
     */
    public void remove(PriorityItem<T> priorityItem){
        if(priorityItem != null){
            this.list.remove(priorityItem);
        }
    }

    /**
     * @param item  The item to remove in the queue
     */
    public void remove(T item){
        PriorityItem<T> deleteItem = read(item);
        if(deleteItem != null){
            this.list.remove(deleteItem);
        }
    }

    /**
     * @return  boolean This returns true only if the queue isn't empty
     */
    public boolean isEmpty(){
        return this.list.isEmpty();
    }

    public void update(PriorityItem<T> item, int newPriority){
        int i = searchPosItem(item);
        if(i != -1){
            this.list.get(i).setPriority(newPriority);
        }
    }

    /**
     * @param item  This is the item to search in the queue
     * @param priority  This is the new priority of the item searched
     */
    public void update(T item, int priority){
        PriorityItem<T> newItem = new PriorityItem<>(item);
        int i = searchPosItem(newItem);
        if(i != -1){
            this.list.get(i).setPriority(priority);
        }
    }

    /**
     * @return  ArrayList&ltPriorityItem&ltT&gt&gt  This returns the queue
     */
    public ArrayList<PriorityItem<T>> getAll(){
        return this.list;
    }

    /**
     * This remove all PriorityItem&ltT&gt in queue
     */
    public void clear() {
        list.clear();
    }

    /**
     * @return  PriorityItem&ltT&gt This returns the PriorityItem&ltT&gt with the lower priority
     */
    private PriorityItem<T> searchMin(){
        PriorityItem<T> min = null;
        for(PriorityItem<T> item : list){
            if (min == null || item.getPriority() < min.getPriority()){
                min = item;
            }
        }
        return min;
    }

    /**
     * @param priorityItem  This is the item to search in the queue
     * @return  PriorityItem&ltT&gt This returns the PriorityItem searched
     */
    private PriorityItem<T> searchItem(PriorityItem<T> priorityItem){
        PriorityItem<T> searched = null;
        for(PriorityItem<T> item:this.list){
            if(priorityItem.equals(item)){
                searched = item;
            }
        }
        return searched;
    }

    /**
     * @param priorityItem  This is the item to search in the queue
     * @return  int This returns the position in the queue of the item searched
     */
    private int searchPosItem(PriorityItem<T> priorityItem){
        for(int i = 0; i < this.list.size(); i++){
            if(list.get(i).equals(priorityItem)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("S = { ");
        int numItems = 0;
        for(PriorityItem item:list){
            if(item.getItem() instanceof Node){
                numItems++;
                Node node = (Node) item.getItem();
                stringBuilder.append(node.getLabel());
                if(numItems < list.size()){
                    stringBuilder.append(" ,");
                }
            }
        }

        stringBuilder.append(" }");

        return stringBuilder.toString();
    }
}
