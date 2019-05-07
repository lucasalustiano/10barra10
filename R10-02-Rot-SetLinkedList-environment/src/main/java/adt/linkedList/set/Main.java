package adt.linkedList.set;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {

        SetLinkedListImpl<Integer> listaUm = new SetLinkedListImpl<>();
        SetLinkedListImpl<Integer> lista2 = new SetLinkedListImpl<>();

        listaUm.insert(5);
        listaUm.insert(1);
        listaUm.insert(5);
        listaUm.insert(6);
        listaUm.insert(4);
        listaUm.insert(5);
        listaUm.insert(3);
        listaUm.insert(6);
        listaUm.insert(2);
        
        lista2.insert(10);
        lista2.insert(12);
        lista2.insert(14);
        lista2.insert(16);
        lista2.insert(18);
        lista2.insert(20);

        SetLinkedListImpl<Integer> l3 = (SetLinkedListImpl<Integer>) listaUm.intersection(lista2);
        SetLinkedListImpl<Integer> l4 = (SetLinkedListImpl<Integer>) lista2.intersection(listaUm);

        System.out.println(Arrays.toString(listaUm.toArray()));
        System.out.println(Arrays.toString(lista2.toArray()));
        
        System.out.println(Arrays.toString(l3.toArray()));
        System.out.println(Arrays.toString(l4.toArray()));



    }
}
