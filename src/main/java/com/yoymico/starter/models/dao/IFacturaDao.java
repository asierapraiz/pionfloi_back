package com.yoymico.starter.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.yoymico.starter.models.entity.Factura;

public interface IFacturaDao extends CrudRepository<Factura, Long>{

}
