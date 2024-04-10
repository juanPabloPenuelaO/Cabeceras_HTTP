package Service;

import java.util.List;
import java.util.Map;

import model.Toy;


public interface ToyRepository<T> {

    List<T> list();
    T byId(int id);

    boolean verifyToyExists(String name);

    void decreaseStock(String name, int quantity);

    void increaseStock(String name, int quantity);

    List<Toy> addToy(Toy toy) throws IllegalArgumentException;
}

