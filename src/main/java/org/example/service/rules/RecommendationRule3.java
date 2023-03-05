package org.example.service.rules;

import org.example.enums.Cuisine;
import org.example.model.Restaurant;
import org.example.model.User;
import org.example.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RecommendationRule3 implements RecommendationRule {
    private final UserService userService;

    public RecommendationRule3(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Restaurant[] applyRecommendationRule(User user, Restaurant[] availableRestaurants) {
        List<Restaurant> restaurants = new ArrayList<>();
        Cuisine primaryCuisine = userService.getPrimaryCuisine(user);
        List<Integer> secondaryCostBracket = Arrays.stream(userService.getSecondaryCostBracket(user)).boxed().collect(Collectors.toList());
        for(Restaurant restaurant : availableRestaurants){
            if(restaurant.getCuisine().equals(primaryCuisine) && secondaryCostBracket.contains(restaurant.getCostBracket()) && restaurant.getRating()>=4.5){
                restaurants.add(restaurant);
            }
        }
        return restaurants.toArray(new Restaurant[0]);
    }
}
