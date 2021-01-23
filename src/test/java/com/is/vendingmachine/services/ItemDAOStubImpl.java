/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.vendingmachine.services;

import com.is.vendingmachine.dao.ItemDAO;
import com.is.vendingmachine.dao.ItemDAOFileImpl;
import com.is.vendingmachine.dao.ItemMapper;
import com.is.vendingmachine.dto.Item;
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
public class ItemDAOStubImpl implements ItemDAO {   
   
    private Map<String, Item> itemList = new HashMap<>();
    private String FILENAME;
    
    public ItemDAOStubImpl(String fileName) {
        this.FILENAME = fileName;
        loadItem();
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
    
}
