package com.example.cse535_assignment_1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Utils {
    // To keep the count of PRACTICE_NUMBER
    public static AtomicInteger practiceCount = new AtomicInteger(1);
    // To keep the count of recordings corresponding to the relevant gesture
    public static HashMap<String, Integer> actions_map = new HashMap<>();
}
