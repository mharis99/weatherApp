
package com.haris.android.weather.data.exception;

import com.haris.android.weather.domain.exception.ErrorBundle;


class RepositoryErrorBundle implements ErrorBundle {

  private final Exception exception;

  RepositoryErrorBundle(Exception exception) {
    this.exception = exception;
  }

  @Override
  public Exception getException() {
    return exception;
  }

  @Override
  public String getErrorMessage() {
    String message = "";
    if (this.exception != null) {
      message = this.exception.getMessage();
    }
    return message;
  }
}
