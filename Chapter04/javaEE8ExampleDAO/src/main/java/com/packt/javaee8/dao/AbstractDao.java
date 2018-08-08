package com.packt.javaee8.dao;


import com.packt.javaee8.entity.Entity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractDao <T extends Entity>{

    @PersistenceContext
    protected EntityManager em;


    protected Class<T> getType() {
        ParameterizedType genericType = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<T>) genericType.getActualTypeArguments()[0];
    }


    public Optional<T> findById ( T entity ){

        return Optional.ofNullable( em.find( (Class<T>) entity.getClass(), entity.getId() ) );

    }

    public Optional<T> persist (T entity ){

        em.persist( entity );
        return Optional.of( entity );

    }

    public Optional<T> update ( T entity ){

        return Optional.ofNullable( em.merge( entity ) );

    }

    public void delete ( T entity ){
        em.remove( entity );
    }

    protected List<T> listWithNamedQuery(String namedQuery, Map<String, Object> parameters){


        TypedQuery<T> query = em.createNamedQuery( namedQuery, getType() );
        parameters.keySet().stream().forEach( key-> query.setParameter( key, parameters.get( key ) ) );
        return query.getResultList();

    }

    protected Optional<T> findWithNamedQuery(String namedQuery, Map<String, Object> parameters){

        TypedQuery<T> query = em.createNamedQuery( namedQuery, getType() );
        parameters.keySet().stream().forEach( key-> query.setParameter( key, parameters.get( key ) ) );
        return Optional.ofNullable(query.getSingleResult());

    }




}
