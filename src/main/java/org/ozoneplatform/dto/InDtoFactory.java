package org.ozoneplatform.dto;

public interface InDtoFactory<T, D extends InDto<T>> extends DtoFactory<T, D> {
    Class<D> getDtoType();
}
