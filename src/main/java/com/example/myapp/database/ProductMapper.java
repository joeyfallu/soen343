package com.example.myapp.database;

import com.example.myapp.productCatalog.Product;
import com.example.myapp.productCatalog.ProductCatalog;
import com.example.myapp.transactions.Transaction;
import com.example.myapp.transactions.UnitOfWork;

import java.util.Map;

public class ProductMapper {

    private ProductIdentityMap productIdentityMap;
    private ProductTDG productTDG;
    private ProductCatalog productCatalog;
    private Transaction.Type commitType= Transaction.Type.add;
    private UnitOfWork u;
    private int mapCount=0;

    public ProductMapper(ProductCatalog productCatalog) {
        this.productTDG = new ProductTDG();
        this.productCatalog = productCatalog;
        this.productIdentityMap = new ProductIdentityMap();
    }

    public void insert(Product product){
        commitType = Transaction.Type.add;
        productIdentityMap.insertProductById(mapCount, product);
        mapCount++;
    }

    public void insertProductCatalog(int k, Product p)
    {
        productCatalog.addProduct(k,p);
    }

    public void modifyProductCatalog(int k, Product p)
    {
        productCatalog.modifyProduct(k,p);
    }

    public void update(Product product){
        commitType = Transaction.Type.modify;
        productIdentityMap.insertProductById(mapCount, product);
        mapCount++;
    }

    public void delete(int id) {
        commitType = Transaction.Type.delete;
        Product empty = new Product(0,"",0,0,"",0);
        empty.setId(id);
        productIdentityMap.insertProductById(mapCount, empty);
        mapCount++;
    }

    public void deleteByIdProductCatalog(int k)
    {
        productCatalog.deleteProduct(k);
    }

    public Product get(int id){

        Product product = productIdentityMap.getProductById(id);

        if(product == null){
            try {
                product = productTDG.dbGet(id);
                productIdentityMap.insertProductById(id, product);
            }
            catch (Exception e){
                //do nothing
            }
        }

        return product;
    }

    public Map<Integer, Product> getAll(){


        Product currentProducts[];

            try{
                currentProducts = productTDG.dbGetAll();

                for(int i = 0; i < currentProducts.length; i++){
                    productCatalog.addProduct(currentProducts[i].getId(), currentProducts[i]);
                }

            }
            catch (Exception e){
                //do nothing
            }

        return productCatalog.getProducts();
    }

    public ProductTDG getProductTDG() {
        return productTDG;
    }

    public void commit()
    {
        if(commitType== Transaction.Type.add)
        {
            u= new UnitOfWork(this);
            for(int i=0; i<mapCount;i++)
            {
                Product p = productIdentityMap.getProductById(i);
                u.registerAdd(p);
            }
            u.commitProducts();

        }
        else if(commitType== Transaction.Type.modify)
        {
            u= new UnitOfWork(this);
            for(int i=0; i<mapCount; i++)
            {
                Product p = productIdentityMap.getProductById(i);
                u.registerModification(p);
            }
            u.commitProducts();
        }
        else if(commitType== Transaction.Type.delete)
        {
        u= new UnitOfWork(this);
        for(int i=0; i<mapCount; i++)
        {
            Product p = productIdentityMap.getProductById(i);
            u.registerDelete(p);
        }
        u.commitProducts();
        }
        mapCount=0;
    }

    public ProductCatalog getProductCatalog() {
        return productCatalog;
    }
}
