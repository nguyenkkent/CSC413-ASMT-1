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
        parse(test1);
//        parse(test2);


    }

    static void parse(String input){

        ParsedRequest request = new ParsedRequest();
        String[] HTTPmessages = input.split("\n");

        //method parse
        String[] firstTextLine = HTTPmessages[0].split(" ");
        request.setMethod(firstTextLine[0]);

        //path parse
        String path = firstTextLine[1];
        if (!path.contains("?")){
            request.setPath(path);
            path = "";
        }
        else{
            request.setPath(path.substring(0,path.indexOf("?")));
            path = path.substring(path.indexOf("?")+1);
        }

        //query parse
        while(path.length()!=0){
            if (path.contains("&")){//more than 1 query parameter
                request.setQueryParam(path.substring(0,path.indexOf("=")), path.substring( (path.indexOf("=")+1),path.indexOf("&")));
                path = path.substring(path.indexOf("&")+1);
                continue;
            }
            request.setQueryParam(path.substring(0,path.indexOf("=")), path.substring( (path.indexOf("=")+1)));
            path = "";
        }

        //HTTP version parse
        request.setVersion(firstTextLine[2]);

        //header parse
        for (int i=1; i<HTTPmessages.length; i++ ){
            if ( !HTTPmessages[i].contains(":")&& !HTTPmessages[i].contains("\n")){
                request.setBody(HTTPmessages[i]);
            }
            request.setHeaderParam( HTTPmessages[i].substring(0,HTTPmessages[i].indexOf(":")) , HTTPmessages[i].substring(HTTPmessages[i].indexOf(":")+2) );
        }

    }

}//end of class
