package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends
           AbstractHashtableOpenAddress<T> {
   
      public HashtableOpenAddressLinearProbingImpl(int size,
                                                   HashFunctionClosedAddressMethod method) {
          super(size);
          hashFunction = new HashFunctionLinearProbing<T>(size, method);
          this.initiateInternalTable(size);
      }
  
      @Override
      public void insert(T element) {
          if (this.isFull()) {
              throw new HashtableOverflowException();
          }
          if (element != null && this.search(element) == null) {
              int index = 0;
              int indexHash = getHashIndex(element, index);
  
              while (index < this.capacity()) {
                  if (this.table[indexHash] == null || deletedElement.equals(table[indexHash])) {
                      this.table[indexHash] = element;
                      elements++;
                      break;
                  } else {
                      index++;
                      indexHash = getHashIndex(element, index);
                      COLLISIONS++;
                  }
              }
          }
      }
  
  
      @Override
      public void remove(T element) {
          if (element != null && !this.isEmpty()) {
              int indexHash = this.indexOf(element);
              if (indexHash != -1) {
                  this.table[indexHash] = deletedElement;
                  elements--;
              }
          }
      }
  
      @Override
      public T search(T element) {
          T result = null;
          if (element != null && !this.isEmpty()) {
              int index = 0;
              int indexHash = getHashIndex(element, index);
              while (index < this.capacity() && this.table[indexHash] != null) {
                  if (this.table[indexHash].equals(element)) {
                      result = (T) this.table[indexHash];
                      break;
                  } else {
                      index++;
                      indexHash = getHashIndex(element, index);
                  }
              }
          }
          return result;
      }
  
      @Override
      public int indexOf(T element) {
          int result = -1;
          if (element != null && !this.isEmpty()) {
              int index = 0;
              int indexHash = getHashIndex(element, index);
              while (index < this.capacity() && this.table[indexHash] != null) {
                  if (this.table[indexHash].equals(element)) {
                      result = indexHash;
                      break;
                  } else {
                      index++;
                      indexHash = getHashIndex(element, index);
                  }
              }
  
          }
          return result;
      }
  
      @SuppressWarnings("unchecked")
	private int getHashIndex(T element, int i) {
          int hashIndex = ((HashFunctionOpenAddress<T>) getHashFunction()).hash(element, i);
          return hashIndex;
      }

}