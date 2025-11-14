package org.crue.hercules.sgi.sgemp.exceptions;

/**
 * EmpresaNotFoundException
 */
public class EmpresaNotFoundException extends SgempNotFoundException {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  public EmpresaNotFoundException(String empresaId) {
    super("Empresa " + empresaId + " does not exist.");
  }

}
