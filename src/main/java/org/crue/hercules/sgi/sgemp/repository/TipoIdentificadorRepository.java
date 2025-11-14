package org.crue.hercules.sgi.sgemp.repository;

import org.crue.hercules.sgi.sgemp.model.TipoIdentificador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TipoIdentificadorRepository
    extends JpaRepository<TipoIdentificador, Long>, JpaSpecificationExecutor<TipoIdentificador> {

}
