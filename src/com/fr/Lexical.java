package com.fr;

import java.io.*;
import java.util.*;

public class Lexical {
    private final File file;

    public Lexical(String fileName) {
        file = new File(fileName);
    }

    public List<Token> run() {
        List<Token> tokens = new ArrayList<>();
        try (var reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String data;
            while ((data = reader.readLine()) != null) {
                for (int i = 0; i < data.length(); i++) {
                    char x = data.charAt(i);
                    if (x == '(') {
                        tokens.add(new Token("lPattern", "("));
                    } else if (x == ')') {
                        tokens.add(new Token("rPattern", ")"));
                    } else if (x >= '0' && x <= '9') {
                        i = readNumber(tokens, i, data);
                    } else if (x == '"') {
                        i = readString(tokens, i, data);
                    } else if (x != ' ' && x != '\t') {
                        i = readId(tokens, i, data);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tokens;
    }

    private int readString(List<Token> tokens, int ptr, String data) {
        var builder = new StringBuilder();
        ptr++;
        while (data.charAt(ptr) != '"') {
            builder.append(data.charAt(ptr));
            ptr++;
        }
        tokens.add(new Token("string", builder.toString()));
        return ptr;
    }

    private int readId(List<Token> tokens, int ptr, String data) {
        var builder = new StringBuilder();
        while (data.charAt(ptr) != ' ' && data.charAt(ptr) != '\t' && data.charAt(ptr) != '(' && data.charAt(ptr) != ')') {
            builder.append(data.charAt(ptr));
            ptr++;
        }
        tokens.add(new Token("id", builder.toString()));
        return ptr - 1;
    }

    private int readNumber(List<Token> tokens, int ptr, String data) {
        var builder = new StringBuilder();
        while ((data.charAt(ptr) >= '0' && data.charAt(ptr) <= '9') || data.charAt(ptr) == '.') {
            builder.append(data.charAt(ptr));
            ptr++;
        }
        tokens.add(new Token("Number", Double.parseDouble(builder.toString())));
        return ptr - 1;
    }
}