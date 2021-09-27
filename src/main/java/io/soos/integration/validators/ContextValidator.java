package io.soos.integration.validators;

import io.soos.integration.domain.Context;
import org.apache.commons.lang3.StringUtils;

public class ContextValidator {

    public static boolean validate(Context context) {
        return StringUtils.isNoneEmpty(context.getBaseURI(),
                context.getSourceCodePath(),
                context.getProjectName(),
                context.getClientId(),
                context.getApiKey());
    }

}
