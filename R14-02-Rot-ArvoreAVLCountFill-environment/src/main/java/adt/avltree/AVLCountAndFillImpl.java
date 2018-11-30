package adt.avltree;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends AVLTreeImpl<T> implements AVLCountAndFill<T> {
  
     private int LLcounter;
     private int LRcounter;
     private int RRcounter;
     private int RLcounter;
  
     public AVLCountAndFillImpl() {
  
     }
  
     @Override
     public int LLcount() {
        return LLcounter;
     }
  
     @Override
     public int LRcount() {
        return LRcounter;
     }
  
     @Override
     public int RRcount() {
        return RRcounter;
     }
  
     @Override
     public int RLcount() {
        return RLcounter;
     }
  
     @Override
     public void fillWithoutRebalance(T[] array) {
        if (array != null && array.length > 0) {
           if (!this.isEmpty()) {
              array = clearTree(array);
           }
           sort(array, 0, array.length - 1);
           Deque<Integer> queue = new LinkedList<Integer>();
  
           int leftIndex = 0;
           int rightIndex = array.length - 1;
           int middle = (leftIndex + rightIndex) / 2;
           this.insert(array[middle]);
  
           if (leftIndex <= middle - 1) {
              queue.addLast(leftIndex);
              queue.addLast(middle - 1);
           }
  
           if (rightIndex >= middle + 1) {
              queue.addLast(middle + 1);
              queue.addLast(rightIndex);
           }
  
           while (!queue.isEmpty()) {
              leftIndex = queue.removeFirst();
              rightIndex = queue.removeFirst();
              middle = (leftIndex + rightIndex) / 2;
              this.insert(array[middle]);
  
              if (leftIndex <= middle - 1) {
                 queue.addLast(leftIndex);
                 queue.addLast(middle - 1);
              }
  
              if (rightIndex >= middle + 1) {
                 queue.addLast(middle + 1);
                 queue.addLast(rightIndex);
              }
           }
  
        }
     }
  
     @SuppressWarnings("unchecked")
     private T[] clearTree(T[] array) {
        T[] order = this.order();
        T[] aux = (T[]) new Comparable[array.length + order.length];
        for (int i = 0; i < array.length; i++) {
           aux[i] = array[i];
        }
        for (int i = 0; i < order.length; i++) {
           aux[i + array.length] = order[i];
        }
        array = aux;
        root = new BSTNode<T>();
        return array;
     }
  
    private void sort(T[] array, int leftIndex, int rightIndex) {
       if (array != null || leftIndex >= 0 || rightIndex < array.length) {
          if (leftIndex + 1 <= rightIndex) {
 
             int pos_pivot = partition(array, leftIndex, rightIndex);
             sort(array, leftIndex, pos_pivot - 1);
             sort(array, pos_pivot + 1, rightIndex);
          }
       }
    }
 
    private int partition(T[] array, int leftIndex, int rightIndex) {
 
       Random random = new Random();
       int radIndex = random.nextInt(rightIndex - leftIndex) + leftIndex;
       int pos_pivot = leftIndex;
       Util.swap(array, radIndex, pos_pivot);
       int j = leftIndex;
 
       for (int i = leftIndex + 1; i <= rightIndex; i++) {
          if (array[i].compareTo(array[pos_pivot]) <= 0) {
             j++;
             Util.swap(array, i, j);
          }
       }
       Util.swap(array, j, pos_pivot);
       return j;
    }
 
    @Override
    protected void rebalance(BSTNode<T> node) {
       if (node != null && !node.isLeaf()) {
          int balance = calculateBalance(node);
          if (balance > LEFT_BALANCING_FACTOR) {
             if (calculateBalance((BSTNode<T>) node.getLeft()) < 0) {
                leftRotation((BSTNode<T>) node.getLeft());
                LRcounter += 1;
             } else {
                LLcounter += 1;
             }
             rightRotation(node);
          } else if (balance < RIGHT_BALANCING_FACTORL) {
             if (calculateBalance((BSTNode<T>) node.getRight()) > 0) {
                rightRotation((BSTNode<T>) node.getRight());
                RLcounter += 1;
             } else {
                RRcounter += 1;
             }
             leftRotation(node);
          }
       }
    }
 
 }
