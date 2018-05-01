package com.simonecavazzoni.algraph.model;

import com.simonecavazzoni.algraph.ui.PriorityItemUI;

/** This class is an implementation of a generic item of PriorityQueue.
 *  This class expose the methods to change the attributes of PriorityItem.
 * @param <T>
 */
public class PriorityItem<T> {

    private T item;
    private int priority;
    private PriorityItemUI<T> priorityItemUI;

    public PriorityItem(){
        priorityItemUI = new PriorityItemUI<>();
    }

    public PriorityItem(T object,int priority){
        setItem(object);
        this.priority = priority;
        priorityItemUI = new PriorityItemUI<>(this);
    }

    public PriorityItem(T object){
        setItem(object);
    }

    /**
     * @return  T This returns the generic item
     */
    public T getItem() {
        return item;
    }

    /**
     * @param item  This sets the item
     */
    public void setItem(T item) {
        this.item = item;
    }

    /**
     * @return This returns the item priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * @param priority  This updates the item priority
     */
    public void setPriority(int priority) {
        this.priority = priority;
        this.priorityItemUI.updatePriorityLabel();
    }

    /**
     * @return  PriorityItemUI This returns the object UI
     */
    public PriorityItemUI<T> getPriorityItemUI(){
        return priorityItemUI;
    }

    @Override
    public boolean equals(Object obj) {
        if(!this.getClass().equals(obj.getClass())){
            return false;
        }

        if(obj instanceof PriorityItem){
            PriorityItem item = (PriorityItem) obj;
            return this.item.equals(item.getItem());
        }

        return false;

    }
}
