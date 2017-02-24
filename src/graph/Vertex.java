/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Christian
 */
class Vertex extends GraphStructure implements VertexInterface
{

  boolean isActive = true;
  boolean isVisited = false;
  
  Vertex(Integer x)
  {
    super(x);
  }
  
  //Um vertice sabe quais arestas incidem nele
  private ArrayList<EdgeInterface> edgesAdjacents = new ArrayList<EdgeInterface>();
  //Um vertice sabe quais vertcies sao adjacentes a ele
  private ArrayList<VertexInterface> verticesAdjacents = new ArrayList<VertexInterface>();
  
 
  public Vertex getVertex()
  {
    return this;
  }
  
  public boolean containEdgeAdjacent(EdgeInterface e)
  {
    return edgesAdjacents.contains(e);
  }

  ArrayList<EdgeInterface> getEdgesAdjacents()
  {
    return (ArrayList<EdgeInterface>) edgesAdjacents;
  }
      
  public boolean containVertexAdjacent(VertexInterface v)
  {
    return verticesAdjacents.contains(v);
  }
  
  ArrayList<VertexInterface> getVerticesAdjacents()
  {
    return (ArrayList<VertexInterface>) verticesAdjacents;
  }
  
  public void addEdge(EdgeInterface e)
  {
    //if (!edgesAdjacents.contains(e))
    {
      edgesAdjacents.add(e);
    }
  }

  public void addVertex(VertexInterface v)
  {
    //if (!verticesAdjacents.contains(v))
    {
      verticesAdjacents.add(v);
    }
  }

  @Override
  public String toString()
  {
    return " vertex = " + this.getId();
  }

  @Override
  public VertexInterface clone()
  {
    Vertex v = new Vertex(this.getId());
     //   v.isActive = this.isActive;
    //  if (this.sv != null)
    {
      //    v.sv = this.sv.clone();
    }
    v.edgesAdjacents = new ArrayList<EdgeInterface>();
    Iterator<EdgeInterface> arestas = this.edgesAdjacents.iterator();
    while (arestas.hasNext())
    {
      v.edgesAdjacents.add(arestas.next().clone());
    }
    v.verticesAdjacents = new ArrayList<VertexInterface>();
    Iterator<VertexInterface> vertices = this.verticesAdjacents.iterator();
    while (vertices.hasNext())
    {
      v.verticesAdjacents.add(vertices.next().clone());
    }
    return v;
  }

  @Override
  public int compareTo(Object t)
  {
    if (!(t instanceof GraphStructure))
    {
      throw new RuntimeException("Impossível comparar " + t + " com " + this);
    }
    GraphStructure v = (GraphStructure) t;
    if (this.getId() > v.getId())
    {
      return 1;
    }
    else if (this.getId() == v.getId())
    {
      return 0;
    }
    else
    {
      return -1;
    }
  }

  public boolean isActive()
  {
    return isActive;
  }
  
  public void setActive(boolean b)
  {
    this.isActive = b;
  }
  
  public boolean isVisited()
  {
    return isVisited;
  }
  
  public void setVisited(boolean b)
  {
    this.isVisited = b;
  }
}
