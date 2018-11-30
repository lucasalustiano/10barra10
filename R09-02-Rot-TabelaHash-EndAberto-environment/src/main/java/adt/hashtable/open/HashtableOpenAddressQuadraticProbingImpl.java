package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
           extends AbstractHashtableOpenAddress<T> {
   
      public HashtableOpenAddressQuadraticProbingImpl(int size,
                                                      HashFunctionClosedAddressMethod method, int c1, int c2) {
          super(size);
          hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
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
                      COLLISIONS++;
                      indexHash = getHashIndex(element, index);
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
  
      private int getHashIndex(T element, int index) {
          int hashIndex = ((HashFunctionOpenAddress<T>) getHashFunction()).hash(element, index);
          return hashIndex;
      }

}