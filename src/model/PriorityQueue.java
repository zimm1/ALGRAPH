package model;

import java.util.ArrayList;

public class PriorityQueue<T> {

    private ArrayList<PriorityItem<T>> list;

    public PriorityQueue(){
        list = new ArrayList<>();
    }

    public void push(T object,int priority){
        PriorityItem<T> priorityItem = new PriorityItem<>(object,priority);
        this.list.add(priorityItem);
    }

    public PriorityItem pop(){
        PriorityItem min = searchMin();
        this.list.remove(min);
        return searchMin();
    }

    public boolean isEmpty(){
        return this.list.isEmpty();
    }

    private PriorityItem searchMin(){
        PriorityItem min = null;
        for(PriorityItem item:this.list){
            if(min == null){
                min = item;
            }
            else if(item.getPriority() < min.getPriority()){
                min = item;
            }
        }
        return min;
    }


}
