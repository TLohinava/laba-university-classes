package com.solvd.university.people.staff;

public interface IDrink {

    public enum Drinks {
        TEA("tea"),
        COFFEE("coffee"),
        COCOA("cocoa");

        private String drinkType;

        private Drinks(String drinkType) {
            this.drinkType = drinkType;
        }

        public String getDrinkType() {
            return drinkType;
        }
    }

    void think(IDrink.Drinks drink);

    void boilWater();

    void brew(IDrink.Drinks drink);

    void sip();

}