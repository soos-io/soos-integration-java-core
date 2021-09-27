package io.soos.integration;

import io.soos.integration.domain.SOOS;
import io.soos.integration.domain.manifest.ManifestTypesResponse;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class SOOSApplication {
    public static void main(String[] args) {
        try {
            SOOS soos = new SOOS();

            ArrayList<LinkedHashMap<String, Object>> response = soos.loadManifestTypes();

            Object structureResponse = soos.getStructure();

            System.out.println(response.toString());
            System.out.println(structureResponse.toString());

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
