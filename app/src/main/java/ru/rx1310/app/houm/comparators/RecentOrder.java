// ! rx1310 <rx1310@inbox.ru> | Copyright (c) rx1310, 2020 | MIT License

package ru.rx1310.app.houm.comparators;

import ru.rx1310.app.houm.LaunchableActivity;

import java.util.Comparator;


public class RecentOrder implements Comparator<LaunchableActivity>{

    @Override
    public int compare(LaunchableActivity lhs, LaunchableActivity rhs) {
        long lhsLaunchTime=lhs.getLaunchTime();
        long rhsLaunchTime = rhs.getLaunchTime();

        if (lhsLaunchTime > rhsLaunchTime)
            return -1;
        if (lhsLaunchTime < rhsLaunchTime)
            return 1;
        return 0;
    }
}
