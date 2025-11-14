package org.crue.hercules.sgi.sgemp.service;

import org.crue.hercules.sgi.sgemp.model.TipoIdentificador;
import org.crue.hercules.sgi.sgemp.model.TipoIdentificador_;
import org.crue.hercules.sgi.sgemp.repository.TipoIdentificadorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(readOnly = true)
public class TipoIdentificadorService {

  private final TipoIdentificadorRepository repository;

  public TipoIdentificadorService(TipoIdentificadorRepository tipoIdentificadorRepository) {
    this.repository = tipoIdentificadorRepository;
  }

  /**
   * Obtiene todas las entidades {@link TipoIdentificador} ordenadas
   * alfabeticamente.
   *
   * @return el listado de entidades {@link TipoIdentificador} ordenadas.
   */
  public Page<TipoIdentificador> findAllSorted() {
    log.debug("findAllSorted() - start");
    Page<TipoIdentificador> returnValue = new PageImpl<>(
        repository.findAll(Sort.by(Sort.Direction.ASC, TipoIdentificador_.NOMBRE)));
    log.debug("findAllSorted() - end");
    return returnValue;
  }

}
