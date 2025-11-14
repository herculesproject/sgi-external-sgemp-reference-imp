package org.crue.hercules.sgi.sgemp.service;

import org.crue.hercules.sgi.sgemp.model.DatosContacto;
import org.crue.hercules.sgi.sgemp.model.Empresa;
import org.crue.hercules.sgi.sgemp.model.EmpresaFormlyRequest;
import org.crue.hercules.sgi.sgemp.model.EmpresaFormlyResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmpresaFormlyService {

  private final EmpresaService empresaService;
  private final DatosContactoService datosContactoService;

  /**
   * Obtiene una entidad {@link EmpresaFormlyResponse} por id.
   * 
   * @param id identificador de la entidad {@link Empresa}.
   * @return la entidad {@link EmpresaFormlyResponse}.
   */
  public EmpresaFormlyResponse findById(String id) {
    log.debug("findById({}) - start", id);
    final Empresa empresa = empresaService.findById(id);
    DatosContacto datosContacto = datosContactoService.findByEmpresaId(id);

    EmpresaFormlyResponse returnValue = new EmpresaFormlyResponse(empresa, datosContacto);
    log.debug("findById({} - end", id);
    return returnValue;
  }

  @Transactional
  public String create(EmpresaFormlyRequest empresaFormly) {
    log.debug("create({}) - start", empresaFormly);
    Empresa empresaNew = empresaFormly.getEmpresa();

    Empresa empresaCreated = empresaService.create(empresaNew);

    datosContactoService.create(empresaFormly.getDatosContacto(), empresaCreated.getId());

    log.debug("create({}) - end", empresaFormly);
    return empresaCreated.getId();
  }

  @Transactional
  public void update(EmpresaFormlyRequest empresaFormly, String empresaId) {
    log.debug("update({}, {}) - start", empresaFormly, empresaId);
    empresaFormly.setId(empresaId);
    Empresa empresaUpdated = empresaFormly.getEmpresa();

    empresaService.update(empresaUpdated);
    datosContactoService.update(empresaFormly.getDatosContacto(), empresaId);
    log.debug("update({}, {}) - end", empresaFormly, empresaId);
  }

}
