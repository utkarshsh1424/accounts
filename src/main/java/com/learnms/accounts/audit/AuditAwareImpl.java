package com.learnms.accounts.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Class to auto-write the logged in user to db in base columns in case of any DB changes are configured.
 */
@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware {

    /**
     * Returns the current auditor of the application.
     *
     * @return the current auditor.
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("shautk");
    }
}
