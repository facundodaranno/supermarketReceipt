package dojo.supermarket.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

    private final List<ProductQuantity> items;
    Map<Product, Double> productQuantities;

    ShoppingCart(){
        items = new ArrayList<>();
        productQuantities = new HashMap<>();
    }

    List<ProductQuantity> getItems() {
        return new ArrayList<>(items);
    }

    void addItem(Product product) {
        this.addItemQuantity(product, 1.0);
    }

    Map<Product, Double> productQuantities() {
        return productQuantities;
    }

    public void addItemQuantity(Product product, double quantity) {
        items.add(new ProductQuantity(product, quantity));
        if (productQuantities.containsKey(product)) {
            productQuantities.put(product, productQuantities.get(product) + quantity);
        } else {
            productQuantities.put(product, quantity);
        }
    }

    void handleOffers(Receipt receipt, List<OfferDefinition> offers, SupermarketCatalog catalog) {
        for (OfferDefinition o: offers) {
            Discount discount =  o.apply(catalog,items);
            receipt.addDiscount(discount);
        }
    }
}
