package dojo.supermarket.model;

import java.util.List;

public interface OfferDefinition {
    Discount apply(SupermarketCatalog catalog, List<ProductQuantity> items);
}

