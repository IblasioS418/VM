/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.vendingmachine.services;

import com.is.vendingmachine.dto.Change;
import com.is.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author ibby4
 */
public interface vmService {
    List<Item> getItems() throws
            vmPersistenceException; 
    BigDecimal insertMoney(BigDecimal money) throws
            vmPersistenceException;              
    void selectItem(String itemCode) throws
            vmPersistenceException,
            ItemNotFoundException;
    Change vendItem() throws 
            vmPersistenceException,
            ItemOutOfStockException, 
            InsufficientFundException;
    Change returnChange();
}


