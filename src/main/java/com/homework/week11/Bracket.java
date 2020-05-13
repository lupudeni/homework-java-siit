package com.homework.week11;

import java.util.*;
import java.util.stream.Collectors;

public class Bracket {

    public boolean isBalanced(String s) {
        if(s == null) {
            return false;
        }
        Map<String, String> bracketsMap = Map.of("}", "{", "]", "[", ")", "(");
        Stack<String> stack = new Stack<>();
        List<String> brackets = Arrays.stream(s.split("")).collect(Collectors.toList());
        for(String bracket : brackets) {
            if (bracketsMap.containsValue(bracket)) {
                stack.push(bracket);
            } else if (bracketsMap.containsKey(bracket)) {
                String lastBracket = stack.peek();
                if (bracketsMap.get(bracket).equals(lastBracket)) {
                    stack.pop();
                }
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
