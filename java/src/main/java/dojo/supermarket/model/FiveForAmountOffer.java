package dojo.supermarket.model;

import java.util.List;

public class FiveForAmountOffer implements OfferDefinition {

    private final Product product;
    private final double amount;

    public FiveForAmountOffer(Product product, double amount) {
        this.product = product;
        this.amount = amount;
    }

    @Override
    public Discount apply(SupermarketCatalog catalog, List<ProductQuantity> items) {
        Discount discount = new EmptyDiscount(product);
        for (ProductQuantity item: items) {
            if (item.getProduct().equals(product) && item.getQuantity() == 5) {
                discount = new FiveForAmountDiscount(product, this.amount, item.getQuantity(), catalog.getUnitPrice(product));
                break;
            }
        }
        return discount;

    }
}
