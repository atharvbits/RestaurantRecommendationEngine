package org.example.service.rules;

import org.example.enums.Cuisine;
import org.example.model.Restaurant;
import org.example.model.User;
import org.example.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecommendationRule8 implements RecommendationRule {

    private final UserService userService;

    public RecommendationRule8(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Restaurant[] applyRecommendationRule(User user, Restaurant[] availableRestaurants) {
        List<Restaurant> restaurants = new ArrayList<>();
        List<Cuisine> secondaryCuisine = Arrays.asList(userService.getSecondaryCuisine(user));
        int primaryCostBracket = userService.getPrimaryCostBracket(user);
        for(Restaurant restaurant : availableRestaurants){
            if(secondaryCuisine.contains(restaurant.getCuisine()) && restaurant.getCostBracket()==primaryCostBracket && restaurant.getRating()<4.5){
                restaurants.add(restaurant);
            }
        }
        return restaurants.toArray(new Restaurant[0]);
    }
}
