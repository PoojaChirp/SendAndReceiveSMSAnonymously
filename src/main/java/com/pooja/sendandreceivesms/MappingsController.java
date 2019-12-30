package com.pooja.sendandreceivesms;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingsController {

    ArrayList<Mappings> maps = new ArrayList<Mappings>();
    MappingsController(){
        maps.add(new Mappings("+3098264420","+17202551511"));
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


}
