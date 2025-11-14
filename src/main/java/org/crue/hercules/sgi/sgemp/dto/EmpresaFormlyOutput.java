package org.crue.hercules.sgi.sgemp.dto;

import java.io.Serializable;
import java.util.List;

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
public class EmpresaFormlyOutput implements Serializable {
  private String empresaId;
  private String tipoIdentificadorId;
  private String numeroIdentificacion;
  private String nombre;
  private boolean datosEconomicos;
  private String padreId;
  private String tipoSede;

  // DatosContacto
  private String direccion;
  private String paisRef;
  private String comunidadAutonomaRef;
  private String provinciaRef;
  private String poblacion;
  private String codigoPostal;
  private List<EmailOutput> emails;
  private List<TelefonoOutput> telefonos;
}
