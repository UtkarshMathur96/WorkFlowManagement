package com.example.demo;

import com.example.demo.DTO.DTO;
import com.example.demo.DTO.ResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil {
    static ObjectMapper objectMapper=new ObjectMapper();

   public   static String OkResponse(DTO message){
        String response = "";
        try {
            response  = objectMapper.writeValueAsString(new ResponseDTO(message));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return response;
    }


}
