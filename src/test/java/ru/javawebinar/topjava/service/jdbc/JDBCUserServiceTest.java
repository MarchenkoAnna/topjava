package ru.javawebinar.topjava.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.UserServiceTest;

import static ru.javawebinar.topjava.Profiles.JDBC;

/**
 * Created by Marchelaga on 30.01.2017.
 */
@ActiveProfiles(JDBC)
public class JDBCUserServiceTest extends UserServiceTest {
}
