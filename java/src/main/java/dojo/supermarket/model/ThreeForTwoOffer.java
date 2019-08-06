package dojo.supermarket.model;

import java.util.List;

public class ThreeForTwoOffer implements OfferDefinition {
    private Product product;

    public ThreeForTwoOffer(Product rice) {
        this.product = rice;
    }

    @Override
    public Discount apply(SupermarketCatalog catalog, List<ProductQuantity> items) {
        Discount discount = new EmptyDiscount(product);
        for (ProductQuantity item : items) {
            if (item.getProduct().equals(product) && item.getQuantity() == 3) {
                discount = new ThreeForTwo(product, item.getQuantity(), catalog.getUnitPrice(product));
                break;
            }
        }
        return discount;
    }
}
