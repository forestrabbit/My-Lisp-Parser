package com.fr;

import java.util.*;

public class Parser {
    private final List<Token> tokens;
    private int ptr = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Object parse() {
        if (tokens.get(ptr).getType().equals("rPattern")) {
            throw new IllegalArgumentException();
        } else if (!tokens.get(ptr).getType().equals("lPattern")) {
            return tokens.get(ptr);
        } else {
            return parseList();
        }
    }

    private List<Object> parseList() {
        List<Object> list = new ArrayList<>();
        ptr++;
        while (!tokens.get(ptr).getType().equals("rPattern")) {
            list.add(parse());
            ptr++;
        }
        return list;
    }
}