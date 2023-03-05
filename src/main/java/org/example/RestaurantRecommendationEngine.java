package org.example;

import org.example.enums.Cuisine;
import org.example.model.CostTracking;
import org.example.model.CuisineTracking;
import org.example.model.Restaurant;
import org.example.model.User;
import org.example.service.RestaurantRecommendations;
import org.example.service.UserService;
import org.example.service.impl.RestaurantRecommendationsImpl;
import org.example.service.impl.UserServiceImpl;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;


public class RestaurantRecommendationEngine {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        RestaurantRecommendations restaurantRecommendations = new RestaurantRecommendationsImpl(userService);
        Restaurant restaurant1 = new Restaurant("1", Cuisine.NORTH_INDIAN,1, 4.5f,true, Date.from(Instant.now()));
        CuisineTracking cuisineTracking1 = new CuisineTracking(Cuisine.NORTH_INDIAN,2);
        CostTracking costTracking1 = new CostTracking(1,2);
        Restaurant restaurant2 = new Restaurant("2", Cuisine.SOUTH_INDIAN,2, 4.5f,true, Date.from(Instant.now()));
        CuisineTracking cuisineTracking2 = new CuisineTracking(Cuisine.CHINESE,200);
        CostTracking costTracking2 = new CostTracking(2,200);
        Restaurant restaurant3 = new Restaurant("3", Cuisine.CHINESE,3, 4.5f,true, Date.from(Instant.now()));
        CuisineTracking cuisineTracking3 = new CuisineTracking(Cuisine.SOUTH_INDIAN,20);
        CostTracking costTracking3 = new CostTracking(3,20);
        CuisineTracking[] cuisines = {cuisineTracking1,cuisineTracking2,cuisineTracking3};
        CostTracking[] costBracket = {costTracking1,costTracking2,costTracking3};
        User user = new User(cuisines,costBracket);
        Restaurant[] restaurants = {restaurant1,restaurant2,restaurant3};
        String[] ids = restaurantRecommendations.getRestaurantRecommendations(user,restaurants);
        System.out.println(Arrays.toString(ids));
    }
}