package request;

public class CustomParser {

    // extract java useable values from a raw http request string
    // https://developer.mozilla.org/en-US/docs/Web/HTTP/Messages
    public static ParsedRequest parse(String input) {

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
        boolean hasBody = false;
        if (HTTPmessages.length >= 2) {
            for (int i = 1; i < HTTPmessages.length; i++) {
                System.out.println("current item for iteration: " + HTTPmessages[i] + "with length = " + HTTPmessages[i].length()+ " and the hasBody = " + hasBody );

                HTTPmessages[i] = HTTPmessages[i].replace(" ", "");

                if ( (i==HTTPmessages.length-2) || HTTPmessages[i].contains("{") || (HTTPmessages[i].contains("}"))  )  {
                    System.out.println("we in the brace or a newline char area ");
                    hasBody = true;
                }

                if ( hasBody&&(i==(HTTPmessages.length-2)) || hasBody&&(i==(HTTPmessages.length-1))    )  {
                    if (HTTPmessages[i - 1].contains("{")) {
//                        String strWithoutWhiteSpace = HTTPmessages[i].substring(HTTPmessages[i].indexOf("\""));
                        String strWithoutWhiteSpace = HTTPmessages[i].replace(" ","");
                        System.out.println("substring: " + strWithoutWhiteSpace);
//                        request.setBody(HTTPmessages[i-1] + strWithoutWhiteSpace + HTTPmessages[i+1]);
                        System.out.println("getBody() = " + request.getBody());
                    } else {
                        request.setBody(HTTPmessages[i]);
                    }
                }
                else if (HTTPmessages[i].length()>4  ){
//                    System.out.println("We're in the header area");
                    request.setHeaderParam(HTTPmessages[i].substring(0, HTTPmessages[i].indexOf(":")), HTTPmessages[i].substring(HTTPmessages[i].indexOf(":")));
                }
            }
        }
        return request;
    }
}
