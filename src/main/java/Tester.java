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
        user.setUserName(user1);
        String test1 = "POST /createUser HTTP/1.1\n"
                + "Host: test\n"
                + "Connection: Keep-Alive\n"
                + "\n"
                + gson.toJson(user);
        parse(test1);
    }

    public static void parse(String input){

        ParsedRequest request = new ParsedRequest();
        String[] HTTPmessages = input.split("\n");
        System.out.println((HTTPmessages.length));
        for (int i=0; i< HTTPmessages.length; i++){
            System.out.println(HTTPmessages[i]);
        }

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
        boolean hasBody = false;
        if (HTTPmessages.length>=2){
            for (int i=1; i<HTTPmessages.length; i++ ){
                if (HTTPmessages[i].length()==0){
                    hasBody = true;
                }
                if( (HTTPmessages[i].contains("{")&&HTTPmessages[i].contains("}")) || (hasBody && (i==HTTPmessages.length-1)) ){
                    request.setBody(HTTPmessages[i].replaceAll("\\s","").replace("\n",""));
                }
                else if (HTTPmessages[i].length()>2){
                    request.setHeaderParam( HTTPmessages[i].substring(0,HTTPmessages[i].indexOf(":")) , HTTPmessages[i].substring(HTTPmessages[i].indexOf(":")+2) );
                }
            }
        }

        System.out.println("Here is the body: " + request.getBody());




    }
}
