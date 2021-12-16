package com.cgi.favoriteservice.repository;

import com.cgi.favoriteservice.model.Favorites;
import com.cgi.favoriteservice.model.Restaurants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FavoritesRepoTest {


    private FavoritesRepo favoritesRepo;

    private Favorites favorites;
    private List<Restaurants> restaurantsList;
    private Restaurants restaurants;
    @Autowired
    public FavoritesRepoTest(FavoritesRepo favoritesRepo) {
        this.favoritesRepo = favoritesRepo;
    }

    @BeforeEach
    public void setUp(){
        restaurants = new Restaurants(1,"Spice" , "url" ,"Delhi" , "Pizza", "10to10" , "image" , "menu" ,"1234");
        restaurantsList = new ArrayList<>();
        restaurantsList.add(restaurants);
        favorites = new Favorites("abcd@123" , restaurantsList);

    }

    @Test
    public void givenRestaurantThenShouldReturnSavedRestaurant(){
        favoritesRepo.save(favorites);
        Restaurants restaurant = favoritesRepo.findByEmail("abcd@123").getRestaurants().get(0);
        assertNotNull(restaurants);
        assertEquals(restaurants.getAddress() ,restaurant.getAddress());
    }

    @Test
    public void givenGetAllRestaurantThenShouldReturnRestaurantList(){
        Restaurants restaurants1 = new Restaurants(2,"Chillies" , "url" ,"Mumbai" , "Tea", "10to10" , "image" , "menu" ,"12345");
        restaurantsList.add(restaurants1);
        favorites.setRestaurants(restaurantsList);
        favoritesRepo.save(favorites);
        Favorites favorites1 = favoritesRepo.findByEmail("abcd@123");
        assertNotNull(favorites1);
        assertEquals(2,favorites1.getRestaurants().size());
    }


}