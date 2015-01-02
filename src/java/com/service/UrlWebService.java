/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.sql.DataSource;

/**
 *
 * @author Daniel Tomé <daniel.tome@estudiants.urv.cat>
 */
@WebService(serviceName = "UrlWebService")
public class UrlWebService {
    @Resource(name = "SOB_P1_DB")
    private DataSource SOB_P1_DB;

    /**
     * This is a sample web service operation
     * @param txt
     * @return 
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    /**
     * This method cuts an url  
     * @param url
     * @return urlShort
     */
    @WebMethod(operationName = "urlCut")
    public String cutUrl (@WebParam(name = "url") String url) throws NoSuchAlgorithmException {
        System.out.println("WEB SERVICE: "+url);

        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.reset();
        byte[] buffer = url.getBytes();
        md.update(buffer);
        byte[] digest = md.digest();

        String hexStr = "";
        for (int i = 0; i < 3; i++) {
            hexStr += Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1);
        }
        System.out.println("WEB SERVICE: "+ hexStr);
        return hexStr;
    }
}
