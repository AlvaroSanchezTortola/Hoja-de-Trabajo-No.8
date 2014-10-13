/* *********************************************************** */
/*                  HOJA DE TRABAJO No.8     		       */
/*             Implementación de BST y MAPEO	               */
/*  TreeMapSet.java           			    	       */
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
import java.util.*;

class TreeMapSet implements WordSet{
    private final TreeMap<String,String> base;
    //Contructor de la clase TreeMap
    public TreeMapSet(){
        base = new TreeMap();
    }
    //Metodo heredado para obtener valor de nodo del árbol
    @Override
    public Word get(Word word){ 
        if(!base.containsKey(word.getWord())){
            return null;
        }
         return new Word(base.ceilingKey(word.getWord()),base.get(base.ceilingKey(word.getWord())));        
    }    
    @Override
    //Método heredado para agregar un nuevo nodo al árbol
    public void add(Word wordObject){
      base.put(wordObject.getWord(), wordObject.getType());
    }

}
