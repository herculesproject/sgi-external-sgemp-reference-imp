package org.crue.hercules.sgi.sgemp.service;

import org.apache.commons.lang3.ObjectUtils;
import org.crue.hercules.sgi.framework.rsql.SgiRSQLJPASupport;
import org.crue.hercules.sgi.sgemp.exceptions.EmpresaNotFoundException;
import org.crue.hercules.sgi.sgemp.model.Empresa;
import org.crue.hercules.sgi.sgemp.model.TipoIdentificador;
import org.crue.hercules.sgi.sgemp.repository.EmpresaRepository;
import org.crue.hercules.sgi.sgemp.repository.predicate.EmpresaPredicateResolver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(readOnly = true)
public class EmpresaService {

  private final EmpresaRepository repository;

  public EmpresaService(EmpresaRepository empresaRepository) {
    this.repository = empresaRepository;
  }

  /**
   * Crea una empresa
   * 
   * @param empresa una empresa
   * @return la empresa creada
   */
  @Transactional
  public Empresa create(Empresa empresa) {
    log.debug("create({}) - start", empresa);
    if (ObjectUtils.isNotEmpty(empresa.getPadreId())) {
      Empresa empresaPadre = this.findById(empresa.getPadreId());
      empresa.setTipoIdentificador(TipoIdentificador.builder().id(empresaPadre.getTipoIdentificador().getId()).build());
      empresa.setNumeroIdentificacion(empresaPadre.getNumeroIdentificacion());
    }

    Empresa empresaCreated = repository.save(empresa);
    log.debug("create({}) - end", empresa);
    return empresaCreated;
  }

  /**
   * Actualiza una empresa
   * 
   * @param empresa una empresa
   * @return la empresa actualizada
   */
  @Transactional
  public Empresa update(Empresa empresa) {
    log.debug("update({}) - start", empresa);

    if (ObjectUtils.isNotEmpty(empresa.getPadreId()) && !repository.existsById(empresa.getPadreId())) {
      throw new EmpresaNotFoundException(empresa.getPadreId());
    }

    return repository.findById(empresa.getId()).map(data -> {
      data.setNombre(empresa.getNombre());
      if (ObjectUtils.isEmpty(empresa.getPadreId())) {
        data.setTipoIdentificador(TipoIdentificador.builder().id(empresa.getTipoIdentificador().getId()).build());
        data.setNumeroIdentificacion(empresa.getNumeroIdentificacion());
      }
      data.setPadreId(empresa.getPadreId());
      data.setDatosEconomicos(empresa.isDatosEconomicos());

      Empresa empresaUpdated = repository.save(data);
      log.debug("update({}) - end", empresa);
      return empresaUpdated;
    }).orElseThrow(() -> new EmpresaNotFoundException(empresa.getId()));
  }

  /**
   * Obtiene una entidad {@link Empresa} por id.
   * 
   * @param id identificador de la entidad {@link Empresa}.
   * @return la entidad {@link Empresa}.
   */
  public Empresa findById(String id) {
    log.debug("findById({}) - start", id);
    final Empresa returnValue = repository.findById(id).orElseThrow(() -> new EmpresaNotFoundException(id));
    log.debug("findById({}) - end", id);
    return returnValue;
  }

  /**
   * Obtiene todas las entidades {@link Empresa} paginadas y filtradas.
   *
   * @param query  información del filtro.
   * @param paging información de paginación.
   * @return el listado de entidades {@link Empresa} paginadas y filtradas.
   */
  public Page<Empresa> findAll(String query, Pageable paging) {
    log.debug("findAll({}, {}) - start", query, paging);
    Specification<Empresa> specs = SgiRSQLJPASupport.toSpecification(query, EmpresaPredicateResolver.getInstance());
    Page<Empresa> returnValue = repository.findAll(specs, paging);
    log.debug("findAll({}, {}) - end", query, paging);
    return returnValue;
  }

}
