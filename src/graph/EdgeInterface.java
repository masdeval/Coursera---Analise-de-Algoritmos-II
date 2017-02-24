/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

/**
 *
 * @author admin
 */
public interface EdgeInterface extends Comparable
{

  String toString();

  Integer getId();

  public EdgeInterface clone();

  Float getWeight();

  void setWeight(Float w);
  
  VertexInterface getV1();
  
  VertexInterface getV2();
  
  @Override
  public int hashCode();

  @Override
  public boolean equals(Object o);

}
