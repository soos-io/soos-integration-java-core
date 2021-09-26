package io.soos.integration.converters;

import io.soos.integration.domain.structure.StructureAPIResponseBody;
import io.soos.integration.domain.structure.StructureResponse;

public class StructureConverter extends AbstractConverter<StructureAPIResponseBody, StructureResponse> {

    public StructureConverter(StructureAPIResponseBody source) {
        super(source);
    }

    @Override
    public StructureResponse convert() {
        return new StructureResponse(this.source.getId(),
                this.source.getProjectId(),
                this.source.getId(),
                this.source.getReportUrl(),
                this.source.getEmbedUrl(),
                this.source.getReportStatusUrl());
    }
}
