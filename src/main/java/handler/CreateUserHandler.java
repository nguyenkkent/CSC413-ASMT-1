package handler;

import com.google.gson.Gson;
import dao.UserDao;
import dto.UserDto;

import java.util.List;
import request.ParsedRequest;
import response.CustomHttpResponse;
import response.ResponseBuilder;
import response.RestApiAppResponse;

public class CreateUserHandler implements BaseHandler{


    @Override
    public CustomHttpResponse handleRequest(ParsedRequest request) {

        String json = request.getBody();

        UserDto user = GsonTool.gson.fromJson(json, UserDto.class);

        System.out.println("here is the body:" + json);
        System.out.println("here is the userDTO: ");
        System.out.println("UserDto's getUserName(): " + user.getUserName());
        System.out.println("UserDto's getUniqueId(): " + user.getUniqueId());

        UserDao userDao = UserDao.getInstance();
        userDao.put(user);
        List<UserDto> listOfUserDto = userDao.getAll();
        var res = new RestApiAppResponse<>(true, listOfUserDto, null);
        return new ResponseBuilder().setStatus("200 OK").setBody(GsonTool.gson.toJson(res)).build();


//        return new ResponseBuilder().setStatus("200 OK").setBody(GsonTool.gson.toJson(user)).build();

    }
}
