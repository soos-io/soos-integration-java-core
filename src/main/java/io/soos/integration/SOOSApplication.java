package io.soos.integration;

import java.util.Arrays;

public class SOOSApplication {
    public static void main(String[] args) {

        Arrays.stream(args).forEach(System.out::println);
    }
}
