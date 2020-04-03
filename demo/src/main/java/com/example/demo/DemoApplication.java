package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DemoApplication {

    public static void main(String[] args) {
        List<Integer> integers1 = Arrays.asList(11, 2, 4);
        List<Integer> integers2 = Arrays.asList(4, 5, 6);
        List<Integer> integers3 = Arrays.asList(10, 8, -12);
        List<List<Integer>> integers = Arrays.asList(integers1, integers2, integers3);
        System.out.println(diagonalDifference(integers));
    }

    public static int diagonalDifference(List<List<Integer>> arr) {
        List<Integer> ints = arr
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        int rowSize = (int) Math.sqrt(ints.size());
        int mainDiagonalStep = rowSize+1;
        int seconDiagonalStep = rowSize-1;
        List<Integer> mainDiagonal = new ArrayList<>();
        List<Integer> secondDiagonal = new ArrayList<>();
        for(int i=0; i<ints.size();i++) {
            if(i%mainDiagonalStep == 0) {
                mainDiagonal.add(i);
            }
            if(i%seconDiagonalStep == 0 && i!=ints.get(0)) {
                secondDiagonal.add(i);
            }
        }
        int firstSum = (int) mainDiagonal.stream().mapToInt(Integer::intValue).sum();
        int secSum = (int) secondDiagonal.stream().mapToInt(Integer::intValue).sum();
        return Math.abs(firstSum-secSum);
    }

}
