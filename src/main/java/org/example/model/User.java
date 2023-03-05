package org.example.model;

import org.example.enums.Cuisine;

import java.util.HashSet;
import java.util.Set;

public class User {
    private CuisineTracking[]  cuisines;
    private CostTracking[] costBracket;

    public User(CuisineTracking[] cuisines, CostTracking[] costBracket) {
        validateCuisines(cuisines);
        validateCostBracket(costBracket);
        this.cuisines = cuisines;
        this.costBracket = costBracket;
    }

    private void validateCuisines(CuisineTracking[] cuisines){
        Set<Cuisine> cuisineSet = new HashSet<>();
        for(CuisineTracking cuisineTracking : cuisines){
            if(cuisineSet.contains(cuisineTracking.getType())){
                throw new ArrayStoreException("Duplicate Cuisine of type - "+cuisineTracking.getType()+" present in input data");
            }
            cuisineSet.add(cuisineTracking.getType());
        }
    }

    private void validateCostBracket(CostTracking[] costBracket){
        Set<Integer> costTrackingSet = new HashSet<>();
        for(CostTracking costTracking : costBracket){
            if(costTrackingSet.contains(costTracking.getType())){
                throw new ArrayStoreException("Duplicate Cost Tracking of type - "+costTracking.getType()+" present in input data");
            }
            costTrackingSet.add(costTracking.getType());
        }
    }

    public CuisineTracking[] getCuisines() {
        return cuisines;
    }

    public void setCuisines(CuisineTracking[] cuisines) {
        this.cuisines = cuisines;
    }

    public CostTracking[] getCostBracket() {
        return costBracket;
    }

    public void setCostBracket(CostTracking[] costBracket) {
        this.costBracket = costBracket;
    }
}
