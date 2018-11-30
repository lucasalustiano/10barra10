package adt.queue;

public class QueueImpl<T> implements Queue<T> {
   
   	private T[] array;
   	private int tail;
   
   	@SuppressWarnings("unchecked")
   	public QueueImpl(int size) {
  		array = (T[]) new Object[size];
  		tail = -1;
  	}
  	
  	//Retorna o primeiro elemento da fila
  	@Override
  	public T head() {
  		T saida = null;
  		if(!isEmpty()) saida = array[0];
  		return saida;
  		
  	}
  	
  	//Verifica se a fila está vazia
  	@Override
  	public boolean isEmpty() {
  		return this.tail == -1;
  	}
  
  		//Verifica se a fila está cheia
  	@Override
  	public boolean isFull() {
  		return this.tail == array.length -1;
  	}
  
  	//Auxilia o dequeue fazendo um shift de todos elementos da fila para esquerda
  	private void shiftLeft() {
  		for (int i = 1; i < array.length; i++) {
  			array[i-1] = array[i];
  		}
  	}
  	
  	
  	//Insere um elemento na fila caso a mesma não esteja cheia
  	@Override
  	public void enqueue(T element) throws QueueOverflowException {
  		if(isFull()) throw new QueueOverflowException();
  		
  		tail ++;
  		array[tail] = element;
  	}
  	
  	
  	//Remove um elemento na fila caso a mesma não esteja vazia
  	@Override
  	public T dequeue() throws QueueUnderflowException {
  		if(isEmpty()) throw new QueueUnderflowException();
  		T saida = array[0];
  		tail --;
  		shiftLeft();
  		return saida;
  	}
  
  }