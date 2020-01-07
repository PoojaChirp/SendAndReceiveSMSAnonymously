package com.pooja.sendandreceivesms;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MappingsController {

    @Autowired
    SMSService service;

    @Autowired
    SMS sms;


    ArrayList<Mappings> maps = new ArrayList<Mappings>();
    MappingsController(){
        maps.add(new Mappings("+13098264420","+17202551511"));
    }

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping( value = "/mappings", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void mapIt(@RequestBody Mappings map){
        // Spring automatically converts JSON to java objects
        maps.add(map);

    }

    @RequestMapping(value ="/mappings", method = RequestMethod.GET)
    public ArrayList listMappings(){
        //Automatic conversion of JAva to JSON is done by spring
        return maps;
    }

    @RequestMapping(value = "/mappings/{id}")
    public boolean deleteMap(@PathVariable int id){
        for (int i=0; i<maps.size();i++) {
            if (id == maps.get(i).getId() ){
                maps.remove(i);
            }
        }
        return true;
    }
    // whenever a request is made to a particular mapping such as /sms, @requestbody contains the information of the requested body.
    @RequestMapping(value="/sms", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void retrieveBody(@RequestBody String body) throws UnsupportedEncodingException {
        Map<String, String> myMessage = convertStringToMap(body);
        //  System.out.println(myMessage.get("Body"));
        String number = URLDecoder.decode(myMessage.get("From"), "UTF-8");
        String toNumber = findToNumber(maps, number);

        if(toNumber ==null){
            throw new IllegalArgumentException("No destination available for the number: "+  number);
        }
        sms.setMessage( URLDecoder.decode(myMessage.get("Body"),"UTF-8"));
        sms.setTo(toNumber);
        service.send(sms);

    }

    public String findToNumber(List<Mappings> maps, String number) {
        String rtnval=null;
        for(int i=0;i<maps.size();i++) {
            if(maps.get(i).getPhone2().toString().equalsIgnoreCase(number))
            {
                rtnval = maps.get(i).getPhone1().toString();
            }
            else if(maps.get(i).getPhone1().toString().equalsIgnoreCase(number))
            {
                rtnval = maps.get(i).getPhone2().toString();
            }

        }

        return rtnval;
    }

    private Map<String,String> convertStringToMap( String body) {
        Map<String, String> myMessage = new HashMap<>() ;
        String s = body;
        String[] pairs = s.split("&");
        for (int i=0;i<pairs.length;i++) {
            String pair = pairs[i];
            String[] keyValue = pair.split("=");
            myMessage.put(keyValue[0], keyValue.length==2?keyValue[1]:"");
        }
        return  myMessage;
    }


}
