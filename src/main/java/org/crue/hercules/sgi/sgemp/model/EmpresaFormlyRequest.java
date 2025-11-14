package org.crue.hercules.sgi.sgemp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EmpresaFormlyRequest {

  private String id;
  private String tipoIdentificadorId;
  private String numeroIdentificacion;
  private String nombre;
  private String padreId;
  private boolean datosEconomicos;

  // DatosContacto
  private String direccion;
  private String pais;
  private String comunidadAutonoma;
  private String provincia;
  private String poblacion;
  private String codigoPostal;
  private String emails;
  private String telefonos;

  public Empresa getEmpresa() {
    return Empresa.builder()
        .id(id)
        .tipoIdentificador(TipoIdentificador.builder().id(tipoIdentificadorId).build())
        .numeroIdentificacion(numeroIdentificacion)
        .nombre(nombre)
        .razonSocial(nombre)
        .padreId(padreId)
        .datosEconomicos(datosEconomicos)
        .build();
  }

  public DatosContacto getDatosContacto() {
    return DatosContacto.builder()
        .direccion(direccion)
        .ciudad(poblacion)
        .paisRef(pais)
        .codigoPostal(codigoPostal)
        .comunidadAutonomaRef(comunidadAutonoma)
        .provinciaRef(provincia)
        .emails(emails)
        .telefonos(telefonos)
        .build();
  }
}
