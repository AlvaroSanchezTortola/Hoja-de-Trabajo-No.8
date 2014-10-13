/* *********************************************************** */
/*                  HOJA DE TRABAJO No.8     		       */
/*             Implementación de BST y MAPEO	               */
/*  SimpleSet.java           			    	       */
/*  Autor: 						       */
/* 	     Olga Lucía Cobaquil, 13020                        */
/*           Álvaro Sánchez Tórtola, 13657             	       */
/*  Fecha: 09/10/2014					       */
/*  Curso: CC2003 Algoritmos y Estructuras de Datos	       */
/* *********************************************************** */

/*
    ** Archivos originales proporcionados por Eduardo Castellanos 
    para el curso de Algoritmos y Estructuras de Datos. **
*/
import java.util.ArrayList;

class SimpleSet implements WordSet{
	private final ArrayList<Word> base;
	//Contructor de la clase
	public SimpleSet()
	{
		base = new ArrayList<>();
	}
	
        @Override
	public Word get(Word word)
	{
		int index = base.indexOf(word);
		if(index == -1) return null;
		return base.get(index);
	}
	
        @Override
	public void add(Word wordObject)
	{
		base.add(wordObject);
	}
}