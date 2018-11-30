package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
   		DoubleLinkedList<T> {
   
   	protected DoubleLinkedListNode<T> last;
   	private DoubleLinkedListNode<T> nil = new DoubleLinkedListNode<T>();
   
   	public DoubleLinkedListImpl(){
  		super();
  		setHead(nil);
  		setLast(nil);
  	}
  	
  	@Override
  	public void insertFirst(T element) {
  		if(element != null){
  			DoubleLinkedListNode<T> head = (DoubleLinkedListNode<T>) getHead();
  			DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<T>(element, head, new DoubleLinkedListNode<>());
  		
  			head.setPrevious(newHead);
  			setHead(newHead);
  			if(this.getLast().isNIL()){
  				this.setLast(newHead);
  			}if(this.getLast().getPrevious()== null){
  				this.getLast().setPrevious(newHead);
  				
  			}
  		}
  	}
  	
  	@Override
  	public void insert(T element) {
  		if (element != null) {
  		DoubleLinkedListNode aux = new DoubleLinkedListNode(element, new DoubleLinkedListNode<>(), last);
  		if (isEmpty()) {
  			super.head = last = aux;
  
  		} else {
  			last.next = aux;
  			last = aux;
  		}
  		}
  	}
  	@Override
  	public void remove(T element) {
  		if (element != null && !isEmpty()) {
              if (this.getHead().getData().equals(element)) {
                  this.removeFirst();
              } else if (this.getLast().getData().equals(element)) {
                  this.removeLast();
              } else {
                  DoubleLinkedListNode<T> aux = this.getLast();
                  DoubleLinkedListNode<T> toRemove = null;
  
                  while(!aux.isNIL() && aux.getPrevious() != null){
                      if(aux.getData().equals(element)){
                          toRemove = aux;
                      }
                      aux = aux.getPrevious();
                  }
  
                  if(toRemove != null){
                      toRemove.getPrevious().setNext(toRemove.getNext());
                      ((DoubleLinkedListNode<T>) toRemove.getNext())
                              .setPrevious(toRemove.getPrevious());
                  }
              }
          }
  	}
  
  	
  	@Override
  	public void removeFirst() {
  		
  		if(!isEmpty()){
  			if(size() > 1){
  				DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) this.getHead().getNext();
  				aux.setPrevious(new DoubleLinkedListNode<>());
  				this.setHead(aux);
  			}else{
  				this.setHead(new DoubleLinkedListNode<>());
  				this.setLast(new DoubleLinkedListNode<>());
  				
  			}
  		}
  	    
  	}
  
  	@Override
  	public void removeLast() {
  		if(!isEmpty()){
  			// se o tamanho for igual a 1, o head sera igual ao last, que por sua vez tbm ser� igual ao next do head, ou seja, o no Nil.
  			if(size() == 1){
  				super.head = this.last = (DoubleLinkedListNode<T>) super.head.getNext();
  			}else{
  				this.last.getPrevious().setNext(new DoubleLinkedListNode<>());
  				// set last
  				this.last = this.last.getPrevious();
 			}
 		}
 	}
 
 	
 	
 	public DoubleLinkedListNode<T> getLast() {
 		return last;
 	}
 	
 	
 	public void setLast(DoubleLinkedListNode<T> last) {
 		this.last = last;
 	}

}