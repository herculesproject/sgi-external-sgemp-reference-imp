package org.crue.hercules.sgi.sgemp.controller;

import org.crue.hercules.sgi.sgemp.model.TipoIdentificador;
import org.crue.hercules.sgi.sgemp.converter.TipoIdentificadorConverter;
import org.crue.hercules.sgi.sgemp.dto.TipoIdentificadorOutput;
import org.crue.hercules.sgi.sgemp.service.TipoIdentificadorService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * TipoIdentificadorController
 */
@RestController
@RequestMapping("/tipos-identificador")
@RequiredArgsConstructor
@Slf4j
public class TipoIdentificadorController {

  private final TipoIdentificadorService service;
  private final TipoIdentificadorConverter converter;

  /**
   * Devuelve una lista de {@link TipoIdentificador} ordenados alfabeticamente.
   * 
   * @return el listado de entidades {@link TipoIdentificador} ordenados.
   */
  @GetMapping()
  public ResponseEntity<Page<TipoIdentificadorOutput>> findAll() {
    log.debug("findAll() - start");
    Page<TipoIdentificadorOutput> page = converter.convert(service.findAllSorted());
    log.debug("findAll() - end");
    return page.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(page, HttpStatus.OK);
  }

}
