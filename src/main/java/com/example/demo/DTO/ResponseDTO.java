package com.example.demo.DTO;

public class ResponseDTO extends DTO{
    boolean ok=true;
    DTO data;

    public ResponseDTO(DTO message) {
        data=message;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public DTO getData() {
        return data;
    }

    public void setData(DTO data) {
        this.data = data;
    }
}