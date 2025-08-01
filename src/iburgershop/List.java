package iburgershop;

/**
 *
 * @author Ravindu Yasith
 */
public class List {
    private Node first;
    
    public void addFirst(Burger burger){
        Node n1 = new Node(burger);
        n1.next = first;
        first = n1;
    }
    
    public void addLast(Burger burger){
        if(first==null){
            addFirst(burger);
        }else{
            Node n1 = new Node(burger);
            Node temp = first;
            while(temp.next!=null){
                temp = temp.next;
            }
            temp.next = n1;
        }
    }
    
    public void add(Burger burger){
        addLast(burger);
    }
    
    public boolean add(int index, Burger burger){
        if(index>= 0&& index<=size()){
            if(index==0){
                addFirst(burger);
            }else{
                Node n1 = new Node(burger);
                Node temp = first;
                int i=0;
                while(i<index-1){
                    i++;
                    temp = temp.next;
                }
                n1.next = temp.next;
                temp.next = n1;
            }
            return true;
        }
        return false;
    }
    
    public int indexOf(Burger burger){
        Node temp = first;
        int index = 0;
        while(temp!=null){
            if(temp.burger.equals(burger)){
                return index;
            }
            index++;
            temp = temp.next;
        }
        return -1;
    }
    
    public boolean contains(Burger burger){
        return indexOf(burger)!=-1;
    }
    
    public boolean remove(Burger burger){
        return remove(indexOf(burger))!=null;
    }
    
    public Burger get(int index){
        if(index>=0 && index<size() && !isEmpty()){
            if(index==0){
                return first.burger;
            }else{
                int i =0;
                Node temp = first;
                while(i<index){
                    i++;
                    temp = temp.next;
                }
                return temp.burger;
            }
        }
        return null;
    }
    
    public Burger remove(int index){
        if(index>=0 && index<size() && !isEmpty()){
            if(index == 0){
                return removeFirst();
            }else{
                int i = 0;
                Node temp = first;
                while(i<index-1){
                    i++;
                    temp = temp.next;
                }
                Burger burger = temp.next.burger;
                temp.next = temp.next.next;
                return burger;
            }
        }
        return null;
    }
    
    public Burger removeFirst(){
        if(!isEmpty()){
            Burger burger = first.burger;
            first = first.next;
            return burger;
        }
        return null;
    }
    
    public boolean isEmpty(){
        return first == null;
    }
    
    public int size(){
        int count = 0;
        Node temp = first;
        while(temp!=null){
            count++;
            temp = temp.next;
        }
        return count;
    }
    
    public Burger[] toArray(){
        Burger[] burgerArray = new Burger[size()];
        Node temp = first;
        for (int i = 0; i < burgerArray.length; i++) {
            burgerArray[i] = temp.burger;
            temp = temp.next;
        }
        return burgerArray;
    }
    
    public void printBurgerList(){
        System.out.print("{");
        Node temp = first;
        while(temp!= null){
            System.out.print(temp.burger+" - ");
            temp = temp.next;
        }
        System.out.println("\b\b}");
    }
    
    public boolean set(Burger burger){
        int index = indexOf(burger);
        if(index==-1){
            return false;
        }
        if(index == 0){
            first.burger = burger;
        }
        int i = 0;
        Node temp = first;
        while(i<index){
            i++;
            temp = temp.next;
        }
        temp.burger = burger;
        return true;
    }
    
    
            
    //--Inner class => Node
    class Node{
        private Burger burger;
        private Node next;
        private Node(Burger burger){
            this.burger = burger;
        }
    }
}
