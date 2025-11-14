package org.crue.hercules.sgi.sgemp.converter;

import org.crue.hercules.sgi.sgemp.model.EmpresaFormlyResponse.TipoSede;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TipoSedeConverter extends AbstractConverter<TipoSede, String> {

  @Override
  protected String convert(TipoSede source) {
    if (source == null) {
      return "";
    }
    return source.getCode();
  }

}
