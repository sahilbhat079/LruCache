package LruCache.customCollections;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * In This will have bucket lets say of capacity 16 if collision happens we will do linked technique to create a linked list
 * The capacity with modulo operator will give the bucket index And store in the index if there is no element and increase the size
 *
 *
 * @param <K> key that the  HashMap  will look to get value
 * @param <V> value that the Hashmap will store in the buckets
 */

public class LinkedHashMap<K,V> {
static class Entry<K,V>{
    K key;
    V value;
    Entry<K,V> next;

    public Entry(K key,V value) {
        this.value= value;
        this.key= key;
        this.next= null;
    }

    @Override
    public String toString() {
        return key.toString()+":"+value.toString();
    }
}


private Entry<K,V>[] buckets;
private int capacity;
private  int size;

    public LinkedHashMap(int capacity) {
        this.size = 0;
        this.buckets = new Entry[capacity];
        this.capacity = capacity;
    }

    /**
     *
     * @param key for which the index is calculated
     * @return index of bucket
     */
    private int getIndex(K key ){
       return Math.abs(Objects.hash(key))%capacity;
    }

    /**
     *
     * @param key key for which the index is calculated
     * @param value the value that will be stored in the map
     */
    public void  put(K key,V value){
        /*
        to insert first get the index and then check if the value is  there or not
        then add a value and if not the look for the value in the list at the bucket index if not available add at the last
                 */
        int  bucketIndex =getIndex(key);
        Entry<K,V> entry=new Entry<>(key,value);

        if(buckets[bucketIndex]==null){
            buckets[bucketIndex]=entry;
            size++;
        }else{
            /**
             * we need to check the linked entries
             * if found need to update otherwise just go to next till reach to null and insert there ;
             */
            Entry<K,V> prev=null;
            Entry<K,V> current=buckets[bucketIndex];
            while(current!=null){
                if(Objects.equals(current.key, key)){
                    current.value=value;
                    return;
                }
                prev=current;
                current=current.next;
            }
            prev.next=entry;
            size++;
        }
    }


    /**
     *
     * @param key check the key in the buckets and look whether the key is there or not
     * @return return true if key exists
     */
    public boolean containsKey(K  key){
        int bucketIndex=getIndex(key);
        Entry<K,V> temp=buckets[bucketIndex];
        if(buckets[bucketIndex]==null){
            return  false;
        }
        while(temp!=null){
            if(temp.key==key){
                return true;
            }
            temp=temp.next;
        }
        return false;
    }

    /**
     *
     * @param key remove the element in the buckets
     */
    void remove(K key){
        int bucketIndex=getIndex(key);
        Entry<K,V> entry=buckets[bucketIndex];
        Entry<K,V> prev=null;
        if(entry==null){
            return;
        }
        while (entry!=null){
            if (entry.key.equals(key)){
                if(prev==null){
                buckets[bucketIndex]=entry.next;
                }else{
                    prev.next=entry.next;
                }
                size--;
            }
            prev=entry;
            entry=entry.next;
        }

    }


    /**
     * this method returns size
     * @return int size
     */
    public int size(){
        return size;
    }


    /**
     * this method returns the value
     * @param key
     * @return
     */

    public V get(K key){
        int bucketIndex=getIndex(key);
        Entry<K,V> entry=buckets[bucketIndex];
        while(entry!=null){
            if(entry.key.equals(key)){
                return entry.value;
            }
            entry=entry.next;
        }
        return null;
    }


    /**
     * this Method returns Entry Set
     * @return Entry Set
     */
    public Set<Entry<K, V>> entrySet(){
        /**
         * we will iterate through the buckets and add the values in the Entry Set
         * also in nested loop we will add the values in the Entry Set
         */
        Set<Entry<K, V>> entrySet=new HashSet<>();
        for (Entry<K, V> entry : buckets) {
            if (entry != null) {
                Entry<K, V> temp = entry;
                while (temp != null) {
                    entrySet.add(temp);
                    temp = temp.next;
                }
            }
        }
        return entrySet;

    }

}
