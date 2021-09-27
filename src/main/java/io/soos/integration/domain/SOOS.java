package io.soos.integration.domain;

import io.soos.integration.domain.manifest.Manifest;
import io.soos.integration.domain.manifest.ManifestTypesResponse;
import io.soos.integration.domain.structure.Structure;
import io.soos.integration.domain.structure.StructureResponse;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

public class SOOS {
    protected Context context;
    protected Script script;

    public SOOS() throws Exception {
        this.context = new Context();
        this.script = new Script();

        boolean load = this.context.load();

        if(!load) {
            throw new Exception("Could not find required Environment/Script Variables.");
        }
    }

    public ArrayList<LinkedHashMap<String, Object>> loadManifestTypes() throws Exception {
        return Manifest.getManifestTypes(this.context);
    }

    public StructureResponse getStructure() throws Exception {
        return Structure.execute(this.context);
    }
}
