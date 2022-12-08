package com.challenge.library.lists;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.util.Collections.emptyList;

public class ListSplitter {
    private ListSplitter() {
    }
    public static <T extends Comparable<T>> List<List<T>> splitList(List<T> list, T divider) {
        return splitAndConvertList(list, divider, Function.identity());
    }

    public static <T extends Comparable<T>, R> List<List<R>> splitAndConvertList(List<T> list, T divider, Function<T, R> converter) {
        checkParams(list, divider);

        if (list.isEmpty()) return emptyList();

        var returnList = new ArrayList<List<R>>();
        var sublist = new ArrayList<R>();
        for (T element : list) {
            if (element.equals(divider)) {
                returnList.add(sublist);
                sublist = new ArrayList<>();
            } else {
                sublist.add(converter.apply(element));
            }
        }
        returnList.add(sublist);
        return returnList;
    }

    private static <T extends Comparable<T>> void checkParams(List<T> list, T divider) {
        if (list == null) throw new IllegalArgumentException("The list cannot be null");
        if (divider == null) throw new IllegalArgumentException("The divider cannot be null");
    }
}
