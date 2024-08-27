package com.robotic.hoover.service;

import com.robotic.hoover.model.HooverRequest;
import com.robotic.hoover.model.HooverResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class HooverServiceTests {
    private final HooverService hooverService = new HooverService();

    @Test
    public void testHooverNavigation() {
        HooverRequest request = new HooverRequest();
        request.setRoomSize(List.of(5, 5));
        request.setCoords(List.of(1, 2));
        request.setPatches(List.of(
                List.of(1, 0),
                List.of(2, 2),
                List.of(2, 3)
        ));
        request.setInstructions("NNESEESWNWW");

        HooverResponse response = hooverService.navigate(request);

        assertEquals(List.of(1, 3), response.getCoords());
        assertEquals(1, response.getPatches());
    }

    @Test
    public void testNegativeScenario_InvalidRoomSize() {
        HooverRequest request = new HooverRequest();
        request.setRoomSize(List.of(-5, 5));
        request.setCoords(List.of(1, 2));
        request.setPatches(List.of(
                List.of(1, 0),
                List.of(2, 2),
                List.of(2, 3)
        ));
        request.setInstructions("NNESEESWNWW");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            hooverService.navigate(request);
        });

        assertEquals("Room dimensions must be positive", exception.getMessage());
    }

    @Test
    public void testNegativeScenario_InvalidInitialCoordinates() {
        HooverRequest request = new HooverRequest();
        request.setRoomSize(List.of(5, 5));
        request.setCoords(List.of(6, 2));
        request.setPatches(List.of(
                List.of(1, 0),
                List.of(2, 2),
                List.of(2, 3)
        ));
        request.setInstructions("NNESEESWNWW");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            hooverService.navigate(request);
        });

        assertEquals("Initial position is outside the room boundaries.", exception.getMessage());
    }

    @Test
    public void testNegativeScenario_InvalidInstructions() {
        HooverRequest request = new HooverRequest();
        request.setRoomSize(List.of(5, 5));
        request.setCoords(List.of(1, 2));
        request.setPatches(List.of(
                List.of(1, 0),
                List.of(2, 2),
                List.of(2, 3)
        ));
        request.setInstructions("NNESEESWNWWZ");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            hooverService.navigate(request);
        });

        assertEquals("Invalid instruction: Z", exception.getMessage());
    }
}