package com.vaadin.addon.frp;

import java.util.function.Function;

/**
 * Created by svenruppert on 25.04.17.
 */
@FunctionalInterface
public interface CheckedFunction<T, R> extends Function<T, Result<R>> {
  @Override
  default Result<R> apply(T t) {
    try {
      return Result.success(applyWithException(t));
    } catch (Exception e) {
      return Result.failure(e.getMessage());
    }
  }

  R applyWithException(T t) throws Exception;

}