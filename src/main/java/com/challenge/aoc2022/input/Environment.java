package com.challenge.aoc2022.input;

import java.nio.file.Path;

public enum Environment {
    PRODUCTION {
        @Override
        public Path getResourcePath() {
            return Path.of("src", "main");
        }
    }, TEST {
        @Override
        public Path getResourcePath() {
            return Path.of("src", "test");
        }
    };

    public abstract Path getResourcePath();
}
