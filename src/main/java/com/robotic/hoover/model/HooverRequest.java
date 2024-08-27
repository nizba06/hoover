package com.robotic.hoover.model;

import lombok.Data;

import java.util.List;

@Data
public class HooverRequest {
    private List<Integer> roomSize;
    private List<Integer> coords;
    private List<List<Integer>> patches;
    private String instructions;
}
