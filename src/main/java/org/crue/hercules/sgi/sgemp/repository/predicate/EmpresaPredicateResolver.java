package org.crue.hercules.sgi.sgemp.repository.predicate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.crue.hercules.sgi.framework.rsql.SgiRSQLPredicateResolver;
import org.crue.hercules.sgi.sgemp.model.DatosContacto;
import org.crue.hercules.sgi.sgemp.model.DatosContacto_;
import org.crue.hercules.sgi.sgemp.model.Empresa;
import org.crue.hercules.sgi.sgemp.util.PredicateResolverUtil;

import cz.jirutka.rsql.parser.ast.ComparisonNode;
import io.github.perplexhub.rsql.RSQLOperators;

public class EmpresaPredicateResolver implements SgiRSQLPredicateResolver<Empresa> {

  public static final String SPLIT_DELIMITER = ",";

  public enum Property {
    /* Pais */
    PAIS_ID("paisId");

    private String code;

    private Property(String code) {
      this.code = code;
    }

    public String getCode() {
      return code;
    }

    public static Property fromCode(String code) {
      for (Property property : Property.values()) {
        if (property.code.equals(code)) {
          return property;
        }
      }
      return null;
    }
  }

  private EmpresaPredicateResolver() {
  }

  public static EmpresaPredicateResolver getInstance() {
    return new EmpresaPredicateResolver();
  }

  @Override
  public boolean isManaged(ComparisonNode node) {
    Property property = Property.fromCode(node.getSelector());
    return property != null;
  }

  @Override
  public Predicate toPredicate(ComparisonNode node, Root<Empresa> root, CriteriaQuery<?> query,
      CriteriaBuilder criteriaBuilder) {
    Property property = Property.fromCode(node.getSelector());
    if (property == null) {
      return null;
    }

    switch (property) {
      case PAIS_ID:
        return buildByPaisId(node, root, query, criteriaBuilder);
      default:
        return null;
    }
  }

  private Predicate buildByPaisId(ComparisonNode node, Root<Empresa> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
    PredicateResolverUtil.validateOperatorIsSupported(node, RSQLOperators.EQUAL);
    PredicateResolverUtil.validateOperatorArgumentNumber(node, 1);

    String paisId = node.getArguments().get(0);

    Subquery<Integer> subquery = query.subquery(Integer.class);
    Root<DatosContacto> datosContactoRoot = subquery.from(DatosContacto.class);

    subquery.select(cb.literal(1))
        .where(
            cb.and(
                cb.equal(datosContactoRoot.get(DatosContacto_.empresaId), root),
                cb.equal(datosContactoRoot.get(DatosContacto_.paisRef), paisId)));

    return cb.exists(subquery);
  }

}
