import dto.UserDto;
import request.ParsedRequest;
import com.google.gson.Gson;
import response.CustomHttpResponse;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static handler.GsonTool.gson;


public class Tester {

    public static void main(String[] args) {
        String user1 = String.valueOf(Math.random());
        UserDto user = new UserDto();

        String test1 = "POST /createUser HTTP/1.1\n"
                + "Host: test\n"
                + "Connection: Keep-Alive\n"
                + "\n"
                + gson.toJson(user1);
        parse(test1);
    }

    public static void parse(String input) {

        ParsedRequest request = new ParsedRequest();
        String[] HTTPmessages = input.split("\n");

        //method parse
        String[] firstTextLine = HTTPmessages[0].split(" ");
        request.setMethod(firstTextLine[0]);

        //path parse
        String path = firstTextLine[1];
        if (!path.contains("?")) {
            request.setPath(path);
            path = "";
        } else {
            request.setPath(path.substring(0, path.indexOf("?")));
            path = path.substring(path.indexOf("?") + 1);
        }

        //query parse
        while (path.length() != 0) {
            if (path.contains("&")) {//more than 1 query parameter
                request.setQueryParam(path.substring(0, path.indexOf("=")), path.substring((path.indexOf("=") + 1), path.indexOf("&")));
                path = path.substring(path.indexOf("&") + 1);
                continue;
            }
            request.setQueryParam(path.substring(0, path.indexOf("=")), path.substring((path.indexOf("=") + 1)));
            path = "";
        }

        //HTTP version parse
        request.setVersion(firstTextLine[2]);

        //header parse
        if (HTTPmessages.length >= 2) {
            for (int i = 1; i < HTTPmessages.length; i++) {
                System.out.println(HTTPmessages[i]);
                if (!(HTTPmessages[i].contains(":")) && !(HTTPmessages[i].contains("\n") && i == HTTPmessages.length)) {
                    request.setBody(HTTPmessages[i]);
                } else {
                    request.setHeaderParam(HTTPmessages[i].substring(0, HTTPmessages[i].indexOf(":")), HTTPmessages[i].substring(HTTPmessages[i].indexOf(":") + 2));
                }

            }
        }


    }
}
