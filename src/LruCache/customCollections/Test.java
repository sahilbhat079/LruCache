package LruCache.customCollections;

public class Test {
    public static void main(String[] args) {
//        LinkedList l1=new LinkedList();
//        l1.add(1);
//        l1.add(2);
//        l1.removeFirst();
//        l1.remove(2);
//        l1.add(3);
//        l1.add(4);
//        l1.add(5);
//        l1.add(6);
//        l1.print();


        LruCache lru=new LruCache(3);
        lru.put(1,"a");
        lru.put(2,"b");
        lru.put(3,"c");
        lru.put(4,"e");
        lru.put(6,"f");



        System.out.println(lru.get(3));
        lru.put(5,"g");
        System.out.println(lru.get(4));
        lru.print();




    }
}
