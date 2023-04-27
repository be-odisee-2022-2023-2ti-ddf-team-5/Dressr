package be.odisee.brainstorm2023wa3sb.service;

import be.odisee.brainstorm2023wa3sb.domain.Persoon;

public interface UserContextService {

    public Persoon getAuthenticatedPersoon();
}
