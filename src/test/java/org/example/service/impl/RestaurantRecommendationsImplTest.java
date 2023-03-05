package org.example.service.impl;

import org.example.enums.Cuisine;
import org.example.model.CostTracking;
import org.example.model.CuisineTracking;
import org.example.model.Restaurant;
import org.example.model.User;
import org.example.service.RestaurantRecommendations;
import org.example.service.UserService;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantRecommendationsImplTest {

    RestaurantRecommendationsImpl restaurantRecommendations;

    @Mock
    UserService userService ;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        restaurantRecommendations = new RestaurantRecommendationsImpl(userService);
    }

    @org.junit.jupiter.api.Test
    void getRestaurantRecommendations() {
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
        List<String> expectedOutput = Arrays.asList("3","2","1");
        Mockito.when(userService.getPrimaryCostBracket(user)).thenReturn(2);
        Mockito.when(userService.getSecondaryCostBracket(user)).thenReturn(new int[]{3,1});
        Mockito.when(userService.getPrimaryCuisine(user)).thenReturn(Cuisine.CHINESE);
        Mockito.when(userService.getSecondaryCuisine(user)).thenReturn(new Cuisine[]{Cuisine.SOUTH_INDIAN,Cuisine.NORTH_INDIAN});
        List<String> output = Arrays.stream(restaurantRecommendations.getRestaurantRecommendations(user,restaurants)).collect(Collectors.toList());
        assertTrue(output.size()==expectedOutput.size() && expectedOutput.containsAll(output) && output.containsAll(expectedOutput));
    }
}