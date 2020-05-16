/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shanonfano;

/**
 *
 * @author zaenal
 */
public class Symbol {

    public final char symbol;
    public StringBuilder code;
    public double p;
    public int count;

    public Symbol(char s, int count) {
        symbol = s;
        this.p = p;
        this.count = count;
        code = new StringBuilder();
    }

    int getL() {
        if( code == null )
            return -1;

        return code.length();
    }

    void setProbability(int total) {
        p = count/(double)total;
    }

    @Override
    public boolean equals(Object o) {
        if( o instanceof Symbol ) {
            Symbol s = (Symbol) o;
            return s.symbol == symbol;
        }

        return false;
    }
}
