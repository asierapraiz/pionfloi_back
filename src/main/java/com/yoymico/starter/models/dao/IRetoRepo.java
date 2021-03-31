package com.yoymico.starter.models.dao;




import com.yoymico.starter.models.entity.Reto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRetoRepo extends JpaRepository<Reto, Long> {
}

