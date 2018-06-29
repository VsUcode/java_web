package com.vsu.async;

import java.util.List;

/**
 * Created by vsu on 2018/03/01.
 */
public interface EventHandler {
    void doHandle(EventModel eventModel);

    List<EventType> getSupportEventTypes();
}
