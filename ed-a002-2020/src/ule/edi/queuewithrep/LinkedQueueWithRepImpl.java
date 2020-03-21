package ule.edi.queuewithrep;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.w3c.dom.Node;

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
			this.next=null;
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
			T result= current.next.elem;
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
	public void add(T element) throws NullPointerException{
		//todo
		QueueWithRepNode<T> previous,current;
		boolean found = false;
		if(element == null) {
			throw new NullPointerException();
		}
		if(!(contains(element))) {
			QueueWithRepNode<T> node = new QueueWithRepNode<T>(element,1);
			node.next=front;
			front=node;
			count++;
		}else {
			previous=front;
			current=front.next;
			while(current!=previous && !found) {
				if(current.elem.equals(element)) {
					found=true;
					front.num++;
				}else {
					previous=current;
					current=current.next;
				}
			}
		}
		
	}
	
	@Override
	public void add(T element, int times) throws NullPointerException, IllegalArgumentException{
		//todo
		QueueWithRepNode<T> previous,current;
		boolean found = false;
		if(element == null) {
			throw new NullPointerException();
		}
		if(times<0) {
			throw new IllegalArgumentException();
		}
		if(!(contains(element))) {
			QueueWithRepNode<T> node = new QueueWithRepNode<T>(element,times);
			node.next=front;
			front=node;
			count++;
		}else {
			previous=front;
			current=front.next;
			while(previous!=current && !found) {
				if (!front.elem.equals(element)) {
					found=true;
					front.num+=times;
				}else {
					previous=current;
					current=current.next;
				}
			}
		}
	}
	


	@Override
	public void remove(T element, int times) throws NullPointerException, IllegalArgumentException,NoSuchElementException{
		//todo
		QueueWithRepNode<T> previous,current;
		boolean found = false;
		
		if(element==null) {
			throw new NullPointerException();
		}
		
		if(front.elem.equals(element)) {
			if(times>=front.num) {
				throw new IllegalArgumentException();
			}else {
			front.num-=times;
			}
		}else {
			previous=front;
			current=front.next;
			while(current!=previous && !found) {
				if(current.elem.equals(element)) {
					found=true;
					if(times>=current.num) {
						throw new IllegalArgumentException();
					}else {
						current.num-=times;
					}
				}else {
					previous=current;
					current=current.next;
				}
				
			}
			if(!found) {
				throw new NoSuchElementException();
				}
			}
	}

	
	@Override
	public boolean contains(T element) {
		//todo
		QueueWithRepNode<T> previous,current;
		boolean found = false;
		
		if(element==null) {
			throw new NullPointerException();
		}
		
		if(front.elem.equals(element)) {
			found = true;
		}else {
			previous=front;
			current=front.next;
			while(previous!=current && !found) {
				if(current.elem.equals(element)) {
					found=true;
				}else {
					previous=current;
					current=current.next;
				}
			}
		}
		return found;
		}

	@Override
	public long size() {
		//todo
		QueueWithRepNode<T> previous,current;
		long tamanyo =0;
		if(!isEmpty()) {
			tamanyo=front.num;
			previous=front;
			current=front.next;
			while(previous!=current) {
				tamanyo+=current.num;
				previous=current;
				current=current.next;
			}
		}
		return tamanyo;
	}

	@Override
	public boolean isEmpty() {
		//todo
		return(front==null);
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
	public int count(T element) throws NullPointerException{
		//todo
		QueueWithRepNode<T> previous,current;
		boolean found = false;
		int veces = 0;
		
		if(element==null) {
			throw new NullPointerException();
		}
		if(front.elem.equals(element)) {
			veces=front.num;
		}else {
			previous=front;
			current=front.next;
			while(previous!=current && !found) {
				if(current.elem.equals(element)){
					veces=current.num;
					found = true;
				}
				previous=current;
				current=current.next;
			}
		}
		return veces;
	}
	
	@Override
	public Iterator<T> iterator() {
		// TODO 
		return new LinkedQueueWithRepIterator<T>(front);
	}


	@Override
	public String toString() {
		
		StringBuffer buffer = new StringBuffer();
		QueueWithRepNode<T> previous,current;
		
		buffer.append("(");
		if(!isEmpty()) {
			buffer.append(front.elem.toString()+" ");
			previous=front;
			current=front.next;
			while(previous!=current) {
				buffer.append(current.elem.toString()+" ");
				previous=current;
				current=current.next;
			}
		}
		buffer.append(")");
		
		return buffer.toString();
	}

}
