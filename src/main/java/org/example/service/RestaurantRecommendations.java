package org.example.service;

import org.example.model.Restaurant;
import org.example.model.User;

public interface RestaurantRecommendations {
    String[] getRestaurantRecommendations(User user, Restaurant[] availableRestaurants);
}
