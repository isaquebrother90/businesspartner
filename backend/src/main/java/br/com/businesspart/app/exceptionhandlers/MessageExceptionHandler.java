package br.com.businesspart.app.exceptionhandlers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class MessageExceptionHandler {

    private Date timestamp;
    private Integer status;
    private String message;

}
