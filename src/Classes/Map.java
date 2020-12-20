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
    public double startEnergy;
    public double energyNeeded;
    public double grassEnergy;
    public String[][] area;

    // used in visualization
    public int era;
    public int numberOfAnimals;
    public int numberOfGrass;
    public int averageNumberOfChildren;
    public int averageEnergyLevel;
    public int averageLongevity;
    public String dominatingGenotype;


    public HashMap<Vector2d, List<Animal>> animals;
    public HashMap<Vector2d, Grass> grass;
    public List<Animal> updateThePosition = new ArrayList<>();
    public List<Animal> dead = new ArrayList<>();

    public Map(int width, int length, int jungleWidth, int jungleLength, HashMap<Vector2d, List<Animal>> animals, HashMap<Vector2d, Grass> grass, double startEnergy, double energyNeeded, double grassEnergy){
        this.width = width;
        this.length = length;
        this.jungleLength = jungleLength;
        this.jungleWidth = jungleWidth;
        this.area = new String[length][width];
        this.animals = animals;
        this.grass = grass;
        fillTheMap();
        this.era = 1;
        this.startEnergy = startEnergy;
        this.energyNeeded = energyNeeded;
        this.grassEnergy = grassEnergy;
        this.numberOfAnimals = countAnimals();
        this.numberOfGrass = countGrass();
        this.averageNumberOfChildren = calculateAverageNumberOfChildren();
        this.averageEnergyLevel = calculateAverageEnergyLevel();
        this.dominatingGenotype = findDominatingGenotype();
        this.averageLongevity = calculateAverageLongevity();
    }

    public Map(int width, int length, int jungleWidth, int jungleLength, double startEnergy, double energyNeeded, double grassEnergy){
        this.width = width;
        this.length = length;
        this.jungleLength = jungleLength;
        this.jungleWidth = jungleWidth;
        this.area = new String[length][width];
        this.animals = new HashMap<>();
        this.grass = new HashMap<>();
        fillTheMap();
        this.era = 1;
        this.startEnergy = startEnergy;
        this.energyNeeded = energyNeeded;
        this.grassEnergy = grassEnergy;
        this.numberOfAnimals = countAnimals();
        this.numberOfGrass = countGrass();
        this.averageNumberOfChildren = calculateAverageNumberOfChildren();
        this.averageEnergyLevel = calculateAverageEnergyLevel();
        this.dominatingGenotype = findDominatingGenotype();
        this.averageLongevity = calculateAverageLongevity();
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

    // animal - 19, grass - 1, nothing - 0
    public void fillTheMap(){
        for(int i = 0; i < length; i++){
            for(int j = 0; j < width; j++){
                area[i][j] = "-";
            }
        }

        for(Grass g : grass.values()){
            if(isOccupied(g.position)) continue;
            area[g.position.x][g.position.y] = "*";
        }

        for(List<Animal> l : animals.values()){
            for(Animal a : l) {
                area[a.position.x][a.position.y] = Double.toString(a.energy);
            }
        }

        //System.out.println(countAnimals());
    }

    public void newEra(){
        updateEnergy();
        removeDeadAnimals();
        addGrass();
        moveAnimals();
        updateAnimals();
        eatGrass();
        animalReproduce();
        this.era++;
        this.numberOfAnimals = countAnimals();
        this.numberOfGrass = countGrass();
        this.averageNumberOfChildren = calculateAverageNumberOfChildren();
        this.averageEnergyLevel = calculateAverageEnergyLevel();
        this.dominatingGenotype = findDominatingGenotype();
        this.averageLongevity = calculateAverageLongevity();
        // visualize
    }

    private void removeDeadAnimals(){              //and update longevity
        List<Animal> toRemove = new ArrayList<>();

        for(List<Animal> l : animals.values()){
            for(Animal a : l){
                if(a.isDead()){
                    dead.add(a);
                    toRemove.add(a);
                }else a.longevity++;
            }
        }

        for(Animal a : toRemove) removeAnimal(a);
    }

    private void removeAnimal(Animal animal){
        animals.get(animal.position).remove(animal);
    }

    private void updateEnergy(){
        for(List<Animal> l : animals.values()){
            for(Animal a : l){
                a.energy -= energyNeeded;
            }
        }
    }

    private void moveAnimals(){  //move and eatGrass
        for(List<Animal> l : animals.values()){
            for(Animal a : l){
                Vector2d dir = a.genes.calculateDirection().toVector2d();
                Vector2d newPos = dir.vectorAddition(a.position);

                if(outOfBounds(newPos)) {
                    a.moveElement(wrapAroundTheMap(newPos), true);
                }else a.moveElement(dir, false);
            }
        }
    }

    private void eatGrass(){
        List<Grass> toRemove = new ArrayList<>();
        for(Grass g : grass.values()){
            if(eatSingleGrass(g.getPosition())) toRemove.add(g);
        }

        for(Grass g : toRemove){
            grass.remove(g.position);
        }
    }

    private boolean eatSingleGrass(Vector2d position){
        if(isOccupied(position)) {
            if (animals.get(position).size() == 1) {
                animals.get(position).get(0).energy += grass.get(position).energy;
                //System.out.println(animals.get(position).get(0));
            } else if (animals.get(position).size() > 1) {
                //List<Animal> temp = animals.get(position);
                Collections.sort(animals.get(position));
                int counter = 0;
                for (int i = 0; i < animals.get(position).size(); i++) {
                    if (animals.get(position).get(i).energy == animals.get(position).get(0).energy) counter++;
                }

                double energyGet = grass.get(position).energy / counter;
                for (int i = 0; i < counter; i++) {
                    animals.get(position).get(i).energy += energyGet;
                }

            }

            //grass.remove(position);
            return true;
        }
        return false;
    }

    private void addGrass(){
        Random generator = new Random();
        int boundx = (width - jungleWidth) / 2;
        int boundy = (length - jungleLength) / 2;

        int x1 = Math.abs(generator.nextInt()) % jungleWidth + boundx;
        int y1 = Math.abs(generator.nextInt()) % jungleLength + boundy;

        grass.put(new Vector2d(x1, y1), new Grass(new Vector2d(x1, y1), grassEnergy));

        int x2 = Math.abs(generator.nextInt()) % boundx;
        int x3 = (Math.abs(generator.nextInt()) % boundx) + jungleWidth + boundx;
        int x4 = Math.abs(generator.nextInt()) % width;

        int y2 = Math.abs(generator.nextInt()) % boundy;
        int y3 = (Math.abs(generator.nextInt()) % boundy) + jungleLength + boundy;
        int y4 = Math.abs(generator.nextInt()) % length;

        if(generator.nextBoolean()){  //horizontal
            if(generator.nextBoolean()) grass.put(new Vector2d(x4, y2), new Grass(new Vector2d(x4, y2), grassEnergy));
            else grass.put(new Vector2d(x4, y3), new Grass(new Vector2d(x4, y3), grassEnergy));
        }else{                        // vertical
            if(generator.nextBoolean()) grass.put(new Vector2d(x2, y4), new Grass(new Vector2d(x2, y4), grassEnergy));
            else grass.put(new Vector2d(x3, y4), new Grass(new Vector2d(x3, y4), grassEnergy));
        }
    }

    private void animalReproduce(){
        List<Animal> toAdd = new ArrayList<>();
        for(Vector2d pos : animals.keySet()){
            if(howManyOnField(pos) > 1){
                Collections.sort(animals.get(pos));
                Animal parent1 = animals.get(pos).get(0);
                Animal parent2 = animals.get(pos).get(1);

                if(parent2.energy < (startEnergy/2)) continue;      // minimal needed energy to reproduce

                double temp1 = parent1.energy / 4;
                double temp2 = parent2.energy / 4;
                parent1.energy -= temp1;
                parent2.energy -= temp2;

                parent1.numberOfChildren++;
                parent2.numberOfChildren++;

                Vector2d childPosition = calculateChildPosition(parent1.position);
                Genes childGenes = parent1.calculateChildDna(parent2);
                Animal child = new Animal(childPosition, temp1 + temp2, this, childGenes);

                toAdd.add(child);
            }
        }

        for(Animal a : toAdd){
            place(a);
        }
    }

    private Vector2d calculateChildPosition(Vector2d parentPosition){
        List<Vector2d> dir = new ArrayList<>();
        dir.add(new Vector2d(0, 1));
        dir.add(new Vector2d(1, 1));
        dir.add(new Vector2d(1, 0));
        dir.add(new Vector2d(1, -1));
        dir.add(new Vector2d(0, -1));
        dir.add(new Vector2d(-1, -1));
        dir.add(new Vector2d(-1, 0));
        dir.add(new Vector2d(-1, 1));

        for(Vector2d d : dir){
            Vector2d childPos = new Vector2d(parentPosition.vectorAddition(d));
            if(outOfBounds(childPos)) childPos = wrapAroundTheMap(childPos);

            if(isOccupied(childPos) || isGrassOnField(childPos)) continue;
            else return childPos;
        }

        Random generator = new Random();
        int p = Math.abs(generator.nextInt()) % 8;

        Vector2d childPos = new Vector2d(parentPosition.vectorAddition(dir.get(p)));
        if(outOfBounds(childPos)) childPos = wrapAroundTheMap(childPos);

        return childPos;
    }

    @Override
    public boolean place(MapElement element) {
        Vector2d pos = new Vector2d(element.getPosition());
        if(outOfBounds(pos)){
            throw new IllegalArgumentException("Out of bounds!");
        }

        if(element instanceof Animal) {
            //if (howManyOnField(pos) >= 4) throw new IllegalArgumentException("Too many animals on a field!");
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

    public void randomlyPlaceAnimals(int numberOfAnimals){
        Random generator = new Random();
        for(int i = 0; i < numberOfAnimals; i++){
            int x = Math.abs(generator.nextInt()) % width;
            int y = Math.abs(generator.nextInt()) % length;
            //System.out.println(new Vector2d(x, y));
            Animal a = new Animal(new Vector2d(x, y), startEnergy, this);
            place(a);
        }
    }

    private Vector2d wrapAroundTheMap(Vector2d position){
        int x = position.x;
        int y = position.y;
        if(x >= width) x = 0;
        if(x < 0) x = width-1;

        if(y >= length) y = 0;
        if(y < 0) y = length-1;

        return new Vector2d(x, y);
    }

    private int countAnimals(){
        int counter = 0;
        if(animals.values() == null) return 0;
        for(List<Animal> l : animals.values()){
            if(l == null) continue;
            counter += l.size();
        }

        return counter;
    }

    private int countGrass(){
        return grass.values().size();
    }

    private int calculateAverageLongevity(){
        int counter = 0, div = 0;
        for(Animal a : dead){
            counter += a.longevity;
            div++;
        }

        dead.clear();
        if(div == 0) return 0;
        return counter / div;
    }

    private int calculateAverageNumberOfChildren(){
        int counter = 0, div = 0;
        if(animals.values() == null) return 0;
        for(List<Animal> l : animals.values()){
            if(l == null) continue;
            for(Animal a : l){
                counter += a.numberOfChildren;
            }
            div += l.size();
        }
        if(div == 0) return 0;
        return counter / div;
    }

    private int calculateAverageEnergyLevel(){
        int counter = 0, div = 0;
        if(animals.values() == null) return 0;
        for(List<Animal> l : animals.values()){
            if(l == null) continue;
            for(Animal a : l){
                counter += a.energy;
            }
            div += l.size();
        }
        if(div == 0) return 0;
        return counter / div;
    }

    private String findDominatingGenotype(){
        int counter = 0;
        HashMap<int [], Integer> dnaMap = new HashMap<>();

        if(animals.values() == null) return "";
        for(List<Animal> l : animals.values()){
            if(l == null) continue;
            for(Animal a : l){
                dnaMap.put(a.genes.dna, 1);
            }
        }
        Genes temp = new Genes();
        for(int[] g : dnaMap.keySet()){
            if(dnaMap.get(g) > counter){
                temp.dna = g;
                counter = dnaMap.get(g);
            }
        }

        return temp.toString();
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
        if(animals.get(position) != null) return animals.get(position).size() >= 1;
        else return false;
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
        updateThePosition.add((Animal)animal);
    }

    private void updateAnimals(){
//        System.out.println("updatetheposition: ");
//        System.out.println(updateThePosition);
//        System.out.println(animals);
        for(Animal a : updateThePosition){
            animals.get(a.lastPosition).remove(a);
            place(a);
        }

        updateThePosition.clear();
    }

}
