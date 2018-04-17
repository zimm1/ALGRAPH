package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
        PriorityItem<T> min = searchMin();
        remove(min);
        return min;
    }

    public PriorityItem<T> read(T item,int priority){
        PriorityItem<T> priorityItem = new PriorityItem<>(item,priority);
        return searchItem(priorityItem);
    }

    public PriorityItem<T> read(T item){
        PriorityItem<T> priorityItem = new PriorityItem<>(item);
        return searchItem(priorityItem);
    }

    public boolean existItem(T item){
        PriorityItem<T> priorityItem = new PriorityItem<>(item);
        return searchItem(priorityItem) != null;
    }

    public void remove(PriorityItem<T> priorityItem){
        if(priorityItem != null){
            this.list.remove(priorityItem);
        }
    }

    public void remove(T item){
        PriorityItem<T> deleteItem = read(item);
        if(deleteItem != null){
            this.list.remove(deleteItem);
        }
    }

    public boolean isEmpty(){
        return this.list.isEmpty();
    }

    public void decrease(PriorityItem<T> item,int newPriority){
        int i = searchPosItem(item);
        if(i != -1){
            this.list.get(i).setPriority(newPriority);
        }
    }

    public void decrease(T item,int priority){
        PriorityItem<T> newItem = new PriorityItem<>(item);
        int i = searchPosItem(newItem);
        if(i != -1){
            this.list.get(i).setPriority(priority);
        }
    }

    public ArrayList<PriorityItem<T>> getAll(){
        return this.list;
    }

    private PriorityItem<T> searchMin(){
        PriorityItem<T> min = null;
        for(PriorityItem<T> item:this.list){
            if(min == null){
                min = item;
            } else if(item.getPriority() < min.getPriority()){
                min = item;
            }
        }
        return min;
    }

    private PriorityItem<T> searchItem(PriorityItem<T> priorityItem){
        PriorityItem<T> searched = null;
        for(PriorityItem<T> item:this.list){
            if(priorityItem.equals(item)){
                searched = item;
            }
        }
        return searched;
    }

    private int searchPosItem(PriorityItem<T> priorityItem){
        for(int i = 0; i < this.list.size(); i++){
            if(list.get(i).equals(priorityItem)){
                return i;
            }
        }
        return -1;
    }

}
