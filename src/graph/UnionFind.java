/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.LinkedHashMap;

/**
 *
 * @author Christian
 */
public class UnionFind
{

  private LinkedHashMap<Integer, VertexUnionFindDecorator> vertices = new LinkedHashMap<Integer, VertexUnionFindDecorator>();

  public VertexUnionFindDecorator getVertex(Integer id)
  {
    return vertices.get(id);
  }

  public void addVertex(VertexUnionFindDecorator v)
  {
    vertices.put(v.getId(),v);
  }
}
