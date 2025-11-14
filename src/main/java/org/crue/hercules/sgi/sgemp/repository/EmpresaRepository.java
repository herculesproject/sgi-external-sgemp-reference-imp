package org.crue.hercules.sgi.sgemp.repository;

import org.crue.hercules.sgi.sgemp.model.Empresa;
import org.crue.hercules.sgi.sgemp.model.Empresa_;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmpresaRepository extends JpaRepository<Empresa, String>, JpaSpecificationExecutor<Empresa> {

  @Override
  @EntityGraph(attributePaths = { Empresa_.TIPO_IDENTIFICADOR }, type = EntityGraph.EntityGraphType.FETCH)
  Page<Empresa> findAll(Specification<Empresa> spec, Pageable pageable);

}
