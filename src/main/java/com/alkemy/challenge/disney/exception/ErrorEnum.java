package com.alkemy.challenge.disney.exception;

public enum ErrorEnum {
    IDFILMNOTVALID, IDACTORNOTVALID, IDGENRENOTVALID;

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
        return ("Error indefinido");
    }
}
