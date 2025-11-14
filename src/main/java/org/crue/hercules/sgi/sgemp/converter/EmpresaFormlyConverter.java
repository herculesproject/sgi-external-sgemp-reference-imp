package org.crue.hercules.sgi.sgemp.converter;

import javax.annotation.PostConstruct;

import org.crue.hercules.sgi.sgemp.dto.EmpresaFormlyInput;
import org.crue.hercules.sgi.sgemp.dto.EmpresaFormlyOutput;
import org.crue.hercules.sgi.sgemp.model.EmpresaFormlyRequest;
import org.crue.hercules.sgi.sgemp.model.EmpresaFormlyResponse;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmpresaFormlyConverter {

  private final ModelMapper modelMapper;
  private final EmailsConverter emailsConverter;
  private final TelefonosConverter telefonosConverter;
  private final ListStringToStringConverter listStringToStringConverter;
  private final TipoSedeConverter tipoSedeConverter;

  @PostConstruct
  public void mapperConfig() {

    Condition<String, String> stringNotEmpty = context -> context.getSource() != null
        && !"".equals(context.getSource().trim());

    modelMapper.typeMap(EmpresaFormlyResponse.class, EmpresaFormlyOutput.class)
        .addMappings(
            mapper -> mapper.using(emailsConverter).map(EmpresaFormlyResponse::getEmails,
                EmpresaFormlyOutput::setEmails))
        .addMappings(
            mapper -> mapper.using(telefonosConverter).map(EmpresaFormlyResponse::getTelefonos,
                EmpresaFormlyOutput::setTelefonos))
        .addMappings(
            mapper -> mapper.using(tipoSedeConverter).map(EmpresaFormlyResponse::getTipoSede,
                EmpresaFormlyOutput::setTipoSede));

    modelMapper.typeMap(EmpresaFormlyInput.class, EmpresaFormlyRequest.class)
        .addMappings(
            mapper -> mapper.when(stringNotEmpty).map(EmpresaFormlyInput::getEmpresaId,
                EmpresaFormlyRequest::setId))
        .addMappings(mapper -> mapper.using(listStringToStringConverter)
            .map(EmpresaFormlyInput::getEmails, EmpresaFormlyRequest::setEmails))
        .addMappings(mapper -> mapper.using(listStringToStringConverter)
            .map(EmpresaFormlyInput::getTelefonos, EmpresaFormlyRequest::setTelefonos));

  }

  public EmpresaFormlyOutput convert(EmpresaFormlyResponse entity) {
    return entity == null ? null : modelMapper.map(entity, EmpresaFormlyOutput.class);
  }

  public EmpresaFormlyRequest convert(EmpresaFormlyInput input) {
    return input == null ? null : modelMapper.map(input, EmpresaFormlyRequest.class);
  }

}
