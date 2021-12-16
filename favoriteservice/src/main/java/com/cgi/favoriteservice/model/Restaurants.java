package com.cgi.favoriteservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Restaurants {
    private  int id;
    private String name;
    private String url;
    private String address;
    private String cuisines;
    private String timings;
    private String featured_image;
    private String menu_url;
    private String phone_numbers;

}
