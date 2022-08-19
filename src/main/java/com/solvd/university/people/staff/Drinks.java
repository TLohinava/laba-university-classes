package com.solvd.university.people.staff;

public enum Drinks {
    TEA("tea"),
    COFFEE("coffee"),
    COCOA("cocoa");

    private final String drinkType;

    Drinks(String drinkType) {
        this.drinkType = drinkType;
    }

    public String getDrinkType() {
        return drinkType;
    }
}
