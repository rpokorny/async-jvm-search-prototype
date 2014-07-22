package org.ozoneplatform.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface InDto<T> {
    //In case the same class is an input and ouput DTO
    @JsonIgnore
    T fromDto();
}
