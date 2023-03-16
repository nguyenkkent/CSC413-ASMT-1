package handler;

import dao.MessageDao;
import dao.UserDao;
import dto.MessageDto;
import java.util.List;
import java.util.stream.Collectors;
import request.ParsedRequest;
import response.CustomHttpResponse;
import response.ResponseBuilder;
import response.RestApiAppResponse;

public class GetMessagesHandler implements BaseHandler {

  @Override
  public CustomHttpResponse handleRequest(ParsedRequest request) {
    return null;
  }

}
