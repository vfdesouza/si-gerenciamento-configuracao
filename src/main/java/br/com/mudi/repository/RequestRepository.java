package br.com.mudi.repository;

import br.com.mudi.models.Request;
import br.com.mudi.models.StatusRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByProductNameIgnoreCaseContaining(String productName);
    List<Request> findByStatusRequest(StatusRequest statusRequest);
}
