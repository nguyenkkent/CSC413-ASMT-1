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
//        if (HTTPmessages.length>=2){
//            for (int i=1; i<HTTPmessages.length; i++ ){
//                if (  (!(HTTPmessages[i].contains("\n"))) && (i== HTTPmessages.length)  )  {
//                    request.setBody(HTTPmessages[i]);
//                }
//                else if (HTTPmessages[i].length()>2){
//                    request.setHeaderParam( HTTPmessages[i].substring(0,HTTPmessages[i].indexOf(":")) , HTTPmessages[i].substring(HTTPmessages[i].indexOf(":")+2) );
//                }
//            }
//        }
        boolean hasBody = false;
        if (HTTPmessages.length>=2){
            for (int i=1; i<HTTPmessages.length; i++ ){
                HTTPmessages[i] = HTTPmessages[i].replace(" ", "").replace("\n","").replace("\r","");
                if ( HTTPmessages[i].contains("{") || (HTTPmessages[i].contains("}")) || (HTTPmessages.length<=2) ){
                    hasBody = true;
                    continue;
                }
                if(  (hasBody && (i==HTTPmessages.length-2))   ){
                    String strWithoutWhiteSpace = HTTPmessages[i].substring(HTTPmessages[i].indexOf("\""));
                    request.setBody("{" + strWithoutWhiteSpace + "}");
                }
                else if (HTTPmessages[i].length()>3 && (!HTTPmessages[i].contains("{") || !HTTPmessages[i].contains("}") )){
                    request.setHeaderParam( HTTPmessages[i].substring(0,HTTPmessages[i].indexOf(":")) , HTTPmessages[i].substring(HTTPmessages[i].indexOf(":")) );
                }
            }
        }
        return request;
    }
}
