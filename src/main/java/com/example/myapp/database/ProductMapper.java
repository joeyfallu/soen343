package com.example.myapp.database;

import com.example.myapp.productCatalog.Product;
import com.example.myapp.productCatalog.ProductCatalog;
import com.example.myapp.transactions.Transaction;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProductMapper {

    private ProductIdentityMap productIdentityMap;
    private ProductTDG productTDG;
    private ProductCatalog productCatalog;
    private Transaction.Type commitType = Transaction.Type.add;
    private int mapCount = 0;

    public ProductMapper() {
        this.productTDG = new ProductTDG();
        this.productCatalog = new ProductCatalog();
        this.productIdentityMap = new ProductIdentityMap();
        getProductCatalog().setProducts(this.getAll());
    }

    public ProductMapper(ProductCatalog productCatalog) {
        this.productTDG = new ProductTDG();
        this.productCatalog = productCatalog;
        this.productIdentityMap = new ProductIdentityMap();
    }

    public void insert(Product product) {
        commitType = Transaction.Type.add;
        productIdentityMap.insertProduct(mapCount, product);
        mapCount++;
    }

    public void insertProductCatalog(String serialNumber, Product product) {
        productCatalog.addProduct(serialNumber, product);
    }

    public void modifyProductCatalog(String serialNumber, Product product) {
        productCatalog.modifyProduct(serialNumber, product);
    }

    public void update(Product product) {
        commitType = Transaction.Type.modify;
        productIdentityMap.insertProduct(mapCount, product);
        mapCount++;
    }

    public void delete(String serialNumber) {
        commitType = Transaction.Type.delete;
        Product empty = new Product("", "", 0, 0, "", 0);
        empty.setSerialNumber(serialNumber);
        productIdentityMap.insertProduct(mapCount, empty);
        mapCount++;
    }

    public void deleteProductCatalog(String serialNumber) {
        productCatalog.deleteProduct(serialNumber);
    }

    public Map<String, Product> getAll() {
        Product currentProducts[];
        try {
            currentProducts = productTDG.dbGetAll();

            for (int i = 0; i < currentProducts.length; i++) {
                productCatalog.addProduct(currentProducts[i].getSerialNumber(), currentProducts[i]);
            }

        } catch (Exception e) {
            //do nothing
        }
        return productCatalog.getProducts();
    }

    public ProductTDG getProductTDG() {
        return productTDG;
    }

    public void commit() {
        mapCount = 0;
    }

    public ProductCatalog getProductCatalog() {
        return productCatalog;
    }

    public ProductIdentityMap getProductIdentityMap() {
        return productIdentityMap;
    }

    public Transaction.Type getCommitType() {
        return commitType;
    }

    public int getMapCount() {
        return mapCount;
    }
}
