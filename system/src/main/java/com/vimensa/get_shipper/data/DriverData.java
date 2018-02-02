package com.vimensa.get_shipper.data;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.vimensa.get_shipper.model.Driver;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DriverData {
    public static final String DRIVERS_FILE = "data/drivers.json";
    public static List<Driver> drivers = new ArrayList<>();

    public static JsonObject config;
    public static void getDrivers(){
        initJson();
        JsonArray arr = config.getAsJsonArray("shippers");
        for(int i=0;i<arr.size();i++){
            JsonObject obj = arr.get(i).getAsJsonObject();
            String id = obj.get("num").getAsString();
            String name = obj.get("name").getAsString();
            String lat = obj.get("latitude").getAsString();
            String lot = obj.get("longitude").getAsString();

            Driver driver = new Driver(id, Double.valueOf(lot), Double.valueOf(lat));
            drivers.add(driver);
        }

    }
    private static void initJson(){
        File file = new File(DRIVERS_FILE);
        StringBuffer contents = new StringBuffer();
        BufferedReader reader = null;
        try {
            Reader r = new InputStreamReader(new FileInputStream(file), "UTF-8");
            reader = new BufferedReader(r);
            String text = null;
            while((text = reader.readLine())!=null){
                contents.append(text).append(System.getProperty("line.separator"));
            }
            config = toJsonObject(contents.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
     * Gson is to convert a java obj to json obj in opposition
     * */
    private static Gson gson = new Gson();
    /*
     * turn json string to jsonObject
     * */
    public static JsonObject toJsonObject(String json){
        try {
            return gson.fromJson(json, JsonObject.class);
        }catch (Exception e){
            return null;
        }
    }
}
