package org.example.nordicStew;

public class NordicStew  implements Dish {
    private static final int BASE_PRICE = 50;
    private static final String NAME  = "Нордское рагу";

    @Override
    public String getDescription() {
        return NAME;
    }

    @Override
    public int getPrice() {
        return BASE_PRICE;
    }
}
