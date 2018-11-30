package adt.stack;

public class StackImpl<T> implements Stack<T> {
   
   	private T[] array;
   	private int top;
   
   	@SuppressWarnings("unchecked")
   	public StackImpl(int size) {
  		array = (T[]) new Object[size];
  		top = -1;
  	}
  	
  	//Retorna o topo de uma pilha caso a mesma não esteja vazia
  	@Override
  	public T top() {
  		T saida = null;
  		if(!isEmpty()) saida = array[top];
  		return saida;
  	}
  	
  	//Verifica se a pilha está vazia
  	@Override
  	public boolean isEmpty() {
  		return this.top == -1;
  	}
  
  	//Verifica se a pilha está cheia
  	@Override
  	public boolean isFull() {
  		return this.top == array.length -1;
  	}
  	
  	//Insere um novo na pilha caso a mesma não esteja cheia
  	@Override
  	public void push(T element) throws StackOverflowException {
  		if(isFull()) throw new StackOverflowException();
  		else
  			top++;
  			array[top] = element;
  	}
  
  	//Remove o último elemento da pilha caso a mesma não esteja vazia
  	@Override
  	public T pop() throws StackUnderflowException {
  		T saida = null;
  		if(isEmpty()) throw new StackUnderflowException();
  		else
  			saida = array[top];
  			top --;
  		return saida;
  	}
  
}