package ule.edi.queuewithrep;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ule.edi.exceptions.EmptyCollectionException;


public class LinkedQueueWithRepImpl<T> implements QueueWithRep<T> {
 
	// Atributos
	private QueueWithRepNode<T> front;
	int count;
	
	
	// Clase interna
	@SuppressWarnings("hiding")
	public class QueueWithRepNode<T> {
		T elem;
		int num;
		QueueWithRepNode<T> next;
		
		public QueueWithRepNode (T elem, int num){
			this.elem=elem;
			this.num=num;
		}
		
	}
	
	///// ITERADOR //////////
	@SuppressWarnings("hiding")
	public class LinkedQueueWithRepIterator<T> implements Iterator<T> {
		
		private QueueWithRepNode<T> current;
		
       	
		public LinkedQueueWithRepIterator(QueueWithRepNode<T> nodo) {
			count=0;
			current=nodo;
			
				}
		
		@Override
		public boolean hasNext() {
			return(current.next!=null);
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			
			current=current.next;
			T result= current.elem;
			return result;
			
				}

		

	}
	////// FIN ITERATOR
	
	public LinkedQueueWithRepImpl() {
		count=0;
		front=null;
		}

	/////////////
	@Override
	public void add(T element) {
		//todo
		if(!(contains(element))) {
			QueueWithRepNode<T> node = new QueueWithRepNode<T>(element,1);
			node.next=front;
			front=node;
			count++;
		}
		
	}
	
	@Override
	public void add(T element, int times) {
		//todo
		if(!(contains(element))) {
			QueueWithRepNode<T> node = new QueueWithRepNode<T>(element,times);
			node.next=front;
			front=node;
			count++;
		}
	}


	@Override
	public void remove(T element, int times) throws EmptyCollectionException, NoSuchElementException{
		//todo
		QueueWithRepNode<T> previous,current;
		boolean found = false;
		T result=null;
		
		if(isEmpty()) {
			throw new EmptyCollectionException("SET");
		}
		if(front.elem.equals(element)) {
			result=front.elem;
			front=front.next;
		}else {
			previous=front;
			current=front.next;
			while(current!=previous && !found) {
				if(current.elem.equals(element)) {
					found=true;
				}else {
					previous=current;
					current=current.next;
				}
				
			}
			if(!found) {
				throw new NoSuchElementException();
			}else {
				result=current.elem;
				previous.next=current.next;
			}
			count--;
		}
		
	}

	
	@Override
	public boolean contains(T element) {
		//todo
		
		}

	@Override
	public long size() {
		//todo

		
	}

	@Override
	public boolean isEmpty() {
		//todo

		
	}

	@Override
	public int remove() throws UnsupportedOperationException {
		//todo
		throw new UnsupportedOperationException();		
	}

	@Override
	public void clear() {
		//todo

	}

	@Override
	public int count(T element) {
		//todo

	
	}
	
	@Override
	public Iterator<T> iterator() {
		// TODO 
	}


	@Override
	public String toString() {
		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("(");
		
		// TODO Ir añadiendo en buffer las cadenas para la representación de la cola. Ejemplo: (A, A, A, B )
		
		
		buffer.append(")");
		return buffer.toString();
	}

	
	

}
