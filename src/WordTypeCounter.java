/* *********************************************************** */
/*                  HOJA DE TRABAJO No.8     		       */
/*             Implementación de BST y MAPEO	               */
/*  WordTypeCounter.java  			    	       */
/*  Autor: 						       */
/* 	     Olga Lucía Cobaquil, 13020                        */
/*           Álvaro Sánchez Tórtola, 13657             	       */
/*  Fecha: 09/10/2014					       */
/*  Curso: CC2003 Algoritmos y Estructuras de Datos	       */
/* Descripción: Programa que analiza el archivo text.txt y     */
/* cuenta las palabras de acuerdo a su tipo, utilizando varios */
/* tipos de mapeo para realizar la busqueda.                   */
/* *********************************************************** */

/*
    ** Archivos originales proporcionados por Eduardo Castellanos 
    para el curso de Algoritmos y Estructuras de Datos. **
*/

import java.io.*;
import java.util.Scanner;

class WordTypeCounter {
    public static void main(String[] args) throws Exception { 		
        // Declaracion e inicializacion de variables.
        // el primer parametro indica el nombre del archivo con las definiciones de las palabras
        File wordFile = new File("Word.txt");

        // el segundo parametro indica el nombre del archivo que tiene el texto a analizar
        File textFile = new File("text.txt");

        // el tercer parametro sirve para seleccionar la implementacion que se usara para
        // guardar el conjunto de palabras. Use el valor 1 para indicar que se empleara
        // la implementacion SimpleSet que acompa�a esta tarea.
        // Para el resto de implementaciones: 
        //  2 Red Black Tree
        //  3 Splay Tree
        //  4 Hash Table
        //  5 TreeMap (de java collection framework      

        System.out.println("Seleccione el método a utilizar:"+"\n"+"1. SimpleSet"+"\n"+"2. RedBlackTree"+"\n"+"3. SplayTree"+"\n"+"4. HashTable"+"\n"+"5. TreeMap");
        Scanner scaner = new Scanner (System.in);
        String seleccion = scaner.next();

        int implementacion = Integer.parseInt(seleccion);

        BufferedReader wordreader;
        BufferedReader textreader;

        int verbs=0;
        int nouns=0;
        int adjectives=0;
        int adverbs=0;
        int gerunds=0;

        long starttime;
        long endtime;

        // Verificar que los dos parmetros que se pasaron sean archivos que existen
        if(wordFile.isFile() && textFile.isFile()) {
                // Leer archivos
                try
                {
                        wordreader = new BufferedReader(new FileReader(wordFile));
                        textreader = new BufferedReader(new FileReader(textFile));
                }
                catch (Exception ex)
                {
                        System.out.println("Error al leer!");
                        return;
                }

                // Crear un WordSet para almacenar la lista de palabras
                // se selecciona la implementacion de acuerdo al tercer parametro pasado en la linea
                // de comando
                // =====================================================
                WordSet words =  WordSetFactory.generateSet(implementacion);
                // =====================================================

                String line = null;
                String[] wordParts;

                // leer el archivo que contiene las palabras y cargarlo al WordSet.
                starttime = System.currentTimeMillis();
                line = wordreader.readLine();
                while(line!=null)
                {
                        wordParts = line.split("\\.");  // lo que esta entre comillas es una expresi�n regular.
                        if(wordParts.length == 2)
                        {
                                words.add(new Word(wordParts[0].trim(),wordParts[1].trim()));
                        }
                        line = wordreader.readLine();
                }
                wordreader.close();
                endtime = System.currentTimeMillis();

                System.out.println("Palabras cargadas en " + (endtime-starttime) + " ms.");

                // Procesar archivo de texto
                starttime = System.currentTimeMillis();
                line = textreader.readLine();
                String[] textParts;
                Word currentword;
                Word lookupword = new Word();

                while(line!=null)
                {
                        // Separar todas las palabras en la l�nea.
                        textParts = line.split("[^\\w-]+"); // utilizar de separador cualquier caracter que no sea una letra, n�mero o gui�n.

                        // Revisar cada palabra y verificar de que tipo es. 
                        for(int i=0;i<textParts.length;i++)
                        {
                                lookupword.setWord(textParts[i].trim().toLowerCase());
                                currentword = words.get(lookupword);
                                if(currentword != null)
                                {
                                        if(currentword.getType().equals("v-d") || currentword.getType().equals("v") || currentword.getType().equals("q"))
                                                verbs++;
                                        else if(currentword.getType().equals("g") )
                                                gerunds++;
                                        else if(currentword.getType().equals("a-s") || currentword.getType().equals("a-c") || currentword.getType().equals("a"))
                                                adjectives++;
                                        else if(currentword.getType().equals("e"))
                                                adverbs++;
                                        else 
                                                nouns++;
                                }
                        }

                        line = textreader.readLine();
                }
                textreader.close();
                endtime = System.currentTimeMillis();
                System.out.println("Texto analizado en " + (endtime-starttime) + " ms.");

                // Presentar estad�sticas
                System.out.println("El texto tiene:");
                System.out.println(verbs + " verbos");
                System.out.println(nouns + " sustantivos");
                System.out.println(adjectives + " adjetivos");
                System.out.println(adverbs + " adverbios");
                System.out.println(gerunds + " gerundios");

        }
        else
        {
                System.out.println("No encuentro los archivos :'( ");
        }
    }
}
