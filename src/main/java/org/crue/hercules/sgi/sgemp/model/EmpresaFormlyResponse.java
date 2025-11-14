package org.crue.hercules.sgi.sgemp.model;

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
public class EmpresaFormlyResponse {

  public enum TipoSede {
    /** Entidad */
    ENTIDAD("entidad"),
    /** SubEntidad */
    SUBENTIDAD("subentidad");

    private String code;

    private TipoSede(String code) {
      this.code = code;
    }

    public String getCode() {
      return code;
    }

    public static TipoSede fromCode(String code) {
      for (TipoSede tipoSede : TipoSede.values()) {
        if (tipoSede.code.equals(code)) {
          return tipoSede;
        }
      }
      return null;
    }
  }

  private String id;
  private String tipoIdentificadorId;
  private String numeroIdentificacion;
  private String nombre;
  private String padreId;
  private TipoSede tipoSede;
  private boolean datosEconomicos;

  // DatosContacto
  private String direccion;
  private String paisRef;
  private String comunidadAutonomaRef;
  private String provinciaRef;
  private String poblacion;
  private String codigoPostal;
  private String tipoVia;
  private String emails;
  private String telefonos;

  public EmpresaFormlyResponse(Empresa empresa, DatosContacto datosContacto) {
    this.setDatosEmpresa(empresa);
    this.setDatosContacto(datosContacto);
  }

  private void setDatosEmpresa(Empresa empresa) {
    if (empresa == null) {
      return;
    }

    this.id = empresa.getId();
    this.tipoIdentificadorId = empresa.getTipoIdentificador().getId();
    this.numeroIdentificacion = empresa.getNumeroIdentificacion();
    this.nombre = empresa.getNombre();
    this.padreId = empresa.getPadreId();
    this.tipoSede = empresa.getPadreId() == null ? TipoSede.ENTIDAD : TipoSede.SUBENTIDAD;
    this.datosEconomicos = empresa.isDatosEconomicos();
  }

  private void setDatosContacto(DatosContacto datosContacto) {
    if (datosContacto == null) {
      return;
    }

    this.paisRef = datosContacto.getPaisRef();
    this.codigoPostal = datosContacto.getCodigoPostal();
    this.direccion = datosContacto.getDireccion();
    this.poblacion = datosContacto.getCiudad();
    this.comunidadAutonomaRef = datosContacto.getComunidadAutonomaRef();
    this.provinciaRef = datosContacto.getProvinciaRef();
    this.emails = datosContacto.getEmails();
    this.telefonos = datosContacto.getTelefonos();
  }

}
