package com.eligible.model;

import com.eligible.BaseMockedNetwokEligibleTest;
import com.eligible.exception.EligibleException;
import com.eligible.model.enrollmentnpi.*;
import com.eligible.net.EligibleResponse;
import com.eligible.net.RequestMethod;
import com.eligible.net.RequestType;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.verifyNoMoreInteractions;

public class EnrollmentNpiTest extends BaseMockedNetwokEligibleTest {

    private static final String PDF_FILE = "./src/test/resources/com/eligible/EligibleSandboxEnrollmentPDF.pdf";

    @Test
    public void testCreate() throws EligibleException {
        EnrollmentNpi.create(DUMMY_PARAMS);

        verifyPost(EnrollmentNpiResponse.class, "https://gds.eligibleapi.com/v1.5/enrollment_npis", DUMMY_PARAMS);
        verifyNoMoreInteractions(networkMock);
    }

    @Test
    public void testRetrieve() throws EligibleException {
        EnrollmentNpi.retrieve("enrollment_npi_id");

        verifyGet(EnrollmentNpiResponse.class, "https://gds.eligibleapi.com/v1.5/enrollment_npis/enrollment_npi_id");
        verifyNoMoreInteractions(networkMock);
    }

    @Test
    public void testUpdate() throws EligibleException {
        EnrollmentNpi.update("enrollment_npi_id", DUMMY_PARAMS);

        verifyRequest(RequestMethod.PUT, EnrollmentNpiResponse.class,
                "https://gds.eligibleapi.com/v1.5/enrollment_npis/enrollment_npi_id", DUMMY_PARAMS,
                RequestType.NORMAL, null);
        verifyNoMoreInteractions(networkMock);
    }

    @Test
    public void testQuery() throws EligibleException {
        EnrollmentNpi.query(DUMMY_PARAMS);

        verifyGet(EnrollmentNpiQueryResponse.class, "https://gds.eligibleapi.com/v1.5/enrollment_npis", DUMMY_PARAMS);
        verifyNoMoreInteractions(networkMock);
    }

    @Test
    public void testGetReceivedPdf() throws EligibleException {
        EnrollmentNpi.getReceivedPdf("enrollment_npi_id");

        verifyGet(ReceivedPdfResponse.class, "https://gds.eligibleapi.com/v1.5/enrollment_npis/enrollment_npi_id/received_pdf");
        verifyNoMoreInteractions(networkMock);
    }

    @Test
    public void testDownloadReceivedPdf() throws Exception {
        byte[] data = new byte[0];
        stubNetwork(new EligibleResponse(200, data));

        EnrollmentNpi.downloadReceivedPdf("enrollment_npi_id");

        verifyRequest(RequestMethod.GET,
                "https://gds.eligibleapi.com/v1.5/enrollment_npis/enrollment_npi_id/received_pdf/download",
                null, RequestType.NORMAL, null);
        verifyNoMoreInteractions(networkMock);
    }

    @Test
    public void testCreateOriginalSignaturePdf() throws EligibleException {
        EnrollmentNpi.createOriginalSignaturePdf("enrollment_npi_id", PDF_FILE);

        Map<String, Object> params = new HashMap<>();
        params.put("file", new File(PDF_FILE));

        verifyRequest(RequestMethod.POST, OriginalSignaturePdfResponse.class,
                "https://gds.eligibleapi.com/v1.5/enrollment_npis/enrollment_npi_id/original_signature_pdf",
                params, RequestType.MULTIPART, null);
        verifyNoMoreInteractions(networkMock);
    }

    @Test
    public void testGetOriginalSignaturePdf() throws EligibleException {
        EnrollmentNpi.getOriginalSignaturePdf("enrollment_npi_id");

        verifyGet(OriginalSignaturePdfResponse.class, "https://gds.eligibleapi.com/v1.5/enrollment_npis/enrollment_npi_id/original_signature_pdf");
        verifyNoMoreInteractions(networkMock);
    }

    @Test
    public void testUpdateOriginalSignaturePdf() throws EligibleException {
        EnrollmentNpi.updateOriginalSignaturePdf("enrollment_npi_id", PDF_FILE);

        Map<String, Object> params = new HashMap<>();
        params.put("file", new File(PDF_FILE));

        verifyRequest(RequestMethod.PUT, OriginalSignaturePdfResponse.class,
                "https://gds.eligibleapi.com/v1.5/enrollment_npis/enrollment_npi_id/original_signature_pdf",
                params, RequestType.MULTIPART, null);
        verifyNoMoreInteractions(networkMock);
    }

    @Test
    public void testDownloadOriginalSignaturePdf() throws Exception {
        byte[] data = new byte[0];
        stubNetwork(new EligibleResponse(200, data));

        EnrollmentNpi.downloadOriginalSignaturePdf("enrollment_npi_id");

        verifyRequest(RequestMethod.GET,
                "https://gds.eligibleapi.com/v1.5/enrollment_npis/enrollment_npi_id/original_signature_pdf/download",
                null, RequestType.NORMAL, null);
        verifyNoMoreInteractions(networkMock);
    }

    @Test
    public void testDeleteOriginalSignaturePdf() throws EligibleException {
        EnrollmentNpi.deleteOriginalSignaturePdf("enrollment_npi_id");

        verifyRequest(RequestMethod.DELETE, OriginalSignaturePdfDeleteResponse.class,
                "https://gds.eligibleapi.com/v1.5/enrollment_npis/enrollment_npi_id/original_signature_pdf",
                null, RequestType.NORMAL, null);
        verifyNoMoreInteractions(networkMock);
    }

}
