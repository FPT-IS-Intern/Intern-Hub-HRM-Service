package com.fis.hrmservice.domain.model.feedback;

import com.fis.hrmservice.domain.model.common.BaseDomain;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FeedbackTypeModel extends BaseDomain {
    private long id;
    private String code;
    private String name;
    private String description;
}
