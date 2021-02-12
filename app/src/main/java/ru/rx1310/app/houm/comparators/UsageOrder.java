// ! rx1310 <rx1310@inbox.ru> | Copyright (c) rx1310, 2020 | MIT License

package ru.rx1310.app.houm.comparators;

import android.util.Log;

import ru.rx1310.app.houm.LaunchableActivity;

import java.util.Comparator;


public class UsageOrder implements Comparator<LaunchableActivity>{

    @Override
    public int compare(LaunchableActivity lhs, LaunchableActivity rhs) {
        int lhsUsageQuantity = lhs.getusagesQuantity();
        int rhsUsageQuantity = rhs.getusagesQuantity();

        return rhsUsageQuantity - lhsUsageQuantity;
    }
}
