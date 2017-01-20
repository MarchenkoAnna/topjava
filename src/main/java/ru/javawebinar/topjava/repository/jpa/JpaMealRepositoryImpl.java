package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User: gkisline
 * Date: 26.08.2014
 */

@Repository
@Transactional(readOnly = true)
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            User user = em.getReference(User.class, userId);
            meal.setUser(user);
            em.persist(meal);
            return meal;
        } else {
            if(em.createNamedQuery(Meal.UPDATE).setParameter("id", meal.getId()).
                    setParameter("user", userId).
                    setParameter("description", meal.getDescription()).
                    setParameter("dateTime", meal.getDateTime()).
                    setParameter("calories", meal.getCalories()).
                    executeUpdate() == 0){
                return null;
            } else {
                return meal;
            }
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Meal.DELETE).setParameter("id", id).setParameter("user", userId).executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        try {
            return (Meal) em.createNamedQuery(Meal.GET).setParameter("id", id).setParameter("user", userId).getSingleResult();
        }catch (NoResultException ex){
            return null;
        }
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.ALL_SORTED, Meal.class).setParameter("user", userId).getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        List<Meal> meals = em.createNamedQuery(Meal.BETWEEN, Meal.class).setParameter("user", userId).setParameter(1, startDate).setParameter(2, endDate).getResultList();
        return meals;
    }
}