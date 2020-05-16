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
public class HuffmanLeaf extends HuffmanTree{
    
    public final char value;
    
    public HuffmanLeaf(int freq, char val){
        super(freq);
        value = val;
    }
    
    
}
