package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {
   
   	protected SkipListNode<T> root;
   	protected SkipListNode<T> NIL;
   
   	protected int maxHeight;
   
  	protected double PROBABILITY = 0.5;
  
  	public SkipListImpl(int maxHeight) {
  		this.maxHeight = maxHeight;
  		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
  		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
  		connectRootToNil();
  	}
  
  	/**
  	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
  	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve conectar
  	 * todos os forward. Senao o ROOT eh inicializado com level=1 e o metodo deve
  	 * conectar apenas o forward[0].
  	 */
  	private void connectRootToNil() {
  		for (int i = 0; i < maxHeight; i++) {
  			root.forward[i] = NIL;
  		}
  	}
  
  	@Override
  	public void insert(int key, T newValue, int height) {
  		if (newValue != null) {
  			SkipListNode<T>[] update = new SkipListNode[height];
  			SkipListNode<T> aux = root;
  
  			for (int i = height - 1; i >= 0; i--) {
  				while (aux.getForward(i) != null && aux.getForward(i).getKey() < key) {
  					aux = aux.getForward(i);
  				}
  				update[i] = aux;
  			}
  
  			aux = aux.getForward(0);
  
  			if (aux.getKey() == key) {
  				aux.setValue(newValue);
  			} else {
  				adjustHeight(height, update);
  				aux = new SkipListNode<T>(key, height, newValue);
  
  				for (int i = 0; i < height; i++) {
  					aux.forward[i] = update[i].forward[i];
  					update[i].forward[i] = aux;
  				}
  
  			}
  		}
  	}
  
  	private void adjustHeight(int height, SkipListNode<T>[] update) {
  		if (height > maxHeight) {
  			for (int i = maxHeight; i < height; i++)
  				root.getForward()[i] = NIL;
  			maxHeight = height;
  		}
  
  	}
  
  	@Override
  	public void remove(int key) {
  		SkipListNode<T>[] update = new SkipListNode[this.maxHeight];
  		SkipListNode<T> aux = root;
  
  		for (int i = maxHeight - 1; i >= 0; i--) {
  			if (aux.forward[i] != NIL) {
  				while (aux.forward[i].value != null && aux.forward[i].key < key)
  					aux = aux.forward[i];
  			}
  			update[i] = aux;
  		}
  		aux = aux.getForward()[0];
  
  		if (aux.key == key) {
  
  			for (int i = 0; i < maxHeight; i++) {
  				if (update[i].getForward()[i] != aux)
  					break;
  				update[i].getForward()[i] = aux.getForward()[i];
  			}
  		}
  	}
  
  	@Override
  	public int height() {
  		int height = maxHeight - 1;
  		while (height >= 0 && root.getForward(height) == NIL) {
  			if (height == 0) {
  				height--;
 				break;
 			} else {
 				height--;
 			}
 		}
 		return height + 1;
 	}
 
 	@Override
 	public SkipListNode<T> search(int key) {
 		SkipListNode<T> aux = root;
 
 		for (int i = height() - 1; i >= 0; i--) {
 			while (aux.getForward(i) != null && aux.getForward(i).getKey() < key) {
 				aux = aux.getForward(i);
 			}
 		}
 
 		aux = aux.getForward(0);
 
 		return key == aux.getKey() ? aux : null;
 	}
 
 	@Override
 	public int size() {
 		int result = 0;
 		SkipListNode<T> aux = root.getForward(0);
 		while (aux != NIL) {
 			result++;
 			aux = aux.getForward(0);
 		}
 		return result;
 	}
 
 	@Override
 	public SkipListNode<T>[] toArray() {
 		int size = size() + 2;
 		SkipListNode<T>[] array = new SkipListNode[size];
 		SkipListNode<T> aux = root;
 
 		for (int i = 0; i < size; i++) {
 			array[i] = aux;
 			aux = aux.getForward(0);
 		}
 		return array;
 	}
 
}