package LruCache;

public class LruCacheClient {
    public static void testCache(){
        LruCache<Integer,String>  lru=new LruCache<Integer, String>(3);
        lru.put(1,"a");
        lru.put(2,"b");
        lru.put(3,"c");
        lru.put(4,"d");


        lru.put(5,"d");
         lru.put(6,"b");
         lru.print();


    }


    public static void main(String[] args) {
        testCache();
    }
}
