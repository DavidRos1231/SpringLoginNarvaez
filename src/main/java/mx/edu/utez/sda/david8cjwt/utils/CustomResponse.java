package mx.edu.utez.sda.david8cjwt.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CustomResponse<T> {
    private T data;
    private boolean error;
    private int code;

    private String message;
}
