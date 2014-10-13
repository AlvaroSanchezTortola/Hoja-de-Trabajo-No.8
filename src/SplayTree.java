/* *********************************************************** */
/*                  HOJA DE TRABAJO No.8     		       */
/*             Implementación de BST y MAPEO	               */
/*  SplayTree.java           			    	       */
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

public class SplayTree<A extends Comparable<? super A>> implements WordSet {    
    private final ArrayList<Word> base;
    //Contructor de la clase
    public SplayTree() {
    sentinel = new Node(null);
    base = new ArrayList<>();
  }
    //Obtiene el valor de un nodo en el árbol
    @Override
    public Word get(Word word){
		int index = base.indexOf(word);
		if(index == -1) return null;
		return base.get(index);
    }
   
    //Agrega un nuevo nodo al árbol 
    @Override
   public void add(Word wordObject){
		base.add(wordObject);
    }
    //Clase nodo del SplayTree
   private class Node {
        private Node parent;
        private Node left;
        private Node right;
        public A contents;
    public Node(A contents) {
      this.contents = contents;
    }
    public void setLeft(Node child) {
      left = child;
      if (child != null) {
        child.parent = this;
      }
    }
    public void setRight(Node child) {
      right = child;
      if (child != null) {
        child.parent = this;
      }
    }
    public Node parent() {
        return parent;
    }
    public Node left() {
        return left;
    }
    public Node right() {
        return right;
    }
  }
   //Rotación en el árbol 
  private final Node sentinel;  
  private void rotateUp(Node child) {
    assert child != null &&
           child.parent() != null &&
           child.parent().parent() != null;

    Node parent      = child.parent();
    Node grandparent = parent.parent();

    if (grandparent.left() == parent) {
      grandparent.setLeft(child);
    } else {
      grandparent.setRight(child);
    }
    if (parent.left() == child) {
      parent.setLeft(child.right());
      child.setRight(parent);
    } else {
      parent.setRight(child.left());
      child.setLeft(parent);
    }
  }
  //Movimiento splay en el árbol
  private void splay(Node toTop) {
    assert toTop != null;

    if (toTop == sentinel) {
      return;
    }

    while (toTop.parent() != sentinel) {
      Node parent      = toTop.parent();
      Node grandparent = parent.parent();

      if (grandparent == sentinel) {
        rotateUp(toTop);
      } else {
        if (grandparent.left()  == parent && parent.left()  == toTop
              ||
            grandparent.right() == parent && parent.right() == toTop) {
          rotateUp(parent);
          rotateUp(toTop);
        } else {
          rotateUp(toTop);
          rotateUp(toTop);
        }
      }
    }
  }

  private class Match {
    public boolean matchFound;    
    public Node node;
    public boolean smallerThanNode;
  
    public Match
      (boolean matchFound, Node node, boolean smallerThanNode) {
      assert node != null;
      assert node != sentinel || smallerThanNode == true;

      this.matchFound      = matchFound;
      this.node            = node;
      this.smallerThanNode = smallerThanNode;
    }
  }


  private Match findMatch(A a) {
    assert a != null;

    Node n = sentinel.left();

    if (n == null) {
      return new Match(false, sentinel, true);
    }

    while (true) {
      int c = a.compareTo(n.contents);
      if (c == 0) {
        return new Match(true, n, false);
      } else if (c < 0) {
        if (n.left()  == null) return new Match(false, n, true);
        else                   n = n.left();
      } else {
        if (n.right() == null) return new Match(false, n, false);
        else                   n = n.right();
      }
    }
  }
  private Node find(A a) {
    assert a != null;

    Match m = findMatch(a);
    splay(m.node);
    return m.matchFound ? m.node : null;
  }

  public boolean member(A a) {
    assert a != null;

    return find(a) != null;
  }

  public void insert(A a) {
    assert a != null;

    Match m = findMatch(a);

    if (m.matchFound) {
      m.node.contents = a;

      splay(m.node);
    } else {
      Node parent  = m.node;
      Node newNode = new Node(a);

      if (m.smallerThanNode) {
        parent.setLeft(newNode);
      } else {
        parent.setRight(newNode);
      }

      splay(newNode);
    }
  }

  private Node findMinNode() {
    Node n = sentinel.left();

    if (n == null) {
      throw new NoSuchElementException("empty tree");
    }

    while (n.left() != null) {
      n = n.left();
    }

    splay(n);

    return n;
  }

  public A findMin() {
    return findMinNode().contents;
  }

  public A deleteMin() {
    Node minimum = findMinNode();

    sentinel.setLeft(minimum.right());

    return minimum.contents;
  }

  public A delete(A a) {
    assert a != null;

    Node n = find(a);

    if (n == null) {
      throw new NoSuchElementException(a + " not found");
    } else {
      if (n.right() == null) {
        sentinel.setLeft(n.left());
      } else {
        sentinel.setLeft(n.right());

        findMin();
        sentinel.left().setLeft(n.left());
      }

      return n.contents;
    }
  }

}