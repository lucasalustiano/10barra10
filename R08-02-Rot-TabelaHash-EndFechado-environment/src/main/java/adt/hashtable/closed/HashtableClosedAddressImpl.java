package adt.hashtable.closed;

import java.util.LinkedList;

import adt.hashtable.hashfunction.HashFunction;
import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionFactory;
import util.Util;

public class HashtableClosedAddressImpl<T> extends AbstractHashtableClosedAddress<T> {

	/**
	 * A hash table with closed address works with a hash function with closed
	 * address. Such a function can follow one of these methods: DIVISION or
	 * MULTIPLICATION. In the DIVISION method, it is useful to change the size of
	 * the table to an integer that is prime. This can be achieved by producing such
	 * a prime number that is bigger and close to the desired size.
	 * 
	 * For doing that, you have auxiliary methods: Util.isPrime and getPrimeAbove as
	 * documented bellow.
	 * 
	 * The length of the internal table must be the immediate prime number greater
	 * than the given size (or the given size, if it is already prime). For example,
	 * if size=10 then the length must be 11. If size=20, the length must be 23. You
	 * must implement this idea in the auxiliary method getPrimeAbove(int size) and
	 * use it.
	 * 
	 * @param desiredSize
	 * @param method
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashtableClosedAddressImpl(int desiredSize, HashFunctionClosedAddressMethod method) {
		int realSize = desiredSize;

		if (method == HashFunctionClosedAddressMethod.DIVISION) {
			realSize = this.getPrimeAbove(desiredSize); // real size must the
			// the immediate prime
			// above
		}
		initiateInternalTable(realSize);
		HashFunction function = HashFunctionFactory.createHashFunction(method, realSize);
		this.hashFunction = function;
	}

	// AUXILIARY
	/**
	 * It returns the prime number that is closest (and greater) to the given
	 * number. If the given number is prime, it is returned. You can use the method
	 * Util.isPrime to check if a number is prime.
	 */
	int getPrimeAbove(int number) {
		int primeAbove = number;
		while (!Util.isPrime(primeAbove)) {
			primeAbove++;
		}

		return primeAbove;
	}

	/**
	 * Caso o elemento nao seja nulo e nao esteja presente na tabela: Eh usado a
	 * funcao de hash para encontra o indice, caso a posicao esteja vazia eh criada
	 * uma linkedlist na posicao E entao eh adicionado o elemento na linkedlist
	 * dessa posicao. O contador de elementos eh incrementado caso a insercao tenha
	 * sucesso. Caso a linkedlist na qual o elemento seja adicionado ja tenha
	 * elementos, o contador de colisoes eh incrementado.
	 */
	@Override
	public void insert(T element) {
		if (!(element == null) && search(element) == null) {
			int index = ((HashFunctionClosedAddress<T>) hashFunction).hash(element);

			if (table[index] == null) {
				LinkedList<T> list = new LinkedList<T>();
				table[index] = list;
			}

			if (!((LinkedList<T>) table[index]).isEmpty()) {
				COLLISIONS++;
			}

			((LinkedList<T>) table[index]).add(element);
			elements++;
		}
	}

	/**
	 * Checa se o elemento dado nao eh null, e checa se quando procurado na tabela,
	 * eh retornado algo diferente de null, indicando que o elemento estah na
	 * tabela. A funcao de hash encontra o indice, e o elemento eh removido da
	 * linkedlist na posicao do indice encontrado. Caso a remocao ocorra com
	 * sucesso, o contador de elementos eh decrementado.
	 */
	@Override
	public void remove(T element) {
		if (!(element == null) && !(search(element) == null)) {
			int index = ((HashFunctionClosedAddress<T>) hashFunction).hash(element);

			((LinkedList<T>) table[index]).remove(element);
			elements--;
		}
	}

	/**
	 * Caso o elemento nao esteja na tabela eh retornado null, senao: O elemento
	 * dado nao sendo nulo, eh feita a funcao de hash, em seguida eh checado se a
	 * posicao do conseguida com o hash nao esta vazia e se a linkedlist da posicao
	 * contem o elemento, se contiver, o elemento eh retornado.
	 */
	@Override
	public T search(T element) {
		T result = null;

		if (!(element == null)) {
			int index = ((HashFunctionClosedAddress<T>) hashFunction).hash(element);

			if (!(table[index] == null) && ((LinkedList<T>) table[index]).contains(element)) {
				result = element;
			}
		}

		return result;
	}

	/**
	 * Caso o elemento nao esteja na tabela eh retornado -1, senao: Eh checado se o
	 * elemento dado nao eh nulo e se ele estah na tabela (atraves do metodo
	 * search), entao o indice eh obtido atraves da funcao hash, e retornado.
	 */
	@Override
	public int indexOf(T element) {
		int result = -1;
		if (!(element == null) && !(search(element) == null)) {
			int index = ((HashFunctionClosedAddress<T>) hashFunction).hash(element);

			result = index;
		}

		return result;
	}
}
