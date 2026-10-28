import java.util.Collection;
import java.util.Iterator;
import javax.naming.OperationNotSupportedException;

public class List<T extends Object> implements Collection<T>{

    // Classes

    protected class Node<E extends Object>{

        private E value;
        private Node<E> next, previous;

        public Node(Node<E> prev, E value, Node<E> next){

            this.previous = prev;
            this.value = value;
            this.next = next;

        }

        public E getValue(){

            return this.value;

        }

        public Node<E> getNext(){

            return this.next;

        }

        public Node<E> getPrevious(){

            return this.previous;

        }

        public void setValue(E value){

            this.value = value;

        }

        public void setNext(Node<E> n){

            this.next = n;

        }

        public void setPrevious(Node<E> n){

            this.previous = n;

        }

    }

    protected class Itr implements Iterator<T>{

        private Node<T> current;
        private List<T> l;

        public Itr(List<T> l){

            this.l = l;
            this.reset();

        }

        @Override
        /* Returns the next element in the iteration.
            @returns The next element in the iteration. Returns null if there are no more elements.
        */
        public T next(){

            if (current == null){
                return null;
            }

            Object val;
            val = this.current.getValue();

            current = current.getNext();

            return (T) val;

        }

        @Override
        /* Checks whether there is a next element in the collection
            @returns true or false
         */
        public boolean hasNext(){

            return (current != null);

        }

        /* Sets the current element back to the first element in the collection, or to null if there are none.
         */
        public void reset(){

            this.current = l.getNode(0);

        }

    }

    // Fields

    protected Node<T> head, tail;
    protected int count;
    
    // Constructor

    public List(){

        this.head = this.tail = null;
        this.count = 0;

    }

    // Collection Methods

    @Override
    public int size(){

        return this.count;

    }

    @Override
    public boolean isEmpty(){

        return (this.count == 0);

    }

    @Override
    public void clear(){

        this.head = this.tail = null;
        this.count = 0;

    }

    @Override
    public boolean contains(Object value){

        for (T elem : this){
            if (elem.equals(value)){
                return true;
            }
        }

        return false;

    }

    @Override
    /*  Returns an array containing all objects in this collection.
        @returns an Object array, or null if this collection is empty.
     */
    public Object[] toArray(){
        if (this.isEmpty()){
            return null;
        }

        Object[] array = new Object[this.size()];

        for (int i = 0; i < this.size(); i++){
            array[i] = this.get(i);
        }

        return array;
    }

    @Override
    /* Returns an array containing all objects in this collection, and also writes it to the given array.
        @param array An object array to be written to.
        @returns the same array, or a new one if it was not sufficiently big.
     */
    public Object[] toArray(Object[] array){

        if (array == null){
            return null;
        }

        if (array.length < this.size()){
            array = new Object[this.size()];
        }

        array = this.toArray();
        return array;

    }

    @Override
    /* Removes from this collection all the elements not present in the given collection
        @param l2 A collection
        @returns true if all removals were successful. Also returns false if l2 is null or empty
     */
    public boolean retainAll(Collection<?> l2){

        if (l2 == null || l2.isEmpty()){
            this.clear();
            return false;
        }

        boolean result = true;

        for (Object o : this){

            if (!l2.contains(o)){

                result = result && this.remove(o);

            }

        }

        return result;

    }

    @Override
    /* Removes from this list all elements present in the given collection
        @param l2 a collection
        @returns true if any objects were removed. Also returns false if l2 is null or empty.
     */
    public boolean removeAll(Collection<?> l2){

        if (l2 == null){
            return false;
        }

        boolean result = false;

        for (Object o : l2){

            if (this.contains(o)){
                this.remove(o);
                result = true;
            }

        }

        return result;

    }

    @Override
    /* Removes a given object from this collection
        @param elem An object
        @returns true if any elements were removed from this collection.
     */
    public boolean remove(Object elem){

        boolean result = false;

        for (int i = 0; i < this.size(); i++){
            if (this.get(i).equals(elem)){
                this.removeIndex(i);
                result = true;
            }
        }

        return result;

    }

    @Override
    /* Appends a value to the end of this collection
        @param value An object of type T
        @returns true if the value was added
     */
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

    @Override
    /* Add all elements from a given collection into this collection
        @param col A collection
        @returns true if all additions were succesfull
     */
    public boolean addAll(Collection<? extends T> col){

        if (col == null){
            return false;
        }

        boolean result = true;

        for (T elem : col) {

            result = result && this.add(elem);
            
        }

        return result;

    }

    @Override
    /* Checks if this collection contains all the elements in the given collection
        @param col A collection
        @return true if col is a subset of this collection
     */
    public boolean containsAll(Collection<?> col){

        if (col == null){
            return false;
        }

        boolean result = true;

        for (Object elem : col) {
            result = result && this.contains(elem);
        }

        return result;

    }

    @Override
    /* Returns an iterator for this collection
     */
    public Iterator<T> iterator(){

        return new Itr(this);
        
    }

    // Protected Methods

    /* Returns the node present at the given index
        @returns A Node of type T, or null if the index is out of bounds
     */
    protected Node<T> getNode(int index){

        if (index < 0 || index >= this.size()){
            return null;
        }

        if (index <= this.size()/2){

            Node<T> current = this.head;

            for (int i = 0; i < index; i++){

                current = current.getNext();

            }

            return current;

        } else {

            Node<T> current = this.tail;

            for (int i = this.size() - 1; i > index; i--){

                current = current.getPrevious();

            }

            return current;

        }
    }

    // Public Methods

    /* Returns the element present at the given index
        @returns An object of type T
        @throws IndexOutOfBoundsException if the given index is invalid
        @throws NullPointerException if the Node present at the given index is null
     */
    public T get(int index){

        if (index < 0 || index >= this.size()){
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for range " + this.size());
        }

        Node<T> target = getNode(index);

        if (target == null){

            throw new NullPointerException("Index " + index + " is null in collection " + this);

        }

        return target.getValue();

    }

    /* Changes a given index to the given value
        @returns true if the operation was successfull
        @throws IndexOutOfBoundsException if the given index is invalid
        @throws NullPointerException if the Node present at the given index is null
     */
    public boolean set(int index, T value){

        if (index >= this.size() || index < 0){

            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for range " + this.size());

        }

        Node<T> target = getNode(index);

        if (target == null){

            throw new NullPointerException("Index " + index + " is null in collection " + this);

        }

        target.setValue(value);

        return true;

    }

    /* Removes the node at the given index
        @throws IndexOutOfBoundsException if the given index is invalid
        @throws NullPointerException if the Node present at the given index is null
     */
    public void removeIndex(int index){

        if (index >= this.size() || index < 0){

            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for range " + this.size());

        }

        Node<T> target = getNode(index);

        if (target == null){

            throw new NullPointerException("Index " + index + " is null in collection " + this);

        }

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

    /* Creates a new node at the given index with the given value
        @returns true if the operation was successfull
        @throws IndexOutOfBoundsException if the given index is invalid
        @throws OperationNotSupportedException if addition in arbitrary indices is not allowed
     */
    public boolean add(int index, T value) throws OperationNotSupportedException{

        if (this.isEmpty() && index == 0){

            this.add(value);

        } else if ((this.isEmpty() && index != 0) || index >= this.size() || index < 0){

            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for range " + this.size());

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
