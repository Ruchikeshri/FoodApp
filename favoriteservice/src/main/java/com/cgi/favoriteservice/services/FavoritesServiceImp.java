package com.cgi.favoriteservice.services;

import com.cgi.favoriteservice.exceptions.RestaurantAlreadyExistsException;
import com.cgi.favoriteservice.exceptions.UserNotFoundException;
import com.cgi.favoriteservice.model.Favorites;
import com.cgi.favoriteservice.model.Restaurants;
import com.cgi.favoriteservice.repository.FavoritesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoritesServiceImp implements FavoritesService{


    private FavoritesRepo favoritesRepo;
    @Autowired
    public FavoritesServiceImp(FavoritesRepo favoritesRepo) {
        this.favoritesRepo = favoritesRepo;
    }


    @Override
    public Favorites getByEmail(String email) throws UserNotFoundException {
        Favorites favorites=favoritesRepo.findByEmail(email);
        if(favorites!=null){
            return favorites;
        }
        else {
            throw new UserNotFoundException();
        }
    }



    @Override
    public Favorites addToFavorites(Favorites favorites) throws RestaurantAlreadyExistsException {
        Favorites favorites1=favoritesRepo.findByEmail(favorites.getEmail());
        Optional favorites2=favoritesRepo.findById(favorites.getEmail());
            if(favorites2.isPresent()){
            boolean isPresent = false;
            List<Restaurants> restaurants1 = favorites1.getRestaurants();
            for (int i = 0; i < restaurants1.size(); i++) {
                if (restaurants1.get(i).getId() == favorites.getRestaurants().get(0).getId())
                    isPresent = true;
            }
            if (isPresent) {
                throw new RestaurantAlreadyExistsException();
            }
            favorites1.getRestaurants().add(favorites.getRestaurants().get(0));
            return favoritesRepo.save(favorites1);
        }
        else{
            return favoritesRepo.save(favorites);
        }

    }

    @Override
    public Restaurants deleteFromFavorites(String email, int id) throws UserNotFoundException {
        Favorites favorites=getByEmail(email);
        int index=0;
        boolean found =false;
        List<Restaurants> restaurants=favorites.getRestaurants();
        for (int i = 0; i < restaurants.size(); i++) {
            if(restaurants.get(i).getId()==id){
                index=i;
                found=true;
                break;
            }
        }
        if(found) {
            Restaurants restaurants1 = restaurants.get(index);
            restaurants.remove(index);
            favorites.setRestaurants(restaurants);
            favoritesRepo.save(favorites);
            return restaurants1;
        }
        else{
            throw new UserNotFoundException();
        }
    }
}
