package org.crue.hercules.sgi.sgemp.converter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.crue.hercules.sgi.sgemp.dto.EmailOutput;
import org.crue.hercules.sgi.sgemp.util.Constantes;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailsConverter extends AbstractConverter<String, List<EmailOutput>> {

  @Override
  protected List<EmailOutput> convert(String source) {
    if (source == null) {
      return Collections.emptyList();
    }

    AtomicInteger index = new AtomicInteger(0);

    return Arrays.asList(source.split(Constantes.LIST_STRING_SEPARATOR))
        .stream()
        .filter(e -> !e.trim().isEmpty())
        .map(email -> EmailOutput.builder().email(email.trim()).principal(index.getAndIncrement() == 0).build())
        .toList();
  }

}
