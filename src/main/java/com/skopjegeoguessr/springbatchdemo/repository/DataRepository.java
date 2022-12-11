package com.skopjegeoguessr.springbatchdemo.repository;

import com.skopjegeoguessr.springbatchdemo.model.AllData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<AllData, String> {
}
