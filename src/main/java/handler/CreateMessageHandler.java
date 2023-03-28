package handler;

import dao.MessageDao;
import dao.UserDao;
import dto.MessageDto;
import dto.UserDto;
import java.util.List;
import request.ParsedRequest;
import response.CustomHttpResponse;
import response.ResponseBuilder;
import response.RestApiAppResponse;

public class CreateMessageHandler implements BaseHandler {

    @Override
    public CustomHttpResponse handleRequest(ParsedRequest request) {
        //set fromId, toId, message, and create uniqueId
        String jsonString = request.getBody();
        MessageDto partaialInfoMessageDto = GsonTool.gson.fromJson(jsonString, MessageDto.class);
        MessageDto fullInfoMessageDto = new MessageDto(String.valueOf(Math.random()));
        fullInfoMessageDto.setFromId(partaialInfoMessageDto.getFromId());
        fullInfoMessageDto.setToId(partaialInfoMessageDto.getToId());
        fullInfoMessageDto.setMessage((partaialInfoMessageDto.getMessage()));

        MessageDao messageDao = MessageDao.getInstance();
        messageDao.put(fullInfoMessageDto);
        List<MessageDto> listOfMessageDto =messageDao.getAll();
        var res = new RestApiAppResponse<>(true, listOfMessageDto, null);
        return new ResponseBuilder().setStatus("200 OK").setBody(GsonTool.gson.toJson(res)).build();
    }

}
