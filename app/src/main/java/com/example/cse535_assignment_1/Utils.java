package com.example.cse535_assignment_1;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Utils {
    // To keep the count of PRACTICE_NUMBER
    public static AtomicInteger practiceCount = new AtomicInteger(1);
    // To keep the count of recordings corresponding to the relevant gesture
    public static ConcurrentHashMap<String, Integer> actions_map = new ConcurrentHashMap<>();
}
