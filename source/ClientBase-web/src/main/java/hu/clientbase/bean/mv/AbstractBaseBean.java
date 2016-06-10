package hu.clientbase.bean.mv;

import javax.annotation.PostConstruct;

public abstract class AbstractBaseBean<T> {

    @PostConstruct
    void init() {
        update();
    }

    public abstract void update();

}
