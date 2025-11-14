package org.crue.hercules.sgi.sgemp.converter;

import org.crue.hercules.sgi.sgemp.dto.DatosContactoOutput;
import org.crue.hercules.sgi.sgemp.model.DatosContacto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DatosContactoConverter {

  private final ModelMapper modelMapper;

  public DatosContactoOutput convert(DatosContacto entity) {
    if (entity == null) {
      return null;
    }

    return modelMapper.map(entity, DatosContactoOutput.class);
  }

}
