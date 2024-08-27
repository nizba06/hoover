package com.robotic.hoover.service;

import com.robotic.hoover.model.HooverRequest;
import com.robotic.hoover.model.HooverResponse;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class HooverService {
    public HooverResponse navigate(HooverRequest request) {
        validateRequest(request);

        int roomWidth = request.getRoomSize().get(0);
        int roomHeight = request.getRoomSize().get(1);
        int x = request.getCoords().get(0);
        int y = request.getCoords().get(1);

        Set<String> dirtPatches = new HashSet<>();
        for (List<Integer> patch : request.getPatches()) {
            dirtPatches.add(patch.get(0) + "," + patch.get(1));
        }

        int cleanedPatches = 0;

        for (char instruction : request.getInstructions().toCharArray()) {
            switch (instruction) {
                case 'N':
                    if (y < roomHeight-1) y++;
                    break;
                case 'E':
                    if (x < roomWidth-1) x++;
                    break;
                case 'S':
                    if (y > 0) y--;
                    break;
                case 'W':
                    if (x > 0) x--;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid instruction: " + instruction);
            }

            String position = x + "," + y;
            if (dirtPatches.contains(position)) {
                cleanedPatches++;
                dirtPatches.remove(position);
            }
        }

        return new HooverResponse(List.of(x, y), cleanedPatches);
    }

    private void validateRequest(HooverRequest request) {
        if (request.getRoomSize().get(0) <= 0 || request.getRoomSize().get(1) <= 0) {
            throw new IllegalArgumentException("Room dimensions must be positive");
        }

        int x = request.getCoords().get(0);
        int y = request.getCoords().get(1);
        if (x < 0 || y < 0 || x >= request.getRoomSize().get(0) || y >= request.getRoomSize().get(1)) {
            throw new IllegalArgumentException("Initial position is outside the room boundaries.");
        }
    }
}
