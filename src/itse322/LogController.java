/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itse322;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 
 */
public class LogController {
    private static final Logger log = LogManager.getLogger(Logger.class);
    
    public static void info(String message) {
        log.info(message);
    }
    public static void warn(String message) {
        log.warn(message);
    }
    public static void error(String message) {
        log.error(message);
    }
    public static void fatal(String message) {
        log.fatal(message);
    }
}
