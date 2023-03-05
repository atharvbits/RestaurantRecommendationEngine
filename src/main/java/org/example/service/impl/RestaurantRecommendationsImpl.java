package org.example.service.impl;

import org.example.model.Restaurant;
import org.example.model.User;
import org.example.service.RestaurantRecommendations;
import org.example.service.UserService;
import org.example.service.rules.RecommendationRule;
import org.example.service.rules.RecommendationRule1;
import org.example.service.rules.RecommendationRule2;
import org.example.service.rules.RecommendationRule3;
import org.example.service.rules.RecommendationRule4;
import org.example.service.rules.RecommendationRule5;
import org.example.service.rules.RecommendationRule6;
import org.example.service.rules.RecommendationRule7;
import org.example.service.rules.RecommendationRule8;
import org.example.service.rules.RecommendationRule9;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RestaurantRecommendationsImpl implements RestaurantRecommendations {

    List<RecommendationRule> recommendationRules = new ArrayList<>();

    public RestaurantRecommendationsImpl(UserService userService) {
        recommendationRules.add(new RecommendationRule1(userService));
        recommendationRules.add(new RecommendationRule2(userService));
        recommendationRules.add(new RecommendationRule3(userService));
        recommendationRules.add(new RecommendationRule4(userService));
        recommendationRules.add(new RecommendationRule5());
        recommendationRules.add(new RecommendationRule6(userService));
        recommendationRules.add(new RecommendationRule7(userService));
        recommendationRules.add(new RecommendationRule8(userService));
        recommendationRules.add(new RecommendationRule9());
    }

    @Override
    public String[] getRestaurantRecommendations(User user, Restaurant[] availableRestaurants) {
        List<Restaurant> restaurants = new ArrayList<>();
        Set<String> restaurantIds = new HashSet<>();
        for(RecommendationRule recommendationRule : recommendationRules){
            List<Restaurant> restaurantList = List.of(recommendationRule.applyRecommendationRule(user, availableRestaurants));
            for(Restaurant restaurant : restaurantList){
                if(!restaurantIds.contains(restaurant.getRestaurantId())){
                    restaurantIds.add(restaurant.getRestaurantId());
                    restaurants.add(restaurant);
                }
                if(restaurantIds.size()==100){
                    break;
                }
            }
        }
        return restaurants.stream().map(Restaurant::getRestaurantId).toArray(String[]::new);
    }
}
