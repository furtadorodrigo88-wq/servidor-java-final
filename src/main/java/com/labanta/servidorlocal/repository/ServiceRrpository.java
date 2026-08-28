package com.labanta.servidorlocal.repository;

import com.labanta.servidorlocal.model.ServiceModel;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRrpository extends JpaRepository<ServiceModel, Long> {

    List<ServiceModel> findByPrecoLessThan (Double valorMaximo);
    List<ServiceModel> findByEstadoTrue();
    List<ServiceModel> findByTituloContainingIgnoreCase(String termo);

}
