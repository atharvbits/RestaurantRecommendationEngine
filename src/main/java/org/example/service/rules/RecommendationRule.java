package org.example.service.rules;

import org.example.model.Restaurant;
import org.example.model.User;

public interface RecommendationRule {
    Restaurant[] applyRecommendationRule(User user, Restaurant[] availableRestaurants);
}
