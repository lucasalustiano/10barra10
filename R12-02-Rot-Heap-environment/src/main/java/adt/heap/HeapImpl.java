package adt.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import util.Util;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o maior sempre no
 * topo. Ou seja, admitindo um comparador normal (responde corretamente 3 > 2),
 * essa heap deixa os elementos maiores no topo. Essa comparação não é feita 
 * diretamente com os elementos armazenados, mas sim usando um comparator. 
 * Dessa forma, dependendo do comparator, a heap pode funcionar como uma max-heap 
 * ou min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {
  
     protected T[] heap;
     protected int index = -1;
     /**
      * O comparador é utilizado para fazer as comparações da heap. O ideal é
      * mudar apenas o comparator e mandar reordenar a heap usando esse
      * comparator. Assim os metodos da heap não precisam saber se vai funcionar
      * como max-heap ou min-heap.
      */
     protected Comparator<T> comparator;
     private static final int INITIAL_SIZE = 20;
  
     private static final int INCREASING_FACTOR = 10;
     private static final int EQUAL = 0;
     private static final int FIRST_INDEX = 0;
  
     /**
      * Construtor da classe. Note que de inicio a heap funciona como uma
      * min-heap.
      */
     @SuppressWarnings("unchecked")
     public HeapImpl(Comparator<T> comparator) {
        this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
        this.comparator = comparator;
     }
  
     // /////////////////// METODOS IMPLEMENTADOS
     private int parent(int i) {
        return (i - 1) / 2;
     }
  
     /**
      * Deve retornar o indice que representa o filho a esquerda do elemento
      * indexado pela posicao i no vetor
      */
     private int left(int i) {
        return (i * 2 + 1);
     }
  
     /**
      * Deve retornar o indice que representa o filho a direita do elemento
      * indexado pela posicao i no vetor
      */
     private int right(int i) {
        return (i * 2 + 1) + 1;
     }
  
     @Override
     public boolean isEmpty() {
        return (index == -1);
     }
  
     @Override
     public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] resp = (T[]) new Comparable[index + 1];
        for (int i = 0; i <= index; i++) {
           resp[i] = this.heap[i];
        }
        return resp;
     }
  
     // ///////////// METODOS A IMPLEMENTAR
  
     /**
      * Valida o invariante de uma heap a partir de determinada posicao, que pode
      * ser a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores
      * (comparados usando o comparator) elementos na parte de cima da heap.
      */
     private void heapify(int posicao) {
        int esquerda = this.left(posicao), direita = this.right(posicao), maior = posicao;
        if (esquerda <= this.index) {
           maior = esquerda;
           if (direita <= this.index) {
              maior = getBiggestElementIndex(this.heap, esquerda, direita);
           }
        }
        maior = getBiggestElementIndex(this.heap, posicao, maior);
  
        if (posicao != maior) {
           Util.swap(this.heap, posicao, maior);
           this.heapify(maior);
       }
    }
 
    /**
     * Given an array and two indexes, this method returns the index of the biggest of the two
     * elements (with the indexes) based on the heap's comparator.
     *
     * @param array       Array of elements.
     * @param firstIndex  Index of the first element
     * @param secondIndex Index of the second element
     * @return The index of the biggest element.
     * Important: if the elements are equal, it returns the second element's index.
     */
    private int getBiggestElementIndex(T[] array, int firstIndex, int secondIndex) {
       return (this.comparator.compare(array[firstIndex], array[secondIndex]) > EQUAL) ? firstIndex : secondIndex;
    }
 
    @Override
    public void insert(T element) {
       // ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
       if (index == heap.length - 1) {
          heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
       }
 
       if (element != null) {
          this.heap[++this.index] = element;
 
          int position = this.index;
          while (getBiggestElementIndex(this.heap, position, this.parent(position)) == position
                && this.parent(position) != position) {
             Util.swap(this.heap, position, this.parent(position));
             position = this.parent(position);
          }
       }
    }
 
    @Override
    public void buildHeap(T[] array) {
       this.heap = array;
       this.index = array.length - 1;
       for (int pos = this.parent(array.length - 1); pos >= FIRST_INDEX; pos--) {
          this.heapify(pos);
       }
    }
 
    @Override
    public T extractRootElement() {
       T element = this.rootElement();
       this.remove(FIRST_INDEX);
       return element;
    }
 
    /**
     * Removes an element of the heap.
     *
     * @param elementIndex The index of the element to be removed.
     */
    private void remove(int elementIndex) {
       if (this.index >= FIRST_INDEX) {
          Util.swap(this.heap, elementIndex, this.index--);
          this.heapify(elementIndex);
       }
    }
 
    @Override
    public T rootElement() {
       return this.heap[FIRST_INDEX];
    }
 
    @Override
     public T[] heapsort(T[] array) {
         Comparator<T> comparator = this.comparator;
 
         this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
         this.comparator = (a, b) -> b.compareTo(a);
         this.index = -1;
 
         for (T element : array) this.insert(element);
 
         T[] newArray = (T[]) (new Comparable[this.size()]);
         for (int index = FIRST_INDEX; index < newArray.length; index++) {
             newArray[index] = this.extractRootElement();
         }
 
         this.comparator = comparator;
         return newArray;
     }
 
    @Override
    public int size() {
       return this.index + 1;
    }
 
    public Comparator<T> getComparator() {
       return comparator;
    }
 
    public void setComparator(Comparator<T> comparator) {
       this.comparator = comparator;
       this.buildHeap(Arrays.copyOf(this.heap, this.size()));
    }
 
    public T[] getHeap() {
       return heap;
    }
 
}