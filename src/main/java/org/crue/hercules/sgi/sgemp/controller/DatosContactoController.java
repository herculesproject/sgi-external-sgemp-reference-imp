package org.crue.hercules.sgi.sgemp.controller;

import org.crue.hercules.sgi.sgemp.model.DatosContacto;
import org.crue.hercules.sgi.sgemp.dto.DatosContactoOutput;
import org.crue.hercules.sgi.sgemp.model.Empresa;
import org.crue.hercules.sgi.sgemp.service.DatosContactoService;
import org.crue.hercules.sgi.sgemp.converter.DatosContactoConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DatosContactoController
 */
@RestController
@RequestMapping(DatosContactoController.REQUEST_MAPPING)
@RequiredArgsConstructor
@Slf4j
public class DatosContactoController {
  public static final String PATH_DELIMITER = "/";
  public static final String REQUEST_MAPPING = PATH_DELIMITER + "datos-contacto";
  public static final String PATH_EMPRESA_ID = PATH_DELIMITER + "empresa/{id}";

  private final DatosContactoService service;
  private final DatosContactoConverter converter;

  /**
   * Devuelve el {@link DatosContacto} con el id {@link Empresa} indicado.
   * 
   * @param id Identificador de {@link Empresa}.
   * @return {@link DatosContacto} correspondiente al id {@link Empresa}.
   */
  @GetMapping(PATH_EMPRESA_ID)
  public ResponseEntity<DatosContactoOutput> findByEmpresaId(@PathVariable String id) {
    log.debug("findByEmpresaId({}) - start", id);
    DatosContactoOutput returnValue = converter.convert(service.findByEmpresaId(id));
    log.debug("findByEmpresaId({}) - end", id);

    return returnValue == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
        : new ResponseEntity<>(returnValue, HttpStatus.OK);
  }

}
