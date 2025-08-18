package LruCache.customCollections;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LruCache {
    private final int capacity;
    Map<Integer,String> store;
    LinkedList order;
    public LruCache(int capacity) {
        this.capacity = capacity;
        store = new ConcurrentHashMap<>(capacity);
        order = new LinkedList();
    }


    public synchronized void put(int key,String value) {
        // first check whether there is capacity or not
        //  we need to check does the value exists  or not if yes we need to reorder
        // if not we need to remove the least accessed

        if (store.size() == this.capacity) {
            //first get the key from the order list
            int tempKey = order.removeFirst();
            store.remove(tempKey);
        }
            // what if the key is already there we need to reorder this
            if(store.containsKey(key)) {
                order.remove(key);
//              we are  not going to remove it from the store we will override
            }


        //put first in the store and the in the order
        store.put(key,value);
        order.add(key);
    }



    public synchronized String get(int key){
/*     for this we need to check if the key is there if yes then reorder in the order list and return;
        for reorder first get then remove and add tho the last*/
        String value = store.get(key);
        if (value != null) {
            order.remove(key);
            order.add(key);
        }
        return value;

    }



    void print(){
        store.entrySet().forEach(System.out::println);
    }



}
