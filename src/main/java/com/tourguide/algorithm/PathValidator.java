package com.tourguide.algorithm;

import com.tourguide.containers.Constants;
import com.tourguide.containers.algorithm.AntPathContainer;

public class PathValidator {
    public static boolean isDayOver(AntPathContainer path, double nextPlaceMoveTime,double nextPlaceSpendTime,double getToNightSpendTime){
        return path.getTimeSpent() + path.getMoveTimeSpent() + nextPlaceMoveTime + nextPlaceSpendTime + getToNightSpendTime
                <= Constants.walkingHours;
    }
    public static double validatePath(AntPathContainer path,double totalBudget,double totalDays){
            int mark = 0;
            if (path.getTotalTimeSpent() + path.getTotalMoveTimeSpent() >= Constants.bestPercent*totalDays*Constants.walkingHours
                    && path.getTotalTimeSpent() + path.getTotalMoveTimeSpent() <= totalDays*Constants.walkingHours){
                mark += 1;
            }
            if (path.getTotalTimeSpent() + path.getTotalMoveTimeSpent() > totalDays*Constants.walkingHours) {
                mark += -10;
            }
            if (path.getTotalMoneySpent() + path.getTotalMoveMoneySpent() >= Constants.bestPercent*totalBudget
                    && path.getTotalMoneySpent() + path.getTotalMoveMoneySpent() <= totalBudget){
                mark += 1;
            }
            if (path.getTotalMoneySpent() + path.getTotalMoveMoneySpent() > totalBudget) {
                mark += -10;
            }
            if (path.getTotalMoveTimeSpent() < 0.5 *path.getTotalTimeSpent()){
                mark +=1;
            }
            if (path.getTotalMoveMoneySpent() < 0.5 *path.getTotalMoneySpent()){
                mark +=1;
            }
            return mark + Math.pow(path.getSumExperience(),1.5);

    }
}
