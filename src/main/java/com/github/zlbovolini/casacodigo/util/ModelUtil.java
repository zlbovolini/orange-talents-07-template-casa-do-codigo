package com.github.zlbovolini.casacodigo.util;

import java.util.Objects;
import java.util.stream.Stream;

public class ModelUtil {

    public static void required(Object... arguments) {
        boolean hasInconsistency = Stream.of(arguments)
                .anyMatch(argument -> Objects.isNull(argument) ||
                        (argument instanceof String && ((String)argument).isBlank()));

        if (hasInconsistency) {
            throw new IllegalArgumentException("There is some invalid value");
        }
    }
}
