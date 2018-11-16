package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;


/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is
 * sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		/*  I suppose that the parameters passed are valid. Be careful with this.
		 *  Eu suponho que os parametros passados sao validos. Tenha cuidado com isso. 
		 */
		
		/*  for each element between leftIndex and rightIndex (including them), compare them:
		 * 		if firstElement > secondElement, swap them.
		 * 	
		 * 	para cada elemento entre o leftIndex e o rightIndex (incluindo eles), compare-os:
		 * 		se firstElement > secondElement, troque-os.
		 */
		
		for (int firstElement = leftIndex; firstElement <= rightIndex; firstElement++) {
			for (int secondElement = leftIndex; secondElement <= rightIndex; secondElement++) {
				if (array[firstElement].compareTo(array[secondElement]) < 0) {
					Util.swap(array, firstElement, secondElement);
				}
			}
		}		
	}
}
