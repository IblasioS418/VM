/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.vendingmachine;

import com.is.vendingmachine.controller.vmController;
import com.is.vendingmachine.services.InsufficientFundException;
import com.is.vendingmachine.services.ItemNotFoundException;
import com.is.vendingmachine.services.ItemOutOfStockException;
import com.is.vendingmachine.services.vmPersistenceException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author ibby4
 */
public class App {
    public static void main(String[] args) throws ItemNotFoundException, ItemOutOfStockException, InsufficientFundException, vmPersistenceException {       
    
    ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        vmController controller
                = ctx.getBean("controller", vmController.class);
        controller.run();
}
}

