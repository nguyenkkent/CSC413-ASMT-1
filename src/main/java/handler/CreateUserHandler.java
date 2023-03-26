package handler;

import com.google.gson.Gson;
import dao.UserDao;
import dto.UserDto;

import java.util.ArrayList;
import java.util.List;
import request.ParsedRequest;
import response.CustomHttpResponse;
import response.ResponseBuilder;
import response.RestApiAppResponse;

public class CreateUserHandler implements BaseHandler{

//    @Override
//    public CustomHttpResponse handleRequest(ParsedRequest request) {
//        String bodyInJsonFormat = request.getBody();
//        String userName = bodyInJsonFormat.substring( bodyInJsonFormat.indexOf(":")+2,bodyInJsonFormat.length()-2 );
//        UserDto newUser = new UserDto();
//        newUser.setUserName(userName);
//        List userNameList = new ArrayList<>();
//        userNameList.add(newUser.getUserName());
//
//        var response = new RestApiAppResponse<>(true,userNameList,null );
//        return new ResponseBuilder().setStatus("200 OK").setBody(GsonTool.gson.toJson(response)).build();
////        return null;
//    }

    @Override
    public CustomHttpResponse handleRequest(ParsedRequest request) {
//        String json = "{\n" +
//                       "    \"userName\": \"Kent1\"" +"\n" +
//                        "}";
        String json = request.getBody();
        UserDto user = GsonTool.gson.fromJson(json, UserDto.class);

        System.out.println("Here is the body:" + request.getBody());

        return new ResponseBuilder().setStatus("200 OK").setBody(GsonTool.gson.toJson(user)).build();

    }
}
