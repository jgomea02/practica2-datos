package ule.edi.queuewithrep;

import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.swing.text.html.parser.Element;

import ule.edi.exceptions.EmptyCollectionException;

public class ArrayQueueWithRepImpl<T> implements QueueWithRep<T> {
	
	// atributos
	
    private final int capacityDefault = 10;
	private final int NOT_FOUND = -1;
	ElemQueueWithRep<T>[] data;
    private int count;
    
	// Clase interna 
    
	@SuppressWarnings("hiding")
	public class ElemQueueWithRep<T> {
		T elem;
		int num;
		public ElemQueueWithRep (T elem, int num){
			this.elem=elem;
			this.num=num;
		}
	}

	
	///// ITERADOR //////////
	@SuppressWarnings("hiding")
	public class ArrayQueueWithRepIterator<T> implements Iterator<T> {
		private int count;
		private int current;
		ElemQueueWithRep<T>[] items;
	
		public ArrayQueueWithRepIterator(ElemQueueWithRep<T>[] cola, int count){
			this.items = cola;
			this.count = count;
			current = 0;
			 if(cola==null) {
				 throw new NullPointerException();
			 }
			if(cola instanceof ElemQueueWithRep[]) {
				throw new IllegalArgumentException();
			}
		}

		@Override
		public boolean hasNext() {
			return(current<count);
		
		}

		@Override
		public T next() {
		
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			current ++;
			return items[current -1].elem;
		}
		
		

	}
	////// FIN ITERATOR
	
	
    // Constructores

	@SuppressWarnings("unchecked")
	public ArrayQueueWithRepImpl() {
		data =   new ElemQueueWithRep[capacityDefault];
		count=0;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayQueueWithRepImpl(int capacity) {
		data =  new ElemQueueWithRep[capacity];
		count=0;
	}
	
	
	 @SuppressWarnings("unchecked")
	 private void expandCapacity() {
		
			ElemQueueWithRep<T>[] nuevo= (ElemQueueWithRep<T>[]) new ElemQueueWithRep[data.length*2];
			
			for (int index=0; index <data.length; index++) {
				nuevo[index] = data[index];
			}
					data= nuevo; 
			// todo
		}
	 
	
	 @Override
	 public void add(T element, int times) {
		 // TODO 
		 if(element==null) {
			 throw new NullPointerException();
		 }
		 if(times<0) {
			 throw new IllegalArgumentException();
		 }
				if(!contains(element)){
					if(size()==data.length) {
						expandCapacity();
					}
				data[count].elem= element;
				data[count].num=times;
				count++;
				}else {
					int search = NOT_FOUND;
					for(int index = 0; index< count && search == NOT_FOUND; index ++) {
						if(data[index].equals(element)) {
							search=index;
							data[search].num +=times;
						}
					}
				}
			}

			

			@Override
			public void add(T element) {
				// TODO 
				if(element==null) {
					throw new NullPointerException();
				}
				if(!contains(element)){
					if(size() == data.length){
						expandCapacity();
					}
					data[count].elem=element;
					count ++;
				}else {
					int search = NOT_FOUND;
					for(int index = 0; index< count && search == NOT_FOUND; index ++) {
						if(data[index].equals(element)) {
							search=index;
							data[search].num++;
						}
					}
				}
				}

			@Override
			public void remove(T element, int times)  {
				//todo
				int search = NOT_FOUND;
				
				if(element==null) {
					throw new NullPointerException();
				}
				for(int index = 0 ; index < count && search==NOT_FOUND; index++) {
					if(data[index].equals(element)) {
						search=index;
					}
				}
				if(search == NOT_FOUND) {
					throw new NoSuchElementException();
				}
				if(times>=data[search].num|| times<0) {
					throw new IllegalArgumentException();
					}
				data[search].num -= times;
				if(data[search].num==0) {
					data[search]=null;
					for(int index=search;index <count;index++) {
						data[index]=data[index+1];
					}
					count--;
				}
			}

			@Override
			public int remove() throws EmptyCollectionException {
				int veces = data[0].num;
				data[0]=null;
				
				if(isEmpty()) {
					throw new EmptyCollectionException("ArrayQueueWithRepImpl");
					}
				
				for(int index = 0 ; index < count; index++) {
					data[index] = data[index+1];
				}
				count --;
				return veces;
				}
			

			@Override
			public void clear() {
				// TODO 
				if(!isEmpty()) {
					for(int index = 0; index < count; index++) {
						data[index]=null;
					}
				}
				
			}
			

			@Override
			public boolean contains(T element) throws NullPointerException {
				// TODO 
			int search = NOT_FOUND;
			
			for(int index = 0; index < count && search== NOT_FOUND; index++) {
				if(data[index].equals(element)) {
					search = index;
				}
			}
			if(element==null) {
				throw new NullPointerException();
			}
			return (search !=NOT_FOUND);
			}

			@Override
			public boolean isEmpty() {
				// TODO 
				return(count==0);
			}

			@Override
			public long size() {
				// TODO 
				return count;
			}

			@Override
			public int count(T element) {
				// TODO 
				int search = NOT_FOUND;
				int veces = 0;
				for(int index = 0; index< count && search == NOT_FOUND; index ++) {
					if(data[index].equals(element))
						veces = data[index].num;
				}
				return veces;
			}

			@Override
			public Iterator<T> iterator() {
				// TODO 
				return new ArrayQueueWithRepIterator<T>(data,count);
			}
			
			@Override
			public String toString() {
				
				final StringBuffer buffer = new StringBuffer();
				
				buffer.append("(");

				for(int index = 0; index < count; index++) {
					buffer.append(data[index].toString() + " ");
				}
				
				buffer.append(")");
				
				return buffer.toString();
			}

	
}
