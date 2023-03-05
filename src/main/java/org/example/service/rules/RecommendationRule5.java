package org.example.service.rules;

import org.example.model.Restaurant;
import org.example.model.User;
import org.example.service.UserService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class RecommendationRule5 implements RecommendationRule {

    @Override
    public Restaurant[] applyRecommendationRule(User user, Restaurant[] availableRestaurants) {
        int count = 0;
        List<Restaurant> restaurants = new ArrayList<>();
        Date date48hoursBefore = Date.from(LocalDateTime.now().minusHours(48).atZone(ZoneId.systemDefault()).toInstant());
        for(Restaurant restaurant : availableRestaurants){
            if(restaurant.getOnboardedTime().after(date48hoursBefore)){
                restaurants.add(restaurant);
            }
            count++;
            if(count==4){
                break;
            }
        }
        restaurants.sort(Comparator.comparing(Restaurant::getRating).reversed());
        return restaurants.toArray(new Restaurant[0]);
    }
}
