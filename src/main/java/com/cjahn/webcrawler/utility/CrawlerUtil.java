package com.cjahn.webcrawler.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.web.bind.annotation.RequestMethod;

public class CrawlerUtil {
    public static String RequestAPI(String apiURL, RequestMethod method, Map<String, String> header) throws Exception {
        HttpURLConnection con;
        URL url = new URL((String) apiURL);

        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(method.toString());
        
        Set<Entry<String, String>> set = header.entrySet();
        Iterator<Entry<String, String>> itr = set.iterator();
        while (itr.hasNext()) {
            Map.Entry<String, String> e = (Map.Entry<String, String>) itr.next();
            con.setRequestProperty(e.getKey(), e.getValue());
        }
        int responseCode = con.getResponseCode();
        BufferedReader br;
        if(responseCode==200) { // 정상 호출
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } else {  // 에러 발생
            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = br.readLine()) != null) {
            response.append(inputLine);
        }
        br.close();
        
        return response.toString();
    }
}
