/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.vendingmachine.dao;

import com.is.vendingmachine.services.vmPersistenceException;

/**
 *
 * @author ibby4
 */
public interface ItemAuditDao {
    void writeAuditEntry(String entry) throws vmPersistenceException;
}
