/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Daniel Tomé <daniel.tome@estudiants.urv.cat>
 */
@WebService(serviceName = "UrlWebService")
public class UrlWebService {

    
    /**
     * This method cuts an url  
     * @param url
     * @return urlShort
     * @throws java.security.NoSuchAlgorithmException
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
