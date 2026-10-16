import java.util.Collection;

public class List<T> implements Collection<T>{

    protected class Node<T>{

        protected T value;
        protected Node<T> next, previous;

        public Node(Node<T> prev, T value, Node<T> next){

            this.previous = prev;
            this.value = value;
            this.next = next;

        }

        public T getValue(){

            return this.value;

        }

        public Node<T> getNext(){

            return this.next;

        }

        public Node<T> getPrevious(){

            return this.previous;

        }

        public void setValue(T value){

            this.value = value;

        }

        public void setNext(Node<T> n){

            this.next = n;

        }

        public void setPrevious(Node<T> n){

            this.previous = n;

        }

    }

    protected Node<T> head, tail;
    protected int count;
    
    public List(){

        this.head = this.tail = null;
        this.count = 0;

    }

    protected Node<T> getNode(int index){

        if (index < 0 || index >= this.size()){
            return null;
        }

        Node<T> current = this.head;

        for (int i = 0; i < index; i++){

            current = current.next;

        }

        return current;

    }

    public int size(){

        return this.count;

    }

    public boolean isEmpty(){

        return (this.count == 0);

    }

    public void clear(){

        this.head = this.tail = null;
        this.count = 0;

    }

    public boolean contains(Object value){

        for (T elem : this){
            if (elem.equals(value)){
                return true;
            }
        }

        return false;

    }

    public Object[] toArray(){
        Object[] array = new Object[this.size()];

        for (int i = 0; i < this.size(); i++){
            array[i] = this.get(i);
        }

        return array;
    }

    public T get(int index){

        Node<T> target = getNode(index);

        return target.getValue();

    }

    public boolean set(int index, T value){

        if (index >= this.size() || index < 0){

            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for range " + this.size());

        }

        Node<T> target = getNode(index);

        target.setValue(value);

        return true;

    }

    public void remove(int index){

        if (index >= this.size() || index < 0){

            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for range " + this.size());

        }

        Node<T> target = getNode(index);

        if (this.size() == 1){
            this.head = this.tail = null;
            this.count--;
        } else if (index == 0){
            this.head = target.getNext();
            this.head.setPrevious(null);
            this.count--;
        } else if (index == this.size() - 1){
            this.tail = target.getPrevious();
            this.tail.setNext(null);
            this.count--;
        } else {

            target.getPrevious().setNext(target.getNext());
            target.getNext().setPrevious(target.getPrevious());
            this.count--;

        }

    }

    public boolean remove(Object elem){

        for (int i = 0; i < this.size(); i++){
            if (this.get(i).equals(elem)){
                this.remove(i);
                return true;
            }
        }

        return false;

    }

    public boolean add(T value){

        if (this.isEmpty()){
            this.head = this.tail = new Node<T>(null, value, null);
            this.count++;
        } else {
            Node<T> last = this.getNode(this.size() - 1);
            Node<T> added = new Node<T>(last, value, null);
            last.setNext(added);
            this.tail = added;
            this.count++;
        }

        return true;

    }

    public boolean add(int index, T value){

        if (this.isEmpty() && index == 0){
            this.add(value);
        } else if ((this.isEmpty() && index != 0) || index >= this.size() || index < 0){
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for range " + this.size());
        } else if (index == 0){
            Node<T> target = getNode(index);
            Node<T> added = new Node<T>(null, value, target);

            this.head = added;
            target.setPrevious(added);

            this.count++;
        } else {
            Node<T> target = getNode(index);
            Node<T> previous = target.getPrevious();
            Node<T> added = new Node<T>(previous, value, target);

            previous.setNext(added);
            target.setPrevious(added);

            this.count++;
        }

        return true;

    }
}
