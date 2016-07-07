package org.hackerrank.java.interview.cci.stack.shelter;

import java.util.LinkedList;
import java.util.List;

public class Shelter {
    private final List<Animal> dogs;
    private final List<Animal> cats;

    public Shelter() {
        this.dogs = new LinkedList<>();
        this.cats = new LinkedList<>();
    }

    public synchronized void enqueue(Animal a) {
        if (a instanceof Dog) {
            dogs.add(a);
        } else if (a instanceof Cat) {
            cats.add(a);
        } else {
            throw new RuntimeException("We do not accept animals of this kind " + a);
        }
    }

    public synchronized Animal dequeueAny() {
        Animal adopted;
        if(dogs.isEmpty() && cats.isEmpty()){
            adopted = null;
        } else if(!dogs.isEmpty()){
            adopted = dogs.remove(0);
        } else if(!cats.isEmpty()){
            adopted = cats.remove(0);
        } else {
            if(dogs.get(0).getTimeArrived() > cats.get(0).getTimeArrived()){
                adopted = dogs.remove(0);
            } else {
                adopted = cats.remove(0);
            }
        }
        return adopted;
    }

    public synchronized Animal dequeueDog(){
        if(dogs.isEmpty()){
            return null;
        }
        return dogs.remove(0);
    }

    public synchronized Animal dequeueCat(){
        if(cats.isEmpty()){
            return null;
        }
        return cats.remove(0);
    }
}
