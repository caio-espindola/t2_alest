public class ExclusiveList<T extends Object> extends List<T>{

    @Override
    public boolean set(int index, T value){

        if (index >= this.size() || index < 0){

            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for range " + this.size());

        } else if (this.contains(value)){

            return false;

        }

        Node<T> target = getNode(index);

        target.setValue(value);

        return true;

    }

    @Override
    public boolean add(T value){

        if (this.isEmpty() && !(this.contains(value))){

            this.head = this.tail = new Node<T>(null, value, null);
            this.count++;
            return true;

        } else if (!this.contains(value)){

            Node<T> last = this.getNode(this.size() - 1);
            Node<T> added = new Node<T>(last, value, null);
            last.setNext(added);
            this.tail = added;
            this.count++;
            return true;

        } else {
            return false;
        }

    }

    @Override
    public boolean add(int index, T value){

        if (this.isEmpty() && index == 0){

            this.add(value);
            return true;

        } else if ((this.isEmpty() && index != 0) || index >= this.size() || index < 0){

            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for range " + this.size());

        } else if (index == 0 && !(this.contains(value))){

            Node<T> target = getNode(index);
            Node<T> added = new Node<T>(null, value, target);

            this.head = added;
            target.setPrevious(added);

            this.count++;

            return true;

        } else if (!this.contains(value)){

            Node<T> target = getNode(index);
            Node<T> previous = target.getPrevious();
            Node<T> added = new Node<T>(previous, value, target);

            previous.setNext(added);
            target.setPrevious(added);

            this.count++;

            return true;

        } else {

            return false;

        }

    }
    
}
