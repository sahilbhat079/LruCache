package LruCache;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class LruCache<T,S> {
    private final int capacity;
    Map<T, S> store;
    LinkedList<T> order;
//    we can  create a lock object so that concurrent access is maintained
    public LruCache(int capacity) {
        if(capacity<=-1){
            throw new RuntimeException("Capacity Cannot be negative");
        }
        this.capacity = capacity;
        store = new ConcurrentHashMap<>(capacity);
        order = new LinkedList<>();
    }


    //two methods put and  get
    public synchronized void put(T key, S value) {
        // first check whether there is capacity or not
        //  we need to check does the value exists  or not if yes we need to reorder
        // if not we need to remove the least accessed

        if (store.size() == this.capacity) {
            //first get the key from the order list
            T tempKey = order.removeFirst();
            store.remove(tempKey);
        }

        if (store.containsKey(key)) {
            store.put(key, value);   // update value
            order.remove(key);       // remove old position
            order.addLast(key);      // reinsert as most recent
            return;
        }

        //put first in the store and the in the order
        store.put(key,value);
        order.add(key);

}




    public synchronized S get(T key){
/*      for this we need to check if the key is there if yes then reorder in the order list and return;
        for reorder first get then remove and add tho the last*/
       S value = store.get(key);
        if (value != null) {
            order.remove(key);
           order.addLast(key);
        }
        return value;

    }



    void print(){
        store.entrySet().forEach(System.out::println);
    }



}
