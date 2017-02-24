
import huffman.Huffman;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Christian
 */
public class HuffmanTests
{

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args)
  {
    // TODO code application logic here
    
    Huffman huff = new Huffman();
    
    huff.run("C:\\christian\\Dropbox\\Projetos_NetBeans\\Coursera - Analise de Algoritmos II\\huffman.txt","C:\\christian\\Dropbox\\Projetos_NetBeans\\Coursera - Analise de Algoritmos II\\huffman_cod.txt");
    
  }
  
}
