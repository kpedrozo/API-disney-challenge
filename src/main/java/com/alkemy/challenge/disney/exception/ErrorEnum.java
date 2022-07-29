package com.alkemy.challenge.disney.exception;

public enum ErrorEnum {
    IDFILMNOTVALID, IDACTORNOTVALID, IDGENRENOTVALID, USEREXISTS, USERINVALID, TOKENNOTFOUND;

    public String getMessage() {
        if (ErrorEnum.this == IDFILMNOTVALID) {
            return ("ID Film no valido");
        }
        if (ErrorEnum.this == IDACTORNOTVALID) {
            return ("ID Actor no valido");
        }
        if (ErrorEnum.this == IDGENRENOTVALID) {
            return ("ID Genero no valido");
        }
        if (ErrorEnum.this == USEREXISTS) {
            return ("User already exists");
        }
        if (ErrorEnum.this == USERINVALID) {
            return ("Username or password not found");
        }
        if (ErrorEnum.this == TOKENNOTFOUND) {
            return ("Token not found");
        }
        return ("Error indefinido");
    }
}
