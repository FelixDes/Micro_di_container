package org.di;

import org.reflections.Reflections;

import java.net.URLClassLoader;
import java.util.Set;

public class JavaConfig implements Config {
    private String currentPackage;
    private Reflections scanner;

    public JavaConfig(String currentPackage, URLClassLoader[] extraClassLoaders) {
        this.currentPackage = currentPackage;
        this.scanner = new Reflections(currentPackage, extraClassLoaders);
    }

    public <T> Class<? extends T> getImplClass(Class<T> anInterface) {
        Set<Class<? extends T>> classes = scanner.getSubTypesOf(anInterface);
        if (classes.size() == 0) {
            throw new RuntimeException(anInterface + " has 0 implementations");
        }
        return classes.iterator().next();
    }

    @Override
    public Reflections getScanner() {
        return null;
    }
}
