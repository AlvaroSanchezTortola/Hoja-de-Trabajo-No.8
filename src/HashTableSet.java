/* *********************************************************** */
/*                  HOJA DE TRABAJO No.8     		       */
/*             Implementación de BST y MAPEO	               */
/*  HashTableSet.java          			    	       */
/*  Autor: 						       */
/* 	     Olga Lucía Cobaquil, 13020                        */
/*           Álvaro Sánchez Tórtola, 13657             	       */
/*  Fecha: 09/10/2014					       */
/*  Curso: CC2003 Algoritmos y Estructuras de Datos	       */
/* *********************************************************** */

/*
    ** Archivos originales proporcionados por Nils Anders Danielsson 
    para el curso de Algoritmos y Estructuras de Datos. **
*/
import java.util.*;

public class HashTableSet implements WordSet{
    private final HashMap<String,String> base;
    
    //Contructor de la clase con base inicial al nuevo HashMap
    public HashTableSet(){
        base = new HashMap();
    }
    
    /**
     *
     * @param word
     * @return
     */
    @Override
    public Word get(Word word){ 
        if(!base.containsKey(word.getWord())){
            return null;
        }
         return new Word(word.getWord(),base.get(word.getWord()));        
    }    
    
    /**
     *
     * @param wordObject
     */
    @Override
    public void add(Word wordObject){
      base.put(wordObject.getWord(), wordObject.getType());
    }
}

