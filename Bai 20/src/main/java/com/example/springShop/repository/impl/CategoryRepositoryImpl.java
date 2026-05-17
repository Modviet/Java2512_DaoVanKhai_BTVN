package com.example.springShop.repository.impl;

import com.example.springShop.entity.Category;
import com.example.springShop.repository.CategoryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Category> findAll(String keyword, Integer parentId, int page, int size) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> cq = cb.createQuery(Category.class);
        Root<Category> root = cq.from(Category.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("deleted"),(byte) 0));

        if(keyword != null && !keyword.isBlank()) {
            predicates.add(cb.like(cb.lower(root.get("name")), "%" + keyword.trim().toLowerCase() + "%"));
        }

        if(parentId != null){
            predicates.add(cb.equal(root.get("parent").get("id"),parentId));
        } else {

        }
        cq.select(root)
                .where(predicates.toArray(new Predicate[0]))
                .orderBy(cb.desc(root.get("createdAt")));

        int safePage = Math.max(page,1);
        int safeSize = Math.min(Math.max(size,1),100);
        int offset = (safePage - 1) * safeSize;

        TypedQuery<Category> query = entityManager.createQuery(cq);
        query.setFirstResult(offset);
        query.setMaxResults(safeSize);
        return query.getResultList();

    }

    @Override
    public long countAll(String keyword, Integer parentId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Category> root = cq.from(Category.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("deleted"), (byte) 0));

        if(keyword != null && !keyword.isBlank()){
            predicates.add(cb.like(cb.lower(root.get("name")), "%" + keyword.trim().toLowerCase() + "%"));
        }

        if(parentId != null){
            predicates.add(cb.equal(root.get("parent").get("id"), parentId));
        }

        cq.select(cb.countDistinct(root)).where(predicates.toArray(new Predicate[0]));
        Long result = entityManager.createQuery(cq).getSingleResult();
        return result == null ? 0 : result;
    }

    @Override
    public Optional<Category> findById(Integer id) {
        if(id == null)
            return Optional.empty();

        TypedQuery<Category> query = entityManager.createQuery(
                "SELECT c FROM Category c LEFT JOIN FETCH c.parent WHERE c.id = :id AND c.deleted = 0",
                Category.class
        );
        query.setParameter("id",id);

        List<Category> results = query.getResultList();
        if(results.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(results.get(0));
    }

    @Override
    public Category save(Category category) {
        if(category.getId() == null){
            entityManager.persist(category);
            return category;
        } else {
            return entityManager.merge(category);
        }
    }

    @Override
    public int deleteById(Integer id) {
        if(id == null)
            return 0;

        int updated = entityManager.createQuery("UPDATE Category c SET c.deleted = 1 WHERE c.id = :id")
                .setParameter("id",id)
                .executeUpdate();

        return updated;

    }
}
