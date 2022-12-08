package org.di;

import org.di.annotations.Component;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class JavaConfig implements Config {
    private final Reflections scanner;

    public JavaConfig(Class<?> aClass, URLClassLoader... extraClassLoaders) {
//        this.scanner = new Reflections(currentPackage, extraClassLoader);
        this.scanner = new Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper.forClass(aClass)).addClassLoaders(extraClassLoaders));
//        this.scanner = new Reflections(Main.class);
    }

    public <T> List<Class<? extends T>> getImplComponent(Class<T> type) {
        if (type.isInterface()) {
//            System.out.println(type);
//            System.out.println(scanner.getSubTypesOf(type.getClass()));
            List<Class<? extends T>> classes = scanner.getSubTypesOf(type).stream().filter(aClass -> aClass.isAnnotationPresent(Component.class)).collect(Collectors.toList());
            if (classes.size() == 0) {
                throw new RuntimeException("Interface: " + type + " has 0 implementations");
            }
            return classes;
        } else {
            if (type.isAnnotationPresent(Component.class)) {
                return Collections.singletonList(type);
            }
        }
        return new ArrayList<>();
    }

    @Override
    public Reflections getScanner() {
        return scanner;
    }
}
