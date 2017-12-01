package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static ArrayList<JSONObject> lessons;

//    public static void main(String[] args) {
    public static void main(String args[])
    {
        lessons = new ArrayList<JSONObject>();
        JSONParser parser = new JSONParser();
        try
        {
            FileReader file = new FileReader("coursera_webgl_data.json");
            Object object = parser
                    .parse(file);

            //convert Object to JSONObject
            JSONObject jsonObject = (JSONObject)object;
//            System.out.println(jsonObject);
            //Reading the String
//            String name = (String) jsonObject.get("Name");
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(jsonObject.get("links"));

            JSONArray clean = (JSONArray) jsonArray.get(0);

            for(int i =0; i< clean.size(); i++){
                JSONArray temp = (JSONArray) clean.get(i);
                String link = temp.get(0).toString();
                String name = temp.get(1).toString();

                System.out.println("DOWNLOADING - "+name);
                URL website = new URL(link);
                ReadableByteChannel rbc = Channels.newChannel(website.openStream());
                FileOutputStream fos = new FileOutputStream(name);
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                System.out.println("SUCCESSFULLY DOWNLOADED "+name);
            }
        }
        catch(FileNotFoundException fe)
        {
            fe.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
//        InputStream in = new FileInputStream("coursera_webgl_data.json");
//        BufferedReader buf = new BufferedReader(new InputStreamReader(in));
//
//        String line = buf.readLine();
//        StringBuilder sb = new StringBuilder();
//
//        while (line != null){
//            sb.append(line).append("\n");
//            line = buf.readLine();
//        }
//
//        String fileAsString = sb.toString();
//        System.out.println(fileAsString);

//    }
}
