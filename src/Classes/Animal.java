package Classes;

import Enums.*;
import Interfaces.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;

public class Animal implements MapElement, MapElementObservable, Comparable<Animal>{
    public Vector2d position;
    public Vector2d lastPosition;
    public double energy;
    public Direction direction;
    public Genes genes;
    public ObserverOfMapElements map;
    public int numberOfChildren;
    public int longevity;

    public Animal(Vector2d position, double energy, Map map){
        this.position = position;
        this.lastPosition = position;
        this.energy = energy;
        this.genes = new Genes();
        this.direction = genes.calculateDirection();
        this.numberOfChildren = 0;
        this.map = map;
        this.longevity = 0;
    }

    public Animal(Vector2d position, double energy, Map map, Genes genes){
        this.position = position;
        this.lastPosition = position;
        this.energy = energy;
        this.genes = genes;
        this.direction = genes.calculateDirection();
        this.numberOfChildren = 0;
        this.map = map;
        this.longevity = 0;
    }

    public String toString(){
        return "now: " + position.toString() +" last: " + lastPosition.toString() + " energy: " + energy;
    }


    @Override
    public void moveElement(Vector2d dir, boolean outOfBounds) {
        Vector2d lastPos = new Vector2d(this.position.x, this.position.y);
        this.lastPosition = lastPos;

        if(outOfBounds){
            this.position = new Vector2d(dir);
        }else {
            this.position.x += dir.x;
            this.position.y += dir.y;
        }
        notifyObserver(this.map);
    }

    public Genes calculateChildDna(Animal parent2){
        Genes childGenes = new Genes();
        Random generator = new Random();

        int div1 = Math.abs(generator.nextInt()) % 32;
        int div2 = Math.abs(generator.nextInt()) % 32;

        while(div1 == div2) div2 = Math.abs(generator.nextInt()) % 32;

        if(div1 > div2){
            int temp = div1;
            div2 = div1;
            div1 = temp;
        }

        int [] newDna = new int[32];
        int i = 0;
        while (i < 32){
            if(i < div1){
                newDna[i] = this.genes.dna[i];
            }else if (i >= div1 && i < div2){
                newDna[i] = parent2.genes.dna[i];
            }else{
                newDna[i] = this.genes.dna[i];
            }
            i++;
        }

        while(!childGenes.checkIfContainsAllDnaParts(newDna)){
            int [] temp = new int[8];

            for(int d : newDna){
                temp[d]++;
            }
            int dnaPart = 0;
            for(int k = 0; k < 8; k++){
                if(temp[k] == 0) dnaPart = k;
            }

            int change = Math.abs(generator.nextInt()) % 32;

            newDna[change] = dnaPart;

        }

        Arrays.sort(newDna);
        childGenes.dna = newDna;
        return childGenes;
    }

    public int getEnergyLevel(double standard){
        if(this.energy >= (standard / 2)) return 1;
        else if(this.energy < (standard / 2) && this.energy >= (standard / 4)) return 0;
        else if(this.energy < (standard / 4)) return -1;
        return 2;
    }


    @Override
    public Vector2d getPosition() {
        return this.position;
    }

    public boolean isDead() {return this.energy <= 0;}

    @Override
    public void notifyObserver(ObserverOfMapElements map) {
        map.update(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        Animal animal = (Animal) o;
        return energy == animal.energy &&
                Objects.equals(position, animal.position) &&
                direction == animal.direction &&
                Objects.equals(genes, animal.genes) &&
                Objects.equals(map, animal.map);
    }

    public int compareTo(Animal a){
        if(this.energy > a.energy) return -1;
        else if (this.energy == a.energy) return 0;
        else return 1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, energy, direction, genes, map);
    }
}
