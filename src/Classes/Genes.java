package Classes;

import Enums.Direction;

import java.util.*;

public class Genes {
    public int [] dna;

    public Genes(){
        dna = generateRandomDNA();
    }

    private int [] generateRandomDNA(){
        int [] res = new int[32];
        Random generator = new Random();
        boolean check = true;
        while(check) {
            for (int i = 0; i < 32; i++) {
                int temp = Math.abs(generator.nextInt()) % 8;
                res[i] = temp;
            }

            if (checkIfContainsAllDnaParts(res)) check = false;
        }
        Arrays.sort(res);
        return res;
    }

    public Direction calculateDirection(){
        Random generator = new Random();
        int rand = Math.abs(generator.nextInt()) % 32;
        Direction dir = Direction.NORTH;
        Vector2d res = new Vector2d(0,0);
        return dir.fromNumber(this.dna[rand]);
    }


//    private void fixTheDna(int [] dnaToFix){
//        int [] dnaParts = new int[8];
//        for(int i = 0; i < 8; i++) dnaParts[i] = 0;
//        for(int i : dna){
//            dnaParts[i]++;
//        }
//
//        int num, change = 0;
//        for(int i = 0; i < 8; i++) {
//            if(dnaParts[i] == 0){
//                num = i;
//            }
//            change = Math.max(dnaParts[i], change);
//        }
//
//
//    }

    public boolean checkIfContainsAllDnaParts(int [] dna){
        if(dna == null) return false;

        int [] dnaParts = new int[8];
        for(int i = 0; i < 8; i++) dnaParts[i] = 0;

        for(int i : dna){
            dnaParts[i]++;
        }

        for(int i : dnaParts){
            if(i == 0) return false;
        }

        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genes)) return false;
        Genes genes = (Genes) o;
        return Arrays.equals(dna, genes.dna);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(dna);
    }
}
