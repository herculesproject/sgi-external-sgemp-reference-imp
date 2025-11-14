package org.crue.hercules.sgi.sgemp.controller.publico;

import org.crue.hercules.sgi.sgemp.converter.EmpresaConverter;
import org.crue.hercules.sgi.sgemp.dto.EmpresaOutput;
import org.crue.hercules.sgi.sgemp.model.Empresa;
import org.crue.hercules.sgi.sgemp.service.EmpresaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * EmpresaPublicController
 */
@RestController
@RequestMapping(EmpresaPublicController.REQUEST_MAPPING)
@Slf4j
@RequiredArgsConstructor
public class EmpresaPublicController {

  public static final String PATH_DELIMITER = "/";
  public static final String PATH_PUBLIC = PATH_DELIMITER + "public";
  public static final String REQUEST_MAPPING = PATH_PUBLIC + PATH_DELIMITER + "empresas";

  public static final String PATH_ID = PATH_DELIMITER + "{id}";

  private final EmpresaService service;
  private final EmpresaConverter converter;

  /**
   * Devuelve la {@link Empresa} con el id indicado.
   * 
   * @param id Identificador de {@link Empresa}.
   * @return {@link Empresa} correspondiente al id
   */
  @GetMapping(PATH_ID)
  public EmpresaOutput findById(@PathVariable String id) {
    log.debug("findById({}) - start", id);
    EmpresaOutput returnValue = converter.convert(service.findById(id));
    log.debug("findById({}) - end", id);
    return returnValue;
  }

}
