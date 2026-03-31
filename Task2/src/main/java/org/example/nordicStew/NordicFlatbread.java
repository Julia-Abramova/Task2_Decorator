package org.example.nordicStew;

public class NordicFlatbread extends AdditiveDecorator {
    private static final int ADDITIONAL_PRICE = 7;
    private static final String ADDITION_NAME = "Нордская лепешка";

    public NordicFlatbread(Dish dish) {
        super(dish);
    }

    @Override
    public String getDescription() {
        return wrappedDish.getDescription() + " + " + ADDITION_NAME;
    }

    @Override
    public int getPrice() {
        return wrappedDish.getPrice() + ADDITIONAL_PRICE;
    }
}
