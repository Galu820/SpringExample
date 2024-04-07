package org.example;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepo extends CrudRepository<User, Integer> {

    List<User> findAllByUsername(String username);
    List<User> findUsersByFio(String fio);
    List<User> findUsersByFioContains(String fio);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Users SET username = :username WHERE id = :id", nativeQuery = true)
    int updateUsersSetUsernameForIdNative(@Param("username") String Username, @Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Users (username, fio) VALUES (?1, ?2)", nativeQuery = true)
    int insertUser(String username, String fio);


}
