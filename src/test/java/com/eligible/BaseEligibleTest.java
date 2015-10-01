package com.eligible;

import com.eligible.util.TestUtil;
import lombok.Getter;
import org.junit.BeforeClass;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

/** Eligible Test base. */
@Getter
public class BaseEligibleTest {

    /** DUMMY PARAMETER Object for API calls. */
    protected static final Map<String, Object> DUMMAY_PARAMS = Collections.emptyMap();

    /**
     * Setup Eligible global parameters.
     */
    @BeforeClass
    public static void setUp() {
        Eligible.apiKey = "foobar";
        Eligible.isTest = true;
    }

    protected String resource(String path) throws IOException {
        return TestUtil.resource(path, getClass());
    }
}
