package com.example.library.repo;

import com.example.library.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByNationalId(Long nationalId);
    void deleteByNationalId(Long nationalId);
    boolean existsByNationalId(Long nationalId);
}
