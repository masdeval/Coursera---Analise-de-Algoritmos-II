/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author admin
 */
public class DirectedGraph
{

  private Graph graph = new Graph();

  public VertexInterface addVertex()
  {
    return graph.addVertex();
  }

  public VertexInterface addVertex(Integer v)
  {
    return graph.addVertex(v);
  }
 
  public void addEdge(VertexInterface tail, VertexInterface head)
  {
    //Vertex V1 = getReal(new Vertex(v1));
    //Vertex V2 = getReal(new Vertex(v2));

    VertexInterface V1 =  tail;
    VertexInterface V2 =  head;

    //Edge e = this.addEdge();
    //e.v1 = (Vertex) tail;
    //e.v2 = (Vertex) head;

    // ((Vertex) tail).addEdge(e);
    // ((Vertex) v2).addEdge(e);
    V1.addVertex(V2);
  }

  
  /*
    Esse metodo inverte o grafo dirigido e para isso acessa a estrutura de dados interna dos vertices. 
    So consegue fazer isso porque esta no mesmo pacote da classe Vertex.
  */   
  public void invertEdges()
  {
    this.resetVisitedFlag();
    Iterator<VertexInterface> it = graph.getVertices().iterator();

    while (it.hasNext())//varre todos os vertices do grafo
    {
      VertexInterface v = it.next();
      (v).setVisited(true);
      
      //Obtem a lista de vertices adjacentes a v
      Vertex vertex = v.getVertex();//obtem-se a classe concreta Vertex para poder acessar metodos de pacote
      ListIterator<VertexInterface> vertices = vertex.getVerticesAdjacents().listIterator(); 

      while (vertices.hasNext())//varre todas as adjacencias de v
      {
        VertexInterface verticeAdjacente = vertices.next();
        if (verticeAdjacente.getId() == -1)
        {
          vertices.remove();
          break;
        }
        
        if(v == verticeAdjacente)
          continue;

        //inverte a relacao vertice v -> verticeAdjacente
        //significa que nao foi inserido na lista de adjacencias deste vertice algum outro
        if (verticeAdjacente.isVisited() == false)
        {
          verticeAdjacente.setVisited(true);   
          //pode ser que insere duplicado na lista de adjacencias mas vai remover depois
          verticeAdjacente.addVertex(new Vertex(-1));//apenas para marcar o inicio da nova lista de adjacencias
        }
        
        //verticeAdjacente -> v
        verticeAdjacente.addVertex(v);//pode ser que insere duplicado na lista de adjacencias mas vai remover depois
        vertices.remove(); //nao pode ter self-loop senao dá pau aqui!!!!!
      }
    }
    this.resetVisitedFlag();
  }

  public void resetVisitedFlag()
  {
    graph.setAllVerticesVisitedFalse();
  }
  
  public List<VertexInterface> getVertices()
  {
    return graph.getVertices();
  }
  
  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    Iterator<VertexInterface> it = graph.getVertices().iterator();
    while (it.hasNext())
    {
      VertexInterface v = it.next();

      if (v.isActive())
      {
        sb.append("\n Vertice " + v + " esta ligado aos vertices: ");

        Vertex vertex = v.getVertex();//precisou acessar a classe concreta Vertex para ter acesso a um metodo de pacote
        Iterator<VertexInterface> verticesAdjacentes = vertex.getVerticesAdjacents().iterator();
        while(verticesAdjacentes.hasNext())
        {
          VertexInterface vAdjacente = verticesAdjacentes.next();
          if(vAdjacente.isActive())
          {
            sb.append(" => " + vAdjacente + ", ");
          }
        }
      }
    }

    return sb.toString();
  }
}
