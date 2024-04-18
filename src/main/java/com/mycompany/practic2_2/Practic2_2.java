/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.practic2_2;

import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class Practic2_2 {

    public static void main(String[] args) {
        System.out.println("Start Program!");
        String server = "https://android-for-students.ru";
        String serverPath = "/materials/practical/hello.php";
        HashMap<String, String> map = new HashMap();
        map.put("name", "Gavrilov");
        map.put("group", "RIBO-01-22");
        HTTPRunnable hTTPRunnable = new HTTPRunnable(server + serverPath, map   );
        Thread th = new Thread(hTTPRunnable);
        th.start();
        try {
            th.join();
        } catch (InterruptedException ex) {
        } finally{
            JSONObject jSONObject = new JSONObject(hTTPRunnable.getResponseBody());
            int result = jSONObject.getInt("result_code");
            System.out.println("Result: " + result);
            System.out.println("Type: " + jSONObject.getString("message_type"));
            System.out.println("Text: " + jSONObject.getString("message_text"));
            switch (result) {
                case 1: //server vernyl dannie
                    JSONArray jSONArray = jSONObject.getJSONArray("task_list");
                    System.out.println("Task list:");
                    for(int i = 0; i < jSONArray.length(); i++){
                        System.out.println((i + 1) + ") " + jSONArray.getString(i));
                    }
                    break;
                case 0: //server vernyl owibky
                    System.out.println("Error!");
                    break;
                default: //ost slychai
                    break;
            }
        }
    }
}