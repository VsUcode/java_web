package com.vsu.async.handler;

import com.vsu.async.EventHandler;
import com.vsu.async.EventModel;
import com.vsu.async.EventType;
import com.vsu.model.Message;
import com.vsu.model.User;
import com.vsu.service.MessageService;
import com.vsu.service.UserService;
import com.vsu.util.NKWUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by vsu on 2018/03/01.
 */
@Component
public class LikeHandler implements EventHandler{

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Override
    public void doHandle(EventModel eventModel) {

        Message message = new Message();
        message.setFromId(NKWUtils.SYSTEM_USERID);
        message.setToId(eventModel.getEntityOwnerId());
        message.setCreatedDate(new Date());
        User user = userService.getUser(eventModel.getActorId());
        message.setContent("用户" + user.getName()
                + "赞了你的评论,http://127.0.0.1:8080/question/" + eventModel.getExt("questionId"));

        messageService.addMessage(message);
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.LIKE);
    }
}
