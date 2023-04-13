package com.example.ambula.service;

import com.example.ambula.ResourceNotFoundException;
import com.example.ambula.model.UserLocation;
import com.example.ambula.repository.UserLocationRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class UserLocationService {

    private final UserLocationRepository userLocationRepository;

    public UserLocationService(UserLocationRepository userLocationRepository) {
        this.userLocationRepository = userLocationRepository;
    }

    public UserLocation createUserLocation(UserLocation userLocation) {
        return userLocationRepository.save(userLocation);
    }

    public UserLocation updateUserLocation(Long id, UserLocation userLocation) {
        UserLocation existingUserLocation = userLocationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User location not found with id " + id));
        existingUserLocation.setName(userLocation.getName());
        existingUserLocation.setLatitude(userLocation.getLatitude());
        existingUserLocation.setLongitude(userLocation.getLongitude());
        return userLocationRepository.save(existingUserLocation);
    }
    public List<UserLocation> getUsers(Integer count) {
        return userLocationRepository.findAllByOrderByDistanceFromOriginAsc(0.0, 0.0, (Pageable) PageRequest.of(0, count));
    }
}