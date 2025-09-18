package com.taskmanager.repository;

import com.taskmanager.model.Member;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MemberRepository implements PanacheRepository<Member> {
    
    public Member findByEmail(String email) {
        return find("email", email).firstResult();
    }
    
    public boolean existsByEmail(String email) {
        return find("email", email).count() > 0;
    }
}
