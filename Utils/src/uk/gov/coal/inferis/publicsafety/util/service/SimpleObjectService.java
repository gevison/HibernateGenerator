package uk.gov.coal.inferis.publicsafety.util.service;

import java.util.List;

public interface SimpleObjectService<OBJECT_TYPE extends DomainObject<ID_TYPE>, ID_TYPE extends Object>
{
    OBJECT_TYPE findById(ID_TYPE id);

    List<OBJECT_TYPE> findAll();

    OBJECT_TYPE create(OBJECT_TYPE object);
}
