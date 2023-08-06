package com.challenge.aoc2022.day5;

import com.challenge.aoc2022.day5.exception.ShipSuppliesFileParserException;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.util.regex.Pattern;

public class ShipSuppliesFileParserTest {

    @Test
    public void testParse() throws ShipSuppliesFileParserException {
        Path filePath = Path.of("src", "test", "resources", "com", "challenge", "aoc2022", "day5", "ship_supplies.txt");
        var parser = ShipSuppliesFileParser.of(filePath);

        Assertions.assertThat(parser.parse()).containsExactly("NZ", "DCM", "P");
    }
}