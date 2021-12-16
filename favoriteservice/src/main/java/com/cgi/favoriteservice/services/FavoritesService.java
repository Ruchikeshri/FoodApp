package com.cgi.favoriteservice.services;

import com.cgi.favoriteservice.exceptions.RestaurantAlreadyExistsException;
import com.cgi.favoriteservice.exceptions.UserNotFoundException;
import com.cgi.favoriteservice.model.Favorites;
import com.cgi.favoriteservice.model.Restaurants;



public interface FavoritesService {

    Favorites getByEmail(String email) throws UserNotFoundException;
    Favorites addToFavorites(Favorites favorites) throws RestaurantAlreadyExistsException;
    Restaurants deleteFromFavorites(String email , int id) throws UserNotFoundException;
}
