/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.vendingmachine.dao;

import com.is.vendingmachine.dto.Item;
import com.is.vendingmachine.services.vmPersistenceException;
import com.is.vendingmachine.services.vmService;
import com.is.vendingmachine.services.vmServiceImplTest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ibby4
 */
public class ItemDAOFileImplTest {
     ItemDAO dao = new ItemDAOFileImpl("InventoryTest.txt"); 
     public String TEST_FILE;     
     private String userInput = "A9";
    
    @BeforeAll
    public static void setUpClass() {
    
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws Exception {    
     // File TEST_FILE = new file();
        
    }
    
    @AfterEach
    public void tearDown() {
     Item item = new Item();
        item.setItemCode(userInput);
        item.setItemName("Test1");
        item.setCost(new BigDecimal("2.00"));
        item.setQuantity(10);
       
        try {
            dao.update(userInput, item);
        } catch (vmPersistenceException ex) {
            Logger.getLogger(vmServiceImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    

   
    /**
     * Test of getAllItems method, of class ItemDAOFileImpl.
     */
    @Test
    public void testGetAllItems() throws Exception {  
        //ACT
        List<Item> ItemList = dao.getAllItems();
        //ASSERT
        assertEquals(3,ItemList.size());
               
    }

    /**
     * Test of getItem method, of class ItemDAOFileImpl.
     */
    @Test
    public void testGetItem() throws Exception {      
        //ARRANGE
        Item item = new Item();
        item.setItemCode("A9");
        item.setItemName("Test1");
        item.setCost(new BigDecimal("2.00"));
        item.setQuantity(Integer.parseInt("10"));
        
        String expResult = item.getItemCode() +
                        item.getItemName() +
                        item.getCost() +
                        item.getQuantity();

        // ACT
       
        Item fromDao = dao.getItem(userInput);
                String result = fromDao.getItemCode() +
                        fromDao.getItemName() +
                        fromDao.getCost() + 
                        fromDao.getQuantity();
                        
        // ASSERT
        assertEquals(expResult,result);
        
        tearDown();
    }

    /**
     * Test of update method, of class ItemDAOFileImpl.
     */
    @Test
    public void testUpdate() throws Exception {
        //ARRANGE    
        int expResult = 9;        
        Item fromDao = dao.getItem(userInput);
        fromDao.setQuantity(fromDao.getQuantity()-1);
        
        //ACT
        dao.update(userInput, fromDao);        
        int result = fromDao.getQuantity();
        
        //ASSERT
        assertEquals(expResult,result);
        
        tearDown();
    }
    
}
