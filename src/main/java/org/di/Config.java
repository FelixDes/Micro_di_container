package org.di;

import org.reflections.Reflections;

import java.util.List;

public interface Config {
    <T> List<Class<? extends T>> getImplComponent(Class<T> ifc);

    Reflections getScanner();
}