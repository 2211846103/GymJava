/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itse322;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Zenjar
 */
public class LoginHandler {
    private static ArrayList<Integer> clientIDs;
    private static ArrayList<String> clientPasses;
    
    public static void init() {
        int[] clientIDArray = {123, 321, 231, 232};
        String[] clientPassArray = {"test", "test2", "test3", "test4"};
        clientIDs = new ArrayList(Arrays.asList(clientIDArray));
    }
    
    public static boolean checkUser(int userID, String userPass) {
        
    }
}
