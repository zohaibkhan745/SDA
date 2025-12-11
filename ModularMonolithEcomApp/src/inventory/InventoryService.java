package inventory;

import products.Product;
import java.util.ArrayList;
import java.util.List;

/*
 Module: Inventory
 Manages products and stock levels. Other modules depend on this for product info.
*/
public class InventoryService {
    private List<Product> products = new ArrayList<>();

    public InventoryService(){
        products.add(new Product(1,"Laptop",150000,10));
        products.add(new Product(2,"Mouse",1500,50));
        products.add(new Product(3,"Keyboard",2500,30));
        products.add(new Product(4,"USB Drive",1200,20));
    }

    public List<Product> getAll(){ return products; }

    public Product getById(int id){
        return products.stream().filter(p -> p.getId()==id).findFirst().orElse(null);
    }

    public boolean reduceStock(int id, int qty){
        Product p = getById(id);
        if(p == null || p.getStock() < qty) return false;
        p.reduceStock(qty);
        return true;
    }
}
