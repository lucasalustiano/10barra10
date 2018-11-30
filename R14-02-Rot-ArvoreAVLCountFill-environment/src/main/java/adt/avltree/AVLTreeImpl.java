package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
  		AVLTree<T> {
  
  	protected static final int LEFT_BALANCING_FACTOR = 1;
  	protected static final int RIGHT_BALANCING_FACTORL = -1;
  	
  	// TODO Do not forget: you must override the methods insert and remove
  	// conveniently.
  
  	// AUXILIARY
  	protected int calculateBalance(BSTNode<T> node) {
  		int balance = 0;
  		if(node != null && !node.isEmpty()) {
  			balance =  height((BSTNode<T>) node.getLeft()) - height((BSTNode<T>) node.getRight()); 
  		}
  		return balance;
  	}
  
  	// AUXILIARY
  	protected void rebalance(BSTNode<T> node) {
  		if(node != null && !node.isLeaf()) {
  			int balance = calculateBalance(node);
  			if(balance > LEFT_BALANCING_FACTOR) {
  				if(calculateBalance((BSTNode<T>) node.getLeft()) < 0) {
  					leftRotation((BSTNode<T>) node.getLeft());
  				}
  				rightRotation(node);
  			} else if(balance < RIGHT_BALANCING_FACTORL) {
  				if(calculateBalance((BSTNode<T>) node.getRight()) > 0) {
  					rightRotation((BSTNode<T>) node.getRight());
  				}
  				leftRotation(node);
  			}
  		}
  	}
  
  	protected void rightRotation(BSTNode<T> node) {
  		BSTNode<T> parent = (BSTNode<T>) node.getParent();
  		BSTNode<T> son = (BSTNode<T>) node.getLeft();
  		son.setParent(parent);
  		node.setParent(son);
  		node.setLeft(son.getRight());
  		son.setRight(node);
  		if (parent != null) {
  			if (parent.getLeft().equals(node)) {
  				parent.setLeft(son);
  			} else {
  				parent.setRight(son);
  			}
  		}
  		if (node == root) {
  			root = son;
  		}
  	}
  
  	protected void leftRotation(BSTNode<T> node) {
  		BSTNode<T> parent = (BSTNode<T>) node.getParent();
  		BSTNode<T> son = (BSTNode<T>) node.getRight();
  		son.setParent(parent);
  		node.setParent(son);
  		node.setRight(son.getLeft());
  		son.setLeft(node);
  		if (parent != null) {
  			if (parent.getLeft().equals(node)) {
  				parent.setLeft(son);
  			} else {
  				parent.setRight(son);
  			}
  		}
  		if (node == root) {
  			root = son;
  		}
  	}
  
  	// AUXILIARY
  	protected void rebalanceUp(BSTNode<T> node) {
  		if(node != null) {
  			BSTNode<T> parent = (BSTNode<T>) node.getParent();
  			while (parent != null) {
  				rebalance(parent);
  				parent = (BSTNode<T>) parent.getParent();
  			}
  		}
  	}
  	
  	@Override
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
 		rebalance(node);
 	}
 	
 	@Override
 	protected void remove(BSTNode<T> node) {
 		if(!node.isEmpty()) {
 			if(node.isLeaf()) {
 				removeLeaf(node);
 				rebalanceUp(node);
 			} else if(withSingleSon(node)) {
 				removeNodeWithSingleSon(node);
 				rebalanceUp(node);
 			} else {
 				BSTNode<T> sucessor = sucessor(node.getData());
 				node.setData(sucessor.getData());
 				remove(sucessor);
 			}
 		}
 	}

}