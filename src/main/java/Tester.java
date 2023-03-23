import request.ParsedRequest;
import com.google.gson.Gson;
import response.CustomHttpResponse;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Tester {

    public static void main(String[] args) {
        String status = "200 Success";
        String version = "HTTP/1.1";
        String body = "Response toString test";
        Map<String,String> headers = new HashMap<>();

        headers.put("Server","Apache");
        headers.put("Content-Encoding","gzip");
        headers.put("Content-Type","text/html; charset=utf-8");

        CustomHttpResponse response = new CustomHttpResponse(headers, status, version, body);

        System.out.println(response.toString());

    }



}
