package com.simonecavazzoni.algraph.model;

import com.simonecavazzoni.algraph.ui.PriorityItemUI;

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

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
        this.priorityItemUI.updatePriorityLabel();
    }

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
