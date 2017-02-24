/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author admin
 */
public class Graph
{

  private LinkedHashMap<Integer, VertexInterface> vertices = new LinkedHashMap<Integer, VertexInterface>();
  private LinkedHashMap<Integer, EdgeInterface> edges = new LinkedHashMap<Integer, EdgeInterface>();

  private int lastVertexId;
  private int lastEdgeId;

  //private int numberOfVertices;
  private int numberOfEdges;

  public Integer getNumberOfVertices()
  {

    int count = 0;

    Iterator<VertexInterface> it = vertices.values().iterator();
    while (it.hasNext())
    {
      VertexInterface v = it.next();
      if (v.isActive())
      {
        count++;
      }
    }

    return count;
  }

  public Integer getNumberOfEdges()
  {
    return edges.size();
  }

  public VertexInterface addVertex(Integer id)
  {
    VertexInterface v = this.getVertex(id);
    if (v != null)
    {
      return v;
    }

    v = new Vertex(id);
    vertices.put(id, v);

    //numberOfVertices++;
    lastVertexId = id;

    return v;
  }

  public VertexInterface addVertex()
  {
    Vertex v = new Vertex(lastVertexId + 1);
    vertices.put(v.getId(), v);
    //numberOfVertices++;
    lastVertexId = v.getId();

    return v;
  }

  public VertexInterface addVertex(VertexInterface vertex)
  {
    VertexInterface v = this.getVertex(vertex.getId());
    if (v != null)
    {
      return v;
    }

    vertices.put(vertex.getId(), vertex);
    //numberOfVertices++;
    lastVertexId = vertex.getId();
    return vertex;
  }

  public VertexInterface getVertex(Integer id)
  {
    return vertices.get(id);
  }

  private EdgeInterface addEdge(Integer id)
  {
    EdgeInterface e = this.getEdge(id);

    if (e != null)
    {
      return e;
    }

    e = new Edge(id);
    edges.put(id, e);

    lastEdgeId = id;
    numberOfEdges++;
    return e;
  }

  private EdgeInterface addEdge(Integer id, Float weight)
  {
    EdgeInterface e = this.getEdge(id);

    if (e != null)
    {
      ((Edge) e).weight = weight;
      return e;
    }

    e = new Edge(id);
    e.setWeight(weight);
    edges.put(id, e);

    lastEdgeId = id;
    numberOfEdges++;
    return e;
  }

  private Edge addEdge()
  {
    Edge e = new Edge(lastEdgeId + 1);
    edges.put(e.getId(), e);
    lastEdgeId = e.id;
    numberOfEdges++;

    return e;
  }

  public EdgeInterface getEdge(Integer id)
  {
    return edges.get(id);
  }

  public void addEdge(VertexInterface v1, VertexInterface v2)
  {
    //Vertex V1 = getReal(new Vertex(v1));
    //Vertex V2 = getReal(new Vertex(v2));

    VertexInterface V1 = v1;
    VertexInterface V2 = v2;

    //Previne arestas paralelas
    if (V1.containVertexAdjacent(V2))
    {
      return;
    }
    if (V2.containVertexAdjacent(V1))
    {
      return;
    }

    Edge e = this.addEdge();
    e.v1 = v1;
    e.v2 = v2;

    (v1).addEdge(e);
    (v2).addEdge(e);
    V1.addVertex(V2);
    V2.addVertex(V1);

  }

  public void addEdge(VertexInterface v1, VertexInterface v2, Edge e)
  {
    //Vertex V1 = getReal(new Vertex(v1));
    //Vertex V2 = getReal(new Vertex(v2));

    Vertex V1 = (Vertex) v1;
    Vertex V2 = (Vertex) v2;

    //Previne arestas paralelas
    if (V1.containVertexAdjacent(V2))
    {
      return;
    }
    if (V2.containVertexAdjacent(V1))
    {
      return;
    }

    e.v1 = v1;
    e.v2 = v2;

    (v1).addEdge(e);
    (v2).addEdge(e);
    V1.addVertex(V2);
    V2.addVertex(V1);
  }

