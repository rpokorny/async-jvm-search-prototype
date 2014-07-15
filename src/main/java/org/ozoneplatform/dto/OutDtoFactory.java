package org.ozoneplatform.dto;

public interface OutDtoFactory<T extends HasOutDto, D extends OutDto<T>>
        extends DtoFactory<T, D> {
    D toDto(T obj);
}
