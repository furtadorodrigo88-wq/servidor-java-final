package com.labanta.servidorlocal.repository;

import com.labanta.servidorlocal.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UsersModel, Long> {

    Optional<UsersModel> findByUsername(String username);
}
