package org.example.service.rules;

import org.example.model.Restaurant;
import org.example.model.User;
import org.example.service.UserService;

public class RecommendationRule9 implements RecommendationRule {

    @Override
    public Restaurant[] applyRecommendationRule(User user, Restaurant[] availableRestaurants) {
        return availableRestaurants;
    }
}
