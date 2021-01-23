/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.vendingmachine.controller;

import com.is.vendingmachine.dao.ItemDAO;
import com.is.vendingmachine.dto.Change;
import com.is.vendingmachine.services.vmPersistenceException;
import com.is.vendingmachine.dto.Item;
import com.is.vendingmachine.services.InsufficientFundException;
import com.is.vendingmachine.services.ItemNotFoundException;
import com.is.vendingmachine.services.ItemOutOfStockException;
import com.is.vendingmachine.services.vmService;
import com.is.vendingmachine.view.UserIO;
import com.is.vendingmachine.view.UserIOConsoleImpl;
import com.is.vendingmachine.view.vmView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author ibby4
 */
public class vmController {

    private vmService service;
    ItemDAO dao;
    vmView view;

    public vmController(vmService service, vmView view) {
        this.service = service;
        this.view = view;
    }

    private UserIO io = new UserIOConsoleImpl();

    public void run() throws vmPersistenceException, ItemNotFoundException, ItemOutOfStockException, InsufficientFundException {
        String itemCode = "";
        boolean keepGoing = true;
        boolean balance = false;
        boolean notFound = true;
        boolean newMenu = true;

        while (keepGoing) {

            try {
                getMenu();
                bigDecimalMoney();

                while (notFound) {

                    try {

                        if (newMenu == true) {
                            itemCode = getMenuSelection();
                            notFound = false;
                        } else {
                            itemCode = newMenuSelection();
                            notFound = true;
                        }
                    } catch (vmPersistenceException | ItemNotFoundException ex) {
                        view.displayErrorMessage(ex.getMessage());
                        notFound = true;
                        newMenu = false;

                    }
                }
                if (!itemCode.equalsIgnoreCase("x")) {
                    vendItem();
                    keepGoing = false;

                } else {
                    exit();
                    keepGoing = false;
                }

            } catch (InsufficientFundException ex) {
                view.displayErrorMessage(ex.getMessage());
                
                balance = true;
            } catch (ItemOutOfStockException ex) {
                view.displayErrorMessage(ex.getMessage());
                notFound = true;
            }

        }
    }

    private String getMenuSelection() throws vmPersistenceException, ItemNotFoundException {
        String selectItem = view.getSelection();
        if (selectItem.equalsIgnoreCase("x")) {
            selectItem = "x".toUpperCase();
        } else {
            service.selectItem(selectItem);
        }

        return selectItem;
    }

    private String newMenuSelection() throws vmPersistenceException, ItemNotFoundException {
        String selectItem = view.getNewSelection();
        service.selectItem(selectItem);
        return selectItem;
    }

    private List<Item> getMenu() throws vmPersistenceException {
        List<Item> Items = service.getItems();
        view.getMenu(Items);
        return Items;
    }

    private void vendItem() throws ItemOutOfStockException, InsufficientFundException, vmPersistenceException {
        Change coin = service.vendItem();
        view.displayVendingItemBanner();
        view.displayItemVended(coin);

    }

    private void bigDecimalMoney() throws vmPersistenceException {
        BigDecimal money = view.getMoney();
        service.insertMoney(money);
        money = service.insertMoney(new BigDecimal("0.00"));
        view.money(money);
    }

    public void exit() {
        Change coin = service.returnChange();
        view.displayItemVended(coin);
        view.displayUnknownCommand();
    }

}
