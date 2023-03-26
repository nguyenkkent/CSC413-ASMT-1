package handler;

import request.ParsedRequest;

public class HandlerFactory {

    // routes based on the path. Add your custom handlers here
    public static BaseHandler getHandler(ParsedRequest request) {
        String endPoint = request.getPath();
        switch(endPoint){
            case "/createUser":
                return new CreateUserHandler();
            case "/createMessage":
                return new CreateMessageHandler();
            case "/getMessages":
                return new GetMessagesHandler();
            case  "/getUsers":
                return new GetUsersHandler();
        }
        return null;
    }

}
