package LruCache;

public class Main {

    public static void main(String[] args) {
        LruCache<Integer,String>  lru=new LruCache<Integer, String>(3);
        lru.put(1,"a");
        lru.put(2,"b");
        lru.put(3,"c");
        lru.put(4,"e");
        lru.put(6,"f");



        lru.put(3,"c");
        System.out.println(lru.get(4));
        lru.put(5,"g");
//      System.out.println(lru.get(3));
        lru.print();


    }


}
