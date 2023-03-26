package dao;

import dto.MessageDto;
import dto.UserDto;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

// TODO fill this out
public class UserDao implements BaseDao<UserDto> {

    private static UserDao instance = new UserDao();
    private HashMap<String,UserDto> userDtoMap = new HashMap<>();

    public static UserDao getInstance() {
        return instance;
    }

    // TODO fill this out
    @Override
    public void put(UserDto userDto) {
        this.userDtoMap.put(userDto.getUniqueId(), userDto);
    }

    // TODO fill this out
    @Override
    public UserDto get(String uniqueId) {
        return this.userDtoMap.get(uniqueId);
    }

    // TODO fill this out
    @Override
    public List<UserDto> getAll() {
        return this.userDtoMap.values().stream().collect(Collectors.toList());
//        return new ArrayList<>(this.userDtoMap.values());

    }

    // only for testing, do not call this method
    public static void reset(){
        instance = new UserDao();
    }
}
