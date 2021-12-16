package com.cgi.searchservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;


@RestController
@RequestMapping("/api/v1/")
public class RestaurantController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("restaurants")
    public ResponseEntity<?> getRestaurants(@RequestParam(value = "start") int start){
        System.out.println("Get restaurants");
        final String url="https://developers.zomato.com/api/v2.1/search?start="+start+"&count=18";
        HttpHeaders headers = new HttpHeaders();
        headers.set("user-key" , "39f6dbeccbb0bd94593baf9a4d295c66");
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return  restTemplate.exchange(url, HttpMethod.GET, entity,Object.class);
    }

    @GetMapping("restaurantsbycity")
    public ResponseEntity<?> getRestaurantByCity(@RequestParam(value = "city") int city, @RequestParam(value = "start") int start){
        final String url = "https://developers.zomato.com/api/v2.1/search?entity_id="+city+"&entity_type=city&start="+start+"&count=18";
        HttpHeaders headers = new HttpHeaders();
        headers.set("user-key" , "39f6dbeccbb0bd94593baf9a4d295c66");
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return  restTemplate.exchange(url, HttpMethod.GET, entity,Object.class);
    }

    @GetMapping("restaurantsbykey")
    public ResponseEntity<?> getRestaurantByCityAndKey(@RequestParam(value = "city") int city,@RequestParam(value = "q") String q , @RequestParam(value = "start") int start){
        final String url = "https://developers.zomato.com/api/v2.1/search?entity_id="+city+"&entity_type=city&q="+q+"&start="+start+"&count=18";
        HttpHeaders headers = new HttpHeaders();
        headers.set("user-key" , "39f6dbeccbb0bd94593baf9a4d295c66");
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return  restTemplate.exchange(url, HttpMethod.GET, entity,Object.class);
    }



}
