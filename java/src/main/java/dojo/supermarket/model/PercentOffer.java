package dojo.supermarket.model;

import java.util.List;

public class PercentOffer implements OfferDefinition {
    private final Product product;
    private final int percent;

    public PercentOffer(Product product, int percent) {
        this.product = product;
        this.percent = percent;
    }

    @Override
    public Discount apply(SupermarketCatalog catalog, List<ProductQuantity> items) {
        Discount discount = new EmptyDiscount(product);
        for (ProductQuantity item: items) {
            if (item.getProduct().equals(product)) {
                discount = new PercentDiscount(product, percent, item.getQuantity(), catalog.getUnitPrice(product));
                break;
            }
        }
        return discount;
    }
}
