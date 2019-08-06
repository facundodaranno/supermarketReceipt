package dojo.supermarket.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SupermarketTest {

    // Todo: test all kinds of discounts are applied properly

    @Test
    public void tenPercentDiscount() {
        SupermarketCatalog catalog = new FakeCatalog();
        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 0.99);
        Product apples = new Product("apples", ProductUnit.Kilo);
        catalog.addProduct(apples, 1.99);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(apples, 2.5);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(new PercentOffer(toothbrush,10));

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(4.975, receipt.getTotalPrice(), 0.01);

    }

    @Test
    public void two_toothbrush_for_one() {
        SupermarketCatalog catalog = new FakeCatalog();
        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 0.99);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(toothbrush, 2);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(new TwoForAmountOffer(toothbrush, 0.99));

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(0.99, receipt.getTotalPrice(), 0.01);
    }

    @Test
    public void twentyPercentInApples() {
        SupermarketCatalog catalog = new FakeCatalog();
        Product apples = new Product("apples", ProductUnit.Kilo);

        catalog.addProduct(apples, 1.99);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(apples, 1);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(new PercentOffer(apples,20));

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(1.6, receipt.getTotalPrice(), 0.01);
    }

    @Test
    public void five_toothpaste_with_discount() {
        SupermarketCatalog catalog = new FakeCatalog();
        Product toothpaste = new Product("toothpaste", ProductUnit.Each);
        catalog.addProduct(toothpaste, 1.79);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(toothpaste, 5);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(new FiveForAmountOffer(toothpaste,7.49));

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(7.49, receipt.getTotalPrice(), 0.01);
    }

    @Test
    public void two_cherrybox_for_amount() {
        SupermarketCatalog catalog = new FakeCatalog();
        Product cherrybox = new Product("cherrybox", ProductUnit.Each);
        catalog.addProduct(cherrybox, 0.69);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(cherrybox, 2);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(new TwoForAmountOffer(cherrybox, 0.99));

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(0.99, receipt.getTotalPrice(), 0.01);
    }

    @Test
    public void three_for_two_rice() {
        SupermarketCatalog catalog = new FakeCatalog();
        Product rice = new Product("rice", ProductUnit.Each);
        catalog.addProduct(rice, 2.49);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(rice, 3);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(new ThreeForTwoOffer(rice));

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        assertEquals(4.98, receipt.getTotalPrice(), 0.01);
    }

    @Test
    public void test_buy_rice_with_10_percentage_discount_and_two_boxes_of_cherry_must_pay_3_23() {
        //given:
        SupermarketCatalog catalog = new FakeCatalog();
        Product rice = new Product("rice", ProductUnit.Each);
        Product cherry = new Product("cherry", ProductUnit.Each);
        catalog.addProduct(rice, 2.49);
        catalog.addProduct(cherry, 0.69);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(rice, 1);
        cart.addItemQuantity(cherry, 2);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(new PercentOffer(rice, 10));
        teller.addSpecialOffer(new TwoForAmountOffer(cherry,0.99) );

        //when:
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        //then:
        assertEquals(3.23, receipt.getTotalPrice(), 0.01);
    }



    @Test
    public void test_when_buy_all_product_of_a_bundle_you_get_ten_percentage_of_discount_on_it() {
        //given:
        SupermarketCatalog catalog = new FakeCatalog();

        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        Product toothPaste = new Product("toothPast", ProductUnit.Each);

        catalog.addProduct(toothbrush, 0.99);
        catalog.addProduct(toothPaste, 1.79);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(toothbrush, 1);
        cart.addItemQuantity(toothPaste, 1);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer( new BundleTenPercentOffer( toothbrush, toothPaste ) );

        //when:
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        //then:
        assertEquals(3, receipt.getTotalPrice(), 0.01);
    }

}
