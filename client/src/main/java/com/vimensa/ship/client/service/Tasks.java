package com.vimensa.ship.client.service;

import com.vimensa.ship.client.APIStart;
import com.vimensa.ship.client.data.DataProcess;
import com.vimensa.ship.client.model.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;

@Service
public class Tasks {
    private final static Logger logger = LoggerFactory.getLogger(APIStart.class);
    @Autowired
    private DataProcess dao;
    public static String getDecodedJwt(String jwt)
    {
        String result = "";

        String[] parts = jwt.split("[.]");
        try
        {
            int index = 0;
            for(String part: parts)
            {
                if (index >= 2)
                    break;

                index++;
                byte[] partAsBytes = part.getBytes("UTF-8");
                String decodedPart = new String(java.util.Base64.getUrlDecoder().decode(partAsBytes), "UTF-8");

                result += decodedPart;
            }
        }
        catch(Exception e)
        {
            throw new RuntimeException("Couldn't decode jwt", e);
        }
        result = result.substring(result.indexOf("sub") + 6, result.indexOf(",") - 1);
        return result;
    }
    /**
     * add new order in db
     * @param: timestamp - from- to -mass- adv_paym - status - client_phone - note
     * */
    public void addNewOrder(String from, String to, String mass, String adv_paym, String note, String jwt){
        String phone = getDecodedJwt(jwt);
        long timestamp = Calendar.getInstance().getTimeInMillis();
        dao.addNewOrder(String.valueOf(timestamp),from,to,mass,adv_paym,
                String.valueOf(Status.ORDER_WAITING_FOR_CONFIRMATION),phone,note);
        logger.info(Tasks.class.getName()+" insert successfully");
    }
}
