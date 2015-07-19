package com.example.supersaiyans.hangout.Util;

import android.content.Context;


import com.example.supersaiyans.hangout.model.Event;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Chetan on 7/18/2015.
 */
public class ReadEventXML {

    public ArrayList<Event> readEventXML (Context c){
        final ArrayList<Event> events = new ArrayList<Event>();

        try {

            InputStream is = c.getAssets().open("events.xml");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
               // ArrayList<Event> events = new ArrayList<Event>();
                boolean eID = false;
                boolean ename = false;
                boolean gLat = false;
                boolean gLong = false;
                String eventID;
                Double[] location = new Double[2];
                String organizer;
                String eventName;


                public void startElement(String uri, String localName,String qName,
                                         Attributes attributes) throws SAXException {

                    System.out.println("Start Element :" + qName);

                    if (qName.equalsIgnoreCase("Event")) {
                        eID = true;
                        eventID = attributes.getValue(0);
                    }

                    if (qName.equalsIgnoreCase("Name")) {
                        ename = true;
                    }

                    if (qName.equalsIgnoreCase("GeoLat")) {
                        gLat = true;
                    }

                    if (qName.equalsIgnoreCase("GeoLong")) {
                        gLong = true;
                    }

                }

                public void endElement(String uri, String localName,
                                       String qName) throws SAXException {

                    if (qName.equalsIgnoreCase("Event")) {
                        events.add(new Event(eventName,Integer.parseInt(eventID),location));

                    }

                }

                public void characters(char ch[], int start, int length) throws SAXException {

                    if (eID) {
                        eID = false;
                    }

                    if (ename) {
                        eventName = new String(ch, start, length);
                        ename = false;
                    }

                    if (gLat) {
                        location[0] = Double.parseDouble(new String(ch, start, length));
                        gLat = false;
                    }

                    if (gLong) {
                        location[1] = Double.parseDouble(new String(ch, start, length));
                        gLong = false;
                    }

                }

            };

            saxParser.parse(is, handler);
            return events;
        }catch (Exception e) {
            return null;
        }
        //return ents;

    }

}
