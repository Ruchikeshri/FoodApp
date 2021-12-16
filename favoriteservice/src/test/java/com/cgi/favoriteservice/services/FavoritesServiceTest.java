package com.cgi.favoriteservice.services;

import com.cgi.favoriteservice.exceptions.RestaurantAlreadyExistsException;
import com.cgi.favoriteservice.exceptions.UserNotFoundException;
import com.cgi.favoriteservice.model.Favorites;
import com.cgi.favoriteservice.model.Restaurants;
import com.cgi.favoriteservice.repository.FavoritesRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class FavoritesServiceTest {

    @Mock
    private FavoritesRepo favoritesRepo;
    @InjectMocks
    private FavoritesServiceImp favoritesService;

    private Restaurants restaurant1,restaurant2;
    private List<Restaurants> restaurantsList;
    private Favorites favorites;

    @BeforeEach
    public void setUp(){
        restaurant1 = new Restaurants(1,"Spice" , "url" ,"Delhi" , "Pizza", "10to10" , "image" , "menu" ,"1234");
        restaurant2 = new Restaurants(2,"Chillies" , "url" ,"Mumbai" , "Tea", "10to10" , "image" , "menu" ,"12345");
        restaurantsList = new ArrayList<>();
    }

    @Test
    public void givenRestaurantToSaveThenShouldSaveRestaurant() throws RestaurantAlreadyExistsException {
        restaurantsList.add(restaurant1);
        favorites=new Favorites("abc@123" , restaurantsList);
        restaurantsList=new ArrayList<>();
        restaurantsList.add(restaurant2);
        Favorites favorite2 = new Favorites("abc@123" , restaurantsList);
        when(favoritesRepo.save(any())).thenReturn(favorites);
        when(favoritesRepo.findByEmail(any())).thenReturn(favorite2);
        when(favoritesRepo.findById(anyString())).thenReturn(java.util.Optional.of(favorite2));
        favoritesService.addToFavorites(favorites);
        verify(favoritesRepo,times(1)).save(any());
    }

    @Test
    public void givenRestaurantToSaveThenShouldNotSaveRestaurant() throws RestaurantAlreadyExistsException{
        restaurantsList.add(restaurant1);
        favorites=new Favorites("abc@123" , restaurantsList);
        when(favoritesRepo.findByEmail(any())).thenReturn(favorites);
        when(favoritesRepo.findById(anyString())).thenReturn(java.util.Optional.of(favorites));
        assertThrows(RuntimeException.class , ()->{
            favoritesService.addToFavorites(favorites);
        });
    }

    @Test
    public void givenEmailToGetAllRestaurants() throws UserNotFoundException {
        restaurantsList.add(restaurant1);
        favorites=new Favorites("abc@123" , restaurantsList);
        when(favoritesRepo.findByEmail(any())).thenReturn(favorites);
        favoritesService.getByEmail("abc@123");
        verify(favoritesRepo , times(1)).findByEmail("abc@123");
    }

}