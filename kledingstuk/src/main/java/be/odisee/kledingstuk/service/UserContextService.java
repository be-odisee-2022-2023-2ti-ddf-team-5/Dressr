package be.odisee.kledingstuk.service;

import be.odisee.kledingstuk.domain.Persoon;

public interface UserContextService {

    public Persoon getAuthenticatedPersoon();
}
