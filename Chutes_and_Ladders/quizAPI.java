import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;
// import com.google.gson.Gson;

public class quizAPI {
    private String url;
    private HttpURLConnection con;
    private String json;
    private int response;
    public quizAPI() {
        this.url = "https://opentdb.com/api.php?amount=" + 10;   // url for api
        this.con = sendGet(url);                                 // sets up Http connection to website
        this.json = getWebsiteString(con);                       // get website string (api json)
        this.response = getResponseCode(con);        // gets the response of the website
    }

    // String url = "http://api.openweathermap.org/data/2.5/forecast?q=" + city + "&APPID=b351e40979955f1a541e74a6c4da057a";  // url for api
    // HttpURLConnection con = sendGet(url);       // sets up Http connection to website
    // String json = getWebsiteString(con);        // get website string (api json)
    // int response = getResponseCode(con);        // gets the response of the website

    public void disconnect() {
        con.disconnect();                           // disconnects from website
    }
    // HTTP GET request, sets up link to website
    private HttpURLConnection sendGet(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        return con;
    }
    // WEBSITE STRING method, returns string of website
    private String getWebsiteString(HttpURLConnection con) throws Exception{
        StringBuilder result = new StringBuilder();  // new string builder
        if (con.getResponseCode() > 200) {  // check if response code is greater than 200
            System.out.print("NOTE: Error with connection, city was not valid. \n\nPlease enter in a city, otherwise enter \"exit\".  ");
            return null;  // return null if response > 200
        }
        BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));  // buffered reader
        String line;  // generic line
        while ((line = rd.readLine()) != null) {  // while buffered reader has a lines that are not null
            result.append(line + "\n");  // append the string builder
        }
        rd.close();  // close buffer reader
        return result.toString();  // return string
    }
    // RETURN RESPONSE CODE, gets the response code of website
    private int getResponseCode(HttpURLConnection con) throws Exception {
        int code = con.getResponseCode(); // returns response code
        return code;
    }

}
