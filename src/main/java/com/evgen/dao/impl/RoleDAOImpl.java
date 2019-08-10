package com.evgen.dao.impl;

import com.evgen.dao.RoleDAO;
import com.evgen.entity.user.RoleEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
@Transactional
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext(unitName = "emf")
    private EntityManager em;

    @Override
    public RoleEntity get(int id) {
        return em.find(RoleEntity.class, id);
    }

    @Override
    public RoleEntity getRoleByName(String roleName) {

        String queryString = "select r from RoleEntity r where r.roleName = :roleName";
        Query query = em.createQuery(queryString);
        query.setParameter("roleName", roleName);
        RoleEntity role = (RoleEntity) query.getSingleResult();

        return role;
    }

    @Override
    public List<RoleEntity> getAll() {

        String queryString = "select r from RoleEntity r";
        Query query = em.createQuery(queryString);
        return query.getResultList();
    }
}
