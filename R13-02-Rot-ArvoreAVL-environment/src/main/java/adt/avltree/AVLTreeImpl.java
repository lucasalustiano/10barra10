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
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {
  
  	// TODO Do not forget: you must override the methods insert and remove
  	// conveniently.
  
  	// AUXILIARY
  	protected int calculateBalance(BSTNode<T> node) {
  		// Se o node for nulo ou vazio, retorne 0
  		if (node == null || node.isEmpty()) {
  			return 0;
  		}
  		// Retorne a altura do filho a direita - altura do filho a esquerda
  		return height((BSTNode<T>) node.getRight()) - height((BSTNode<T>) node.getLeft());
  	}
  
  	// AUXILIARY
  	protected void rebalance(BSTNode<T> node) {
  		//Se o node nao for nulo nem vazio
  		if (node != null && !node.isEmpty()) {
  			// Calcule o balanceamento dele
  			int balance = calculateBalance(node);
  			BSTNode<T> leftNode = (BSTNode<T>) node.getLeft();
  			BSTNode<T> rightNode = (BSTNode<T>) node.getRight();
  			// Se estiver desbalanceado pra esquerda
  			if (balance > 1) {
  				// Se balance do filho a direita < 0
  				if (calculateBalance(rightNode) < 0) {
  					// Rotacione o filho a direita para a direita e 
  					// rotacione o node a esquerda
  					rightRotation(rightNode);
  					leftRotation(node);
  				// Senao
  				} else {
  					// Rotacione o node a esquerda
  					leftRotation(node);
  				}
  			// Se estiver desbalanceado pra direita
  			} else if (balance < -1) {
  				// Se o balance do filho a esquerda > 0
  				if (calculateBalance(leftNode) > 0) {
  					// Rotacione o filho a esquerda para a direita e 
  					// rotacione o node a direta
  					leftRotation(leftNode);
  					rightRotation(node);
  				// Senao
  				} else {
  					// Rotacione o node a direita
  					rightRotation(node);
  				}
  			}
  		}
  	}
  
  	// AUXILIARY
  	protected void rebalanceUp(BSTNode<T> node) {
  		if (node != null) {
  			// Vai rebalancear os nodes abaixo do node passado
  			rebalance((BSTNode<T>) node);
  			// Vai subir ate chegar ao root
  			rebalanceUp((BSTNode<T>) node.getParent());
  		}
  	}
  
  	private void leftRotation(BSTNode<T> node) {
  		// aux vai ser o filho a direita
  		BSTNode<T> aux = Util.leftRotation(node);
  		// Se o node eh root, o parent dele (aux) vira o novo root
  		if (node.equals(root)) {
  			root = (BSTNode<T>) node.getParent();
  		// Senao
  		} else {
  			// Se o node for filho a direita do parent de aux,
  			// setar a direita do parent como sendo aux
  			if (aux.getParent().getRight().equals(node)) {
  				aux.getParent().setRight(aux);
  			// Senao, setar a esquerda do parent como sendo aux
  			} else {
  				aux.getParent().setLeft(aux);
  			}
  		}
  	}
  
  	private void rightRotation(BSTNode<T> node) {
  		// aux vai ser o filho a esquerda do node
  		BSTNode<T> aux = Util.rightRotation(node);
 		// Se o node eh root, o parent dele (aux) vira o novo root
 		if (node.equals(root)) {
 			root = (BSTNode<T>) node.getParent();
 		// Senao
 		} else {
 			// Se o node for filho a direita do parent de aux,
 			// setar a direita do parent como sendo aux
 			if (aux.getParent().getRight().equals(node)) {
 				aux.getParent().setRight(aux);
 			// Senao, setar a esquerda do parent como sendo aux
 			} else {
 				aux.getParent().setLeft(aux);
 			}
 		}
 	}
 
 	@Override
 	public void insert(T element) {
 		if (element != null) {
 			insert(element, root);
 		}
 
 	}
 
 	private void insert(T element, BSTNode<T> node) {
 		// Se a arvore estiver vazia
 		if (node.isEmpty()) {
 			node.setData(element);
 			node.setLeft(new BSTNode<T>());
 			node.setRight(new BSTNode<T>());
 			node.getLeft().setParent(node);
 			node.getRight().setParent(node);
 
 			rebalanceUp((BSTNode<T>) node);
 		// Se o element for menor do que o valor do node, insere a esquerda
 		} else if (element.compareTo(node.getData()) < 0) {
 			insert(element, (BSTNode<T>) node.getLeft());
 		// Se o element for maior do que o valor do node, onsere a direita	
 		} else if (element.compareTo(node.getData()) > 0) {
 			insert(element, (BSTNode<T>) node.getRight());
 		}
 	}
 
 	@Override
 	public void remove(T element) {
 		if (element != null) {
 			BSTNode<T> node = search(element);
 			remove(node);
 		}
 
 	}
 
 	private void remove(BSTNode<T> node) {
 		if (node != null && !node.isEmpty()) {
 			// O node eh folha
 			if (node.getLeft().isEmpty() && node.getRight().isEmpty()) {
 				node.setData(null);
 				node.setLeft(null);
 				node.setRight(null);
 				rebalanceUp((BSTNode<T>) node);
 			// Se o node tiver somente o filho da direita
 			} else if (!node.getRight().isEmpty() && node.getLeft().isEmpty()) {
 				node.getRight().setParent(node.getParent());
 				// Seo node nao for root
 				if (node.getParent() != null) {
 					// Se o node for filho a esquerda, setar o filho do node pra esquerda do parent
 					if (node.getParent().getLeft().equals(node)) {
 						node.getParent().setLeft(node.getRight());
 					// Senao, setar o filho do node pra direita do parent
 					} else {
 						node.getParent().setRight(node.getRight());
 					}
 				// Senao, o node eh root
 				} else {
 					node.setRight(null);
 					node.setLeft(null);
 					node.setData(null);
 				}
 				rebalanceUp((BSTNode<T>) node);
 			// Se o node tiver somente o filho da esquerda
 			} else if (!node.getLeft().isEmpty() && node.getRight().isEmpty()) {
 				node.getLeft().setParent(node.getParent());
 				// Se o node nao for root
 				if (node.getParent() != null) {
 					// Se o node for filho a direita, setar o filho do node pra direita do parent
 					if (node.getParent().getRight().equals(node)) {
 						node.getParent().setRight(node.getLeft());
 					// Senao, setar o filho do node pra esquerda do parent
 					} else {
 						node.getParent().setLeft(node.getLeft());
 					}
 				// Senao, o node eh root
 				} else {
 					node.setRight(null);
 					node.setLeft(null);
 					node.setData(null);
 				}
 				rebalanceUp((BSTNode<T>) node);
 			// Senao, o node tem os dois filhos. Setar o valor do node com sendo o sucessor
 			// e ir removendo o sucessor.
 			} else {
 				BSTNode<T> sucessor = sucessor(node.getData());
 				T nodeData = node.getData();
 				node.setData(sucessor.getData());
 				sucessor.setData(nodeData);
 
 				remove(sucessor);
 			}
 		}
 	}

}
