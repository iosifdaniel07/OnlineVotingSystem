package com.sistemonlinevoting.Server.dataBase;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate,Long> {
    Candidate findByIdUser(String id);

}
