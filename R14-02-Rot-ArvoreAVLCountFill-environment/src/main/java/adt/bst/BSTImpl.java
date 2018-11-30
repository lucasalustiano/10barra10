package adt.bst;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {
   
   	protected BSTNode<T> root;
   
   	public BSTImpl() {
  		root = new BSTNode<T>();
  	}
  
  	public BSTNode<T> getRoot() {
  		return this.root;
  	}
  
  	@Override
  	public boolean isEmpty() {
  		return root.isEmpty();
  	}
  
  	@Override
  	public int height() {
  		int height = -1;
  		if(!this.isEmpty()) {
  			height = height(root);
  		}
  		return height;
  	}
  
  	protected int height(BSTNode<T> node) {
  		int height = -1;
  		if(!node.isEmpty()) {
  			height = 1 + Math.max(height((BSTNode<T>) node.getLeft()), height((BSTNode<T>) node.getRight()));
  		}
  		return height;
  	}
  
  	@Override
  	public BSTNode<T> search(T element) {
  		return search(element, root);
  	}
  
  	private BSTNode<T> search(T element, BSTNode<T> node) {
  		BSTNode<T> found = null;
  		if (node.isEmpty() || node.getData().equals(element)) {
  				found = node;
  		} else {
  			if (element.compareTo(node.getData()) > 0) {
  				found = search(element, (BSTNode<T>) node.getRight());
  			} else {
  				found = search(element, (BSTNode<T>) node.getLeft());
  			}
  		}
  		return found;
  	}
  
  	@Override
  	public void insert(T element) {
  		if (element != null) {
  			insert(element, root);
  		}
  	}
  
  	protected void insert(T element, BSTNode<T> node) {
  		if (node.isEmpty()) {
  			node.setData(element);
  			node.setLeft(new BSTNode<T>());
  			node.getLeft().setParent(node);
  			node.setRight(new BSTNode<T>());
  			node.getRight().setParent(node);
  			
  		} else {
  			if(element.compareTo(node.getData()) > 0) {
  				insert(element, (BSTNode<T>) node.getRight());
  			} else if(element.compareTo(node.getData()) < 0) {
  				insert(element, (BSTNode<T>) node.getLeft());
  			}
  		}
  		
  	}
  
  	@Override
  	public BSTNode<T> maximum() {
  		BSTNode<T> maximum = null;
  		
  		if (!this.isEmpty()) {
  			maximum = maximum(root);
  		}
  		return maximum;
  	}
  
  	private BSTNode<T> maximum(BSTNode<T> node) {
  		while (!node.getRight().isEmpty()) {
  			node = (BSTNode<T>) node.getRight();
  		}
  		return node;
  	}
  
 	@Override
 	public BSTNode<T> minimum() {
 		BSTNode<T> minimum = null;
 		
 		if (!this.isEmpty()) {
 			minimum = minimum(root);
 		}
 		return minimum;
 	}
 	
 	private BSTNode<T> minimum(BSTNode<T> node) {
 		while (!node.getLeft().isEmpty()) {
 			node = (BSTNode<T>) node.getLeft();
 		}
 		return node;
 	}
 
 	@Override
 	public BSTNode<T> sucessor(T element) {
 		BSTNode<T> node = search(element);
 		BSTNode<T> sucessor = null;
 		if(element != null) {
 			if (!node.isEmpty()) {
 				if(!node.getRight().isEmpty()) {
 					sucessor = minimum((BSTNode<T>) node.getRight());
 				} else {
 					BSTNode<T> parent = (BSTNode<T>) node.getParent();
 					while(parent != null && element.compareTo(parent.getData()) > 0) {
 						parent = (BSTNode<T>) parent.getParent();
 					}
 					sucessor = parent;
 				}
 			}
 		}
 		return sucessor;
 	}
 
 	@Override
 	public BSTNode<T> predecessor(T element) {
 		BSTNode<T> node = search(element);
 		BSTNode<T> predecessor = null;
 		if(element != null) {
 			if (!node.isEmpty()) {
 				if(!node.getLeft().isEmpty()) {
 					predecessor = maximum((BSTNode<T>) node.getLeft());
 				} else {
 					BSTNode<T> parent = (BSTNode<T>) node.getParent();
 					while(parent != null && element.compareTo(parent.getData()) < 0) {
 						parent = (BSTNode<T>) parent.getParent();
 					}
 					predecessor = parent;
 				}
 			}
 		}
 		return predecessor;
 	}
 
 	@Override
 	public void remove(T element) {
 		if(element != null || this.isEmpty()) {
 			BSTNode<T> node = search(element);
 			remove(node);
 		}
 	}
 
 	protected void remove(BSTNode<T> node) {
 		if(!node.isEmpty()) {
 			if(node.isLeaf()) {
 				removeLeaf(node);
 			} else if(withSingleSon(node)) {
 				removeNodeWithSingleSon(node);
 			} else {
 				BSTNode<T> sucessor = sucessor(node.getData());
 				node.setData(sucessor.getData());
 				remove(sucessor);
 			}
 		}
 	}
 
 	protected void removeNodeWithSingleSon(BSTNode<T> node) {
 		BSTNode<T> son = null;
 		if(node.getLeft().isEmpty()) {
 			son = (BSTNode<T>) node.getRight();
 		} else {
 			son = (BSTNode<T>) node.getLeft();
 		}
 		
 		if(node != root) {
 			if(isLeft(node)) {
 				node.getParent().setLeft(son);
 			} else {
 				node.getParent().setRight(son);
 			}
 			son.setParent(node.getParent());
 		} else {
 			root = son;
 		}
 	}
 
 	protected void removeLeaf(BSTNode<T> node) {
 		BSTNode<T> nil = new BSTNode<T>();
 		if(node != root) {
 			if(isLeft(node)) {
 				node.getParent().setLeft(nil);
 			} else {
 				node.getParent().setRight(nil);
 			}
 			nil.setParent(node.getParent());
 		} else {
 			root = nil;
 		}
 	}
 
 	private boolean isLeft(BSTNode<T> node) {
 		return node.getParent().getLeft() == node;
 	}
 	
 	protected boolean withSingleSon(BSTNode<T> node) {
 		return node.getLeft().isEmpty() || node.getRight().isEmpty();
 	}
 
 	@SuppressWarnings("unchecked")
 	@Override
 	public T[] preOrder() {
 		LinkedList<T> list = new LinkedList<T>();
 		preOrder(list, root);
 		return list.toArray((T[]) new Comparable[list.size()]);
 	}
 
 	private void preOrder(LinkedList<T> list, BSTNode<T> node) {
 		if(!node.isEmpty()) {
 			list.add(node.getData());
 			preOrder(list, (BSTNode<T>) node.getLeft());
 			preOrder(list, (BSTNode<T>) node.getRight());
 		}
 	}
 
 	@SuppressWarnings("unchecked")
 	@Override
 	public T[] order() {
 		LinkedList<T> list = new LinkedList<T>();
 		order(list, root);
 		return list.toArray((T[]) new Comparable[list.size()]);
 	}
 
 	private void order(LinkedList<T> list, BSTNode<T> node) {
 		if(!node.isEmpty()) {
 			order(list, (BSTNode<T>) node.getLeft());
 			list.add(node.getData());
 			order(list, (BSTNode<T>) node.getRight());
 		}
 		
 	}
 
 	@SuppressWarnings("unchecked")
 	@Override
 	public T[] postOrder() {
 		LinkedList<T> list = new LinkedList<T>();
 		postOrder(list, root);
 		return list.toArray((T[]) new Comparable[list.size()]);
 	}
 
 	private void postOrder(LinkedList<T> list, BSTNode<T> node) {
 		if(!node.isEmpty()) {
 			postOrder(list, (BSTNode<T>) node.getLeft());
 			postOrder(list, (BSTNode<T>) node.getRight());
 			list.add(node.getData());
 		}
 	}
 
 	/**
 	 * This method is already implemented using recursion. You must understand
 	 * how it work and use similar idea with the other methods.
 	 */
 	@Override
 	public int size() {
 		return size(root);
 	}
 
 	private int size(BSTNode<T> node) {
 		int result = 0;
 		// base case means doing nothing (return 0)
 		if (!node.isEmpty()) { // indusctive case
 			result = 1 + size((BSTNode<T>) node.getLeft())
 					+ size((BSTNode<T>) node.getRight());
 		}
 		return result;
 	}
 
}