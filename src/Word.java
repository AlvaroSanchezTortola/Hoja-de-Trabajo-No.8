/* *********************************************************** */
/*                  HOJA DE TRABAJO No.8     		       */
/*             Implementación de BST y MAPEO	               */
/*  Word.java           			    	       */
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
class Word implements Comparable<Word> {
	private String word;
	private String type;
	
	// Constructor, inicializa la palabra con su tipo
	public Word(String word, String type)
	{
		this.word=word;
		this.type=type;
	}
	
	public Word(){
		this.word= "";
		this.type="";
        }
	
	@Override
	public int compareTo(Word o){
		return this.word.compareTo(o.getWord());
            }
	
        @Override
	public boolean equals(Object obj){
		return (obj instanceof Word && getWord().equals(((Word)obj).getWord()));
	}
	
	// Metodos de acceso
	public void setWord(String word){
		this.word=word;
	}
	public void setType(String type){
		this.type=type;
	}
	public String getWord(){
		return word;
	}	
	public String getType(){
		return type;
	}
	
}