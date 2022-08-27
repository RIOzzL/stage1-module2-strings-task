package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source     source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String delimiter : delimiters) {
            stringBuilder.append(delimiter);
        }
        StringTokenizer st = new StringTokenizer(source, stringBuilder.toString());
        List<String> list = new ArrayList<>();
        while (st.hasMoreTokens()) {
            list.add(st.nextToken());
        }
        return list;
        // throw new UnsupportedOperationException("You should implement this method.");
    }
}
