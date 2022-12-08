package org.di;

import java.net.URLClassLoader;

public class Application {
    public static ApplicationContext run(Class<?> aClass, URLClassLoader... extraClassLoaders) {
        JavaConfig config = new JavaConfig(aClass, extraClassLoaders);
        ApplicationContext context = new ApplicationContext(config);
        ObjectFactory objectFactory = new ObjectFactory(context);
        context.setFactory(objectFactory);
        return context;
    }
}
