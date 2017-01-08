package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * GKislin
 * 15.06.2015.
 */
@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 2;
    {
        save(new User(USER_ID, "User", "user@yandex.ru", "password", Role.ROLE_USER));
        save(new User(ADMIN_ID, "Admin", "admin@yandex.ru", "admin", Role.ROLE_ADMIN));

    }

    @Override
    public boolean delete(int id) {
        LOG.info("delete " + id);
        User user = repository.remove(id);
        return user != null;
    }

    @Override
    public User save(User user) {
        LOG.info("save " + user);
        if (user.isEnabled()) {
            user.setId(counter.incrementAndGet());
        }
        repository.put(user.getId(), user);
        return user;
    }

    @Override
    public User get(int id) {
        LOG.info("get " + id);
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        LOG.info("getAll");
        Collection<User> users = repository.values();
        List <User> userList = null;
        userList.addAll(users);
        userList.sort( (User u1, User u2) -> (u1.getName().compareTo(u2.getName())));
        return userList;

    }

    @Override
    public User getByEmail(String email) {
        LOG.info("getByEmail " + email);
        return getAll().stream().filter(u -> u.getEmail().equals(email)).findFirst().orElse(null);
    }
}
