import javax.naming.OperationNotSupportedException;

public class Deck<T> extends List<T> {

    private int max_size;

    public Deck(int max_size){

        this.max_size = max_size;

    }

    @Override
    /* Addition to arbitraty positions in the collection is not allowed for this type
        @throws OperationNotSupportedException
     */
    public boolean add(int index, T value) throws OperationNotSupportedException{
        throw new OperationNotSupportedException();
    }


    /* Adds the given element to the start of the deck
        @returns The element removed from the other side, if necessary
     */
    public T addFirst(T value) throws OperationNotSupportedException{

        if (this.size() == this.max_size){

            super.add(0, value);
            return this.removeLast();

        } else {

            super.add(0, value);
            return null;

        }

    }

    /* Adds the given element to the end of the deck
        @returns The element removed from the other side, if necessary
     */
    public T addLast(T value) throws OperationNotSupportedException{

        if (this.size() == this.max_size){

            this.add(value);
            return this.removeFirst();

        } else {

            this.add(value);
            return null;

        }

    }

    /* Removes the first element and returns it
     */
    public T removeFirst(){

        T removed = this.getFirst();
        this.removeIndex(0);
        return removed;

    }

    /* Removes the last element and returns it
     */
    public T removeLast(){

        T removed = this.getLast();
        this.removeIndex(this.size() - 1);
        return removed;

    }

    /* Returns the first element in this list
        @throws NullPointerException if the list is empty or the first element is null
     */
    public T getFirst(){

        if (this.head == null){
            throw new NullPointerException("Head node is null for collection " + this);
        }

        return this.head.getValue();

    }

    /* Returns the last element in this list
        @throws NullPointerException if the list is empty or the last element is null
     */
    public T getLast(){

        if (this.tail == null){
            throw new NullPointerException("Tail node is null for collection " + this);
        }

        return this.tail.getValue();
        
    }
    
}
