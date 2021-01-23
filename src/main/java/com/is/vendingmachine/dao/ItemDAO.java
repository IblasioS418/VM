/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.vendingmachine.dao;

import com.is.vendingmachine.dto.Item;
import com.is.vendingmachine.services.vmPersistenceException;
import java.util.List;

/**
 *
 * @author ibby4
 */
public interface ItemDAO {    
    List<Item> getAllItems() throws vmPersistenceException;
    Item getItem(String item) throws vmPersistenceException;
    Item update(String itemCode, Item item) throws vmPersistenceException;    
    
   
}
