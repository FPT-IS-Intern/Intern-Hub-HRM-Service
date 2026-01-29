package com.fis.hrmservice.infra.persistence.adapter;

import com.fis.hrmservice.domain.port.output.IdGeneratorPort;
import com.intern.hub.library.common.utils.Snowflake;
import org.springframework.stereotype.Component;

@Component
public class IdGeneratorAdapter implements IdGeneratorPort {

    private final Snowflake snowflake;

    public IdGeneratorAdapter(Snowflake snowflake) {
        this.snowflake = snowflake;
    }

    @Override
    public Long generateId() {
        return snowflake.next();
    }
}
