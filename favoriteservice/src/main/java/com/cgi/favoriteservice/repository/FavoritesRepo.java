package com.cgi.favoriteservice.repository;

import com.cgi.favoriteservice.model.Favorites;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FavoritesRepo extends MongoRepository<Favorites , String> {

    Favorites findByEmail(String email);

}
