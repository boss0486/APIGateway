/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.datawsdl.soapxml;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ibot.module.base.entities.ApiResultModel;
import com.ibot.module.entities.Api01TopupRequest;
import com.ibot.module.entities.Api01TopupResult;
import com.ibot.module.entities.Test01;
import com.ibot.module.type.EnumService;
import com.ibot.notifization.MessageText;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Allen
 */
public class ApiRechargeService {

    public Api01TopupResult apiTest() {
        // call api service

//       String uri = "https://nap3s-6d9472890e72.banglangtim.club/api/recharge";
        String uri = "http://localhost:51759/api/weatherforecast/test";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-COM-PERSIST", "true");
        headers.set("X-COM-LOCATION", "USA");

        //HttpEntity<Test01> request = new HttpEntity<>(model, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        System.out.println("::" + result);

        return null;
    }

    public Api01TopupResult apiCom01_Topup(Api01TopupRequest model) {
        // call api service

//       String uri = "https://nap3s-6d9472890e72.banglangtim.club/api/recharge";
        String uri = "http://localhost:51759/api/weatherforecast/Recharge";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        // Dữ liệu đính kèm theo yêu cầu.
        HttpEntity<Api01TopupRequest> requestBody = new HttpEntity<>(model, headers);
        // Gửi yêu cầu với phương thức POST. 
        //ResponseEntity<String> result1 = restTemplate.postForEntity(uri, requestBody, String.class);
        //Gson gson = new Gson();
        //Api01TopupResult api01TopupResult = gson.fromJson(result1.getBody(), Api01TopupResult.class); 
        //
        Api01TopupResult api01TopupRequest = restTemplate.postForObject(uri, requestBody, Api01TopupResult.class);
        //
        if (api01TopupRequest != null) {
            System.out.println("::" + api01TopupRequest.getTransID());
        }
        
        
        
        return null;
    }
    // XML ################################################################################################################

    public ApiResultModel apiCom01_TopupXML(String strParam) {
        String wsURL = "https://www.dataaccess.com/webservicesserver/NumberConversion.wso?WSDL";
        URL url;
        URLConnection connection;
        HttpURLConnection httpConn;
        String responseString;
        String outputString = "";
        OutputStream out;
        InputStreamReader isr;
        BufferedReader in;

        String xmlInput = "<?xml version='1.0' encoding='utf-8'?>"
                + "<soap:Envelope xmlns:soap='http://schemas.xmlsoap.org/soap/envelope/'>"
                + "<soap:Body>"
                + "<NumberToWords xmlns='http://www.dataaccess.com/webservicesserver/'>"
                + "<ubiNum>500</ubiNum>"
                + "</NumberToWords>"
                + "</soap:Body>"
                + "</soap:Envelope>";

        try {
            url = new URL(wsURL);
            connection = url.openConnection();
            httpConn = (HttpURLConnection) connection;

            byte[] buffer = new byte[xmlInput.length()];
            buffer = xmlInput.getBytes();

            String SOAPAction = "";
            // Set the appropriate HTTP parameters.
            httpConn.setRequestProperty("Content-Length", String.valueOf(buffer.length));
            httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");

            httpConn.setRequestProperty("SOAPAction", SOAPAction);
            httpConn.setRequestMethod("POST");
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            out = httpConn.getOutputStream();
            out.write(buffer);
            out.close();

            // Read the response and write it to standard out.
            isr = new InputStreamReader(httpConn.getInputStream());
            in = new BufferedReader(isr);

            while ((responseString = in.readLine()) != null) {
                outputString = outputString + responseString;
            }
            //System.out.println(outputString);
            // Get the response from the web service call
            Document document = parseXmlFile(outputString);
            //
            NodeList nodeLst = document.getElementsByTagName("soap:Body");
            String webServiceResponse = nodeLst.item(0).getTextContent();
            //
            int transStatus = -1;
            System.out.println("The response from the web service call is : " + webServiceResponse);
            if (webServiceResponse != null && !webServiceResponse.isEmpty()) {
                transStatus = 1;
            }
            // end demo  
            if (transStatus == 1) {
                return new ApiResultModel(EnumService.APIPartnerEnum.COMP01, 1, "OK : " + webServiceResponse);
            }
            return new ApiResultModel(EnumService.APIPartnerEnum.COMP01, -1, MessageText.NotService);

        } catch (IOException | DOMException e) {
            return new ApiResultModel(EnumService.APIPartnerEnum.COMP01, -1, MessageText.NotService);
        }
    }

    private Document parseXmlFile(String in) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(in));
            return db.parse(is);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
