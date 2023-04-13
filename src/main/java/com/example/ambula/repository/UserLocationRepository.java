package com.example.ambula.repository;

import com.example.ambula.model.UserLocation;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface UserLocationRepository extends JpaRepository<UserLocation, Long> {

    //@Query("SELECT u FROM UserLocation u ORDER BY SQRT(u.latitude * u.latitude + u.longitude * u.longitude)")
    //List<UserLocation> findAllByOrderByDistanceFromOriginAsc(Double originLatitude, Double originLongitude, Pageable pageable);
    default List<UserLocation> findAllByOrderByDistanceFromOriginAsc(Double originLatitude, Double originLongitude, Pageable pageable) {
        return findAll((Sort) pageable).stream()
                .sorted(Comparator.comparingDouble(u -> u.getDistanceFromOrigin(originLatitude, originLongitude)))
                .collect(Collectors.toList());
    }
}
