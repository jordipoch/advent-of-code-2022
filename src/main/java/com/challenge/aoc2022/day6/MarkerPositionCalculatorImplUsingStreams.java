package com.challenge.aoc2022.day6;

import java.util.ArrayList;
import java.util.List;

public class MarkerPositionCalculatorImplUsingStreams implements MarkerPositionCalculator {
    @Override
    public int calculateMarkerPosition(String data) {
        return createGroups(data).stream()
                .filter(CharacterGroup::allCharactersUnique)
                .findFirst()
                .map(CharacterGroup::position)
                .orElse(0);
    }

    private List<CharacterGroup> createGroups(String data) {
        List<CharacterGroup> groups = new ArrayList<>();
        for (int i = MARKER_SIZE; i <= data.length(); i++) {
            var s = data.substring(i - MARKER_SIZE, i);
            var group = CharacterGroup.of(s, i);
            groups.add(group);
        }
        return groups;
    }
}
