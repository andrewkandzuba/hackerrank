package org.hackerrank.java.interview.cci.stack.shelter;

import org.junit.Assert;
import org.junit.Test;

public class TestShelter {

    @Test
    public void testAdoption() throws Exception {
        Shelter shelter = new Shelter();
        shelter.enqueue(new Cat("Marie"));
        shelter.enqueue(new Dog("Jack"));
        shelter.enqueue(new Dog("Dick"));
        shelter.enqueue(new Dog("Mate"));

        Assert.assertEquals("Marie", shelter.dequeueCat().getName());
        Assert.assertEquals("Jack", shelter.dequeueDog().getName());
        Assert.assertEquals("Dick", shelter.dequeueAny().getName());
    }
}
