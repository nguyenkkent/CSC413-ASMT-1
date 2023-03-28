package handler;

import request.ParsedRequest;
import response.CustomHttpResponse;
import response.ResponseBuilder;
import response.RestApiAppResponse;

public class FallbackHandler implements BaseHandler {

    @Override
    public CustomHttpResponse handleRequest(ParsedRequest request) {
        return new ResponseBuilder().setStatus("HTTP/1.1 404 Not Found\n").build();
    }
}
