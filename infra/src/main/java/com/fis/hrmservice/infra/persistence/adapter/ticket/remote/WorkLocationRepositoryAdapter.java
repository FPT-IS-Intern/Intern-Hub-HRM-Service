package com.fis.hrmservice.infra.persistence.adapter.ticket.remote;

import lombok.RequiredArgsConstructor;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import com.fis.hrmservice.domain.model.user.WorkLocationModel;
import com.fis.hrmservice.domain.port.output.ticket.remoteticket.WorkLocationRepositoryPort;
import com.fis.hrmservice.infra.mapper.WorkLocationMapper;
import com.fis.hrmservice.infra.persistence.repository.ticket.WorkLocationRepository;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class WorkLocationRepositoryAdapter implements WorkLocationRepositoryPort {

    private final WorkLocationMapper workLocationMapper;

    private final WorkLocationRepository workLocationRepository;

    @Override
    public boolean existByLocationName(String locationName) {
        return workLocationRepository.existsByName(locationName);
    }

    @Override
    public List<String> findAllLocationNames() {
        return workLocationRepository.getAllWorkLocationName();
    }

    @Override
    public WorkLocationModel findByLocationName(String locationName) {
        return workLocationMapper.toModel(workLocationRepository.findByName((locationName)));
    }
}

