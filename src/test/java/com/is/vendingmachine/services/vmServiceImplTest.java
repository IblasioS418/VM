/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.vendingmachine.services;

import com.is.vendingmachine.dao.ItemAuditDao;
import com.is.vendingmachine.dao.ItemDAO;
import com.is.vendingmachine.dto.Change;
import com.is.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ibby4
 */

public class vmServiceImplTest {
    
    private vmService service;    
    ItemDAO dao;
    ItemAuditDao auditDao;
    String userInput = "A9";
    BigDecimal balance = new BigDecimal("0.00");
    
    
    
    //ItemAuditDao auditDao;
    
    public vmServiceImplTest() {
        this.dao = new ItemDAOStubImpl("InventoryTest.txt");
        this.auditDao = new ItemAuditDaoStubImpl();
        service = new vmServiceImpl(dao);
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
       
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
     * Test of getItems method, of class vmServiceImpl.
     */
    @Test
    public void testGetItems() throws Exception {
        setUp();
        //Arrange      
        Item testItem = dao.getItem(userInput);
        testItem.setQuantity(testItem.getQuantity()*0);
        dao.update(userInput, testItem);
        
        List<Item> newItemList = dao.getAllItems();       
        Predicate<Item> checkCode = (Item item) -> item.getItemCode().equals(userInput);
        newItemList.stream()
                   .filter(checkCode)
                   .forEach(item -> item.getItemCode());
        newItemList.removeIf(checkCode);                
     
      
        //ACT
        List<Item> itemList = service.getItems();
        
        //ASSERT
        assertEquals(newItemList.size(), itemList.size());
        
        tearDown();
    }
//
//    /**
//     * Test of insertMoney method, of class vmServiceImpl.
//     */
    @Test
    public void testInsertMoney() throws Exception {       
        
        BigDecimal money = service.insertMoney(new BigDecimal("0.00"));
        assertEquals(new BigDecimal("0.00"), money);
        
        BigDecimal money2 = service.insertMoney(new BigDecimal("2.25"));
        assertEquals(new BigDecimal("2.25"), money2);
        
        BigDecimal money3 = service.insertMoney(new BigDecimal("0.75"));
        assertEquals(new BigDecimal("3.00"), money3);

        money3.multiply(new BigDecimal ("0"));


        tearDown();
    }

//    /**
//     * Test of selectItem method, of class vmServiceImpl.
//     */
    @Test
    public void testSelectItem() throws Exception {
        //ARRANGE
        String nullItem = "X1";       
       
        //ACT        
        try{
        service.selectItem(nullItem);
            fail("Expected ItemNotFoundException was not thrown. ");
        } catch (ItemNotFoundException e){
        }
      
    }

//    /**
//     * Test of vendItem method, of class vmServiceImpl.
//     */
    @Test
    public void testVendItem() throws Exception {
        setUp();
        //ARRANGE
        Item it = dao.getItem(userInput);
        it.setQuantity(0);        
        service.insertMoney(new BigDecimal("0.00"));
        service.selectItem(userInput);      
        

        
        //ACT
        try{
        service.vendItem();
            fail("Expected ItemOutOfStockException was not thrown. ");
            fail("Expected InsufficientFundsException was not thrown. ");
        }catch (ItemOutOfStockException | InsufficientFundException ex){
        };
        
        
        
        
       
       tearDown();
        
       
        
        
       
    }

    /**
     * Test of returnChange method, of class vmServiceImpl.
     */
    @Test
    public void testReturnChange() throws Exception {
        setUp();
        service.returnChange();
        
        //Change coins = new Change();
        //BigDecimal money = new BigDecimal("3.87");        
        
        Change change = new Change();
        change.setAmoutQrter(4);
        change.setAmoutdime(1);
        change.setAmountNick(1);       
        change.setAmountpen(2);
        
      
        //ACT
        
        //System.out.println(service.insertMoney(new BigDecimal("0.00")));
        service.insertMoney(new BigDecimal("1.17"));
        Change coin = service.returnChange();
        
        //ASSERT
        assertEquals(change.getAmoutQrter(),coin.getAmoutQrter());        
        assertEquals(change.getAmoutdime(),coin.getAmoutdime());
        assertEquals(change.getAmountNick(),coin.getAmountNick());
        assertEquals(change.getAmountpen(),coin.getAmountpen());
         
        
        
        tearDown();
    }
    
}
