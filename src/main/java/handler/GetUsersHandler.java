package handler;

import dao.UserDao;
import dto.UserDto;
import java.util.List;
import request.ParsedRequest;
import response.CustomHttpResponse;
import response.ResponseBuilder;
import response.RestApiAppResponse;

public class GetUsersHandler implements BaseHandler{

  @Override
  public CustomHttpResponse handleRequest(ParsedRequest request) {
    UserDao userDao = UserDao.getInstance();
    var res = new RestApiAppResponse<>(true, userDao.getAll(), null);
    return new ResponseBuilder().setStatus("200 OK").setBody(GsonTool.gson.toJson(res)).build();
  }

}