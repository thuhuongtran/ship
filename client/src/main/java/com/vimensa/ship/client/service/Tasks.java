package com.vimensa.ship.client.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vimensa.ship.client.APIStart;
import com.vimensa.ship.client.authentication.security.TokenAuthenticationService;
import com.vimensa.ship.client.dao.OrderLog;
import com.vimensa.ship.client.data.DataProcess;
import com.vimensa.ship.client.model.Status;
import com.vimensa.ship.client.request.NewOrderRequest;
import com.vimensa.ship.client.response.NewOrderResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.apache.http.protocol.HTTP.USER_AGENT;

@Service
public class Tasks {
    private final static Logger logger = LoggerFactory.getLogger(APIStart.class);
    @Autowired
    private DataProcess dao;

    public static String getDecodedJwt(String jwt) {
        String result = "";

        String[] parts = jwt.split("[.]");
        try {
            int index = 0;
            for (String part : parts) {
                if (index >= 2)
                    break;

                index++;
                byte[] partAsBytes = part.getBytes("UTF-8");
                String decodedPart = new String(java.util.Base64.getUrlDecoder().decode(partAsBytes), "UTF-8");

                result += decodedPart;
            }
        } catch (Exception e) {
            throw new RuntimeException("Couldn't decode jwt", e);
        }
        result = result.substring(result.indexOf("sub") + 6, result.indexOf(",") - 1);
        return result;
    }

    public static String getOrderID() {
        String str = "";
        long timestamp = Calendar.getInstance().getTimeInMillis();
        return str + timestamp + "OD";
    }

    public static String getTimestamp() {
        String str = "";
        long timestamp = Calendar.getInstance().getTimeInMillis();
        return str + timestamp;
    }

    public static double getFee(double distance) { // distance in km
        double fee = 20000;
        if (distance <= 3) {
            fee = 20000;
        } else {
            int mul = (int) (distance / 2);
            fee = fee + mul * 5000;
        }
        return fee;
    }

    /**
     * call get_driver_api
     * response driver_phone to client
     */
    public static NewOrderResponse getDriver(NewOrderRequest order, String orderID, String jwt) throws IOException {
        String url = "http://192.168.1.192:8072/getshipper";

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);

// add header
        post.setHeader("User-Agent", USER_AGENT);
        post.setHeader("Authorization", jwt);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("client_phone", order.getClient_phone()));
        urlParameters.add(new BasicNameValuePair("order_id", orderID));
        urlParameters.add(new BasicNameValuePair("from_lat", String.valueOf(order.getFrom_lat())));
        urlParameters.add(new BasicNameValuePair("from_log", String.valueOf(order.getFrom_log())));
        urlParameters.add(new BasicNameValuePair("to_lat", String.valueOf(order.getTo_lat())));
        urlParameters.add(new BasicNameValuePair("to_log", String.valueOf(order.getTo_log())));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        HttpResponse response = client.execute(post);
        HttpEntity entity = response.getEntity();
        String json = EntityUtils.toString(entity);
        EntityUtils.consume(entity);
        JsonObject result = (JsonObject) new JsonParser().parse(json);

        NewOrderResponse newOrderResponse = new NewOrderResponse();
        newOrderResponse.setShipper_phone(result.get("shipperPhone").getAsString());
        newOrderResponse.setShipper_lat(result.get("shipperLat").getAsString());
        newOrderResponse.setShipper_log(result.get("shipperLog").getAsString());

        return newOrderResponse;
    }
    public static boolean checkClientRole(String jwt){
        Claims claims = Jwts.parser().setSigningKey(TokenAuthenticationService.SECRET).parseClaimsJws(jwt).getBody();
        String body = claims.getSubject();
        return body.contains("cLi");
    }

}
