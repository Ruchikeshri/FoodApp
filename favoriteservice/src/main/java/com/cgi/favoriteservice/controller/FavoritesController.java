package com.cgi.favoriteservice.controller;

import com.cgi.favoriteservice.exceptions.RestaurantAlreadyExistsException;
import com.cgi.favoriteservice.exceptions.UserNotFoundException;
import com.cgi.favoriteservice.model.Favorites;
import com.cgi.favoriteservice.model.Restaurants;
import com.cgi.favoriteservice.services.FavoritesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(value = "*")
@RequestMapping("/api/v1/")
public class FavoritesController {
    private static Logger logger = LoggerFactory.getLogger(Favorites.class);


    private FavoritesService favoritesService;

    @Autowired
    public FavoritesController(FavoritesService favoritesService) {
        this.favoritesService = favoritesService;
    }

    @GetMapping("favorites")
    public ResponseEntity<Favorites> getFavorites(@RequestParam(value = "email") String email) throws UserNotFoundException {
        logger.info("....Fetching Favorites List");
        return new ResponseEntity<>(favoritesService.getByEmail(email) , HttpStatus.OK);
    }


    @PostMapping("favoriteadd")
    public ResponseEntity<Favorites> addFavorites(@RequestBody Favorites favorites) throws RestaurantAlreadyExistsException {
        logger.info("...Adding Favorite to Database");
        return new ResponseEntity<>(favoritesService.addToFavorites(favorites) , HttpStatus.OK);
    }


    @DeleteMapping("favoritedelete")
    public ResponseEntity<Restaurants> deleteFavorites(@RequestParam(value = "email") String email , @RequestParam(value = "id") int id) throws UserNotFoundException {
        logger.info("...Deleting Favorite from Database");
        return new ResponseEntity<>(favoritesService.deleteFromFavorites(email, id) , HttpStatus.OK);
    }
}
