package uk.gov.coal.inferis.publicsafety.util.service;

public interface DomainObject<ID_TYPE extends Object>
{
    public ID_TYPE getKey();
}
