package com.solvd.university.doc;

@FunctionalInterface
public interface IValidate<T> {

    boolean isValid(T t);

}
