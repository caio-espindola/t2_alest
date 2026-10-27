public class Deck<T> extends List<T> {

    private int max_size;

    public Deck(int max_size){

        this.max_size = max_size;

    }

    public T addFirst(T value){

        if (this.size() == this.max_size){

            this.add(0, value);
            return this.removeLast();

        } else {

            this.add(0, value);
            return null;

        }

    }

    public T addLast(T value){

        if (this.size() == this.max_size){

            this.add(value);
            return this.removeFirst();

        } else {

            this.add(value);
            return null;

        }

    }

    public T removeFirst(){

        T removed = this.getFirst();
        this.removeIndex(0);
        return removed;

    }

    public T removeLast(){

        T removed = this.getLast();
        this.removeIndex(this.size() - 1);
        return removed;

    }

    public T getFirst(){

        if (this.head == null){
            throw new NullPointerException("Head node is null for collection " + this);
        }

        return this.head.getValue();

    }

    public T getLast(){

        if (this.tail == null){
            throw new NullPointerException("Tail node is null for collection " + this);
        }

        return this.tail.getValue();
        
    }
    
}
