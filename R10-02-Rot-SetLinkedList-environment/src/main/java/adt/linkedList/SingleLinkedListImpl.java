package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {
   
   	protected SingleLinkedListNode<T> head;
   
   	public SingleLinkedListImpl() {
   		this.head = new SingleLinkedListNode<T>();
   	}
  
  	@Override
  	public boolean isEmpty() {
  		return this.getHead().isNIL();
  	}
  
  	@Override
  	public int size() {
  		int result = 0;
  		if(isEmpty()){
  			return result;
  		}else{
  			SingleLinkedListNode<T> aux = this.getHead();
  			while(!aux.isNIL()){
  				result += 1;
  				aux= aux.next;
  				
  			}
  		}
  		return result;
  	}
  
  	@Override
  	public T search(T element) {
  		T result = null;
  		if (element != null && !isEmpty()){
  				if(this.head.getData().equals(element)){
  				result = this.head.getData();
  			}else{
  				SingleLinkedListNode<T> aux = this.getHead();
  				while(!(aux.isNIL())){
  					if(aux.getData().equals(element)){
  						result = aux.getData();
  					}
  					aux = aux.next;
  				}
  				
  			}
  		}
  		return result;
  	}
  
  	@Override
  	public void insert(T element) {
  		if(element != null){
  			SingleLinkedListNode<T> next = new SingleLinkedListNode<>();
  			if(isEmpty()){
  				SingleLinkedListNode<T> newHead = new SingleLinkedListNode<T>(element, next);
  				setHead(newHead);
  			}else{
  			SingleLinkedListNode<T> aux = this.getHead();
  			while(!(aux.isNIL())){
  				aux = aux.getNext();
  			}
  			aux.setData(element);
  			aux.setNext(next);
  		}
  		}
  	}
  
  	@Override
  	public void remove(T element) {
  		if (element != null && !(isEmpty())){
  			
  			if(this.head.getData().equals(element)){
  				this.head = head.getNext();
  			}else{
  				SingleLinkedListNode<T> aux = this.getHead();
  				SingleLinkedListNode<T> previous = new SingleLinkedListNode<T>();
  				while(!(aux.isNIL()) && !(aux.getData().equals(element))){
  					previous = aux;
  					aux = aux.getNext();
  				}
  				
  				if(!(aux.isNIL())){
  					previous.setNext(aux.getNext());
  				}
  			}
  		
  		}
  	}
  
  	@Override
  	public T[] toArray() {
  		T[] array = (T[]) new Object[this.size()];
  		int index = 0;
  		
  		SingleLinkedListNode<T> aux = this.getHead();
  		while(!(aux.isNIL())){
  			array[index] = aux.getData();
 			aux= aux.getNext();
 			index++;
 		
 		}
 		return array;
 	}
 
 	public SingleLinkedListNode<T> getHead() {
 		return head;
 	}
 
 	public void setHead(SingleLinkedListNode<T> head) {
 		this.head = head;
 	}
 
 }