  public void addEdge(VertexInterface v1, VertexInterface v2, Float weight)
  {
    //Vertex V1 = getReal(new Vertex(v1));
    //Vertex V2 = getReal(new Vertex(v2));

    VertexInterface V1 = v1;
    VertexInterface V2 = v2;

    //Previne arestas paralelas
    if (V1.containVertexAdjacent(V2))
    {
      return;
    }
    if (V2.containVertexAdjacent(V1))
    {
      return;
    }

    Edge e = this.addEdge();
    e.weight = weight;
    e.v1 = v1;
    e.v2 = v2;

    (v1).addEdge(e); //Adiciona na lista de arestas de v1  
    (v2).addEdge(e);
    V1.addVertex(V2);//Adiciona na lista de adjacencias de V1 o V2
    V2.addVertex(V1);
  }

  /* private void removeVertex(Vertex v)
   {
   //Nao precisa se preocupar em remover esse vertice das listas de adjacencias  ?
   vertices.remove(v.getId());
   //numberOfVertices--;
   } */

  /* protected VertexInterface getReal(VertexInterface v)
   {
   if (!exist(v))
   {
   throw new RuntimeException("Vertice " + v + " não existe.");
   }

   return this.vertices.get(v.getId());
   }

   private EdgeInterface getReal(Edge e)
   {
   if (!exist(e))
   {
   throw new RuntimeException("Aresta " + e + " não existe.");
   }

   return this.edges.get(e.getId());
   }
   */
  public void setAllVerticesActiveTrue()
  {
    Iterator<VertexInterface> it = this.vertices.values().iterator();
    while (it.hasNext())
    {
      VertexInterface v = it.next();
      (v).setActive(true);
    }
  }

  public void setAllVerticesActiveFalse()
  {
    Iterator<VertexInterface> it = this.vertices.values().iterator();
    while (it.hasNext())
    {
      VertexInterface v = it.next();
      v.setActive(false);
    }
  }

  public void setAllVerticesVisitedTrue()
  {
    Iterator<VertexInterface> it = this.vertices.values().iterator();
    while (it.hasNext())
    {
      VertexInterface v = it.next();
      v.setVisited(true);
    }
  }

  public void setAllVerticesVisitedFalse()
  {
    Iterator<VertexInterface> it = this.vertices.values().iterator();
    while (it.hasNext())
    {
      VertexInterface v = it.next();
      v.setVisited(false);
    }
  }

  public boolean exist(VertexInterface v)
  {

    return this.vertices.containsKey(v.getId());

  }

  public boolean exist(EdgeInterface e)
  {
    return this.edges.containsKey(e.getId());
  }

  List<VertexInterface> getVertices()
  {
    List aux = new ArrayList<VertexInterface>(vertices.values());
    //Collections.sort(aux);

    return aux;
  }

  List<EdgeInterface> getEdges()
  {
    List aux = new ArrayList<EdgeInterface>(edges.values());
    //Collections.sort(aux);

    return aux;
  }

  public void removeVertex(VertexInterface v)
  {
    this.vertices.remove(v);
    //varre a lista de adjacenicas de todos os vertices para remover 
    Iterator<VertexInterface> it = vertices.values().iterator();
    while (it.hasNext())
    {
      VertexInterface vertex = it.next();
      Iterator<VertexInterface> adjacencias = vertex.getVertex().getVerticesAdjacents().iterator();
      while (adjacencias.hasNext())
      {
        VertexInterface adjacente = adjacencias.next();
        if (v.equals(adjacente))
        {
          adjacencias.remove();
          break;
        }
      }

    }
  }

