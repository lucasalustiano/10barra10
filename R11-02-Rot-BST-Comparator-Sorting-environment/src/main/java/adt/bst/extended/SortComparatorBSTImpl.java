package adt.bst.extended;

import java.util.ArrayList;
import java.util.Comparator;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {
  
  	private Comparator<T> comparator;
  	
  	public SortComparatorBSTImpl(Comparator<T> comparator) {
  		super();
  		this.comparator = comparator;
  	}
  
  	@Override
  	public BSTNode<T> search(T element) {
  		return search(element, this.root);
  	}
  	
  	private BSTNode<T> search(T element, BSTNode<T> node) {
  		if (node.isEmpty() || node.getData().equals(element)) {
  			return node;
  		}
  		if (comparator.compare(element,node.getData()) > 0) {
  			return search(element, (BSTNode<T>)node.getRight());
  		}
  		else {
  			return search(element, (BSTNode<T>)node.getLeft());
  		}
  		
  	} 
  	
  	@Override
  	public void insert(T element) {
  		if(isEmpty()) {
  			this.root.setData(element);
  			this.root.setRight(new BSTNode<>());
  			this.root.setLeft(new BSTNode<>());
  		}
  		else {
  			insert(this.root, this.root, element);
  		}
  	}
  	
  	private void insert(BSTNode<T> node, BSTNode<T> parent, T element) {
  		if(node.isEmpty()) {	
  			node.setData(element);
  			node.setRight(new BSTNode<>());
  			node.setLeft(new BSTNode<>());
  			node.setParent(parent);
  		}
  		else {
  			if (comparator.compare(element,node.getData()) > 0) {
  				insert((BSTNode<T>)node.getRight(), node, element);
  			}
  			else {
  				insert((BSTNode<T>)node.getLeft(), node, element);
  			}
  		}
  		
  	}
  
  	@Override
  	public T[] sort(T[] array) {
  		emptyTree();
  		for (T element: array) {
  			this.insert(element);
  		}
  		return this.order();
  	}
  
  	private void emptyTree() {
  		while (!isEmpty()) {
  			remove(this.getRoot().getData());
  		}
  	}
  
  	@SuppressWarnings("unchecked")
  	@Override
  	public T[] reverseOrder() {
  		ArrayList<T> array = reverseOrder(new ArrayList<>(), this.root);
  		return array.toArray((T[]) new Comparable[size()]);
  	}
  	
  	private ArrayList<T> reverseOrder(ArrayList<T> array, BSTNode<T> node) {
  		if(!node.isEmpty()){
  			reverseOrder(array, (BSTNode<T>) node.getRight());
 			visit(array, node);
 			reverseOrder(array, (BSTNode<T>) node.getLeft());
 		}
 		return array;
 	}
 	
 	private void visit(ArrayList<T> array, BSTNode<T> node) {
 		array.add(node.getData());
 		
 	}
 
 	public Comparator<T> getComparator() {
 		return comparator;
 	}
 
 	public void setComparator(Comparator<T> comparator) {
 		this.comparator = comparator;
 	}

}