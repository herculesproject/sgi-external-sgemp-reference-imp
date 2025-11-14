package org.crue.hercules.sgi.sgemp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = Empresa.TABLE_NAME)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Empresa extends BaseEntity {

  protected static final String TABLE_NAME = "empresa";

  private static final String ID_COLUMN_NAME = "id";
  private static final String TIPO_IDENTIFICADOR_COLUMN_NAME = "tipo_identificador_id";
  private static final String NUMERO_IDENTIFICACION_COLUMN_NAME = "numero_identificacion";
  private static final String RAZON_SOCIAL_COLUMN_NAME = "razon_social";
  private static final String DATOS_ECONOMICOS_COLUMN_NAME = "datos_economicos";
  private static final String NOMBRE_COLUMN_NAME = "nombre";
  private static final String PADRE_ID_COLUMN_NAME = "padre_id";

  /** Id */
  @Id
  @Column(name = ID_COLUMN_NAME, nullable = false)
  @GeneratedValue(generator = "uuid-hibernate-generator")
  @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
  private String id;

  /** Numero de identificacion */
  @Column(name = NUMERO_IDENTIFICACION_COLUMN_NAME, nullable = false)
  @NotBlank
  private String numeroIdentificacion;

  /** Nombre */
  @Column(name = NOMBRE_COLUMN_NAME, nullable = false)
  @NotBlank
  private String nombre;

  /** Razon social */
  @Column(name = RAZON_SOCIAL_COLUMN_NAME)
  private String razonSocial;

  /** Flag datos economicos */
  @Column(name = DATOS_ECONOMICOS_COLUMN_NAME, nullable = false)
  @NotNull
  private boolean datosEconomicos;

  /** Padre Id */
  @Column(name = PADRE_ID_COLUMN_NAME, nullable = true)
  private String padreId;

  /** Tipo de identificador */
  @ManyToOne
  @JoinColumn(name = TIPO_IDENTIFICADOR_COLUMN_NAME, foreignKey = @ForeignKey(name = "FK_EMPRESA_TIPO_IDENTIFICADOR"))
  private TipoIdentificador tipoIdentificador;

}
