package uk.gov.coal.inferis.publicsafety.util;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerHelper
{
    private static Map<String, EntityManager> managers = new HashMap<String, EntityManager>();

    public static EntityManager getEntityManager(String persistenceUnitName)
    {
        if (managers.containsKey(persistenceUnitName) == false)
        {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);

            EntityManager entityManager = entityManagerFactory.createEntityManager();

            managers.put(persistenceUnitName, entityManager);
        }

        return managers.get(persistenceUnitName);
    }
}
