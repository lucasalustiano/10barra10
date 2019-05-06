package adt.linkedList.set;

import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

public class SetLinkedListImpl<T> extends SingleLinkedListImpl<T> implements SetLinkedList<T> {

	@Override
	public void removeDuplicates() {
		SingleLinkedListNode<T> aux1 = this.getHead();
		SingleLinkedListNode<T> aux2 = this.getHead();
		while (!aux1.isNIL()) {
			while(!aux2.isNIL()) {
				if (aux1.equals(aux2.getNext())) {
					this.remove(aux2.getNext().getData());
				}
				aux2 = aux2.getNext();
			}
			aux1 = aux1.getNext();
		}
	}

	@Override
	public SetLinkedList<T> union(SetLinkedList<T> otherSet) {
		SetLinkedListImpl<T> result =  new SetLinkedListImpl<>();
		SingleLinkedListNode<T> aux1 = this.getHead();
		SingleLinkedListNode<T> aux2 = ((SingleLinkedListImpl<T>) otherSet).getHead();
		
		// Se o set atual for maior ou igual a other
		if (this.size() >= otherSet.size()) {
			while (!aux1.isNIL()) {
				while(!aux2.isNIL()) {
					if (aux1.equals(aux2.getNext())) {
						result.insert(aux2.getData());
						aux1 = aux1.getNext();
						aux2 = aux2.getNext();
					}
					aux2 = aux2.getNext();
				}
				aux1 = aux1.getNext();
			}
		// Se other for maior que o set atual
		} else {
			while (!aux2.isNIL()) {
				while(!aux1.isNIL()) {
					if (aux2.equals(aux1.getNext())) {
						result.insert(aux2.getData());
						aux2 = aux2.getNext();
						aux1 = aux1.getNext();
					}
					aux1 = aux1.getNext();
				}
				aux2 = aux2.getNext();
			}
		}
		
		result.removeDuplicates();
		
		return result;
	}

	@Override
	public SetLinkedList<T> intersection(SetLinkedList<T> otherSet) {
		//TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public void concatenate(SetLinkedList<T> otherSet) {
		//TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}

}
