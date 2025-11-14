package org.crue.hercules.sgi.sgemp.exceptions;

import org.crue.hercules.sgi.framework.exception.NotFoundException;

/**
 * SgempNotFoundException
 */
public class SgempNotFoundException extends NotFoundException {

  /**
   * Serial version
   */
  private static final long serialVersionUID = 1L;

  public SgempNotFoundException(String message) {
    super(message);
  }

}
