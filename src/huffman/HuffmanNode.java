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
public class HuffmanNode extends HuffmanTree{
    
    public final HuffmanTree right;
    public final HuffmanTree left;
    
    public HuffmanNode(HuffmanTree l, HuffmanTree r){
        super(l.frequency + r.frequency);
        left = l;
        right = r;
    }
}
