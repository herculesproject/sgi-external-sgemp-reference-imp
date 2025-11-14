package org.crue.hercules.sgi.sgemp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = DatosContacto.TABLE_NAME)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DatosContacto extends BaseEntity {
  protected static final String TABLE_NAME = "datos_contacto";

  private static final String EMPRESA_ID_COLUMN_NAME = "empresa_id";
  private static final String DIRECCION_COLUMN_NAME = "direccion";
  private static final String PAIS_REF_COLUMN_NAME = "pais_ref";
  private static final String COM_AUTONOMA_REF_COLUMN_NAME = "comunidad_autonoma_ref";
  private static final String PROVINCIA_REF_COLUMN_NAME = "provincia_ref";
  private static final String CIUDAD_COLUMN_NAME = "ciudad";
  private static final String CODIGO_POSTAL_COLUMN_NAME = "codigo_postal";
  private static final String EMAILS_COLUMN_NAME = "emails";
  private static final String TELEFONOS_COLUMN_NAME = "telefonos";

  /**
   * Serial version
   */
  private static final long serialVersionUID = 1L;

  /** Empresa Id */
  @Id
  @Column(name = EMPRESA_ID_COLUMN_NAME, nullable = false)
  @NotBlank
  private String empresaId;

  /** Direccion */
  @Column(name = DIRECCION_COLUMN_NAME)
  private String direccion;

  @Column(name = PAIS_REF_COLUMN_NAME)
  private String paisRef;

  @Column(name = COM_AUTONOMA_REF_COLUMN_NAME)
  private String comunidadAutonomaRef;

  @Column(name = PROVINCIA_REF_COLUMN_NAME)
  private String provinciaRef;

  @Column(name = CIUDAD_COLUMN_NAME)
  private String ciudad;

  @Column(name = CODIGO_POSTAL_COLUMN_NAME)
  private String codigoPostal;

  @Column(name = EMAILS_COLUMN_NAME)
  private String emails;

  @Column(name = TELEFONOS_COLUMN_NAME)
  private String telefonos;

}
