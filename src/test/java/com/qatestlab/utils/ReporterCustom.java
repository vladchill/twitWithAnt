package com.qatestlab.utils;


import org.testng.Reporter;

public class ReporterCustom {

    public static void log(String message){
        Reporter.log(message+"<br>");
    }
}
