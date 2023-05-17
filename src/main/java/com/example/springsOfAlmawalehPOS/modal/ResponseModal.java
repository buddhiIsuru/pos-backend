package com.example.springsOfAlmawalehPOS.modal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
public class ResponseModal<T> {
    private int status;
    private T data;
}
