package model;

import java.io.Serializable;

    public class Toy implements Serializable {
        private String name;
        private int type;
        private double price;
        private int quantity;


        public Toy(String name, int type, double price, int quantity) {
            this.name = name;
            this.type = type;
            this.price = price;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }