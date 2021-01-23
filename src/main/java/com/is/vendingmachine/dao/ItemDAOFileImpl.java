/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.vendingmachine.dao;

import com.is.vendingmachine.dto.Item;
import com.is.vendingmachine.services.vmPersistenceException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ibby4
 */
public class ItemDAOFileImpl implements ItemDAO {
  
    private String FILENAME;
    private String DELIMITER;
    private Map<String, Item> itemList = new HashMap<>();

    public ItemDAOFileImpl(String filename) {
        this.FILENAME = filename;
        this.loadItem();
    }

    
    

    
 /*
        private Item unmarshallItem(String itemAsText){
            String [] itemTokens = itemAsText.split(DELIMITER);
            
            Item itemFromInventory = new Item();
            itemFromInventory.setItemCode(itemTokens[0]);
            itemFromInventory.setItemName(itemTokens[1]);            
            itemFromInventory.setCost(Double.parseDouble(itemTokens[2]));
            itemFromInventory.setQuantity(Integer.parseInt(itemTokens[3]));
            
            return itemFromInventory;
        } 
*/
    public void save() {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(this.FILENAME));
            for (Item selectedItem : itemList.values()) {
                out.println(ItemMapper.MapToString(selectedItem));
                out.flush();
            }
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(ItemDAOFileImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/*    
    private String marshallItem(Item item) {
        String itemAsText = "";
        itemAsText += item.getItemCode() + DELIMITER;
        itemAsText += item.getItemName() + DELIMITER;
        itemAsText += item.getCost()+ DELIMITER;
        itemAsText += item.getQuantity() + DELIMITER;
        
        return itemAsText;
    }
*/
    private void loadItem() {
        try {
            Scanner sc = new Scanner(
                    new BufferedReader(new FileReader(this.FILENAME)));
            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                Item selectedItem = ItemMapper.MapToObject(currentLine);                
                itemList.put(selectedItem.getItemCode(), selectedItem);   
              
                
            }
            sc.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ItemDAOFileImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Item> getAllItems() throws vmPersistenceException {
        return new ArrayList<>(itemList.values());
    }

    @Override
    public Item getItem(String item) throws vmPersistenceException {
       return itemList.get(item);
    }

    @Override
    public Item update(String itemCode, Item item) throws vmPersistenceException {
        getAllItems();
        item = itemList.put(itemCode, item);
        save();        
        return item;
    }
}
   
    


