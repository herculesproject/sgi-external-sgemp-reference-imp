package org.crue.hercules.sgi.sgemp.converter;

import org.crue.hercules.sgi.sgemp.dto.TipoIdentificadorOutput;
import org.crue.hercules.sgi.sgemp.model.TipoIdentificador;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TipoIdentificadorConverter extends AbstractConverter<TipoIdentificador, TipoIdentificadorOutput> {

  private final ModelMapper modelMapper;

  public TipoIdentificadorOutput convert(TipoIdentificador entity) {
    if (entity == null) {
      return null;
    }

    return modelMapper.map(entity, TipoIdentificadorOutput.class);
  }

  public Page<TipoIdentificadorOutput> convert(Page<TipoIdentificador> page) {
    return page.map(this::convert);
  }

}
