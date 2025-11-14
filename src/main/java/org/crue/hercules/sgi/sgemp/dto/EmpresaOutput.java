package org.crue.hercules.sgi.sgemp.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpresaOutput implements Serializable {
  private String id;
  private TipoIdentificadorOutput tipoIdentificador;
  private String numeroIdentificacion;
  private String nombre;
  private String razonSocial;
  private String padreId;
  private boolean datosEconomicos;
}
