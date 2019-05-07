package adt.linkedList.set;

import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

	public class SetLinkedListImpl<T> extends SingleLinkedListImpl<T> implements SetLinkedList<T> {

	@Override
	public void removeDuplicates() {
        SingleLinkedListNode<T> auxiliary1 = this.getHead();

        while(!auxiliary1.getNext().isNIL()) {
            SingleLinkedListNode<T> auxiliary2 = auxiliary1.getNext();
            while(!auxiliary2.getNext().isNIL()) {
                if(auxiliary1.getData() == auxiliary2.getData()) {
                    auxiliary1 = auxiliary1.getNext();
                    remove(auxiliary2.getData());
                }
                auxiliary2 = auxiliary2.getNext();
            }
            auxiliary1 = auxiliary1.getNext();
        }

    }

	@Override
	public SetLinkedList<T> union(SetLinkedList<T> otherSet) {
		SetLinkedListImpl<T> result =  new SetLinkedListImpl<>();
		SingleLinkedListNode<T> auxiliary1 = this.getHead();
		SingleLinkedListNode<T> auxiliary2 = ((SingleLinkedListImpl<T>) otherSet).getHead();
		
		// Se o set atual for maior ou igual a other
		if (this.size() >= otherSet.size()) {
			while (!auxiliary1.isNIL()) {
				while(!auxiliary2.isNIL()) {
					if (auxiliary1.equals(auxiliary2.getNext())) {
						result.insert(auxiliary2.getData());
						auxiliary1 = auxiliary1.getNext();
						auxiliary2 = auxiliary2.getNext();
					}
					auxiliary2 = auxiliary2.getNext();
				}
				auxiliary1 = auxiliary1.getNext();
			}
		// Se other for maior que o set atual
		} else {
			while (!auxiliary2.isNIL()) {
				while(!auxiliary1.isNIL()) {
					if (auxiliary2.equals(auxiliary1.getNext())) {
						result.insert(auxiliary2.getData());
						auxiliary2 = auxiliary2.getNext();
						auxiliary1 = auxiliary1.getNext();
					}
					auxiliary1 = auxiliary1.getNext();
				}
				auxiliary2 = auxiliary2.getNext();
			}
		}
		
		result.removeDuplicates();
		
		return result;
	}

	@Override
	public SetLinkedList<T> intersection(SetLinkedList<T> otherSet) {
		SetLinkedListImpl<T> result =  new SetLinkedListImpl<>();
		SingleLinkedListNode<T> auxiliary1 = this.getHead();
		SingleLinkedListNode<T> auxiliary2 = ((SingleLinkedListImpl<T>) otherSet).getHead();
		
		// Se o set atual for maior ou igual a other
		if (this.size() >= otherSet.size()) {
			while (!auxiliary1.isNIL()) {
				while(!auxiliary2.isNIL()) {
					if (!auxiliary1.equals(auxiliary2.getNext()) && !auxiliary2.getNext().isNIL()) {
						result.insert(auxiliary2.getData());
						auxiliary1 = auxiliary1.getNext();
						auxiliary2 = auxiliary2.getNext();
					}
					auxiliary2 = auxiliary2.getNext();
				}
				auxiliary1 = auxiliary1.getNext();
			}
		// Se other for maior que o set atual
		} else {
			while (!auxiliary2.isNIL()) {
				while(!auxiliary1.isNIL()) {
					if (!auxiliary2.equals(auxiliary1.getNext()) && !auxiliary1.getNext().isNIL()) {
						result.insert(auxiliary2.getData());
						auxiliary2 = auxiliary2.getNext();
						auxiliary1 = auxiliary1.getNext();
					}
					auxiliary1 = auxiliary1.getNext();
				}
				auxiliary2 = auxiliary2.getNext();
			}
		}
		
		result.removeDuplicates();
		
		return result;
	}

	@Override
	public void concatenate(SetLinkedList<T> otherSet) {
		SetLinkedListImpl<T> result =  new SetLinkedListImpl<>();
		SingleLinkedListNode<T> auxiliary1 = this.getHead();
		SingleLinkedListNode<T> auxiliary2 = ((SingleLinkedListImpl<T>) otherSet).getHead();
		
		while (!auxiliary1.getNext().isNIL()) {
			auxiliary1 = auxiliary1.getNext();
		}
		
		auxiliary1.setNext(auxiliary2);
		
		result.removeDuplicates();	
		
	}

}
