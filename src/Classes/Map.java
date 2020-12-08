package Classes;

import Interfaces.IMap;
import Interfaces.MapElement;
import Interfaces.MapElementObservable;
import Interfaces.ObserverOfMapElements;

import java.util.*;

public class Map implements IMap, ObserverOfMapElements {
    public int width;
    public int length;
    public int jungleWidth;
    public int jungleLength;
    public int[][] area;


    public HashMap<Vector2d, List<Animal>> animals;
    //public List<Animal> animals;
    public HashMap<Vector2d, Grass> grass;
    //public List<Grass> grass;

    public Map(int width, int length, int jungleWidth, int jungleLength, HashMap<Vector2d, List<Animal>> animals, HashMap<Vector2d, Grass> grass){
        this.width = width;
        this.length = length;
        this.jungleLength = jungleLength;
        this.jungleWidth = jungleWidth;
        this.area = new int[length][width];
        this.animals = animals;
        this.grass = grass;
        fillTheMap();
    }

    public String toString(){
        for(int i = 0; i < length; i++){
            for(int j = 0; j < width; j++){
                System.out.printf("%3s ", area[i][j]);
            }
            System.out.println();
        }

        return "";
    }

    // animal - 7, grass - 1, nothing - 0
    private void fillTheMap(){
        for(int i = 0; i < length; i++){
            for(int j = 0; j < width; j++){
                area[i][j] = 0;
            }
        }

        for(List<Animal> l : animals.values()){
            for(Animal a : l) {
                area[a.position.x][a.position.y] = 7;
            }
        }

        for(Grass g : grass.values()){
            area[g.position.x][g.position.y] = 1;
        }
    }

    public void NextEra(){

    }

    @Override
    public boolean place(MapElement element) {
        Vector2d pos = element.getPosition();
        if(outOfBounds(pos)){
            throw new IllegalArgumentException("Out of bounds!");
        }
        if(element instanceof Animal) {
            if (howManyOnField(pos) >= 4) throw new IllegalArgumentException("Too many animals on a field!");
            if (animals.containsKey(pos)) animals.get(pos).add((Animal)element);
            else {
                List<Animal> temp = new ArrayList<>();
                temp.add((Animal) element);
                animals.put(pos, temp);
            }

        }else if(element instanceof Grass){
            if(isGrassOnField(pos)) throw new IllegalArgumentException("There is already grass on a field!");
            else grass.put(pos, (Grass) element);
        }

        return true;
    }

    private boolean outOfBounds(Vector2d position){
        return (position.x < 0 || position.x >= width || position.y < 0 || position.y >= length);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.get(position).size() > 1;
    }

    private boolean isGrassOnField(Vector2d position){
        return grass.containsKey(position);
    }

    private int howManyOnField(Vector2d position){
        if(isOccupied(position)) return animals.get(position).size();
        else return 0;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return null;
    }

    @Override
    public void update(MapElementObservable animal) {
        Vector2d pos = ((Animal) animal).lastPosition;
        for(Animal a : animals.get(pos)){
            if(a.equals(animal)){
                if(animals.get(((Animal)animal).position).size() == 1) animals.remove(pos);
                else{
                    animals.get(pos).remove((Animal) animal);
                }
                break;
            }
        }
        pos = ((Animal) animal).position;
        animals.get(pos).add((Animal)animal);
    }
}
