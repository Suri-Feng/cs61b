public class ArrayDeque<T> implements Deque<T>{

	private T[] items;
	private int size;
	private int nextFirst;
	private int nextLast;

	public ArrayDeque(){
		items = (T[]) new Object[8];
		size = 0;
		nextFirst = 4;
		nextLast = 5;
	}

	public ArrayDeque(ArrayDeque other){
		items = (T[]) new Object[other.size()];
		size = other.size;
		nextFirst = other.nextFirst;
		nextLast = other.nextLast;

		for (int i = 0; i< other.size(); i+=1){
			addLast((T) other.get(i));
		}
	}

	private void resize(int capacity){
		T[] a = (T[]) new Object[capacity];
		int i = nextFirst + 1;
		for (int j = 0; j < size; j++){
			if (i == items.length){
				i = 0;
			}
			a[j] = items[i];
			i ++;
		}
		items = a;
		nextFirst = capacity - 1;
		nextLast = size;
	}

	@Override
	public void addFirst(T item){
		if (size == items.length){
			resize(size*2);
		}
		items[nextFirst] = item;
		size += 1;
		if (nextFirst== 0){
			nextFirst = items.length - 1;
		} else{
			nextFirst -= 1;
		}
	}

	@Override
	public void addLast(T item){
		if (size == items.length){
			resize(size*2);
		}
		items[nextLast] =item;
		size += 1;
		if (nextLast == items.length -1){
			nextLast = 0;
		}else{
			nextLast += 1;
		}
	}

	@Override
	public boolean isEmpty(){
		if (size == 0){
			return true;
		} else{
			return false;
		}
	}

	@Override
	public int size(){
		return size;
	}

	@Override
	public void printDeque(){
		int i = nextFirst + 1;
		for (int j = 0; j< size; j++){
			if (i == items.length){
				i = 0;
			}
			System.out.print(items[i].toString() + " ");
			i ++;
		}
		System.out.println();
	}

	@Override
	public T removeFirst(){
		if ((size > 16) && (size == (items.length/4))){
			resize(size/2);
		}
		T first;
		nextFirst += 1;
		if (nextFirst == items.length){
			nextFirst = 0;
		}
		first = items[nextFirst];
		items[nextFirst] = null;
		size -= 1;
		return first;
	}

	@Override
	public T removeLast(){
		if ((size > 16) && (size == (items.length/4))){
			resize(size/2);
		}
		T last;
		nextLast -= 1;
		if (nextLast == -1){
			nextLast = items.length - 1;
		}
		last = items[nextLast];
		items[nextLast] = null;
		size -= 1;
		return last;
	}

	@Override
	public T get(int index){
		int realIndex = nextFirst+1+index ;
		if (realIndex > items.length -1 ){
			realIndex -= items.length;
		}
		return items[realIndex];
	}

	public static void main(String[] args) {
		ArrayDeque<Integer> ll = new ArrayDeque<>();

		ll.addFirst(10);
		ll.addFirst(40);
		ll.addLast(20);
		ll.addLast(30);
		ll.addLast(20);
		ll.addLast(20);
		ll.addFirst(40);
		ll.addFirst(40);
		ll.addFirst(40);
		ll.addFirst(40);
		ll.addFirst(40);

		System.out.println("Printing out deque: ");
		ll.printDeque();

		ll.removeLast();
		ll.removeFirst();
		ll.removeLast();

		System.out.println("Printing out deque: ");
		ll.printDeque();
	}

}