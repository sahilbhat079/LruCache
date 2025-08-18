package LruCache.customCollections;

public class LinkedList{
    Node head;
    Node tail;
    public LinkedList() {
        head=null;
        tail=null;
    }

    //add
    //remove
    //remove firs
    //add First

    public void add(int  value) {
            Node newNode = new Node(value);
            Node temp = head;
            if (head == null) {
                head = newNode;
                tail = head;
                temp = newNode;
            }
            else {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }
    }





    void remove(int value){
        Node temp=head;
        while (temp != null) {
            if (temp.value == value) {
                if (temp == head && temp == tail) { // only one node
                    head = tail = null;
                } else if (temp == head) { // remove head
                    head = head.next;
                    if (head != null) head.prev = null;
                } else if (temp == tail) { // remove tail
                    tail = tail.prev;
                    if (tail != null) tail.next = null;
                } else {
                    temp.prev.next = temp.next;
                    temp.next.prev = temp.prev;
                }
                return;
            }
            temp = temp.next;
        }
    }


    public int removeFirst(){
        Node temp=head;
        if (head == null) throw new RuntimeException("List is empty");
        int val = head.value;

        if (head == tail) { // only one node
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        return val;

    }


    public void addFirst(int key){
        Node newnode=new Node(key);
        if(head==null){
            tail=head=newnode;
        }else{
        newnode.next=head;
        head.prev=newnode;
        head=newnode;
        }
    }








    void print(){
        Node temp=head;
        while(temp!=null){
            System.out.println(temp.value);
            temp=temp.next;
        }
    }







}
