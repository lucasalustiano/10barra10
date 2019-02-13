package adt.btree;
   
import java.util.ArrayList;
   
public class BTreeImpl<T extends Comparable<T>> implements BTree<T> {
  
   	protected BNode<T> root;
   	protected int order;
   
  	public BTreeImpl(int order) {
  		this.order = order;
  		this.root = new BNode<T>(order);
  	}
  
  	@Override
  	public BNode<T> getRoot() {
  		return this.root;
  	}
  
  	@Override
  	public boolean isEmpty() {
  		return this.root.isEmpty();
  	}
  
  	@Override
  	public int height() {
  		return height(this.root);
  	}
  
  	private int height(BNode<T> node) {
  		int height = -1;
  
  		if (!node.isEmpty()) {
  			if (node.isLeaf()) {
  				height = 0;
  			} else {
  				height = 1 + this.height(node.getChildren().getFirst());
  			}
  		}
  		return height;
  	}
  
  	@Override
  	public BNode<T>[] depthLeftOrder() {
  		ArrayList<BNode<T>> arrayList = new ArrayList<>();
  		arrayList = depthLeftOrder(root, arrayList);
  		return arrayList.toArray(new BNode[arrayList.size()]);
  	}
  
  	private ArrayList<BNode<T>> depthLeftOrder(BNode<T> node, ArrayList<BNode<T>> arrayList) {
  		if (!node.isEmpty()) {
  			arrayList.add(node);
  
  			for (int i = 0; i < node.getChildren().size(); i++) {
  				this.depthLeftOrder(node.getChildren().get(i), arrayList);
  			}
  		}
  		return arrayList;
  	}
  
  	@Override
  	public int size() {
  		return this.size(this.root);
  	}
  
  	private int size(BNode<T> node) {
  		int size = 0;
  
  		if (!node.isEmpty()) {
  			size = node.size();
  
  			if (!node.isLeaf()) {
  				for (int i = 0; i < node.getChildren().size(); i++) {
  					size += size(node.getChildren().get(i));
  				}
  			}
  		}
  		return size;
  	}
  
  	@Override
  	public BNodePosition<T> search(T element) {
  		return search(this.root, element);
  	}
  
  	private BNodePosition<T> search(BNode<T> node, T element) {
  
  		BNodePosition<T> returnNode = new BNodePosition<>();
  
  		if (element != null) {
  			int index = 0;
  
  			while (index < node.size() && element.compareTo(node.getElementAt(index)) > 0) {
  				index++;
  			}
  
  			if (index < node.size() && element.equals(node.getElementAt(index))) {
  				returnNode = new BNodePosition<>(node, index);
 			} else if (!node.isLeaf()) {
 				returnNode = this.search(node.getChildren().get(index), element);
 			}
 		}
 		return returnNode;
 	}
 
 	@Override
 	public void insert(T element) {
 		if (element != null) {
 			this.insert(this.root, element);
 		}
 
 	}
 
 	private void insert(BNode<T> node, T element) {
 
 		int index = 0;
 
 		while (node.size() > index && node.getElementAt(index).compareTo(element) < 0) {
 			index++;
 		}
 
 		if (node.getElements().contains(element)) {
 			node.getElements().set(index, element);
 		} else if (node.isLeaf()) {
 			node.addElement(element);
 		} else {
 			this.insert(node.getChildren().get(index), element);
 		}
 		
 		if (node.size() == node.getOrder()) {
 			split(node);
 		}
 	}
 
 	private void split(BNode<T> node) {
 		int median = (int) node.size() / 2;
 
 		BNode<T> newNode = new BNode<>(order);
 		BNode<T> leftSide = new BNode<>(order);
 		BNode<T> rightSide = new BNode<>(order);
 
 		newNode.addElement(node.getElementAt(median));
 		newNode.setParent(node.getParent());
 
 		for (int i = 0; i < median; i++) {
 			leftSide.addElement(node.getElementAt(i));
 		}
 		for (int i = median + 1; i < node.size(); i++) {
 			rightSide.addElement(node.getElementAt(i));
 		}
 
 		this.copyChildrenNodes(median, node, leftSide, rightSide);
 
 		newNode.addChild(0, leftSide);
 		newNode.addChild(1, rightSide);
 
 		this.addParentChildrenNodes(node, leftSide, rightSide);
 
 		this.promote(newNode);
 	}
 
 	private void copyChildrenNodes(int median, BNode<T> node, BNode<T> leftSide, BNode<T> rightSide) {
 
 		if (!node.getChildren().isEmpty()) {
 
 			for (int i = 0; i <= median; i++) {
 				leftSide.addChild(i, node.getChildren().get(i));
 			}
 			int j = 0;
 			for (int i = median + 1; i < node.getChildren().size(); i++) {
 				rightSide.addChild(j++, node.getChildren().get(i));
 			}
 		}
 
 	}
 
 	private void addParentChildrenNodes(BNode<T> node, BNode<T> leftSide, BNode<T> rightSide) {
 
 		BNode<T> parent = node.getParent();
 
 		if (parent != null) {
 
 			int nodeIndex = parent.indexOfChild(node);
 			parent.removeChild(node);
 
 			parent.addChild(nodeIndex, rightSide);
 			parent.addChild(nodeIndex, leftSide);
 
 			leftSide.setParent(node.getParent());
 			rightSide.setParent(node.getParent());
 		}
 
 	}
 
 	private void promote(BNode<T> node) {
 		if (node.getParent() == null) {
 			this.root = node;
 		} else {
 			node.getParent().addElement(node.getElementAt(0));
 		}
 	}
 
 	// NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
 	@Override
 	public BNode<T> maximum(BNode<T> node) {
 		// NAO PRECISA IMPLEMENTAR
 		throw new UnsupportedOperationException("Not Implemented yet!");
 	}
 
 	@Override
 	public BNode<T> minimum(BNode<T> node) {
 		// NAO PRECISA IMPLEMENTAR
 		throw new UnsupportedOperationException("Not Implemented yet!");
 	}
 
 	@Override
 	public void remove(T element) {
 		// NAO PRECISA IMPLEMENTAR
 		throw new UnsupportedOperationException("Not Implemented yet!");
 	}
 
 }