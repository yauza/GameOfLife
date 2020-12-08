package Classes;

import Enums.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Genes {
    public int [] dna;

    public Genes(){
        dna = generateRandomDNA();
    }

    private int [] generateRandomDNA(){
        int [] res = new int[32];
        Random generator = new Random();
        for(int i = 0; i < 32; i++){
            int temp = Math.abs(generator.nextInt()) % 8;
            res[i] = temp;
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
