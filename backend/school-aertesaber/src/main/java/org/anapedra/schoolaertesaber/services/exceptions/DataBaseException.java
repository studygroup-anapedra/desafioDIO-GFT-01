package org.anapedra.schoolaertesaber.services.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

import java.io.Serial;

public class DataBaseException extends DataIntegrityViolationException {
    @Serial
    private static final long serialVersionUID=1L;
    public DataBaseException(String msg){
        super(msg);
    }
}