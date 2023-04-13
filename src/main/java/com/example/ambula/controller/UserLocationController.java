package com.example.ambula.controller;

import com.example.ambula.model.UserLocation;
import com.example.ambula.service.UserLocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserLocationController {

    private final UserLocationService userLocationService;

    public UserLocationController(UserLocationService userLocationService) {
        this.userLocationService = userLocationService;
    }

    @PostMapping("/user-locations")
    public UserLocation createUserLocation(@RequestBody UserLocation userLocation) {
        return userLocationService.createUserLocation(userLocation);
    }

    @PutMapping("/update-data")
    public UserLocation updateUserLocation(@PathVariable Long id, @RequestBody UserLocation userLocation) {
        return userLocationService.updateUserLocation(id, userLocation);
    }

    @GetMapping("/get-users/{count}")
    public List<UserLocation> getUsers(@PathVariable int count) {
        return userLocationService.getUsers(count);
    }
}