/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.vendingmachine.services;

import com.is.vendingmachine.dao.ItemDAO;
import com.is.vendingmachine.dto.Change;
import com.is.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author ibby4
 */
public class vmServiceImpl implements vmService {

    BigDecimal moneyInMachine = new BigDecimal("0.00");
    String itemSelected;
    ItemDAO dao;

    public vmServiceImpl(ItemDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Item> getItems() throws vmPersistenceException {
        List<Item> itemList = dao.getAllItems();
        Predicate<Item> checkStock = (Item item) -> item.getQuantity() > 0;
        Comparator<Item> compareByCode = (Item i1, Item i2)
                -> i1.getItemCode().compareTo(i2.getItemCode());
        return (List<Item>) itemList
                .stream()
                .sorted(compareByCode)
                .filter(checkStock)
                .collect(Collectors.toList());

    }

    @Override
    public BigDecimal insertMoney(BigDecimal money){
        moneyInMachine = moneyInMachine.add(money);
        return moneyInMachine;
    }

    @Override
    public void selectItem(String itemCode) throws
            ItemNotFoundException, vmPersistenceException {
        Item item = dao.getItem(itemCode);
        if (item == null ) {
            throw new ItemNotFoundException("Item Entry: " + itemCode + " could not be located");
        } else {
            itemSelected = itemCode;
        }
    }

    @Override
    public Change vendItem() throws
            ItemOutOfStockException,
            InsufficientFundException,
            vmPersistenceException {

        Item it = dao.getItem(itemSelected);
        List items = getItems();
        Predicate<Item> checkCode = (Item item) -> item.getItemCode().equals(itemSelected);
        items.stream()
                .filter(checkCode)
                .collect(Collectors.toList());
        Change change = new Change();

        if (it.getQuantity() == 0) {

            throw new ItemOutOfStockException("Item out of Stock");

        } else if (moneyInMachine.compareTo(it.getCost()) < 0) {

            throw new InsufficientFundException("Insufficient Funds ");
        } else {

            it.setQuantity(it.getQuantity() - 1);
            dao.update(itemSelected, it);
            moneyInMachine = moneyInMachine.subtract(it.getCost());
            change = returnChange();

        }

        return change;
    }

    @Override
    public Change returnChange() {
        Change coins = new Change();

        BigDecimal changeDue = moneyInMachine;       
        
        coins.setAmoutQrter(changeDue.divide(coins.getQuarters()).intValue());
        changeDue = changeDue.subtract(coins.getQuarters().multiply(BigDecimal.valueOf(coins.getAmoutQrter())));

        coins.setAmoutdime(changeDue.divide(coins.getDimes()).intValue());
        changeDue = changeDue.subtract(coins.getDimes().multiply(BigDecimal.valueOf(coins.getAmoutdime())));

        coins.setAmountNick(changeDue.divide(coins.getNickels()).intValue());
        changeDue = changeDue.subtract(coins.getNickels().multiply(BigDecimal.valueOf(coins.getAmountNick())));

        coins.setAmountpen(changeDue.divide(coins.getPennies()).intValue());
        changeDue.subtract(coins.getPennies().multiply(BigDecimal.valueOf(coins.getAmountpen())));
        
        moneyInMachine = new BigDecimal("0.00");
        
        return coins;
    }

}
