package org.example.service.impl;

import org.example.enums.Cuisine;
import org.example.model.CostTracking;
import org.example.model.CuisineTracking;
import org.example.model.User;
import org.example.service.UserService;
import org.example.service.exceptions.PrimaryCostBracketNotFoundException;
import org.example.service.exceptions.PrimaryCuisineNotFoundException;
import org.example.service.exceptions.SecondaryCostBracketNotFoundException;
import org.example.service.exceptions.SecondaryCuisineNotFoundException;

import java.util.Arrays;
import java.util.Comparator;

public class UserServiceImpl implements UserService {

    @Override
    public Cuisine getPrimaryCuisine(User user) {
        CuisineTracking[] cuisineTracking = user.getCuisines();
        if(cuisineTracking.length<1){
            throw new PrimaryCuisineNotFoundException("Primary Cuisine not present for user");
        }
        Arrays.sort(cuisineTracking, Comparator.comparing(CuisineTracking::getNoOfOrders).reversed());
        return cuisineTracking[0].getType();
    }

    @Override
    public int getPrimaryCostBracket(User user) {
        CostTracking[] costTracking = user.getCostBracket();
        if(costTracking.length<1){
            throw new PrimaryCostBracketNotFoundException("Primary Cost Bracket not present for user");
        }
        Arrays.sort(costTracking,Comparator.comparing(CostTracking::getNoOfOrders).reversed());
        return costTracking[0].getType();
    }

    @Override
    public Cuisine[] getSecondaryCuisine(User user) {
        CuisineTracking[] cuisineTracking = user.getCuisines();
        if(cuisineTracking.length<3){
            throw new SecondaryCuisineNotFoundException("Secondary Cuisine not present for user");
        }
        Arrays.sort(cuisineTracking, Comparator.comparing(CuisineTracking::getNoOfOrders).reversed());
        Cuisine[] secondaryCuisine = new Cuisine[2];
        secondaryCuisine[0] = cuisineTracking[1].getType();
        secondaryCuisine[1] = cuisineTracking[2].getType();
        return secondaryCuisine;
    }

    @Override
    public int[] getSecondaryCostBracket(User user) {
        CostTracking[] costTracking = user.getCostBracket();
        if(costTracking.length<3){
            throw new SecondaryCostBracketNotFoundException("Secondary Cost Bracket not present for user");
        }
        Arrays.sort(costTracking,Comparator.comparing(CostTracking::getNoOfOrders).reversed());
        int[] secondaryCostBracket = new int[2];
        secondaryCostBracket[0] = costTracking[1].getType();
        secondaryCostBracket[1] = costTracking[2].getType();
        return secondaryCostBracket;
    }
}
