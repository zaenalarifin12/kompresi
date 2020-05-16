/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

/**
 *
 * @author zaenal
 */
abstract class HuffmanTree implements Comparable<HuffmanTree>{
    
    public final int frequency;
    
    public HuffmanTree(int freg){
        frequency = freg;
        
    }
    
    @Override
    public int compareTo(HuffmanTree tree){
        return frequency - tree.frequency;
    }
    
}
