import request.ParsedRequest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Tester {

    public static void main(String[] args) {

        String test1 = "GET /hello HTTP/1.1\n"
                + "User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)\n"
                + "Accept-Language: en-us\n"
                + "Accept-Encoding: gzip, deflate\n"
                + "Connection: Keep-Alive";

        String test2 = "STUFF /hello?location=sfsu&state=california HTTP/1.1\n"
                + "User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)\n"
                + "Accept-Language: en-us\n"
                + "Accept-Encoding: gzip, deflate\n"
                + "Connection: Keep-Alive";
        parse(test2);


    }

    static void parse(String input){

        ParsedRequest request = new ParsedRequest();
        String[] HTTPmessages = input.split("\n");

        //item 0 = method, path, query
        String[] textLine = HTTPmessages[0].split(" ");
        request.setMethod(textLine[0]);

        String path = textLine[1];

        while(path.length()!=0){
            if (path.indexOf("?")<0){//path has no query parameter
            }
        }
        request.setPath(path.substring(0,path.indexOf("?")));
        path = path.substring(path.indexOf("?"));




        //iterate through array
        //item 1-n = headers and possibly a body


    }

}
