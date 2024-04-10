package Service;


import model.Toy;
import utils.Constants;
import utils.FileUtils;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class toyRepositoryImpl implements ToyRepository<Toy> {

    private List<Toy> toys;

    public void decreaseStock(String name, int quantity) {
        try {
            for (Toy toy : toys) {
                if (toy.getName().equals(name)) {
                    int currentQuantity = toy.getQuantity();
                    if (currentQuantity >= quantity && quantity >= 0) {
                        toy.setQuantity(currentQuantity - quantity);

                        Thread.sleep(2000);

                        System.out.println("Stock decreased successfully for toy: " + name);
                        return;
                    } else {
                        System.out.println("Invalid quantity or insufficient stock for toy: " + name);
                        return;
                    }
                }
            }
            System.out.println("Toy not found with name: " + name);
        } catch (InterruptedException e) {
            System.out.println("Thread sleep interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public synchronized void increaseStock(String name, int quantity) {
        try {
            if (quantity <= 0) {
                throw new IllegalArgumentException("Quantity should be greater than zero");
            }

            for (Toy toy : toys) {
                if (toy.getName().equalsIgnoreCase(name)) {
                    int currentQuantity = (int) toy.getQuantity();
                    toy.setQuantity(currentQuantity + quantity);
                    System.out.println("Stock increased successfully for toy: " + name);

                    Thread.sleep(2000);

                    return;
                }
            }
            System.out.println("Toy not found with name: " + name);
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted while sleeping: " + e.getMessage());
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            System.out.println("An error occurred while increasing stock: " + e.getMessage());
            e.printStackTrace();
        }
    }




    @Override
    public List<Toy> list() {
        return null;
    }

    @Override
    public Toy byId(int id) {
        return null;
    }

    @Override
    public boolean verifyToyExists(String name) {
        return toys != null && toys.stream().anyMatch(e -> e.getName().equalsIgnoreCase(name));
    }

    @Override
    public List<Toy> addToy(Toy toy) {
        try {
            if (!verifyToyExists(toy.getName())) {
                toys.add(toy);
                FileUtils.saveToys(new File(Constants.PATH_Toys), toys);
                return toys;
            } else {
                System.out.println("This toy already exists in the store.");
                return null;
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Unexpected error occurred during toy addition.", e);
        }
    }
}
