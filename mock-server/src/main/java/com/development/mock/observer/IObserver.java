package com.development.mock.observer;

import com.development.mock.model.MockContext;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName IObserver
 * @Description
 * @date 2022/1/8 15:42
 */
public interface IObserver<Context> {
    void update(Context context);


}
