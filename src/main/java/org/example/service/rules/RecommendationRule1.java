package org.example.service.rules;

import org.example.enums.Cuisine;
import org.example.model.Restaurant;
import org.example.model.User;
import org.example.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RecommendationRule1 implements RecommendationRule {

    private final UserService userService;

    public RecommendationRule1(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Restaurant[] applyRecommendationRule(User user, Restaurant[] availableRestaurants) {
        Cuisine primaryCuisine = userService.getPrimaryCuisine(user);
        int primaryCostBracket = userService.getPrimaryCostBracket(user);
        List<Restaurant> restaurants = new ArrayList<>(getFeaturedPrimaryCuisineAndPrimaryCostRestaurants(availableRestaurants, primaryCuisine, primaryCostBracket));
        if(restaurants.isEmpty()){
            Cuisine[] secondaryCuisine = userService.getSecondaryCuisine(user);
            int[] secondaryCostBracket = userService.getSecondaryCostBracket(user);
            restaurants.addAll(getFeaturedPrimaryCuisineAndSecondaryCostRestaurants(availableRestaurants,primaryCuisine,secondaryCostBracket));
            restaurants.addAll(getFeaturedSecondaryCuisineAndPrimaryCostRestaurants(availableRestaurants,secondaryCuisine,primaryCostBracket));
        }
        return restaurants.toArray(new Restaurant[0]);
    }

    private List<Restaurant> getFeaturedPrimaryCuisineAndPrimaryCostRestaurants(Restaurant[] availableRestaurants, Cuisine primaryCuisine, int primaryCostBracket){
        List<Restaurant> restaurants = new ArrayList<>();
        for(Restaurant restaurant : availableRestaurants){
            if(restaurant.isRecommended() && restaurant.getCuisine().equals(primaryCuisine) && restaurant.getCostBracket()==primaryCostBracket){
                restaurants.add(restaurant);
            }
        }
        return restaurants;
    }

    private List<Restaurant> getFeaturedPrimaryCuisineAndSecondaryCostRestaurants( Restaurant[] availableRestaurants, Cuisine primaryCuisine, int[] secondaryCostBracket){
        List<Restaurant> restaurants = new ArrayList<>();
        List<Integer> secondaryCostBracketList = Arrays.stream(secondaryCostBracket).boxed().collect(Collectors.toList());
        for(Restaurant restaurant : availableRestaurants){
            if(restaurant.isRecommended() && restaurant.getCuisine().equals(primaryCuisine) && secondaryCostBracketList.contains(restaurant.getCostBracket())){
                restaurants.add(restaurant);
            }
        }
        return restaurants;
    }

    private List<Restaurant> getFeaturedSecondaryCuisineAndPrimaryCostRestaurants( Restaurant[] availableRestaurants, Cuisine[] secondaryCuisine, int primaryCostBracket){
        List<Restaurant> restaurants = new ArrayList<>();
        for(Restaurant restaurant : availableRestaurants){
            if(restaurant.isRecommended() && Arrays.asList(secondaryCuisine).contains(restaurant.getCuisine()) && restaurant.getCostBracket()==primaryCostBracket){
                restaurants.add(restaurant);
            }
        }
        return restaurants;
    }
}
