package com.equipotrabajo.repository;

import com.equipotrabajo.entity.TeamMember;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaTeamMemberRepository implements Repository<TeamMember> {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("teamMemberPU");

    @Override
    public void save(TeamMember member) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(member);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public TeamMember findById(int id) {
        EntityManager em = emf.createEntityManager();
        TeamMember member = em.find(TeamMember.class, id);
        em.close();
        return member;
    }

    @Override
    public List<TeamMember> findAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<TeamMember> query = em.createQuery("SELECT t FROM TeamMember t", TeamMember.class);
        List<TeamMember> members = query.getResultList();
        em.close();
        return members;
    }

    @Override
    public void update(TeamMember updatedMember) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(updatedMember);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        TeamMember member = em.find(TeamMember.class, id);
        if (member != null) {
            em.remove(member);
        }
        em.getTransaction().commit();
        em.close();
    }
}
