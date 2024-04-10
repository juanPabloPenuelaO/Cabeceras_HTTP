package utils;

import model.Toy;

import java.io.*;
import java.util.List;

public class FileUtils {

    public static void saveToys(File file, List<Toy> toyList) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(toyList);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al guardar los juguetes: " + e.getMessage());
        }
    }

    public static List<Toy> getToys(File file) {
        try {
            if (file.exists()) {
                try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
                    return (List<Toy>) objectInputStream.readObject();
                }
            } else {
                throw new FileNotFoundException("El archivo no existe");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al leer los juguetes: " + e.getMessage());
        }
    }
}

