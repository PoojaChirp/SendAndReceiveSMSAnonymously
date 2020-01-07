package com.pooja.sendandreceivesms;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MappingsControllerTest {

    @Test
    void mapIt() {
    }

    @Test
    void listMappings() {
    }

    @Test
    void deleteMap() {
    }

    @Test
    void retrieveBody() {
    }

    @Test
    void whenInputMatchesFirstAttrReturnSecondAttr(){
        String input ="+13098264420";
        List<Mappings> list = new ArrayList<>();
        String toNumber = "+17202551511";
        Mappings mappings = new Mappings(input, toNumber);
        list.add(mappings);
        MappingsController ctrl = new MappingsController();
        String rtnval = ctrl.findToNumber(list, input);
        assertEquals(toNumber,rtnval);
    }
    @Test
    void whenInputMatchesSecondAttrReturnFirstAttr(){
        String input ="+17202551511";
        List<Mappings> list = new ArrayList<>();
        String toNumber = "+13098264420";
        Mappings mappings = new Mappings(input, toNumber);
        list.add(mappings);
        MappingsController ctrl = new MappingsController();
        String rtnval = ctrl.findToNumber(list, input);
        assertEquals(toNumber,rtnval);
    }

    @Test
    void whenInputDoesntMatchAnyParamReturnNull(){
        String fromNumber ="+17202551511";
        List<Mappings> list = new ArrayList<>();
        String toNumber = "+13098264420";
        Mappings mappings = new Mappings(fromNumber, toNumber);
        list.add(mappings);
        MappingsController ctrl = new MappingsController();
        String rtnval = ctrl.findToNumber(list, "+132456789");
        assertEquals(null,rtnval);
    }

    @Test
    void testDecoding() throws UnsupportedEncodingException {
        String str ="%2B14845529250";
        String rtnval = URLDecoder.decode(str, "UTF-8");

        assertEquals("+14845529250",rtnval);
    }
}