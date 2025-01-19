package com.rahul.dsa;

import java.util.Stack;

public class AsteroidCollisionUtil {
    public static int[] remainingAsteroids(int[] a) {
        Stack<Integer> s = new Stack<>();
        if(a.length == 0 || a.length == 1)
            return a;
        s.push(a[0]);
        int i = 1;
        while(i < a.length) {
            int x = a[i];
            if(x > 0) {
                s.push(x);
                i++;
            } else if(!s.empty() && s.peek() > 0) {
                if(-1 * x > s.peek())
                    s.pop();
                else if(-1 * x == s.peek()) {
                    s.pop();
                    i++;
                } else {
                    i++;
                }
            } else {
                s.push(x);
                i++;
            }
        }
        int n = s.size();
        int[] ans = new int[n];
        int j = 1;
        while(!s.empty()) {
            ans[n-j] = s.pop();
            j++;
        }
        return ans;
    }
}
