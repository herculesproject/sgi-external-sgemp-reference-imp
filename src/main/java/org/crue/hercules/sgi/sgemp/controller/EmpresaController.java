package org.crue.hercules.sgi.sgemp.controller;

import javax.validation.Valid;

import org.crue.hercules.sgi.framework.web.bind.annotation.RequestPageable;
import org.crue.hercules.sgi.sgemp.converter.EmpresaConverter;
import org.crue.hercules.sgi.sgemp.converter.EmpresaFormlyConverter;
import org.crue.hercules.sgi.sgemp.dto.EmpresaFormlyInput;
import org.crue.hercules.sgi.sgemp.dto.EmpresaFormlyOutput;
import org.crue.hercules.sgi.sgemp.dto.EmpresaOutput;
import org.crue.hercules.sgi.sgemp.model.Empresa;
import org.crue.hercules.sgi.sgemp.service.EmpresaFormlyService;
import org.crue.hercules.sgi.sgemp.service.EmpresaService;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * EmpresaController
 */
@RestController
@RequestMapping(EmpresaController.REQUEST_MAPPING)
@Slf4j
@RequiredArgsConstructor
public class EmpresaController {

  public static final String PATH_DELIMITER = "/";
  public static final String REQUEST_MAPPING = PATH_DELIMITER + "empresas";

  public static final String PATH_ID = PATH_DELIMITER + "{id}";
  public static final String PATH_FORMLY = PATH_DELIMITER + "formly";
  public static final String PATH_FORMLY_ID = PATH_FORMLY + PATH_ID;

  private final EmpresaService service;
  private final EmpresaFormlyService empresaFormlyService;
  private final EmpresaConverter converter;
  private final EmpresaFormlyConverter empresaFormlyConverter;

  /**
   * Devuelve una lista paginada y filtrada de {@link Empresa}.
   * 
   * @param query  filtro de búsqueda.
   * @param paging {@link Pageable}.
   * @return el listado de entidades {@link Empresa} paginadas y filtradas.
   */
  @GetMapping()
  public ResponseEntity<Page<EmpresaOutput>> findAll(@RequestParam(name = "q", required = false) String query,
      @RequestPageable(sort = "s") Pageable paging) {
    log.debug("findAll({}, {}) - start", query, paging);
    Page<EmpresaOutput> page = converter.convert(service.findAll(query, paging));
    log.debug("findAll({}, {}) - end", query, paging);
    return page.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(page, HttpStatus.OK);
  }

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

  /**
   * Crea una nueva {@link Empresa}
   * 
   * @param empresa la nueva {@link Empresa}.
   * @return {@link Empresa} creada.
   */
  @PostMapping(PATH_FORMLY)
  public ResponseEntity<String> createEmpresaFormly(@Valid @RequestBody EmpresaFormlyInput empresa) {
    log.debug("createEmpresaFormly({}) - start", empresa);
    String empresaId = empresaFormlyService.create(empresaFormlyConverter.convert(empresa));
    log.debug("createEmpresaFormly({}) - end", empresa);
    return new ResponseEntity<>(JSONObject.quote(empresaId), HttpStatus.OK);
  }

  /**
   * Actualiza una {@link Empresa}
   * 
   * @param empresa la {@link Empresa} actualizada.
   * @param id      identificador de la {@link Empresa}.
   */
  @PutMapping(PATH_FORMLY_ID)
  public ResponseEntity<Void> updateEmpresaFormly(@Valid @RequestBody EmpresaFormlyInput empresa,
      @PathVariable String id) {
    log.debug("updateEmpresaFormly({}, {}) - start", empresa, id);
    empresaFormlyService.update(empresaFormlyConverter.convert(empresa), id);
    log.debug("updateEmpresaFormly({}, {}) - end", empresa, id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Devuelve la {@link Empresa} con el id indicado.
   * 
   * @param id Identificador de {@link Empresa}.
   * @return {@link Empresa} correspondiente al id
   */
  @GetMapping(PATH_FORMLY_ID)
  public EmpresaFormlyOutput findEmpresaFormlyById(@PathVariable String id) {
    log.debug("findEmpresaFormlyById({}) - start", id);
    EmpresaFormlyOutput returnValue = empresaFormlyConverter.convert(empresaFormlyService.findById(id));
    log.debug("findEmpresaFormlyById({}) - end", id);
    return returnValue;
  }

}
