package adt.hashtable.closed;

import util.Util;

import java.util.Iterator;
import java.util.LinkedList;

import adt.hashtable.hashfunction.HashFunction;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionFactory;

public class HashtableClosedAddressImpl<T> extends
      AbstractHashtableClosedAddress<T> {
  
    /**
     * A hash table with closed address works with a hash function with closed
     * address. Such a function can follow one of these methods: DIVISION or
     * MULTIPLICATION. In the DIVISION method, it is useful to change the size
     * of the table to an integer that is prime. This can be achieved by
     * producing such a prime number that is bigger and close to the desired
     * size.
     * 
     * For doing that, you have auxiliary methods: Util.isPrime and
     * getPrimeAbove as documented bellow.
     * 
     * The length of the internal table must be the immediate prime number
     * greater than the given size (or the given size, if it is already prime). 
     * For example, if size=10 then the length must
     * be 11. If size=20, the length must be 23. You must implement this idea in
     * the auxiliary method getPrimeAbove(int size) and use it.
     * 
     * @param desiredSize
     * @param method
     */
  
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public HashtableClosedAddressImpl(int desiredSize,
        HashFunctionClosedAddressMethod method) {
      int realSize = desiredSize;
  
      if (method == HashFunctionClosedAddressMethod.DIVISION) {
        realSize = this.getPrimeAbove(desiredSize); // real size must the
                              // the immediate prime
                              // above
      }
      initiateInternalTable(realSize);
      HashFunction function = HashFunctionFactory.createHashFunction(method,
          realSize);
      this.hashFunction = function;
    }
  
    // AUXILIARY
    /**
     * It returns the prime number that is closest (and greater) to the given
     * number.
     * If the given number is prime, it is returned. 
     * You can use the method Util.isPrime to check if a number is
     * prime.
     */
    int getPrimeAbove(int number) {
      int prime = number;
      
      while(!Util.isPrime(prime)) {
        prime += 1;
      }
      
      return prime;
    }
  
    @Override
    public void insert(T element) {
      if(element != null) {
        int hashKey = hashKey(element);
        if(this.table[hashKey] == null) {
          this.table[hashKey] = new LinkedList<T>();
          chainingAccess(hashKey).addFirst(element);
          this.elements += 1;
        } else {
          if(!chainingAccess(hashKey).contains(element)) {
            chainingAccess(hashKey).addFirst(element);
            this.elements += 1;
            this.COLLISIONS += 1;
          }
        }
      }
    }
  
    @Override
    public void remove(T element) {
      int hashKey = hashKey(element);
      if(this.table[hashKey] != null) {
        boolean sucess = chainingAccess(hashKey).remove(element);
        if(sucess) {
          this.elements -= 1;
        }
      }
    }
  
   @SuppressWarnings("unchecked")
   private int hashKey(T element) {
     return (((HashFunctionClosedAddress<T>) this.hashFunction).hash(element));
   }
 
   @Override
   public T search(T element) {
     int hashKey = hashKey(element);
     T elem = null;
     if(this.table[hashKey] != null) {
       Iterator<T> itr = chainingAccess(hashKey).iterator();
       while (itr.hasNext()) {
         T next = itr.next();
         if(element.equals(next)) {
           elem = next;
         }
       }
     }
     return elem;
   }
 
   @Override
   public int indexOf(T element) {
     int hashKey = hashKey(element);
     int index = -1;
     if(this.table[hashKey] != null) {
       boolean contains = chainingAccess(hashKey).contains(element);
       if(contains) {
         index = hashKey;
       }
     }
     return index;
   }
 
   @SuppressWarnings("unchecked")
   private LinkedList<T> chainingAccess(int hashKey) {
     return (LinkedList<T>) this.table[hashKey];
   }
   
}