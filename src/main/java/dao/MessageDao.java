package dao;

import dto.MessageDto;
import dto.UserDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// TODO fill this out
public class MessageDao implements BaseDao<MessageDto> {

    private static MessageDao instance = new MessageDao();
    private HashMap<String, MessageDto> messageDtoMap = new HashMap<>();

    public static MessageDao getInstance() {
        return instance;
    }
    private MessageDao(){
    };

    // TODO fill this out
    @Override
    public void put(MessageDto messageDto) {
        messageDtoMap.put(messageDto.getUniqueId(),messageDto);
    }

    // TODO fill this out
    @Override
    public MessageDto get(String uniqueId) {
        return messageDtoMap.get(uniqueId);
    }

    // TODO fill this out
    @Override
    public List<MessageDto> getAll() {
        return messageDtoMap.values().stream().collect(Collectors.toList());
    }

    // only for testing, do not call this method
    public static void reset(){
        instance = new MessageDao();
    }
}
