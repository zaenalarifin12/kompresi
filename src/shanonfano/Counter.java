/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shanonfano;


import java.text.DecimalFormat;
import java.util.*;

/**
 *
 * @author zaenal
 */
public class Counter {

    private ArrayList<Symbol> list;
    public final int total;
    public double avg_l;

    public Counter(String text) {

        list = new ArrayList<>();


        int i;
        for (i=0; i<text.length(); i++) {
            if( list.contains(new Symbol(text.charAt(i), -1)) )
                continue;

            String c;
            if( text.charAt(i) == '.' )
                c  = "\\.";
            else
                c  = ""+text.charAt(i);

            String replaced = text.replaceAll(c, "");
            list.add(new Symbol(text.charAt(i), text.length()-replaced.length()));
        }

        total = i;
        for(Symbol s: list)
            s.setProbability(total);

        list.sort(new Comparator<Symbol>() {
            @Override
            public int compare(Symbol o1, Symbol o2) {
                if( o1.p < o2.p ) return 1;
                if( o1.p == o2.p ) return 0;
                return -1;
            }
        });

        fanon(0, list.size()-1);

        avg_l = 0.0;
        for(i=0; i<list.size(); i++)
            avg_l += list.get(i).getL() * list.get(i).p;
    }

    private void fanon(int start, int end) {
        if( start == end || start > end || end < start)
            return;

        int cut = start;
        double ratio1 = getRatio(start, end, cut);
        double ratio2 = getRatio(start, end, cut+1);

        while( Math.abs(ratio1-1) > Math.abs(ratio2-1) && cut < end ) {
            cut++;
            ratio1 = getRatio(start, end, cut);
            ratio2 = getRatio(start, end, cut+1);
        }

        applySubcode('0', start, cut);
        applySubcode('1', cut+1, end);

        fanon(start, cut);
        fanon(cut+1, end);
    }

    private void applySubcode(char sc, int from, int to) {
        for(int i=from; i<=to; i++) {
            list.get(i).code.append(sc);
        }
    }

    /**
     * cut is included in the left group
     * @param from
     * @param to
     * @param cut
     * @return
     */
    private double getRatio(int from, int to, int cut) {
        if(from > to || cut < from || cut > to )
            throw new IllegalArgumentException("Illegal parameter set. from <= cut <= to.. \n "+from+"<="+cut+"<="+to+" does not apply");
        double sumLeft = getSum(from, cut);
        double sumRight = getSum(cut+1, to);
        return sumLeft/sumRight;
    }

    private double getSum(int from, int to) {
        double sum = 0;
        for(int i=from; i<=to; i++) {
            sum += list.get(i).p;
        }

        return sum;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#.##");

        outputConsole(sb, df);

        sb.append("\n");

//        sb.append("Rata2 panjang = "+df.format(avg_l)+" Bits/Symbol \n");

        return sb.toString();
    }

    private void outputLaTex(StringBuilder sb, DecimalFormat df) {
        sb.append("Printing LaTex table version: \n\n");
        sb.append("Zeichen & $p_i$ & $n$ & Codewort \\\\");

        for(int i=0; i<list.size(); i++) {
            Symbol s = list.get(i);
            sb.append(s.symbol+" & "+df.format(s.p)+" & "+s.count+" & "+s.code+"\\\\\n");
        }
    }

    private void outputConsole(StringBuilder sb, DecimalFormat df) {
//        sb.append("Printing console table version: \n\n");
        Formatter f = new Formatter(sb, Locale.ENGLISH);

        System.out.println("SYMBOL|WEIGHT|SHANNON-FANO CODE");
        
        for(int i=0; i<list.size(); i++) {
            Symbol s = list.get(i);
//            f.format("%2s => %4s %4d %15s \n", s.symbol, df.format(s.p), s.count, s.code);
            f.format("%2s %7d %12s \n", s.symbol, s.count, s.code);
        }
    }

}