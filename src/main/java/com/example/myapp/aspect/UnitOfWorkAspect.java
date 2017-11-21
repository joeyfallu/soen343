package com.example.myapp.aspect;

import com.example.myapp.database.ProductMapper;
import com.example.myapp.database.UserMapper;
import com.example.myapp.productCatalog.Product;
import com.example.myapp.purchases.Purchase;
import com.example.myapp.purchases.PurchaseMapper;
import com.example.myapp.transactions.Transaction;
import com.example.myapp.userCatalog.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Aspect
@Component
public class UnitOfWorkAspect {

    private ArrayList<Object> add = new ArrayList<Object>();
    private ArrayList<Object> delete = new ArrayList<Object>();
    private ArrayList<Object> modify = new ArrayList<Object>();

    private ProductMapper productMapper;
    private UserMapper userMapper;
    private PurchaseMapper purchaseMapper;

    @Before(value = "execution(* com.example.myapp.database.UserMapper.commit(..))")
    public void beforeUserCommit(JoinPoint joinPoint) {
        userMapper = (UserMapper) joinPoint.getThis();
        int mapCount = userMapper.getMapCount();
        Transaction.Type transactionType = userMapper.getCommitType();
        if (transactionType == Transaction.Type.add) {
            for (int i = 0; i < mapCount; i++) {
                User user = userMapper.getUserIdentityMap().getUserById(i);
                registerAdd(user);
            }
            commitUsers();
        } else if (transactionType == Transaction.Type.delete) {
            for (int i = 0; i < mapCount; i++) {
                User user = userMapper.getUserIdentityMap().getUserById(i);
                registerDelete(user);
            }
            commitUsers();
        }
    }


    @Before(value = "execution(* com.example.myapp.purchases.PurchaseMapper.commit(..))")
    public void beforePurchaseCommit(JoinPoint joinPoint) {
        purchaseMapper = (PurchaseMapper) joinPoint.getThis();
        int mapCount = purchaseMapper.getMapCount();
        Transaction.Type transactionType = purchaseMapper.getCommitType();
        if (transactionType == Transaction.Type.purchase) {
            for (int i = 0; i < mapCount; i++) {
                Purchase purchase = purchaseMapper.getPurchasesIdentityMap().getPurchase(i);
                registerAdd(purchase);
            }
            commitPurchase();
        }
        if (transactionType == Transaction.Type.returnItem) {
            for (int i = 0; i < mapCount; i++) {
                Purchase purchase = purchaseMapper.getPurchasesIdentityMap().getPurchase(i);
                registerDelete(purchase);
            }
            commitPurchase();
        }
    }

    @Before(value = "execution(* com.example.myapp.database.ProductMapper.commit(..))")
    public void beforeProductCommit(JoinPoint joinPoint) {
        productMapper = (ProductMapper) joinPoint.getThis();
        int mapCount = productMapper.getMapCount();
        Transaction.Type transactionType = productMapper.getCommitType();

        if (transactionType == Transaction.Type.add) {
            for (int i = 0; i < mapCount; i++) {
                Product product = productMapper.getProductIdentityMap().getProductById(i);
                registerAdd(product);
            }
            commitProducts();
        } else if (transactionType == Transaction.Type.modify) {
            for (int i = 0; i < mapCount; i++) {
                Product product = productMapper.getProductIdentityMap().getProductById(i);
                registerModification(product);
            }
            commitProducts();
        } else if (transactionType == Transaction.Type.delete) {
            for (int i = 0; i < mapCount; i++) {
                Product product = productMapper.getProductIdentityMap().getProductById(i);
                registerDelete(product);
            }
            commitProducts();
        }
    }

    public void registerModification(Object object) {
        modify.add(object);
    }

    public void registerAdd(Object object) {
        add.add(object);
    }

    public void registerDelete(Object object) {
        delete.add(object);
    }

    public void commitUsers() {


        for (Object object : add) {
            int id=0;
            try {
                 id= userMapper.getUserTDG().dbInsert((User) object);
            } catch (Exception e) {
                System.out.println("failed to insert user from unit of work");
            }
            ((User)object).setId(id);
            userMapper.insertUserCatalog((User) object);
        }
        add = new ArrayList<Object>();


        for (Object object : delete) {
            try {
                userMapper.getUserTDG().dbDelete(((User) object).getId());
            } catch (Exception e) {
                System.out.println("failed to delete user from unit of work");
            }
            userMapper.deleteUserCatalog(((User) object).getId());
        }
        delete = new ArrayList<Object>();
    }


    public void commitPurchase() {
        for (Object object : add) {
            String serialNumber = ((Purchase) object).getProduct().getSerialNumber();
            try {
                purchaseMapper.getPurchaseTDG().dbInsert((Purchase) object);
            } catch (Exception e) {
                System.out.println("failed to insert purchase from unit of work");
            }
            purchaseMapper.insertPurchaseHistory(serialNumber, (Purchase) object);
        }

        for (Object object : delete) {
            String serialNumber = ((Purchase) object).getProduct().getSerialNumber();
            try {
                purchaseMapper.getPurchaseTDG().dbDelete(serialNumber);
            } catch (Exception e) {
                System.out.println("failed to return from unit of work");
            }
            purchaseMapper.deletePurchaseHistory(serialNumber);
        }


        add = new ArrayList<Object>();
        delete = new ArrayList<Object>();
    }

    public void commitProducts() {

        for (Object o : add) {
                try {
                    productMapper.getProductTDG().dbInsert((Product) o);
                } catch (Exception e) {
                    System.out.println("failed to insert product from unit of work");
                }
            productMapper.insertProductCatalog(((Product) o).getSerialNumber(), (Product) o);
        }

        for (Object o : delete) {
            try {
                productMapper.getProductTDG().dbDelete(((Product) o).getSerialNumber());
            } catch (Exception e) {
                System.out.println("failed to delete product from unit of work");
            }
            productMapper.deleteProductCatalog(((Product) o).getSerialNumber());
        }

        for (Object o : modify) {
            try {
                productMapper.getProductTDG().dbModify(((Product) o).getSerialNumber(), (Product) o);
            } catch (Exception e) {
                System.out.println("failed to modify product from unit of work");
                e.printStackTrace();
            }
            productMapper.modifyProductCatalog(((Product) o).getSerialNumber(), (Product) o);
        }
        add = new ArrayList<Object>();
        delete = new ArrayList<Object>();
        modify = new ArrayList<Object>();
    }

}
