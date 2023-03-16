package dao;

import dto.MessageDto;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DaoTests {

  @Test(singleThreaded = true)
  public void testDao(){
    MessageDao.reset();
    MessageDao messageDao = MessageDao.getInstance();
    Assert.assertEquals(messageDao, MessageDao.getInstance());

    // test that constructor is private
    List<Constructor> constructors = Arrays.asList(MessageDao.class.getConstructors());
    Assert.assertEquals(constructors.size(), 0);
    constructors = Arrays.asList(MessageDao.class.getDeclaredConstructors());
    Assert.assertEquals(constructors.size(), 1);
    Assert.assertTrue(Modifier.isPrivate(constructors.get(0).getModifiers()));
  }

  @Test(singleThreaded = true)
  public void testStore(){
    MessageDao.reset();
    MessageDao messageDao = MessageDao.getInstance();
    String id = String.valueOf(Math.random());
    MessageDto messageDto = new MessageDto(id);
    messageDao.put(messageDto);
    // id's that do not exist should be empty
    Assert.assertNull(messageDao.get(String.valueOf(Math.random())));
    // ids that do exist should return the object
    Assert.assertEquals(messageDao.get(id), messageDto);
    Assert.assertNull(messageDao.get(String.valueOf(Math.random())));
  }
}
