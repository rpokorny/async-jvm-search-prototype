package org.ozoneplatform.dto;

public interface InDto<T> extends Dto<T> {
    T fromDto();
}
