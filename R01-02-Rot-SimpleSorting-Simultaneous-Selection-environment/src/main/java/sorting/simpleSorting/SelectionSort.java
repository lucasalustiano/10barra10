package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The selection sort algorithm chooses the smallest element from the array and
 * puts it in the first position. Then chooses the second smallest element and
 * stores it in the second position, and so on until the array is sorted.
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		/*  I suppose that the parameters passed are valid. Be careful with this.
		 *  Eu suponho que os parametros passados sao validos. Tenha cuidado com isso. 
		 */
		
		/*  for each i between leftIndex and rightIndex (including them):
		 * 		smallest = i
		 * 		for each j between i + 1 and rightIndex (including rightIndex):
		 * 			if array[smallest] > array[j]:
		 * 				smallest = j
		 * 		swap array[i] and array[smallest]
		 * 	
		 * 	para cada i entre o leftIndex e o rightIndex (incluindo rightIndex):
		 * 		menor = i
		 * 		para cada j entre i + 1 e rightIndex (incluindo rightIndex):
		 * 			se array[menor] > array[j]:
		 * 				menor = j
		 * 		swap array[i] e array[menor]
		 */
		
		for (int i = leftIndex; i <= rightIndex; i++) {
			int smallest = i;
			for (int j = i + 1; j <= rightIndex; j++) {
				if (array[smallest].compareTo(array[j]) > 0) {
					smallest = j;
				}
			}
			Util.swap(array, i, smallest);
		}
	}
}
