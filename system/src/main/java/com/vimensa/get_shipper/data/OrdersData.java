package com.vimensa.get_shipper.data;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.vimensa.get_shipper.model.Order;
import com.vimensa.trans_ords.ggl_api.model.TransOrd;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersData {
    public static final String ORDERS_FILE = "data/orders.json";
    public static List<Order> orders = new ArrayList<>();

    public static JsonObject config;
    public static void getOrders(){
        initJson();
        JsonArray arr = config.getAsJsonArray("ords");
        for(int i=0;i<arr.size();i++){
            JsonObject obj = arr.get(i).getAsJsonObject();
            String id = obj.get("num").getAsString();
            String oriName = obj.get("oriName").getAsString();
            double oriLat =  Double.valueOf(obj.get("oriLat").getAsString());
            double oriLot =  Double.valueOf(obj.get("oriLot").getAsString());
            String desName =  obj.get("desName").getAsString();
            double desLat =  Double.valueOf(obj.get("desLat").getAsString());
            double desLot = Double.valueOf(obj.get("desLot").getAsString());
            Order order = new Order(id,oriLat,oriLot,desLat,desLot);
            orders.add(order);
         }

    }
    private static void initJson(){
        File file = new File(ORDERS_FILE);
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
