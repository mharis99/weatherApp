
package com.haris.android.weather.domain.exception;


public interface ErrorBundle {
  Exception getException();

  String getErrorMessage();
}
