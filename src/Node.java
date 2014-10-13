/* *********************************************************** */
/*                  HOJA DE TRABAJO No.8     		       */
/*             Implementación de BST y MAPEO	               */
/*  Node.java                    			       */
/*  Autor: 						       */
/* 	     Olga Lucía Cobaquil, 13020                        */
/*           Álvaro Sánchez Tórtola, 13657             	       */
/*  Fecha: 09/10/2014					       */
/*  Curso: CC2003 Algoritmos y Estructuras de Datos	       */
/* *********************************************************** */

/*
    ** Archivos originales proporcionados por Mark Allen Weiss     
    para el curso de Algoritmos y Estructuras de Datos. **
*/

public class Node{  
      public int color;  
      public Comparable data;
      public Node parent;     
      public Node right;
      public Node left;

      //Constructor de la clase
      public Node() {}  
      //Metodo para agregar un nuevo nodo al árbol RedBlack
      public void addNode(Node newNode){  
         int comp = newNode.data.compareTo(data);
         if (comp < 0){  
            if (left == null){
               left = newNode;
               left.parent = this;
            }
            else { left.addNode(newNode); }
         }
         else if (comp > 0){  
            if (right == null){
               right = newNode;
               right.parent = this;
            }
            else { right.addNode(newNode); }
         }
      }
      //Agrega nodo hijo a la derecha del padre
      public void setRightChild(Node child){
         right = child;
         if (child != null) { child.parent = this; }
      }
      //Agrega nodo hijo a la izquierda del padre
      public void setLeftChild(Node child){
         left = child;
         if (child != null) { child.parent = this; }
      }	  
      //Reemplaza un nodo en el árbol RedBlack      
      public void replaceWith(Node replacement){
          if (parent == null) return;
          if (this == parent.left) parent.setLeftChild(replacement);
          else parent.setRightChild(replacement);
      }	  
   }