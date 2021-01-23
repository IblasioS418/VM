/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.vendingmachine.view;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 *
 * @author ibby4
 */
public class UserIOConsoleImpl implements UserIO {
    Scanner sc = new Scanner(System.in);
    
    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public double readDouble(String prompt) {
        print(prompt);
        double userInput = sc.nextDouble();
        return userInput;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float readFloat(String prompt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
         int answer = readInt(prompt);
        while (answer < min || answer > max){
            print("Invalid Number");
            answer = readInt(prompt);
        }
        return answer;
    
    }

    @Override
    public int readInt(String prompt) {
        print(prompt);
        String userInput = sc.nextLine();
        return Integer.parseInt(userInput);
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int answer = readInt(prompt);
        while (answer < min || answer > max){
            print("Invalid Number");
            answer = readInt(prompt);
        }
        return answer;
    }

    @Override
    public long readLong(String prompt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        return sc.nextLine();
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        print (prompt);
        BigDecimal userInput = new BigDecimal(sc.nextLine());
        return userInput;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
