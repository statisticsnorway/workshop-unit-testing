package learning.lambdas;

import store.model.Item;

import java.util.function.Supplier;

public class TestSupplier {

    public static void main(String[] args) {
        Supplier<Item> supplier = () -> {
            Item item = new Item();
            item.setName("Item A");
            item.setPrice(500);

            return item;
        };

        Item item = supplier.get();
        System.out.println("Item details: "+ item.getName()+ " , "+ item.getPrice());
    }
}
