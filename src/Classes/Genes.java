package Classes;

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
}
