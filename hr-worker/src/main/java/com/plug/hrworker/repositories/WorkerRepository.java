package com.plug.hrworker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plug.hrworker.entities.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

}
