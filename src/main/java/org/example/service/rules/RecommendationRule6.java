package org.example.service.rules;

import org.example.enums.Cuisine;
import org.example.model.Restaurant;
import org.example.model.User;
import org.example.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class RecommendationRule6 implements RecommendationRule {

    private final UserService userService;

    public RecommendationRule6(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Restaurant[] applyRecommendationRule(User user, Restaurant[] availableRestaurants) {
        List<Restaurant> restaurants = new ArrayList<>();
        Cuisine primaryCuisine = userService.getPrimaryCuisine(user);
        int primaryCostBracket = userService.getPrimaryCostBracket(user);
        for(Restaurant restaurant : availableRestaurants){
            if(restaurant.getCuisine().equals(primaryCuisine) && restaurant.getCostBracket()==primaryCostBracket && restaurant.getRating()<4){
                restaurants.add(restaurant);
            }
        }
        return restaurants.toArray(new Restaurant[0]);
    }
}
