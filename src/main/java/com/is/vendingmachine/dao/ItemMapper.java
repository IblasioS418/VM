/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.vendingmachine.dao;

import com.is.vendingmachine.dto.Item;
import java.math.BigDecimal;

/**
 *
 * @author ibby4
 */
public class ItemMapper {

    private final static String DELIMITER = "::";

    public static String MapToString(Item Item) {
        return    Item.getItemCode() + DELIMITER
                + Item.getItemName() + DELIMITER                
                + Item.getCost() + DELIMITER
                + Item.getQuantity();
    }
    public static Item MapToObject(String info){
        String[] information = info.split(DELIMITER);
        Item Item = new Item();
        Item.setItemCode(information[0]);
        Item.setItemName(information[1]);
        Item.setCost(new BigDecimal(information[2]));
        Item.setQuantity(Integer.parseInt(information[3]));        
        return Item;
    }
}
