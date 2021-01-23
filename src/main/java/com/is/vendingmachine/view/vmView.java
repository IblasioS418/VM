/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.vendingmachine.view;

import com.is.vendingmachine.dto.Change;
import com.is.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author ibby4
 */
public class vmView {
    BigDecimal money = new BigDecimal("0.00");
    Change coin = new Change();

    private UserIO io;

    public vmView(UserIO io) {
        this.io = io;
    }

    public BigDecimal getMoney() {        
        money = io.readBigDecimal("Enter Money into Vending machine");
        return money;
    }
    
    public void money(BigDecimal money){          
        io.print("Current Balance: " + money.toString());     
    }

    public String getSelection() {
        return io.readString("Select Item code for desired item or Press X to exit").toUpperCase();
    }

    public void getMenu(List<Item> itemList) {
        io.print("=======> Vending Machine <=======");
        for (Item it : itemList) {
            io.print(it.getItemCode() + " //"
                    + it.getItemName() + " //"
                    + "Price => " + it.getCost() + " //"
                    + "Current Stock =>" + it.getQuantity());
            
            
           

        }io.print("");
         io.print("");
        

    }


    public void displayVendingItemBanner() {
        io.print("  Vending Item..... *clunk* ");
    }

    public void displayItemVended(Change coin) {
        io.print("=======Change Due =======");
        io.print("Your change: ");
        io.print("You Recieve " + coin.getAmoutQrter() + " Quarters");
        io.print("You Recieve " + coin.getAmoutdime() + " Dimes");
        io.print("You Recieve " + coin.getAmountNick() + " Nickels");
        io.print("You Recieve " + coin.getAmountpen() + " Pennies");
    }

    

    public void displayErrorMessage(String errorMsg) {        
        io.print(errorMsg);        
    }

    public void displayUnknownCommand() {
        io.print("Thank you");
    }
    
    public String getNewSelection () {
        return io.readString("Please select valid item code or Press X to exit").toUpperCase();
    }
}
