package com.github.zlbovolini.casacodigo.validation.rule;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum RelationshipRule {

    UNIQUE("="),
    EXISTS(">");

    private String comparator;

    private static final Map<String, String> comparatorMessageMap;

    RelationshipRule(String comparator) {
        this.comparator = comparator;
    }

    static {
        Map<String, String> messages = new HashMap<>();

        messages.put(UNIQUE.comparator, "{com.github.zlbovolini.constraints.UniqueStateInCountry}");
        messages.put(EXISTS.comparator, "{com.github.zlbovolini.constraints.ExistsStateInCountry}");

        comparatorMessageMap = Collections.unmodifiableMap(messages);
    }

    public String getComparator() {
        return comparator;
    }

    public String getMessage() {
        return comparatorMessageMap.get(comparator);
    }
}
