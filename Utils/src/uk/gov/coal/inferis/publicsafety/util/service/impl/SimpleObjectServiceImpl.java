package uk.gov.coal.inferis.publicsafety.util.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;


import uk.gov.coal.inferis.publicsafety.util.EntityManagerHelper;
import uk.gov.coal.inferis.publicsafety.util.service.*;

public abstract class SimpleObjectServiceImpl<OBJECT_TYPE extends DomainObject<ID_TYPE>, ID_TYPE extends Object> implements
    SimpleObjectService<OBJECT_TYPE, ID_TYPE>
{
    private String persistenceUnitName;

    private EntityManager entityManager;

    private Class<OBJECT_TYPE> entityClass;

    public String getPersistenceUnitName()
    {
        return persistenceUnitName;
    }

    public void setPersistenceUnitName(String persistenceUnitName)
    {
        this.persistenceUnitName = persistenceUnitName;
    }

    @SuppressWarnings("unchecked")
    public void afterPropertiesSet() throws Exception
    {
        if ((persistenceUnitName == null) || (persistenceUnitName.length() == 0))
        {
            throw new IllegalStateException("persistenceUnitName cannot be null or empty.");
        }

        entityManager = EntityManagerHelper.getEntityManager(persistenceUnitName);

        this.entityClass =
            (Class<OBJECT_TYPE>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public OBJECT_TYPE findById(ID_TYPE id)
    {
        return (OBJECT_TYPE)entityManager.find(entityClass, id);
    }

    @SuppressWarnings("unchecked")
    public List<OBJECT_TYPE> findAll()
    {
        String className = entityClass.getSimpleName();

        String queryString =
            "SELECT " + className.toLowerCase() + " FROM " + className + " AS " + className.toLowerCase();

        Query query = entityManager.createQuery(queryString);

        return query.getResultList();
    }

    public OBJECT_TYPE create(OBJECT_TYPE object)
    {
        EntityTransaction transaction = null;

        try
        {
            transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.persist(object);

            transaction.commit();

            return object;
        }
        catch (RuntimeException e)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }
            throw e;
        }
    }
}
