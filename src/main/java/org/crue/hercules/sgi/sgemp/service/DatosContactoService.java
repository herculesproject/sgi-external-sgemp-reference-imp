package org.crue.hercules.sgi.sgemp.service;

import org.crue.hercules.sgi.sgemp.exceptions.EmpresaNotFoundException;
import org.crue.hercules.sgi.sgemp.model.DatosContacto;
import org.crue.hercules.sgi.sgemp.model.Empresa;
import org.crue.hercules.sgi.sgemp.repository.DatosContactoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(readOnly = true)
public class DatosContactoService {

  private final DatosContactoRepository repository;

  public DatosContactoService(DatosContactoRepository datosContactoRepository) {
    this.repository = datosContactoRepository;
  }

  /**
   * Crea los datos de contacto de una empresa
   * 
   * @param empresaId     Identificador de la empresa
   * @param datosContacto Datos de contacto de la empresa
   * @return los datos de contacto
   */
  @Transactional
  public DatosContacto create(DatosContacto datosContacto, String empresaId) {
    log.debug("create({}, {}) - start", datosContacto, empresaId);
    datosContacto.setEmpresaId(empresaId);
    DatosContacto datosContactoCreated = repository.save(datosContacto);
    log.debug("create({}, {}) - end", datosContacto, empresaId);
    return datosContactoCreated;
  }

  /**
   * Actualiza los {@link DatosContacto} de la {@link Empresa}
   * 
   * @param datosContacto {@link DatosContacto} de la empresa actualizada
   * @param empresaId     Identificador de la {@link Empresa}
   * @return los {@link DatosContacto} de la {@link Empresa} actualizada
   */
  @Transactional
  public DatosContacto update(DatosContacto datosContacto, String empresaId) {
    log.debug("update({}, {}) - start", datosContacto, empresaId);

    return repository.findByEmpresaId(empresaId).map(data -> {
      data.setCiudad(datosContacto.getCiudad());
      data.setCodigoPostal(datosContacto.getCodigoPostal());
      data.setComunidadAutonomaRef(datosContacto.getComunidadAutonomaRef());
      data.setDireccion(datosContacto.getDireccion());
      data.setEmails(datosContacto.getEmails());
      data.setPaisRef(datosContacto.getPaisRef());
      data.setProvinciaRef(datosContacto.getProvinciaRef());
      data.setTelefonos(datosContacto.getTelefonos());

      DatosContacto datosContactoUpdated = repository.save(data);

      log.debug("update({}, {}) - end", datosContacto, empresaId);
      return datosContactoUpdated;
    }).orElseThrow(() -> new EmpresaNotFoundException(empresaId));
  }

  /**
   * Obtiene una entidad {@link DatosContacto} con el id {@link Empresa} indicado.
   * 
   * @param empresaId identificador de la entidad {@link Empresa}.
   * @return la entidad {@link DatosContacto}.
   */
  public DatosContacto findByEmpresaId(String empresaId) {
    log.debug("findByEmpresaId({}) - start", empresaId);
    final DatosContacto returnValue = repository.findByEmpresaId(empresaId).orElse(null);
    log.debug("findByEmpresaId({}) - end", empresaId);
    return returnValue;
  }

}
