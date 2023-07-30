package com.challenge.aoc2022.day5;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class StackOfCrates {
    private final Deque<Character> crates;

    private StackOfCrates(Deque<Character> crates) {
        this.crates = crates;
    }

    public List<Character> getCrateListInReversOrder() {
        List<Character> list = new ArrayList<>();
        crates.descendingIterator().forEachRemaining(list::add);
        return list;
    }

    public int size() {
        return crates.size();
    }

    public boolean isEmpty() {
        return crates.isEmpty();
    }

    public Character popCrate() {
        return crates.pop();
    }

    public void pushCrate(Character crate) {
        crates.push(crate);
    }

    // The first character represents top-most crate in the stack
    public static StackOfCrates of(String s) {
        var characterList = s.chars()
                .mapToObj(i -> (char) i)
                .toList();
        return new StackOfCrates(new ArrayDeque<>(characterList));
    }

    @Override
    public String toString() {
        return crates.toString();
    }
}
