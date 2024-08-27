package com.robotic.hoover.model;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class HooverResponse {
    private List<Integer> coords;
    private int patches;

}
