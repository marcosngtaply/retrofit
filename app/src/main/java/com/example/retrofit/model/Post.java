package com.example.retrofit.model;

import lombok.Builder;
import lombok.Data;

/**
//PASSO 4 - criar classe de modelo de acordo com recurso utilizado;
 **/
@Data
@Builder
public class Post {
    private Integer userId;
    private Integer id;
    private String title;
    private String body;
}
