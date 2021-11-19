package com.grin.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ClassPathScanner {

    public static Set<Class<?>> getAllClasesInPackage(String packageName) throws IOException, ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace(".", "/");
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();

        while (resources.hasMoreElements()) {
            URL recource = resources.nextElement();
            dirs.add(new File(recource.getFile()));
        }

        Set<Class<?>> classes = new HashSet<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }

        return classes;
    }

    private static Collection<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();

        if (!directory.exists()) {
            return classes;
        }

        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + "." + file.getName().substring(0, file.getName().length() - 6)));
            }

        }

        return classes;
    }
}