package com.taskmanager.repository;

import com.taskmanager.model.TeamMember;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TeamMemberRepository implements PanacheRepository<TeamMember> {
    
    public TeamMember findByEmail(String email) {
        return find("email", email).firstResult();
    }
    
    public boolean existsByEmail(String email) {
        return find("email", email).count() > 0;
    }
}
