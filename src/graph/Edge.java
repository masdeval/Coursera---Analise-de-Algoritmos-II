/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

/**
 *
 * @author Christian
 */
class Edge extends GraphStructure implements EdgeInterface
{

  VertexInterface v1 = null;
  VertexInterface v2 = null;
  Float weight = 0f;

  Edge(Integer x)
  {
    super(x);
  }

  @Override
  public String toString()
  {
    return " edge = " + this.getId();
  }

  @Override
  public EdgeInterface clone()
  {
    Edge e = new Edge(this.getId());
    e.v1 = this.v1.clone();
    e.v2 = this.v2.clone();
    return e;
  }

  @Override
  public Float getWeight()
  {
    return this.weight;
  }

  @Override
  public void setWeight(Float w)
  {
    this.weight = w;
  }

  public VertexInterface getV1()
  {
    return v1;
  }

  public VertexInterface getV2()
  {
    return v2;
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

}
