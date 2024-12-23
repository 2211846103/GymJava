/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itse322;

// Imports
import java.util.regex.*;

/**
 *
 * @author Zenjar
 */
public class Validator {
    // Define Patterns
    private final static Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    private final static Pattern PHONE_PATTERN = Pattern.compile("^(09[1-6]\\d{7}|0[2-8]\\d{7})$");
    private final static Pattern DECIMAL_PATTERN = Pattern.compile("^(?:\\d+(?:\\.\\d+)?)$");
    
    // Methods
    public static boolean isEmail(String text) {
        return EMAIL_PATTERN.matcher(text).matches();
    }
    public static boolean isPhone(String text) {
        return PHONE_PATTERN.matcher(text).matches();
    }
    public static boolean isDecimal(String text) {
        return DECIMAL_PATTERN.matcher(text).matches();
    }
}
