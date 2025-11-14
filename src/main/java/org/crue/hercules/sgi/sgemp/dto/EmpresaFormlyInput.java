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
public class EmpresaFormlyInput implements Serializable {

  // Datos generales
  private String empresaId;
  private String nombre;
  private boolean datosEconomicos;
  private String padreId;
  private String tipoIdentificadorId;
  private String numeroIdentificacion;

  // Datos de contacto
  private String paisRef;
  private String comunidadAutonomaRef;
  private String provinciaRef;
  private String poblacion;
  private String codigoPostal;
  private String direccion;
  private List<String> emails;
  private List<String> telefonos;
}
