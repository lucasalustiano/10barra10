package adt.linkedList.set;

import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

public class SetLinkedListImpl<T> extends SingleLinkedListImpl<T> implements SetLinkedList<T> {

   //	by: MatheusBorges
   //	ESSA CACETA REMOVE A PRIMEIRA OCORR NCIA, SE VIRE PRA MODIFICAR E DEIXAR A PRIMEIRA
   // 	OCORR NCIA E EXCLUIR APENAS AS OCORR NCIAS POSTERIORES :)
   //	public static void main(String[] args) {
   //		
   //		SetLinkedListImpl<Integer> listaUm = new SetLinkedListImpl<>();
   //		
   //		listaUm.insert(5);
   //		listaUm.insert(1);
   //		listaUm.insert(5);
   //		listaUm.insert(6);
   //		listaUm.insert(4);
   //		listaUm.insert(5);
   //		listaUm.insert(3);
   //		listaUm.insert(6);
   //		listaUm.insert(2);
   //		
   //		System.out.println("Criou conjunto 1");
   //		System.out.println(Arrays.toString(listaUm.toArray()));
   //
   //		listaUm.removeDuplicates();
   //		
   //		System.out.println("Removeu Duplicatas conjunto 1");
   //		System.out.println(Arrays.toString(listaUm.toArray()));
   //		
   //		
   //		SetLinkedListImpl<Integer> listaDois = new SetLinkedListImpl<>();
   //		
   //		listaDois.insert(5);
   //		listaDois.insert(9);
   //		listaDois.insert(4);
   //		listaDois.insert(1);
   //		listaDois.insert(3);
   //		listaDois.insert(2);
   //		listaDois.insert(0);
   //		
   //		System.out.println("Criou conjunto 2");
   //		System.out.println(Arrays.toString(listaDois.toArray()));
   //
   //		listaDois.removeDuplicates();
   //		
   //		System.out.println("Removeu Duplicatas conjunto 2");
   //		System.out.println(Arrays.toString(listaDois.toArray()));
   //		
   //		
   //		System.out.println("Conjunto Uni√£o entre 1 e 2");
   //		System.out.println(Arrays.toString(listaUm.union(listaDois).toArray()));
   //		
   //		
   //		System.out.println("Conjunto Intersec√ß√£o entre 1 e 2");
   //		System.out.println(Arrays.toString(listaUm.intersection(listaDois).toArray()));
   //		
   //		
   //		System.out.println("Conjunto 1 concatenado ao conjunto 2");
   //		listaUm.concatenate((listaDois));
   //		
   //		System.out.println(Arrays.toString(listaUm.toArray()));
   //		
   //	}

   @Override
   public void removeDuplicates() {

      SingleLinkedListNode<T> aux1 = this.getHead();

      while (aux1.getNext().isNIL() == false) {
         SingleLinkedListNode<T> aux2 = aux1.getNext();
         while (aux2.getNext().isNIL() == false) {

            if (aux1.getData() == aux2.getData()) {
               aux1 = aux1.getNext();
               remove(aux2.getData());
            }
            aux2 = aux2.getNext();
         }
         aux1 = aux1.getNext();
      }

   }

   @Override
   public SetLinkedList<T> union(SetLinkedList<T> otherSet) {
      SetLinkedList<T> saida = new SetLinkedListImpl<>();
      SetLinkedListImpl<T> outra = (SetLinkedListImpl<T>) otherSet;

      SingleLinkedListNode<T> auxLocal = this.getHead();
      SingleLinkedListNode<T> auxOutra = outra.getHead();

      while (!auxLocal.isNIL()) {
         saida.insert(auxLocal.getData());
         auxLocal = auxLocal.getNext();
      }

      while (!auxOutra.isNIL()) {
         saida.insert(auxOutra.getData());
         auxOutra = auxOutra.getNext();
      }

      saida.removeDuplicates();

      return saida;
   }

   @Override
   public SetLinkedList<T> intersection(SetLinkedList<T> otherSet) {
      SetLinkedList<T> saida = new SetLinkedListImpl<>();
      SetLinkedListImpl<T> outra = (SetLinkedListImpl<T>) otherSet;

      SingleLinkedListNode<T> auxLocal = this.getHead();

      while (!auxLocal.isNIL()) {
         SingleLinkedListNode<T> auxOutra = outra.getHead();
         while (!auxOutra.isNIL()) {
            if (auxLocal.equals(auxOutra)) {
               saida.insert(auxLocal.getData());
            }
            auxOutra = auxOutra.getNext();
         }
         auxLocal = auxLocal.getNext();
      }

      saida.removeDuplicates();

      return saida;
   }

   @Override
   public void concatenate(SetLinkedList<T> otherSet) {
      SingleLinkedListNode<T> auxLocal = this.getHead();
      SetLinkedListImpl<T> other = (SetLinkedListImpl<T>) otherSet;

      while (auxLocal.getNext().isNIL() == false) {
         auxLocal = auxLocal.getNext();
      }

      auxLocal.setNext(other.getHead());

      this.removeDuplicates();

   }

}
