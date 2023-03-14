package it.exercise.TestUnit.repositories;

import it.exercise.TestUnit.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Drumstyle92
 * repository interface that extends the Spring Data JPA JpaRepository
 * interface to be able to define the CRUD (Create, Read, Update, Delete)
 * operations that can be performed on the database users table
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

}
