package com.zc.dem.restful;

import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

/**
 * @author Joehart
 * @version 1.0
 */
public class ScheduleTaskClient
{
    /** 
    * 添加用户 
    */  
    private void example()
    {
        Client c = Client.create();
        WebResource r = c.resource("http://localhost:9998/helloworld");
        JSONObject obj = new JSONObject();
        try
        {
            obj.put("a", "1");
            obj.put("b", "2");
        }
        catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JSONObject response = r.type(MediaType.APPLICATION_JSON_TYPE).post(JSONObject.class, obj);
//        System.out.println(response.toString());
    }
}
