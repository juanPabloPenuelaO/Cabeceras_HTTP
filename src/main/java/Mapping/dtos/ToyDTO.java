package Mapping.dtos;

import java.io.Serializable;

    public record ToyDTO(String name, int type, double price, int quantity) implements Serializable {
    }