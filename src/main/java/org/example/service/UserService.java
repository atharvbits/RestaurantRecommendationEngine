package org.example.service;

import org.example.enums.Cuisine;
import org.example.model.User;

public interface UserService {
    Cuisine getPrimaryCuisine(User user);

    int getPrimaryCostBracket(User user);

    Cuisine[] getSecondaryCuisine(User user);

    int[] getSecondaryCostBracket(User user);
}
