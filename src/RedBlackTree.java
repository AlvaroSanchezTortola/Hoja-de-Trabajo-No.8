/* *********************************************************** */
/*                  HOJA DE TRABAJO No.8     		       */
/*             Implementación de BST y MAPEO	               */
/*  RedBlackTree.java           			       */
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

public class RedBlackTree implements WordSet{
    
   static final int red = 0;
   static final int black = 1;
   private static final int double_black = 2;
   private static final int negative_red = -1;   

   //Contructor de la clase con raíz inicial vacía
   public RedBlackTree(){
       root = null;
   }
   //Metodo para agregar un nuevo nodo al árbol
   public void add(Word wordObject){  
      Node newNode = new Node();
      newNode.data = wordObject;
      newNode.left = null;
      newNode.right = null;
      if (root == null) {root = newNode;}else {root.addNode(newNode);}
      fixAfterAdd(newNode);
   }
   //Reordenamiento si se encuetnran dos nodos rojos consecutivos
   private void fixDoubleRed(Node child){
      Node parent = child.parent;      
      Node grandParent = parent.parent;
      if (grandParent == null) { parent.color = black; return; }
      Node n1, n2, n3, t1, t2, t3, t4;
      if (parent == grandParent.left){
         n3 = grandParent; t4 = grandParent.right;
         if (child == parent.left){
            n1 = child; n2 = parent;
            t1 = child.left; t2 = child.right; t3 = parent.right;
         }else{
            n1 = parent; n2 = child;
            t1 = parent.left; t2 = child.left; t3 = child.right;
         }
      }else{
         n1 = grandParent; t1 = grandParent.left;
         if (child == parent.left){
            n2 = child; n3 = parent;
            t2 = child.left; t3 = child.right; t4 = parent.right;
         }else{
            n2 = parent; n3 = child;
            t2 = parent.left; t3 = child.left; t4 = child.right;
         }        
      }
     
      if (grandParent == root){
         root = n2;
         n2.parent = null;
      }else{grandParent.replaceWith(n2);}
     
      n1.setLeftChild(t1);
      n1.setRightChild(t2);
      n2.setLeftChild(n1);
      n2.setRightChild(n3);
      n3.setLeftChild(t3);
      n3.setRightChild(t4);
      n2.color = grandParent.color - 1;
      n1.color = black;
      n3.color = black;

      if (n2 == root){root.color = black;}
      else if (n2.color == red && n2.parent.color == red){fixDoubleRed(n2);}
   }
   //Obtención del valor de un nodo en el árbol
   public Word get(Word word){
       Node current = root;
      while (current != null){
         int d = current.data.compareTo(word);
         if (d == 0)
             return (Word) current.data;
         else if (d > 0)
             current = current.left;
         else
             current = current.right;
      }
      return null;
   }
   Node root;
      
   //Reordenamiento de los nodos a partir de un nuevo balanceo
   private void bubbleUp(Node parent){
      if (parent == null) { return; }
      parent.color++;
      parent.left.color--;
      parent.right.color--;
           
      Node child = parent.left;
      if (child.color == negative_red) { fixNegativeRed(child); return; }
      else if (child.color == red){
         if (child.left != null && child.left.color == red){
            fixDoubleRed(child.left);
            return;
         }
         if (child.right != null && child.right.color == red){fixDoubleRed(child.right); return;}
      }
   
      child = parent.right;
      if (child.color == negative_red) { fixNegativeRed(child); return; }
      else if (child.color == red){
         if (child.left != null && child.left.color == red){
            fixDoubleRed(child.left);
            return;
         }
		 if (child.right != null && child.right.color == red){
            fixDoubleRed(child.right);
            return;
         }
      }
         
      if (parent.color == double_black){
         if (parent.parent == null) { parent.color = black; }
         else { bubbleUp(parent.parent); }
      }
   }
   //Cambia color de nodos al agregar un nodo nuevo
   private void fixAfterAdd(Node newNode){
      if (newNode.parent == null){newNode.color = black;}
      else{
         newNode.color = red;
         if (newNode.parent.color == red) { fixDoubleRed(newNode); }
      }
   }     
   //Reordenamiento para un nodo rojo negativo
   private void fixNegativeRed(Node negRed){    
      Node n1, n2, n3, n4, t1, t2, t3, child;
      Node parent = negRed.parent;
      if (parent.left == negRed){
         n1 = negRed.left;
         n2 = negRed;
         n3 = negRed.right;
         n4 = parent;
         t1 = n3.left;
         t2 = n3.right;
         t3 = n4.right;
         n1.color = red;
         n2.color = black;
         n4.color = black;
         n2.setRightChild(t1);
         Comparable temp = n4.data; n4.data = n3.data; n3.data = temp;
         n3.setLeftChild(t2);
         n3.setRightChild(t3);
         n4.setRightChild(n3);
         child = n1;
      }else{
         n4 = negRed.right;
         n3 = negRed;
         n2 = negRed.left;
         n1 = parent;
         t3 = n2.right;
         t2 = n2.left;
         t1 = n1.left;
         n4.color = red;
         n3.color = black;
         n1.color = black;
         n3.setLeftChild(t3);
         Comparable temp = n1.data; n1.data = n2.data; n2.data = temp;
         n2.setRightChild(t2);
         n2.setLeftChild(t1);
         n1.setLeftChild(n2);
         child = n4;
      }
           
      if (child.left != null && child.left.color == red){
         fixDoubleRed(child.left);
         return;
      }
      if (child.right != null && child.right.color == red){
         fixDoubleRed(child.right);  
      }
   }

}
