/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

/**
 * Essa classe aumenta as funcionalidades de Vertex com as caracteristicas necessarias para poder ser utilizada 
 * na classe UnionFind.
 */
public class VertexUnionFindDecorator extends GraphStructure implements VertexInterface
{
  private Vertex vertex;
  private boolean isLeader = true;
  private VertexUnionFindDecorator leader;

  public VertexUnionFindDecorator(Vertex vertex)
  {
    super(vertex.getId());
    this.vertex = vertex;
  }

  void setLeaderTrue()
  {
    isLeader = true;
  }

  void setLeaderFalse()
  {
    isLeader = false;
  }

  boolean isLeader()
  {
    return isLeader;
  }

  VertexUnionFindDecorator getLeader()
  {
    if (isLeader)
    {
      return this;
    }
    else
    {
      return (this.leader).getLeader();
    }
  }

  void setLeader(VertexUnionFindDecorator v)
  {
    leader = v;
  }
  
  @Override
  public void addEdge(EdgeInterface e)
  {
    this.vertex.addEdge(e);
  }

  @Override
  public void addVertex(VertexInterface v)
  {
    this.vertex.addVertex(v);

  }

  @Override
  public Integer getId()
  {
    return this.vertex.getId();
  }

  @Override
  public boolean isActive()
  {
    return this.vertex.isActive();
    
  }

  @Override
  public boolean isVisited()
  {
    return this.vertex.isVisited();
  }

 
  @Override
  public void setActive(boolean b)
  {
    this.vertex.setActive(b);
  }

  @Override
  public void setVisited(boolean b)
  {
    this.vertex.setVisited(b);
  }

  @Override
  public int compareTo(Object t)
  {
    return this.vertex.compareTo(t);
  }

  @Override
  public VertexInterface clone()
  {
    return this.vertex.clone();
  }

  @Override
  public boolean containEdgeAdjacent(EdgeInterface e)
  {
    return this.vertex.containEdgeAdjacent(e);
  }

 

  @Override
  public boolean containVertexAdjacent(VertexInterface v)
  {
    return this.vertex.containVertexAdjacent(v);
  }

  @Override
  public Vertex getVertex()
  {
    return this.vertex;
  }
  
  @Override
  public String toString()
  {
    return " vertex = " + this.getId();
  }
}
