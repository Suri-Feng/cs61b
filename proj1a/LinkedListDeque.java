public class LinkedListDeque<T> {

	private class TNode{
		public T item;
		public TNode previous;
		public TNode next;
		public TNode(T i, TNode p, TNode n){
			item = i;
			previous = p;
			next = n;
		}
	}

	private TNode sentinel;
	private int size;

    
    /* creates an empty LinkedListDeque*/
	public LinkedListDeque(){
		sentinel = new TNode(null, null, null);
		sentinel.previous = sentinel;
		sentinel.next = sentinel;
		size = 0;
	}

	/*@source https://www.youtube.com/watch?v=JNroRiEG7U4*/
	public LinkedListDeque(LinkedListDeque other){
		sentinel = new TNode(null, null, null);
		sentinel.previous = sentinel;
		sentinel.next = sentinel;
		size = 0;

		for (int i = 0; i< other.size(); i+=1){
			addLast((T) other.get(i));
		}
	}


	public void addFirst(T item){
		TNode newNode = new TNode(item, sentinel, sentinel.next);
		sentinel.next.previous = newNode;
		sentinel.next = newNode;
		size += 1;

	}
	public void addLast(T item){
		TNode newNode = new TNode(item, sentinel.previous, sentinel);
		sentinel.previous.next = newNode; 
		sentinel.previous = newNode;
		size += 1;
	}

	public boolean isEmpty(){
		if (size == 0){
			return true;
		}
		else return false;
	}

	public int size(){
		return size;

	}

	public void printDeque(){
		TNode ptr = sentinel;
		while (ptr.next != sentinel){
			ptr = ptr.next;
			System.out.print(ptr.item.toString() + " ");
		}
		System.out.println();
	}

	public T removeFirst(){
		TNode first = sentinel.next;
		sentinel.next = first.next;
		size -= 1;
		return first.item;
	}

	public T removeLast(){
		TNode last = sentinel.previous;
		sentinel.previous = last.previous;
		size -= 1;
		return last.item;
	}

	public T get(int index){
		TNode ptr = sentinel.next;
		for (int i = 0; i< index; i++){
			ptr = ptr.next;
		}
		return ptr.item;
	}

	public TNode getRecursiveHelper(int index, TNode ptr){
		if (index == 0){
			return ptr;
		} else {
			return getRecursiveHelper((index - 1), ptr.next);
		}
	}

	public T getRecursive(int index){
		TNode ptr = sentinel.next;
		if (index == 0){
			return ptr.item;
		} else{
			ptr = getRecursiveHelper(index, ptr);
			return ptr.item;
		}
	}


}