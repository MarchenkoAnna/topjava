package ru.javawebinar.topjava.service.jdbc;


import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.junit.Assume;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.AbstractMealServiceTest;

import java.time.Month;

import static java.time.LocalDateTime.of;
import static ru.javawebinar.topjava.Profiles.JDBC;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ActiveProfiles(JDBC)
public class JdbcMealServiceTest extends AbstractMealServiceTest {
//    @Override
//    @Test
//    public void testValidation() throws Exception {
//        Assume.assumeTrue(true);
//    }

    @Override
    @Test
    public void testValidation() throws Exception {
        //validateRootCause(() -> service.save(new Meal(null, of(2015, Month.JUNE, 1, 18, 0), "  ", 300), USER_ID), MySQLIntegrityConstraintViolationException.class);
        validateRootCause(() -> service.save(new Meal(null, null, "Description", 300), USER_ID), MySQLIntegrityConstraintViolationException.class);
        //validateRootCause(() -> service.save(new Meal(null, of(2015, Month.JUNE, 1, 18, 0), "Description", 9), USER_ID), MySQLIntegrityConstraintViolationException.class);
        //validateRootCause(() -> service.save(new Meal(null, of(2015, Month.JUNE, 1, 18, 0), "Description", 5001), USER_ID), MySQLIntegrityConstraintViolationException.class);
    }
}