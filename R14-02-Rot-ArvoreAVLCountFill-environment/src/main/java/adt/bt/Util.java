package adt.bt;

import adt.bst.BSTNode;

public class Util {
   
   
   	/**
   	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
  	 * @param node
  	 * @return
  	 */
  	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
  		BSTNode<T> pivot = (BSTNode<T>) node.getRight();		  
  		T aux = node.getData();
  		node.setData(pivot.getData());
  		pivot.setData(aux);
  		node.setRight(pivot.getRight());
  		pivot.setRight(pivot.getLeft());
  		pivot.setLeft(node.getLeft());
  		node.setLeft(pivot);
  		node.getRight().setParent(node);
  		pivot.getLeft().setParent(pivot);
  		return pivot;
  
  	}
  
  	/**
  	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
  	 * @param node
  	 * @return
  	 */
  	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
  		BSTNode<T> pivot = (BSTNode<T>) node.getLeft();
  		T aux = node.getData();
  		node.setData(pivot.getData());
  		pivot.setData(aux);
  		node.setLeft(pivot.getLeft());
  		pivot.setLeft(pivot.getRight());
  		pivot.setRight(node.getRight());
  		node.setRight(pivot);
  		node.getLeft().setParent(node);
  		pivot.getRight().setParent(pivot);		
  		return pivot;
  
  	}
  
  	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
  		@SuppressWarnings("unchecked")
  		T[] array = (T[]) new Comparable[size];
  		return array;
  	}

}