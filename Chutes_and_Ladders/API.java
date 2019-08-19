import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
// hello everyone
public class API {
    private String url;
    private HttpURLConnection con;
    private String json;
    private int response;
    public API() {
        try {
            this.url = "https://opentdb.com/api.php?amount=" + 50;   // url for quiz
            this.con = sendGet(url);                                 // sets up Http connection to website
            this.json = getWebsiteString(con);                       // get website string (api json)
            this.response = getResponseCode(con);        // gets the response of the website
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public String getResponse() {
        return this.json;
    }
    public int getCode() {
        return this.response;
    }
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
