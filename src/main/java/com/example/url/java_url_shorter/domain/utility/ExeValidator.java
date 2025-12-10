package com.example.url.java_url_shorter.domain.utility;

import java.net.HttpURLConnection;
import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExeValidator {

    private static final Logger log = LoggerFactory.getLogger(ExeValidator.class);

    public static boolean isUrlExists(String urlString){
        try{
            log.debug("Checking the url:{}",urlString);
            java.net.URL url = new URI(urlString).toURL();
            
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("HEAD");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            int responseCode = con.getResponseCode();
            return (responseCode>=200 && responseCode <400);
        }catch (Exception e){
            log.error("Error :{}",urlString,e);
            return false;
        }
    }

}