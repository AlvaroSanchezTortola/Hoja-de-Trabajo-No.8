/* *********************************************************** */
/*                  HOJA DE TRABAJO No.8     		       */
/*             Implementación de BST y MAPEO	               */
/*  WordSetFactory.java  			    	       */
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
class WordSetFactory {
    
    public static WordSet generateSet(int tipo){
        //Selección de la implementación a utilizar
        switch (tipo){
            case 1: return new SimpleSet();                    
            case 2: return new RedBlackTree();            
            case 3: return new SplayTree();            
            case 4: return new HashTableSet();            
            case 5: return new TreeMapSet();
            default: return null;                 
        }	
    }
}