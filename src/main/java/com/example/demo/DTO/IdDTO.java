package com.example.demo.DTO;

public class IdDTO extends DTO {
    String id;

    public IdDTO(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
