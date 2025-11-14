package org.crue.hercules.sgi.sgemp.converter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.crue.hercules.sgi.sgemp.dto.TelefonoOutput;
import org.crue.hercules.sgi.sgemp.util.Constantes;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TelefonosConverter extends AbstractConverter<String, List<TelefonoOutput>> {

  @Override
  protected List<TelefonoOutput> convert(String source) {
    if (source == null) {
      return Collections.emptyList();
    }

    return Arrays.asList(source.split(Constantes.LIST_STRING_SEPARATOR))
        .stream()
        .filter(e -> !e.trim().isEmpty())
        .map(
            telefono -> TelefonoOutput.builder().telefono(telefono.trim()).build())
        .collect(Collectors.toList());
  }

}
