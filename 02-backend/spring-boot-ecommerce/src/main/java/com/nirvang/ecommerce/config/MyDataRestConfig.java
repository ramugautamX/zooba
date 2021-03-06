package com.nirvang.ecommerce.config;

import com.nirvang.ecommerce.entity.Country;
import com.nirvang.ecommerce.entity.Product;
import com.nirvang.ecommerce.entity.ProductCategory;

import com.nirvang.ecommerce.entity.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
//import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};

        //disable HTTP methods for Product: PUT, POST, and DELETE
        disableHttpMethods(Product.class, config, theUnsupportedActions);

        //disable HTTP Methods for ProductCategory: PUT, POST and DELETE
        disableHttpMethods(ProductCategory.class, config, theUnsupportedActions);

        //disable HTTP methods for Country: PUT, POST, and DELETE
        disableHttpMethods(Country.class, config, theUnsupportedActions);

        //disable HTTP Methods for State: PUT, POST and DELETE
        disableHttpMethods(State.class, config, theUnsupportedActions);

        //call an internal helper method to expose id's
            exposeIds(config);

    }

    private void disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)));
    }

    private void exposeIds( RepositoryRestConfiguration config){
        //expose entity id's

        //get collection of all entity classes from the entity manager
        Set<EntityType<?>> entities= entityManager.getMetamodel().getEntities();

        //create an array list of entity types
        List<Class> entityClasses = new ArrayList<>();

        //get the entity types for the entities
        for (EntityType tempEntityType: entities){
            entityClasses.add(tempEntityType.getJavaType());
        }

        //expose the entity id's for the array of entity/domain types
        Class[] domainTypes= entityClasses.toArray(new Class[0]);

        config.exposeIdsFor(domainTypes);

    }
}
