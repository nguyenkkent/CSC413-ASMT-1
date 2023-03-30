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
            if ((dto.getFromId().equals(fromId))&&(dto.getToId().equals(toId))){
                filteredListofMessageDtos.add(dto);
            }
        }
        var res = new RestApiAppResponse<>(true, filteredListofMessageDtos, null);

        return new ResponseBuilder().setStatus("200 OK").setBody(GsonTool.gson.toJson(res)).build();
    }

//    @Override
//    public CustomHttpResponse handleRequest(ParsedRequest request) {
//
//        String fromId = request.getQueryParam("fromId");
//        String toId = request.getQueryParam("toId");
//
//        MessageDao messageDao = MessageDao.getInstance();
//        List<MessageDto> listOfAllMessageDtos = messageDao.getAll();
//        List<MessageDto> filteredListofMessageDtos = new ArrayList<>();
//        System.out.println("Here are the DTO data of size: " + listOfAllMessageDtos.size());
//        for (MessageDto dto : listOfAllMessageDtos){
//            System.out.println("Current iteration: ");
//            System.out.println("fromId: " + dto.getFromId() +"\n" + "toId: " + dto.getToId());
//            System.out.println("\n");
//            if ((dto.getFromId().equals(fromId))&&(dto.getToId().equals(toId))){
//                System.out.println("found a matching " + "\n" + "fromId: " + dto.getFromId() + "\n" + "toID: " + dto.getToId());
//                filteredListofMessageDtos.add(dto);
//            }
//        }
//        System.out.println("size of filteredListofMessageDtos: " + filteredListofMessageDtos.size());
//
//        var res = new RestApiAppResponse<>(true, filteredListofMessageDtos, null);
//        System.out.println(GsonTool.gson.toJson(res));
//        return new ResponseBuilder().setStatus("200 OK").setBody(GsonTool.gson.toJson(res)).build();
//    }

}
