package com.cgi.favoriteservice.controller;

import com.cgi.favoriteservice.exceptions.RestaurantAlreadyExistsException;
import com.cgi.favoriteservice.model.Favorites;
import com.cgi.favoriteservice.model.Restaurants;
import com.cgi.favoriteservice.services.FavoritesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class FavoritesControllerTest {

    private MockMvc mockMvc;
    @Mock
    private FavoritesService favoritesService;
    @InjectMocks
    private FavoritesController favoritesController;

    private Restaurants restaurant1;
    private List<Restaurants> restaurantsList;
    private Favorites favorites;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(favoritesController).build();
        restaurant1 = new Restaurants(1,"Spice" , "url" ,"Delhi" , "Pizza", "10to10" , "image" , "menu" ,"1234");
        restaurantsList = new ArrayList<>();
    }

    @Test
    public void givenRestaurantThenShouldAddToRestaurantList() throws Exception {
        restaurantsList.add(restaurant1);
        favorites = new Favorites("abc@123" , restaurantsList);
        when(favoritesService.addToFavorites(any())).thenReturn(favorites);
        mockMvc.perform(post("/api/v1/favoriteadd")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(favorites)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(favoritesService , times(1)).addToFavorites(any());
    }

    @Test
    public void givenEmailThenShouldReturnRestaurantList() throws Exception {
        when(favoritesService.getByEmail(anyString())).thenReturn(favorites);
        restaurantsList.add(restaurant1);
        favorites = new Favorites("abc@123" , restaurantsList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/favorites?email=abc@123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(restaurant1)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(favoritesService , times(1)).getByEmail(any());
    }

    @Test
    public void givenRestaurantIdThenShouldDeleteRestaurantFromList() throws Exception {
        when(favoritesService.deleteFromFavorites(anyString(),anyInt())).thenReturn(restaurant1);
        mockMvc.perform(delete("/api/v1/favoritedelete?email=abc@123&id=1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(restaurant1)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(favoritesService , times(1)).deleteFromFavorites(anyString(),anyInt());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}