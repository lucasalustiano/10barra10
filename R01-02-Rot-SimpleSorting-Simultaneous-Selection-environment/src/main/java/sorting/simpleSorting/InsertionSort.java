package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * As the insertion sort algorithm iterates over the array, it makes the
 * assumption that the visited positions are already sorted in ascending order,
 * which means it only needs to find the right position for the current element
 * and insert it there.
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		/*  I suppose that the parameters passed are valid. Be careful with this.
		 *  Eu suponho que os parametros passados sao validos. Tenha cuidado com isso. 
		 */
		
		/*  for each element between leftIndex + 1 and rightIndex (including rightIndex):
		 * 		while element > 0 and element < element - 1
		 * 			swap them
		 * 			decrease element by one
		 * 	
		 * 	para cada elemento entre o leftIndex + 1 e o rightIndex (incluindo rightIndex), compare-os:
		 * 		enquanto element > 0 e element < element - 1
		 * 			troque-os
		 * 			decremente -1 de element
		 */
		
		for (int element = leftIndex + 1; element <= rightIndex; element++) {
			while (element > 0 && array[element-1].compareTo(array[element]) > 0) {
				Util.swap(array, element, element-1);
				element--;
			}
		}
	}
}
