package request;

public class CustomParser {

    // extract java useable values from a raw http request string
    // https://developer.mozilla.org/en-US/docs/Web/HTTP/Messages
    public static ParsedRequest parse(String input){

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

        if (HTTPmessages.length>=2){
            System.out.println("=============================");
            for (int i=1; i<HTTPmessages.length; i++ ){
                System.out.println(HTTPmessages[i]);
                if ( !(HTTPmessages[i].contains(":")) && !(HTTPmessages[i].contains("\n") && i==HTTPmessages.length) ){
                    request.setBody(HTTPmessages[i]);
                }
                else{
                    request.setHeaderParam( HTTPmessages[i].substring(0,HTTPmessages[i].indexOf(":")) , HTTPmessages[i].substring(HTTPmessages[i].indexOf(":")+2) );
                }
            }
        }

        return request;
    }
}
