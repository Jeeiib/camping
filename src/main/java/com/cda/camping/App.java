package com.cda.camping;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.cda.camping.config.AppConfig;

/**
 * Classe principale de l'application Spring MVC
 *
 * Cette classe remplace le fichier web.xml traditionnel en configurant
 * le DispatcherServlet de Spring de manière programmatique.
 *
 * Elle hérite de AbstractAnnotationConfigDispatcherServletInitializer qui permet
 * de configurer Spring sans XML grâce aux annotations Java.
 */
public class App extends AbstractAnnotationConfigDispatcherServletInitializer {

    public static void main(String[] args) {
        // Point d'entrée de l'application (non utilisé dans une application web déployée)
    }

    /**
     * Configure les classes de configuration du contexte racine de l'application
     * Le contexte racine contient les beans partagés (services, repositories, etc.)
     *
     * @return un tableau contenant la classe de configuration AppConfig
     */
    @Override
    protected Class <?>[] getRootConfigClasses() {
        return new Class[] {AppConfig.class};
    }

    /**
     * Configure les classes de configuration du contexte du servlet
     * Le contexte du servlet contient les beans spécifiques au web (controllers, etc.)
     *
     * @return un tableau contenant la classe de configuration AppConfig
     */
    @Override
    protected Class <?>[] getServletConfigClasses() {
        return new Class[] {AppConfig.class};
    }

    /**
     * Définit les URL patterns que le DispatcherServlet doit gérer
     * "/" signifie que toutes les requêtes passent par Spring MVC
     *
     * @return un tableau contenant le mapping racine "/"
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
    
