package org.crue.hercules.sgi.sgemp.repository;

import java.util.Optional;

import org.crue.hercules.sgi.sgemp.model.DatosContacto;
import org.crue.hercules.sgi.sgemp.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DatosContactoRepository
    extends JpaRepository<DatosContacto, String>, JpaSpecificationExecutor<DatosContacto> {

  /**
   * Obtiene una entidad {@link DatosContacto} con el id {@link Empresa} indicado.
   * 
   * @param empresaId Identificador de la entidad {@link Empresa}.
   * @return la entidad {@link DatosContacto}.
   */
  Optional<DatosContacto> findByEmpresaId(String empresaId);

}
