/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public interface VertexInterface extends Comparable
{

  Integer getId();

  String toString();

  void addEdge(EdgeInterface e);

  void addVertex(VertexInterface v);

  boolean isActive();

  void setActive(boolean b);

  void setVisited(boolean b);

  boolean isVisited();

  int compareTo(Object t);

  VertexInterface clone();

  boolean containEdgeAdjacent(EdgeInterface e);

  //public ArrayList<EdgeInterface> getEdgesAdjacents();
  boolean containVertexAdjacent(VertexInterface v);

  //public ArrayList<VertexInterface> getVerticesAdjacents();
  //alguns metodos de Vertex quero que sejam acessados apenas por outras classes do mesmo pacote
  //entao disponibilizo acesso aa classe concreta Vertex
  Vertex getVertex();

  @Override
  public int hashCode();

  @Override
  public boolean equals(Object o);

}
