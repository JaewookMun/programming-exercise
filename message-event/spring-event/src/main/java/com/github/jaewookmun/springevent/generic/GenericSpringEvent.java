package com.github.jaewookmun.springevent.generic;

import lombok.Getter;

/**
 * NOTICE: comparison between CustomSpringEvent and GenericSpringEvent
 */
@Getter
public class GenericSpringEvent<T> {
    private T what;
    protected boolean success;

    public GenericSpringEvent(T what, boolean success) {
        this.what = what;
        this.success = success;
    }
}
