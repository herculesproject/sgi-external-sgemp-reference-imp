package org.crue.hercules.sgi.sgemp.converter;

import org.crue.hercules.sgi.sgemp.dto.EmpresaOutput;
import org.crue.hercules.sgi.sgemp.model.Empresa;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmpresaConverter {

  private final ModelMapper modelMapper;

  public EmpresaOutput convert(Empresa entity) {
    if (entity == null) {
      return null;
    }

    return modelMapper.map(entity, EmpresaOutput.class);
  }

  public Page<EmpresaOutput> convert(Page<Empresa> page) {
    return page.map(this::convert);
  }

}
