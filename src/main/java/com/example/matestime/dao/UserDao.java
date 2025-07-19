package com.example.matestime.dao;

import com.example.matestime.models.user.User;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.BindList;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

@RegisterBeanMapper(User.class)
public interface UserDao {

    @SqlQuery("SELECT * FROM users WHERE id = :id")
    Optional<User> getById(@Bind("id") int id);

    @SqlUpdate("DELETE FROM users WHERE id = :id")
    int deleteById(@Bind("id") int id);

    @SqlQuery("SELECT * FROM users")
    List<User> getAll();

    @SqlUpdate("INSERT INTO users (name, email) VALUES (:name, :email)")
    void addUser(@Bind("name") String name, @Bind("email") String email);

    @SqlQuery("SELECT * FROM users WHERE id IN (<ids>)")
    List<User> getUsersByCommunityId(@BindList(value = "ids") final List<Integer> ids);

    @SqlQuery("SELECT COUNT(*) > 0 FROM users WHERE email = :email")
    boolean userExistsByEmail(@Bind("email") String email);

    @SqlUpdate("UPDATE users SET name = :name, email = :email WHERE id = :id")
    int updateUser(@BindBean User user);
}