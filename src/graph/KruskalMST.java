package graph;

import graph.Graph;
import graph.VertexInterface;
import graph.VertexUnionFindDecorator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Christian
 */
public class KruskalMST
{

  public static void main(String args[])
  {

    String line;
    Graph g = new Graph();
    try
    {
      BufferedReader input = new BufferedReader(
          new FileReader("C:\\christian\\Dropbox\\Projetos_NetBeans\\Coursera - Analise de Algoritmos II\\kruskal.txt"));

      try
      {
        while ((line = input.readLine()) != null)
        {
          String[] elements = line.split(" ");

          Vertex v = new Vertex(Integer.valueOf(elements[0]));
          VertexInterface v1 = g.addVertex(new VertexUnionFindDecorator(v));

          for (int i = 1; i < elements.length; i++)
          {
            String[] pesos = elements[i].split(",");
            v = new Vertex(Integer.valueOf(pesos[0]));
            VertexInterface v2 = g.addVertex(new VertexUnionFindDecorator(v));
            g.addEdge(v1, v2, Float.valueOf(pesos[1]));
          }

        }
      }
      finally
      {
        input.close();
      }
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }

    System.out.println(g);

    //selecionando arestas de menor custo
    List<EdgeInterface> edges = g.getEdges();
    Collections.sort(edges, new Comparator()
    {

      @Override
      public int compare(Object t, Object t1)
      {
        if (!(t instanceof EdgeInterface) || !(t1 instanceof EdgeInterface))
        {
          throw new RuntimeException("Impossível comparar " + t + " com " + t1);
        }
        
        EdgeInterface e1 = (EdgeInterface) t;
        EdgeInterface e2 = (EdgeInterface) t1;
        
        if (e1.getWeight()> e2.getWeight())
        {
          return 1;
        }
        else if (e1.getWeight()== e2.getWeight())
        {
          return 0;
        }
        else
        {
          return -1;
        }

      }
    }
    );

    System.out.println(edges);
    //escolhe as arestas da mais barata para a mais cara excluindo aquelas que formam um ciclo
    ListIterator<EdgeInterface> it = edges.listIterator();
    
    while(it.hasNext())
    {
      EdgeInterface e = it.next();
      VertexUnionFindDecorator v1 = (VertexUnionFindDecorator) e.getV1();
      VertexUnionFindDecorator v2 = (VertexUnionFindDecorator) e.getV2();
      
      if(!v1.getLeader().equals(v2.getLeader()))
      {
        //Entao os dois nao fazem parte do mesmo componente
        if(v1.isLeader())
        {
          if(v2.isLeader())
          {            
            v2.setLeaderFalse();//se v2 era lider, deixara de ser
            v2.setLeader(v1);
          }
          else 
          {
            VertexUnionFindDecorator aux = v2.getLeader();
            aux.setLeaderFalse();//se v2 nao era lider, o lider do seu grupo deixará de ser lider
            aux.setLeader(v1);
          }
        }
        else if(v2.isLeader())
        {
          VertexUnionFindDecorator aux = v1.getLeader();
          aux.setLeaderFalse();//se v2 nao era lider, o lider do seu grupo deixará de ser lider
          aux.setLeader(v2);
          
        }
      }
      else //os dois vertices da aresta possuem o mesmo lider, entao esta aresta se incluida formara um ciclo
      {
        g.removeEdge(e);
      }      
    }   
    
    System.out.println(g);
    System.out.println(g.getEdges());
  }
  
  
}
