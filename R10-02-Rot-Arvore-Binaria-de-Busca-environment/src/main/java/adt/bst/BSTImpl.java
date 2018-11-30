package adt.bst;

import java.util.ArrayList;
import java.util.LinkedList;
   
  // NOTA: 9.7

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
      return findHeight(this.root);
    }
    
    private int findHeight(BSTNode<T> aNode) {
        if (aNode.isEmpty()) {
            return -1;
        }
  
        int lefth = findHeight((BSTNode<T>) aNode.getLeft());
        int righth = findHeight((BSTNode<T>) aNode.getRight());
  
        if (lefth > righth) {
            return lefth + 1;
        } else {
            return righth + 1;
        }
    }
  
    @Override
    public BSTNode<T> search(T element) {
      return search(element, this.root);
    }
    
    private BSTNode<T> search(T element, BSTNode<T> node) {
      if (node.isEmpty() || node.getData().equals(element)) {
        return node;
      }
      if (element.compareTo(node.getData()) > 0) {
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
        if (element.compareTo(node.getData()) > 0) {
          insert((BSTNode<T>)node.getRight(), node, element);
        }
        else {
          insert((BSTNode<T>)node.getLeft(), node, element);
        }
      }
      
    }
   
    @Override
    public BSTNode<T> maximum() {
      return treeMaximum(this.root);
    }
  
    private BSTNode<T> treeMaximum(BSTNode<T> node) {
      BSTNode<T> exit = null;
      BSTNode<T> aux = node;
     while (!aux.isEmpty()) {
       exit = aux;
       aux = (BSTNode<T>) aux.getRight();
     }
     
     return exit;
   } 
   
   @Override
   public BSTNode<T> minimum() {
     return treeMinimum(this.root);
   }
   
   private BSTNode<T> treeMinimum(BSTNode<T> node) {
     BSTNode<T> exit = null;
     BSTNode<T> aux = node;
     while (!aux.isEmpty()) {
       exit = aux;
       aux = (BSTNode<T>) aux.getLeft();
     }
     return exit;
   } 
 
   @Override
   public BSTNode<T> sucessor(T element) {
     BSTNode<T> node = search(element);
     BSTNode<T> aux = null;
     if (!node.isEmpty()) {
       if (!node.getRight().isEmpty()) {
         return treeMinimum((BSTNode<T>) node.getRight());
       }
       aux = (BSTNode<T>) node.getParent();
       while (aux != null && !aux.isEmpty() && node.equals(aux.getRight())) {
         node = aux;
         aux = (BSTNode<T>) aux.getParent();
       }
     }
     
     return aux;
   }
 
   @Override
   public BSTNode<T> predecessor(T element) {
     BSTNode<T> node = search(element);
     BSTNode<T> aux = null;
     if (!node.isEmpty()) {
       if (!node.getLeft().isEmpty()) {
         return treeMaximum((BSTNode<T>) node.getLeft());
       }
       aux = (BSTNode<T>) node.getParent();
       while (aux != null && !aux.isEmpty() && node.equals(aux.getLeft())) {
         node = aux;
         aux = (BSTNode<T>) aux.getParent();
       }
     }
     return aux;
   }
 
   @Override
   public void remove(T element) {
     BSTNode<T> node = search(element);
     if (node != null && !node.isEmpty()) {
         if (node.isLeaf()) {
             node.setData(null);
             node.setLeft(null);
             node.setRight(null);
             node.setParent(null);
         } else if (degree(node) == 1) {
           if (!node.equals(this.root)) {
           if (isLeftChild(node)) {
             if (!node.getLeft().isEmpty()) {
               node.getParent().setLeft(node.getLeft());
               node.getLeft().setParent(node.getParent());
             } else {
               node.getParent().setLeft(node.getRight());
               node.getRight().setParent(node.getParent());
             }
           } else {
             if (!node.getLeft().isEmpty()) {
               node.getParent().setRight(node.getLeft());
               node.getLeft().setParent(node.getParent());
             } else {
               node.getParent().setRight(node.getRight());
               node.getRight().setParent(node.getParent());
             }
           }
         } else {
           T newData = null;
           if (!node.getLeft().isEmpty()) {
             newData = treeMaximum((BSTNode<T>) node.getLeft()).getData();
           } else {
             newData = treeMinimum((BSTNode<T>) node.getRight()).getData();
           }
           remove(newData);
           this.root.setData(newData);
         }
       } 
       else {
         BSTNode<T> sucessor = sucessor(node.getData());
         T newData = sucessor.getData();
         remove(sucessor.getData());
         node.setData(newData);
       }
     }
   }
 
   private boolean isLeftChild(BSTNode<T> node) {
     if (node.equals(this.root) ) {
       return false;
     }
     else {
       BSTNode<T> parent = (BSTNode<T>) node.getParent();
       return parent.getLeft().equals(node);
     }
   }
 
   private int degree(BSTNode<T> node) {
     int degree = 0;
     if (!node.getLeft().isEmpty()) {
       degree++;
     }
     if (!node.getRight().isEmpty()) {
       degree++;
     }
     
     return degree;
   }
 
   @SuppressWarnings("unchecked")
   @Override
   public T[] preOrder() {
     ArrayList<T> array = preOrder(new ArrayList<>(), this.root);
     return array.toArray((T[]) new Comparable[size()]);
   }
   
   private ArrayList<T> preOrder(ArrayList<T> array, BSTNode<T> node) {
     if(!node.isEmpty()){
       visit(array, node);
       preOrder(array, (BSTNode<T>) node.getLeft());
       preOrder(array, (BSTNode<T>) node.getRight());
     }
     return array;
   }
 
   private void visit(ArrayList<T> array, BSTNode<T> node) {
     array.add(node.getData());
     
   }
 
   @SuppressWarnings("unchecked")
   @Override
   public T[] order() {
     ArrayList<T> array = order(new ArrayList<>(), this.root);
     return array.toArray((T[]) new Comparable[size()]);
   }
 
   private ArrayList<T> order(ArrayList<T> array, BSTNode<T> node) {
     if(!node.isEmpty()){
       order(array, (BSTNode<T>) node.getLeft());
       visit(array, node);
       order(array, (BSTNode<T>) node.getRight());
     }
     return array;
   }
 
   @SuppressWarnings("unchecked")
   @Override
   public T[] postOrder() {
     ArrayList<T> array = postOrder(new ArrayList<>(), this.root);
     return array.toArray((T[]) new Comparable[size()]);
   }
 
   private ArrayList<T> postOrder(ArrayList<T> array, BSTNode<T> node) {
     if(!node.isEmpty()){
       postOrder(array, (BSTNode<T>) node.getLeft());
       postOrder(array, (BSTNode<T>) node.getRight());
       visit(array, node);
     }
     return array;
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
