package dao;

import dto.MessageDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO fill this out
public class MessageDao implements BaseDao<MessageDto> {

  private static MessageDao instance = new MessageDao();

  public static MessageDao getInstance() {
    return null;
  }

  // TODO fill this out
  @Override
  public void put(MessageDto messageDto) {
  }

  // TODO fill this out
  @Override
  public MessageDto get(String uniqueId) {
    return null;
  }

  // TODO fill this out
  @Override
  public List<MessageDto> getAll() {
    return null;
  }

  // only for testing, do not call this method
  public static void reset(){
    instance = new MessageDao();
  }
}
