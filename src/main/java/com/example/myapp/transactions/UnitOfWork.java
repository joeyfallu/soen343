package com.example.myapp.transactions;

import com.example.myapp.database.ProductMapper;
import com.example.myapp.database.UserMapper;
import com.example.myapp.productCatalog.Product;
import com.example.myapp.userCatalog.*;
import org.codehaus.groovy.runtime.powerassert.SourceText;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;

public class UnitOfWork {

    ArrayList<Object> add = new ArrayList<Object>();
    ArrayList<Object> delete = new ArrayList<Object>();
    ArrayList<Object> modify = new ArrayList<Object>();
    ProductMapper productMapper;
    UserMapper userMapper;

    public UnitOfWork(ProductMapper p)
    {
        productMapper = p;
    }

    public UnitOfWork(UserMapper u)
    {
        userMapper = u;
    }

    public void registerModification(Object o)
    {
        modify.add(o);
    }

    public void registerAdd(Object o)
    {
        add.add(o);
    }

    public void registerDelete(Object o)
    {
        delete.add(o);
    }

    public void commitUsers()
    {
        for(Object o : add){
        try{userMapper.getUserTDG().dbInsert((User)o);}catch (Exception e){
            System.out.println("failed to insert user from unit of work");
        }
        userMapper.insertUserCatalog((User)o);
        }
    }

    public void commitProducts()
    {
        for(Object o : add){
        int k=0;
            try{k = productMapper.getProductTDG().dbInsert((Product)o);}catch (Exception e){
                System.out.println("failed to insert product from unit of work");
            }
            ((Product)o).setId(k);
            productMapper.insertProductCatalog(k,(Product)o);
        }

        for(Object o : delete){
            try{productMapper.getProductTDG().dbDelete(((Product)o).getId());}catch (Exception e){
                System.out.println("failed to delete product from unit of work");
            }
            productMapper.deleteByIdProductCatalog(((Product)o).getId());
        }

        for(Object o : modify){
            try{productMapper.getProductTDG().dbModify(((Product)o).getId(),(Product)o);}catch (Exception e){
                System.out.println("failed to modify product from unit of work");
                e.printStackTrace();
            }
            productMapper.modifyProductCatalog(((Product)o).getId(),(Product)o);
        }
    }

    public void save()
    {

    }
}
