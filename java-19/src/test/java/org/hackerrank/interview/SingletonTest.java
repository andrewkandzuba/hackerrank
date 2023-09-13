package org.hackerrank.interview;

import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;

public class SingletonTest {

    @Test
    public void givenSingleton_whenSerializedAndDeserialized_thenStatePreserved() throws IOException, ClassNotFoundException {
        Singleton s1 = Singleton.INSTANCE;
        s1.setState("State One");

        Path tempPath = Files.createTempFile("singleton", "text");

        try (OutputStream fos = new FileOutputStream(tempPath.toFile());
             ObjectOutputStream oos = new ObjectOutputStream(fos);
             FileInputStream fis = new FileInputStream(tempPath.toFile());
             ObjectInputStream ois = new ObjectInputStream(fis)) {


            oos.writeObject(s1);

            // Deserializing.
            Singleton s2 = (Singleton) ois.readObject();

            // Checking if the state is preserved.
            assertEquals(s1.getState(), s2.getState());

            // Checking if s1 and s2 are not the same instance.
            assertEquals(s1, s2);
        }
    }
}