  public void removeEdge(EdgeInterface e)
  {
    this.edges.remove(e.getId());
    //varre a lista de adjacenicas de todos os vertices para remover 
    Iterator<VertexInterface> it = vertices.values().iterator();
    while (it.hasNext())
    {
      VertexInterface vertex = it.next();
      Iterator<EdgeInterface> adjacencias = vertex.getVertex().getEdgesAdjacents().iterator();
      while (adjacencias.hasNext())
      {
        EdgeInterface adjacente = adjacencias.next();
        if (e.equals(adjacente))
        {
          adjacencias.remove();
          break;
        }
      }

    }
    
  }

  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    Iterator<VertexInterface> it = vertices.values().iterator();
    while (it.hasNext())
    {
      VertexInterface v = it.next();

      if (v.isActive())
      {
        sb.append("\n Vertice " + v + " esta ligado aos vertices: ");

        Vertex vertex = v.getVertex();//essa classe so teve acesso ao metodo getEdgesAdjacents porque esta no mesmo pacote que Vertex 
        LinkedHashSet dd = new LinkedHashSet(vertex.getEdgesAdjacents());//para remover duplicatas
        Iterator<EdgeInterface> arestasDeV = dd.iterator();
        while (arestasDeV.hasNext())
        {
          EdgeInterface e = arestasDeV.next();
          /*
           if (e.isActive)//verifica se aresta esta ativa
           {
           //aresta conecta dois supervertices e v tem que ser um supervertice
           if (!e.v1.isActive && !e.v2.isActive)//vertices v1 e v2 nao estao ativos
           {
           if (!(v instanceof SuperVertex))
           {
           throw new RuntimeException("Foi detectado que " + v + " deveria ser supervertice mas nao eh.");
           }
           //v esta conectado ao supervertice e.v2.sv
           if (v == e.v1.sv)
           {
           sb.append(e.v2.sv + " pela aresta " + e + ", ");
           }
           else if (v == e.v2.sv)
           {
           sb.append(e.v1.sv + " pela aresta " + e + ", ");
           }
           else
           {
           throw new RuntimeException("Foi encontrada uma aresta ligada ao supervertice " + v + " que nao o tinha! ");
           }
           } //v eh supervertex e esta ligado em e.v2
           else if (!e.v1.isActive && v instanceof SuperVertex)
           {
           if (e.v1.sv != v)
           {
           throw new RuntimeException("O supervertex " + v + " não está em uma das pontas da aresta " + e);
           }

           sb.append(e.v2 + " pela aresta " + e + ", ");
           } 
           //v nao eh SuperVertex, entao esta ligado ao supervertex que esta em e.v1.sv
           else if (!e.v1.isActive && !(v instanceof SuperVertex))
           {
           sb.append(e.v1.sv + " pela aresta " + e + ", ");
           } //v eh supervertex e esta ligado a e.v1
           else if (!e.v2.isActive && v instanceof SuperVertex)
           {
           if (e.v2.sv != v)
           {
           throw new RuntimeException("O supervertex " + v + " não está em uma das pontas da aresta " + e);
           }

           sb.append(e.v1 + " pela aresta " + e + ", ");
           }
           else if (!e.v2.isActive && !(v instanceof SuperVertex))
           {
           sb.append(e.v2.sv + " pela aresta " + e + ", ");
           } //tanto e.v1 como e.v2 estao ativos entao ambos sao Vertex e v tambem tem que ser
           else  */
          {
            if (e.getV1() == v)
            {
              sb.append(e.getV2() + " pela aresta " + e + ", ");
            }
            else
            {
              sb.append(e.getV1() + " pela aresta " + e + ", ");
            }
          }
          // }
        }
      }
    }

    return sb.toString();
  }

  @Override
  public Graph clone()
  {
    Graph g = new Graph();

    g.edges = new LinkedHashMap<Integer, EdgeInterface>();
    Iterator<EdgeInterface> arestas = this.edges.values().iterator();
    while (arestas.hasNext())
    {
      EdgeInterface e = arestas.next();
      g.edges.put(e.getId(), ((Edge) e).clone());
    }
    g.vertices = new LinkedHashMap<Integer, VertexInterface>();
    Iterator<VertexInterface> vertices = this.vertices.values().iterator();
    while (vertices.hasNext())
    {
      VertexInterface v = vertices.next();
      g.vertices.put(v.getId(), (v).clone());
    }

    return g;
  }
}
