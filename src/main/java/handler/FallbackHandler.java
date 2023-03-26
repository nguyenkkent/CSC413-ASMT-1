package handler;

import request.ParsedRequest;
import response.CustomHttpResponse;
import response.ResponseBuilder;
import response.RestApiAppResponse;

public class FallbackHandler implements BaseHandler {

    @Override
    public CustomHttpResponse handleRequest(ParsedRequest request) {
        var res =  new RestApiAppResponse<>(false, null, null);
        return new ResponseBuilder().setStatus("404 Not Found").build();
    }
}
