package handler;

import com.google.gson.Gson;
import dao.MessageDao;
import dao.UserDao;
import dto.MessageDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import request.ParsedRequest;
import response.CustomHttpResponse;
import response.ResponseBuilder;
import response.RestApiAppResponse;

public class GetMessagesHandler implements BaseHandler {

    @Override
    public CustomHttpResponse handleRequest(ParsedRequest request) {

        String fromId = request.getQueryParam("fromId");
        String toId = request.getQueryParam("toId");

        MessageDao messageDao = MessageDao.getInstance();
        List<MessageDto> listOfAllMessageDtos = messageDao.getAll();
        List<MessageDto> filteredListofMessageDtos = new ArrayList<>();
        for (MessageDto dto : listOfAllMessageDtos){
            if ((dto.getFromId()==fromId)&&(dto.getToId()==toId)){
                filteredListofMessageDtos.add(dto);
            }
        }
        var res = new RestApiAppResponse<>(true, filteredListofMessageDtos, null);

        return new ResponseBuilder().setStatus("200 OK").setBody(GsonTool.gson.toJson(res)).build();
    }

}
