/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author Christian
 */
public class Huffman
{

  public void run(String inputFile, String outputFile)
  {

    //le o arquivo e calcula a frequencia das letras
    TreeMap<Character, Integer> alfabeto = new TreeMap<Character, Integer>();
    char c[] = new char[1];
    try
    {
      BufferedReader input = new BufferedReader(
          new FileReader(inputFile));
      try
      {

        while ((input.read(c, 0, 1)) != -1)
        {
          if (alfabeto.containsKey(c[0]))
          {
            int x = alfabeto.get(c[0]);
            alfabeto.put(c[0], ++x);//incrementa o contador para esse caracter
          }
          else
          {
            alfabeto.put(c[0], 1);
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

    SortedSet<Map.Entry<Character, Integer>> map = entriesSortedByValues(alfabeto);
    System.out.println(map);

    no root = new no();
    //Cria o codigo livre de prefixo
    for (Entry<Character, Integer> cc : map)
    {
      if (root.right == null && root.left == null)
      {
        no aux1 = new no();
        aux1.c = cc.getKey();
        root.right = aux1;
      }
      else if (root.left == null)
      {
        no aux1 = new no();
        aux1.c = cc.getKey();
        root.left = aux1;
      }
      else
      {
        no aux1 = new no(); //no intermediario que nao tem character
        aux1.left = root.left;
        aux1.right = root.right;
        no aux2 = new no();
        aux2.c = cc.getKey();
        root = new no();
        root.right = aux1;
        root.left = aux2;
      }

    }//fim for

    TreeMap<Character, no> codigo = new TreeMap<Character, no>();
    no aux = root;
    String s = "", ss = "1";

    aux.left.code = "0";
    codigo.put(aux.left.c, aux.left);
    aux = aux.right;
    for (int i = 2; i < map.size(); i++)
    {
      aux.left.code = ss + "0";
      codigo.put(aux.left.c, aux.left);
      ss = ss + "1";
      s = s + "1";
      aux = aux.right;
    }
    aux.code = ss + "1";
    codigo.put(aux.c, aux);

    try
    {
      BufferedReader input = new BufferedReader(
          new FileReader(inputFile));

      BufferedWriter output = new BufferedWriter(
          new FileWriter(outputFile));

      while ((input.read(c, 0, 1)) != -1)
      {
        no aux1 = codigo.get(c[0]);
        output.write(aux1.code);
      }     
     
      input.close();
      output.close();
    }     
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
   

  }

  static <K, V extends Comparable<? super V>>
      SortedSet<Map.Entry<K, V>> entriesSortedByValues(Map<K, V> map)
  {
    SortedSet<Map.Entry<K, V>> sortedEntries = new TreeSet<Map.Entry<K, V>>(
        new Comparator<Map.Entry<K, V>>()
        {
          @Override
          public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2)
          {
            return e1.getValue().compareTo(e2.getValue());
          }
        }
    );
    sortedEntries.addAll(map.entrySet());
    return sortedEntries;
  }

  class no
  {

    no left = null;
    no right = null;
    char c;
    String code;
  }

}
