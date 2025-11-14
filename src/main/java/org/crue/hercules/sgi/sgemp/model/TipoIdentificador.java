package org.crue.hercules.sgi.sgemp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = TipoIdentificador.TABLE_NAME, uniqueConstraints = {
    @UniqueConstraint(columnNames = { "nombre" }, name = "UK_TIPOIDENTIFICADOR_NOMBRE") })
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoIdentificador extends BaseEntity {

  protected static final String TABLE_NAME = "tipo_identificador";

  private static final String ID_COLUMN_NAME = "id";
  private static final String NOMBRE_COLUMN_NAME = "nombre";

  /** Id */
  @Id
  @Column(name = ID_COLUMN_NAME, nullable = false)
  @NotBlank
  private String id;

  /** Nombre */
  @Column(name = NOMBRE_COLUMN_NAME)
  @NotBlank
  private String nombre;

}
