package com.eligible.model;

import com.eligible.exception.APIConnectionException;
import com.eligible.exception.APIException;
import com.eligible.exception.AuthenticationException;
import com.eligible.exception.InvalidRequestException;
import com.eligible.model.enrollmentnpi.*;
import com.eligible.net.APIResource;
import com.eligible.net.EligibleResponse;
import com.eligible.net.RequestMethod;
import com.eligible.net.RequestOptions;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Getter
@EqualsAndHashCode(callSuper = false)
public class EnrollmentNpi extends APIResource {
    Integer id;
    String address;
    String city;
    String zip;
    String effectiveDate;
    String facilityName;
    String medicaidId;
    String npi;
    String providerName;
    String ptan;
    String state;
    String status;
    String taxId;
    String createdAt;
    String updatedAt;
    OriginalSignaturePdf originalSignaturePdf;
    ReceivedPdf receivedPdf;
    AuthorizedSigner authorizedSigner;
    com.eligible.model.enrollmentnpi.Payer payer;
    List<String> rejectReasons;

    public static EnrollmentNpi create(Map<String, Object> params)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return create(params, null);
    }

    public static EnrollmentNpiResponse retrieve(String enrollmentNpiId)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return retrieve(enrollmentNpiId, null);
    }

    public static EnrollmentNpiResponse update(String enrollmentNpiId, Map<String, Object> params)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return update(enrollmentNpiId, params, null);
    }

    public static EnrollmentNpi query(Map<String, Object> params)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return query(params, null);
    }

    public static ReceivedPdfResponse getReceivedPdf(String enrollmentNpiId)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return getReceivedPdf(enrollmentNpiId, null);
    }

    public static String downloadReceivedPdf(String enrollmentNpiId)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return downloadReceivedPdf(enrollmentNpiId, (RequestOptions) null);
    }

    public static String downloadReceivedPdf(String enrollmentNpiId, String fileName)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return downloadReceivedPdf(enrollmentNpiId, fileName, null);
    }

    public static OriginalSignaturePdfResponse createOriginalSignaturePdf(String enrollmentNpiId, String file)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return createOriginalSignaturePdf(enrollmentNpiId, file, null);
    }

    public static OriginalSignaturePdfResponse createOriginalSignaturePdf(String enrollmentNpiId, File file)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return createOriginalSignaturePdf(enrollmentNpiId, file, null);
    }

    public static OriginalSignaturePdfResponse getOriginalSignaturePdf(String enrollmentNpiId)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return getOriginalSignaturePdf(enrollmentNpiId, null);
    }

    public static OriginalSignaturePdfResponse updateOriginalSignaturePdf(String enrollmentNpiId, String file)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return updateOriginalSignaturePdf(enrollmentNpiId, file, null);
    }

    public static OriginalSignaturePdfResponse updateOriginalSignaturePdf(String enrollmentNpiId, File file)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return updateOriginalSignaturePdf(enrollmentNpiId, file, null);
    }

    public static String downloadOriginalSignaturePdf(String enrollmentNpiId)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return downloadOriginalSignaturePdf(enrollmentNpiId, (RequestOptions) null);
    }

    public static String downloadOriginalSignaturePdf(String enrollmentNpiId, String fileName)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return downloadOriginalSignaturePdf(enrollmentNpiId, fileName, null);
    }

    public static OriginalSignaturePdfDeleteResponse deleteOriginalSignaturePdf(String enrollmentNpiId)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return deleteOriginalSignaturePdf(enrollmentNpiId, null);
    }

    public static EnrollmentNpi create(Map<String, Object> params, RequestOptions options)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        String url = classURL(EnrollmentNpi.class);
        return request(RequestMethod.POST, url, params, EnrollmentNpi.class, options);
    }

    public static EnrollmentNpiResponse retrieve(String enrollmentNpiId, RequestOptions options)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        String url = instanceURL(EnrollmentNpi.class, enrollmentNpiId);
        return request(RequestMethod.GET, url, null, EnrollmentNpiResponse.class, options);
    }

    public static EnrollmentNpiResponse update(String enrollmentNpiId, Map<String, Object> params, RequestOptions options)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        String url = instanceURL(EnrollmentNpi.class, enrollmentNpiId);
        return request(RequestMethod.PUT, url, params, EnrollmentNpiResponse.class, options);
    }

    public static EnrollmentNpi query(Map<String, Object> params, RequestOptions options)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        String url = classURL(EnrollmentNpi.class);
        return request(RequestMethod.GET, url, params, EnrollmentNpi.class, options);
    }

    public static ReceivedPdfResponse getReceivedPdf(String enrollmentNpiId, RequestOptions options)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return ReceivedPdf.retrieve(enrollmentNpiId, options);
    }

    public static String downloadReceivedPdf(String enrollmentNpiId, RequestOptions options)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return ReceivedPdf.download(enrollmentNpiId, options);
    }

    public static String downloadReceivedPdf(String enrollmentNpiId, String fileName, RequestOptions options)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return ReceivedPdf.download(enrollmentNpiId, fileName, options);
    }

    public static OriginalSignaturePdfResponse createOriginalSignaturePdf(String enrollmentNpiId, String file, RequestOptions options)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return OriginalSignaturePdf.create(enrollmentNpiId, file, options);
    }

    public static OriginalSignaturePdfResponse createOriginalSignaturePdf(String enrollmentNpiId, File file, RequestOptions options)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return OriginalSignaturePdf.create(enrollmentNpiId, file, options);
    }

    public static OriginalSignaturePdfResponse getOriginalSignaturePdf(String enrollmentNpiId, RequestOptions options)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return OriginalSignaturePdf.retrieve(enrollmentNpiId, options);
    }

    public static OriginalSignaturePdfResponse updateOriginalSignaturePdf(String enrollmentNpiId, String file, RequestOptions options)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return OriginalSignaturePdf.update(enrollmentNpiId, file, options);
    }

    public static OriginalSignaturePdfResponse updateOriginalSignaturePdf(String enrollmentNpiId, File file, RequestOptions options)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return OriginalSignaturePdf.update(enrollmentNpiId, file, options);
    }

    public static String downloadOriginalSignaturePdf(String enrollmentNpiId, RequestOptions options)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return OriginalSignaturePdf.download(enrollmentNpiId, options);
    }

    public static String downloadOriginalSignaturePdf(String enrollmentNpiId, String fileName, RequestOptions options)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return OriginalSignaturePdf.download(enrollmentNpiId, fileName, options);
    }

    public static OriginalSignaturePdfDeleteResponse deleteOriginalSignaturePdf(String enrollmentNpiId, RequestOptions options)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return OriginalSignaturePdf.delete(enrollmentNpiId, options);
    }


    @Getter
    @EqualsAndHashCode(callSuper = false)
    public static class PdfResource extends APIResource {
        String name;
        String createdAt;
        String updatedAt;
        String downloadUrl;

        static String download(String enrollmentNpiId, RequestOptions options, Class<?> clazz)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            String className = className(clazz);
            String fileName = "/tmp/" + className + ".pdf";
            return download(enrollmentNpiId, fileName, options, clazz);
        }

        static String download(String enrollmentNpiId, String fileName, RequestOptions options, Class<?> clazz)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            String url = String.format("%s/%s/download", instanceURL(EnrollmentNpi.class, enrollmentNpiId), className(clazz));
            EligibleResponse response = request(RequestMethod.GET, url, null, options);
            File file = new File(fileName);
            try (FileOutputStream fo = new FileOutputStream(file)) {
                fo.write(response.getResponseByteArray());
                return "PDF file stored at " + file.getAbsolutePath();
            } catch (IOException e) {
                throw new APIException("Filed to store PDF file:" + file.getAbsolutePath(), e);
            }
        }

        public String getId() {
            return getName();
        }
    }


    @Getter
    @EqualsAndHashCode(callSuper = false)
    public static class ReceivedPdf extends PdfResource {

        String notificationMessage;

        public static ReceivedPdfResponse retrieve(String enrollmentNpiId)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            return retrieve(enrollmentNpiId, null);
        }

        public static String download(String enrollmentNpiId)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            return download(enrollmentNpiId, (RequestOptions) null);
        }

        public static String download(String enrollmentNpiId, String fileName)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            return download(enrollmentNpiId, fileName, null);
        }

        public static ReceivedPdfResponse retrieve(String enrollmentNpiId, RequestOptions options)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            String url = String.format("%s/%s", instanceURL(EnrollmentNpi.class, enrollmentNpiId), className(ReceivedPdf.class));
            return request(RequestMethod.GET, url, null, ReceivedPdfResponse.class, options);
        }

        public static String download(String enrollmentNpiId, RequestOptions options)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            return download(enrollmentNpiId, options, ReceivedPdf.class);
        }

        public static String download(String enrollmentNpiId, String fileName, RequestOptions options)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            return download(enrollmentNpiId, fileName, options, ReceivedPdf.class);
        }

    }


    @Getter
    @EqualsAndHashCode(callSuper = false)
    public static class OriginalSignaturePdf extends PdfResource {

        public static OriginalSignaturePdfResponse create(String enrollmentNpiId, String file)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            return create(enrollmentNpiId, file, null);
        }

        public static OriginalSignaturePdfResponse create(String enrollmentNpiId, File file)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            return create(enrollmentNpiId, file, null);
        }

        public static OriginalSignaturePdfResponse retrieve(String enrollmentNpiId)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            return retrieve(enrollmentNpiId, null);
        }

        public static OriginalSignaturePdfResponse update(String enrollmentNpiId, String file)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            return update(enrollmentNpiId, file, null);
        }

        public static OriginalSignaturePdfResponse update(String enrollmentNpiId, File file)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            return update(enrollmentNpiId, file, null);
        }

        public static String download(String enrollmentNpiId)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            return download(enrollmentNpiId, (RequestOptions) null);
        }

        public static String download(String enrollmentNpiId, String fileName)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            return download(enrollmentNpiId, fileName, null);
        }

        public static OriginalSignaturePdfDeleteResponse delete(String enrollmentNpiId)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            return delete(enrollmentNpiId, null);
        }

        public static OriginalSignaturePdfResponse create(String enrollmentNpiId, String file, RequestOptions options)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            return create(enrollmentNpiId, new File(file), options);
        }

        public static OriginalSignaturePdfResponse create(String enrollmentNpiId, File file, RequestOptions options)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            return upload(enrollmentNpiId, file, RequestMethod.POST, options);
        }

        public static OriginalSignaturePdfResponse retrieve(String enrollmentNpiId, RequestOptions options)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            String url = String.format("%s/%s", instanceURL(EnrollmentNpi.class, enrollmentNpiId), className(OriginalSignaturePdf.class));
            return request(RequestMethod.GET, url, null, OriginalSignaturePdfResponse.class, options);
        }

        public static OriginalSignaturePdfResponse update(String enrollmentNpiId, String file, RequestOptions options)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            return update(enrollmentNpiId, new File(file), options);
        }

        public static OriginalSignaturePdfResponse update(String enrollmentNpiId, File file, RequestOptions options)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            return upload(enrollmentNpiId, file, RequestMethod.PUT, options);
        }

        static OriginalSignaturePdfResponse upload(String enrollmentNpiId, File file, RequestMethod method, RequestOptions options)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            String url = String.format("%s/%s", instanceURL(EnrollmentNpi.class, enrollmentNpiId), className(OriginalSignaturePdf.class));
            Map<String, Object> params = new HashMap<>();
            params.put("file", file);
            return multipartRequest(method, url, params, OriginalSignaturePdfResponse.class, options);
        }

        public static String download(String enrollmentNpiId, RequestOptions options)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            return download(enrollmentNpiId, options, OriginalSignaturePdf.class);
        }

        public static String download(String enrollmentNpiId, String fileName, RequestOptions options)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            return download(enrollmentNpiId, fileName, options, OriginalSignaturePdf.class);
        }

        public static OriginalSignaturePdfDeleteResponse delete(String enrollmentNpiId, RequestOptions options)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            String url = String.format("%s/%s", instanceURL(EnrollmentNpi.class, enrollmentNpiId), className(OriginalSignaturePdf.class));
            return request(RequestMethod.DELETE, url, null, OriginalSignaturePdfDeleteResponse.class, options);
        }

    }
}
