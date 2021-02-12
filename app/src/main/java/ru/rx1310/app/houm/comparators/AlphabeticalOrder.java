// ! rx1310 <rx1310@inbox.ru> | Copyright (c) rx1310, 2020 | MIT License

package ru.rx1310.app.houm.comparators;

import ru.rx1310.app.houm.LaunchableActivity;

import java.util.Comparator;


public class AlphabeticalOrder implements Comparator<LaunchableActivity> {
    @Override
    public int compare(LaunchableActivity lhs, LaunchableActivity rhs) {
        return lhs.getActivityLabel().compareToIgnoreCase(rhs.getActivityLabel());
    }
}
