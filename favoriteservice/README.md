Favorite Service used to add restaurants to the list of the favorites with POST functionality,
get all restaurants of a particular user with GET functionality, delete a restaurant from the list 
of restaurants. 

Favorite Service is Rest API which contains of following end points -

1. Get By Email - 
            Input - Email of type string 
            Output - An Favorite Object consisting of email and list of the favorite restaurants.
            
2. Add Restaurant to Favorites - 
            Input -  An Favorite Object with one restaurant which is to be added to favorites list.
            Output - An Favorite Object consisting of email and list of the favorite restaurants 
                     with the favorite restaurant.
                   
3. Delete a Restaurant from favorites list - 
            Input - Email and restaurant id which need to be deleted from the list of favorites.
            Output - An Favorite Object consisting of list of restaurants without the deleted restaurant. 