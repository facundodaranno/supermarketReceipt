package dojo.supermarket.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BundleTenPercentOffer implements OfferDefinition {
    private final List<Product> products;

    public BundleTenPercentOffer(Product ... products) {
        this.products = Arrays.asList(products);
    }

    @Override
    public Discount apply(SupermarketCatalog catalog, List<ProductQuantity> items) {
        //Codigo metido así no más para seguir desde acá en el próximo dojo
        Discount discount = new EmptyDiscount( products.get(0) );

        List<Product> itemsProducts = getProductAsList(items);

        Boolean applyOffer = products.stream()
                .allMatch(discountProducts -> itemsProducts.indexOf(discountProducts) > 0 );

        if(applyOffer){
            discount = new PercentDiscount(products.get(0),10,1, 10);
        }

        return discount;
    }

    private List<Product> getProductAsList(List<ProductQuantity> items) {
        List<Product> products = new ArrayList<>();
        items.forEach( (ProductQuantity it) -> { products.add( it.getProduct()); } );
        return products;
    }

}
