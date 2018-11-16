package sorting.variationsOfSelectionsort;

import sorting.AbstractSorting;
import util.Util;

/**
 * This algorithm applies two selection sorts simultaneously. In a same
 * iteration, a selection sort pushes the greatest elements to the right and
 * another selection sort pushes the smallest elements to the left. At the end
 * of the first iteration, the smallest element is in the first position (index
 * 0) and the greatest element is the last position (index N-1). The next
 * iteration does the same from index 1 to index N-2. And so on. The execution
 * continues until the array is completely ordered.
 */
public class SimultaneousSelectionsort<T extends Comparable<T>> extends AbstractSorting<T> {
	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		/*  I suppose that the parameters passed are valid. Be careful with this.
		 *  Eu suponho que os parametros passados sao validos. Tenha cuidado com isso. 
		 */
		
		/*  for each i between leftIndex and rightIndex (including them):
		 * 		smallest = i
		 * 		greatest = rightIndex - 1
		 * 
		 * 		for each j between smallest and rightIndex (including rightIndex):
		 * 			if array[smallest] > array[j]:
		 * 				smallest = j
		 * 	
		 * 		for each k between greatest and i:
		 * 			if array[greatest] < array[k]:
		 * 				greatest = k
		 * 
		 * 		swap array[smallest] and array[leftIndex + i]
		 * 		swap array[greatest] and array[rightIndex - i]
		 * 
		 * 	
		 * 	para cada i entre o leftIndex e o rightIndex (incluindo rightIndex):
		 * 		menor = i
		 * 		maior = rightIndex - 1
		 * 
		 * 		para cada j entre menor e rightIndex (incluindo rightIndex):
		 * 			se array[menor] > array[j]:
		 * 				menor = j
		 * 
		 * 		para cada k entre maior e i:
		 * 			se array[maior] < array[k]:
		 * 				maior = k
		 * 
		 * 		swap array[menor] e array[leftIndex + i]
		 * 		swap array[maior] e array[rightIndex - i]
		 */
		
		
		for (int i = leftIndex; i <= rightIndex; i++) {
			int smallest = i;
			int greatest = rightIndex - i;
			
			for (int j = smallest; j < rightIndex; j++) {
				if (array[j].compareTo(array[smallest]) < 0) {
					smallest = j;
				}
			}
			
			for (int k = greatest; k > i; k--) {
				if (array[k].compareTo(array[greatest]) > 0) {
					greatest = k;
				}
			}
			
			Util.swap(array, smallest, leftIndex + i);
			Util.swap(array, greatest, rightIndex - i);
			
		}
	}
}
