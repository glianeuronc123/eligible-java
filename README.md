# Eligible

[![Circle CI](https://circleci.com/gh/eligible/eligible-java.svg?style=svg)](https://circleci.com/gh/eligible/eligible-java)

Java bindings for [Eligible API](https://eligible.com).

You can request an account at https://eligible.com/request-access


## Documentation

Refer to https://docs.eligible.com/ for full documentation on Eligible APIs, their request parameters
and expected response formats.



## Requirements

Java 1.7 and later.


## Installation

### Maven

Add this dependency to your project's POM:

```xml
<dependency>
  <groupId>com.eligible</groupId>
  <artifactId>eligible-java</artifactId>
  <version>1.8.0</version>
</dependency>
```

### Gradle

Add this dependency to your project's build file:

```groovy
compile "com.eligible:eligible-java:1.8.0"
```

### Others

You'll need to manually install the following JARs:

* The Eligible JAR from https://jcenter.bintray.com/com/eligible/eligible-java/
* [Google Gson](http://code.google.com/p/google-gson/) from <http://google-gson.googlecode.com/files/google-gson-2.2.4-release.zip>.

### [ProGuard](http://proguard.sourceforge.net/)

If you're planning on using ProGuard, make sure that you exclude the Eligible bindings. You can do this by adding the following to your `proguard.cfg` file:

    -keep class com.eligible.** { *; }


## Usage

### Setup
```java
Eligible.api_key = 'YOUR_KEY'
```

### Test
```java
Eligible.test = true
```

### Parameters overwrite

On each API call, you can overwrite the API key or the test parameter:

```java
RequestOptions requestOptions = new RequestOptionsBuilder()
                                            .setApiKey("YOUR-SECRET-KEY")
                                            .setTest(true)
                                            .build();
Payer.all(requestOption)
```

# Important notes

## Payer List for Eligibility

Payer IDs, required for most api calls, are provided on the Eligible
website in XML and JSON formats, and may be embedded into your
application.

[https://eligible.com/resources/payers/eligibility.xml](https://eligible.com/resources/payers/eligibility.xml)

[https://eligible.com/resources/payers/eligibility.json](https://eligible.com/resources/payers/eligibility.json)

## Payer List for Claims

Payer ID lists are also provided for payers supporting claims services.

## Medical

[https://eligible.com/resources/payers/claims/medical.xml](https://eligible.com/resources/payers/claims/medical.xml)

[https://eligible.com/resources/payers/claims/medical.json](https://eligible.com/resources/payers/claims/medical.json)

## Institutional

[https://eligible.com/resources/payers/claims/institutional.xml](https://eligible.com/resources/payers/claims/institutional.xml)

[https://eligible.com/resources/payers/claims/institutional.json](https://eligible.com/resources/payers/claims/institutional.json)

## Dental

[https://eligible.com/resources/payers/claims/dental.xml](https://eligible.com/resources/payers/claims/dental.xml)

[https://eligible.com/resources/payers/claims/dental.json](https://eligible.com/resources/payers/claims/dental.json)

## Service Type Codes

the parameter `service_type`, required on the API calls, is provided
by Eligible from its website, in xml and json format, which you can
embed into your applications.

[https://eligible.com/resources/service-codes.xml](https://eligible.com/resources/service-codes.xml)
[https://eligible.com/resources/service-codes.json](ttps://eligible.com/resources/service-codes.json)

## Place of Service

[https://eligible.com/resources/place_of_service.json](https://eligible.com/resources/place_of_service.json)

## Health Care Provider Taxonomy

[https://eligible.com/resources/health-care-provider-taxonomy-code-set.json](https://eligible.com/resources/health-care-provider-taxonomy-code-set.json)


## Payer

### Reference

[https://reference.eligible.com/docs#payers-introduction](https://reference.eligible.com/docs#payers-introduction)

### List all payers

```java
List<Payer> payers = Payer.all()
```

### View a single payer

```java
Payer payer = Payer.retrieve("12345");
response.to_hash
```

### Search options for a payer

```java
Payer.SearchOptions searchOptions = Payer.searchOptions("12345");
```

### Search options for all payers

```java
List<Payer.SearchOptions> searchOptions = Payer.searchOptions();
```

## Coverage

### Reference

[https://reference.eligible.com/#coverage](https://reference.eligible.com/#coverage)

### Retrieve eligibility and benefit information

```java
Map<String, Object> params = new HashMap<>();
params.put("payer_id", "00001");
params.put("provider_last_name", "Doe");
params.put("provider_first_name", "John");
params.put("provider_npi", "0123456789");
params.put("member_id", "ZZZ445554301");
params.put("member_first_name", "IDA");
params.put("member_last_name", "FRANKLIN");
params.put("member_dob", "1701-12-12");
params.put("service_type", "30");
params.put("network", "OUT");

Coverage coverage = Coverage.all(params) // returns all coverage info for the request
```

## Medicare

### Reference

[https://reference.eligible.com/#medicare](https://reference.eligible.com/#medicare)

### Retrieve eligibility and benefit information from CMS Medicare for a patient

```java
Map<String, Object> params = new HashMap<>();
params.put("provider_last_name", "Doe");
params.put("provider_first_name", "John");
params.put("provider_npi", "0123456789");
params.put("member_id", "111111111A");
params.put("member_first_name", "IDA");
params.put("member_last_name", "FRANKLIN");
params.put("member_dob", "1701-12-12");

// returns all coverage info for the request
Coverage.Medicare medicareCoverage = Coverage.medicare(params);
```

## Cost Estimate

### Reference

[https://reference.eligible.com/#cost-estimates](https://reference.eligible.com/#cost-estimates)

### Retrieve eligibility and benefit information from CMS Medicare for a patient

```java
Map<String, Object> params = new HashMap<>();
params.put("provider_price", "1500.50");
params.put("service_type", "1");
params.put("network", "IN");
params.put("payer_id", "00001");
params.put("provider_last_name", "Doe");
params.put("provider_first_name", "John");
params.put("provider_npi", "0123456789");
params.put("member_id", "ZZZ445554301");
params.put("member_first_name", "IDA");
params.put("member_last_name", "FRANKLIN");
params.put("member_dob", "1701-12-12");

// returns all coverage info for the request
Coverage.CostEstimates costEstimates = Coverage.costEstimate(params);
```

## Enrollment

Enrollment requests can have multiple enrollment NPIs. You can repeat
the enrollment for a NPI multiple times across different enrollment
requests.

### Reference
[https://reference.eligible.com/#enrollment-introduction](https://reference.eligible.com/#enrollment-introduction)

### Create an Enrollment Request

```java
Map<String, Object> params = new HashMap<>();
params.put("payer_id", "0007");
params.put("endpoint", "coverage");
params.put("effective_date", "2012-12-24");
params.put("facility_name", "Quality");
params.put("provider_name", "Jane Austen");
params.put("tax_id", "12345678");
params.put("address", "125 Snow Shoe Road");
params.put("city", "Sacramento");
params.put("state", "CA");
params.put("zip", "94107");
params.put("ptan", "54321");
params.put("medicaid_id", "22222");
params.put("npi", "987654321");

EnrollmentNpiResponse enrollment = EnrollmentNpi.create(params);
```

### Retrieve an Enrollment Request

```java
EnrollmentNpiResponse enrollment = EnrollmentNpi.retrieve("557604291");
```

### Query Enrollments

```java
Map<String, Object> params = new HashMap<>();
params.put("status", "accepted");

EnrollmentNpiQueryResponse queryResponse = EnrollmentNpi.query(params);
```

## Received PDF

### Reference

[https://reference.eligible.com/#view-received-pdf](https://reference.eligible.com/#view-received-pdf)

### Get Received PDF

```java
ReceivedPdfResponse receivedPdf = EnrollmentNpi.getReceivedPdf("557604291");
```

### Download Received PDF
By default, it downloads to /tmp/received_pdf.pdf
```java
String receivedPdf = EnrollmentNpi.downloadReceivedPdf("557604291", "file_path_where_to_download");
```

## Original Signature PDF

### Reference

[https://reference.eligible.com/#create-original-signature-pdf](https://reference.eligible.com/#create-original-signature-pdf)

### Get Original Signature PDF

```java
OriginalSignaturePdfResponse originalSignaturePdf = EnrollmentNpi.getOriginalSignaturePdf("557604291");
```

### Create Original Signature PDF

```java
OriginalSignaturePdfResponse originalSignaturePdf = EnrollmentNpi.createOriginalSignaturePdf("557604291", "path_to_file");
```

### Update Original Signature PDF

```java
OriginalSignaturePdfResponse originalSignaturePdf = EnrollmentNpi.updateOriginalSignaturePdf("557604291", "path_to_file");
```

### Download Original Signature PDF
By default, it downloads to /tmp/original_signature_pdf.pdf
```java
String originalSignaturePdf = EnrollmentNpi.downloadOriginalSignaturePdf("557604291", "file_path_where_to_download");
```

### Delete Original Signature PDF

```java
OriginalSignaturePdfDeleteResponse originalSignaturePdf = EnrollmentNpi.deleteOriginalSignaturePdf("557604291");
```


## Claims

### Reference

[https://reference.eligible.com/#create-a-claim](https://reference.eligible.com/#create-a-claim)

### Create Claim object

```java

Map<String, Object> createAddress(String line1, String line2, String city, String state, String zip) {
    Map<String, Object> address = new HashMap<>();
    address.put("street_line_1", line1);
    address.put("street_line_2", line2);
    address.put("city", city);
    address.put("state", state);
    address.put("zip", zip);

    return address;
}

Map<String, Object> createBillingProvider() {
    Map<String, Object> billingProvider = new HashMap<>();
    billingProvider.put("tax_id", "123456789");
    billingProvider.put("tax_id_type", "EI");
    billingProvider.put("entity", "false");
    billingProvider.put("phone_number", "1234567890");
    billingProvider.put("organization_name", "ELIGIBLE INC");
    billingProvider.put("last_name", "SOME");
    billingProvider.put("first_name", "PROVIDER");
    billingProvider.put("middle_name", null);
    billingProvider.put("address", createAddress("1842 UNION STREET", null, "Seattle", "WA", "981011231"));
    billingProvider.put("npi", "1234567893");
    billingProvider.put("taxonomy_code", "101YM0800X");
    return billingProvider;
}

Map<String, Object> createPayer() {
    Map<String, Object> payer = new HashMap<>();
    payer.put("id", "60054");
    payer.put("name", "Aetna");
    payer.put("address", createAddress("603 3rd Ave Van", null, "San Francisco", "CA", "941231232"));
    return payer;
}

Map<String, Object> createSubsciber() {
    Map<String, Object> subscriber = new HashMap<>();
    subscriber.put("id", "1234567890");
    subscriber.put("first_name", "Benjamin");
    subscriber.put("last_name", "Franklin");
    subscriber.put("middle_name", null);
    subscriber.put("address", createAddress("123 NW St", null, "Seattle", "WA", "981171232"));
    subscriber.put("phone_number", "9129129121");
    subscriber.put("group_id", "100012345");
    subscriber.put("dob", "1974-02-06");
    subscriber.put("gender", "M");
    subscriber.put("group_name", null);
    return subscriber;
}

Map<String, Object> createDependent() {
    Map<String, Object> dependent = new HashMap<>();
    dependent.put("last_name", "Franklin");
    dependent.put("first_name", "Cheryl");
    dependent.put("middle_name", null);
    dependent.put("dob", "1976-03-06");
    dependent.put("gender", "F");
    dependent.put("address", createAddress("123 NW St", null, "Seattle", "WA", "981171232"));
    dependent.put("relationship", "01");
    dependent.put("phone_number", "9129129123");
    return dependent;
}

Map<String, Object> createRenderingProvider() {
    Map<String, Object> renderingProvider = new HashMap<>();
    renderingProvider.put("entity", null);
    renderingProvider.put("organization_name", null);
    renderingProvider.put("last_name", "Franklin");
    renderingProvider.put("first_name", "John");
    renderingProvider.put("npi", "1234567893");
    return renderingProvider;
}

Map<String, Object> createServiceLine() {
    Map<String, Object> serviceLine = new HashMap<>();
    serviceLine.put("service_date_from", "2014-05-07");
    serviceLine.put("service_date_to", "2014-05-07");
    serviceLine.put("place_of_service", "11");
    serviceLine.put("procedure_code", "90837");
    serviceLine.put("procedure_modifiers", Arrays.asList("UN"));
    serviceLine.put("diagnosis_code_pointers", Arrays.asList("1"));
    serviceLine.put("charge_amount", "118.05");
    serviceLine.put("units", "1");
    serviceLine.put("rendering_provider", createRenderingProvider());
    return serviceLine;
}

Map<String, Object> createClaim() {
    Map<String, Object> claim = new HashMap<>();
    claim.put("patient_signature_on_file", "Y");
    claim.put("direct_payment_authorized", "Y");
    claim.put("frequency", "1");
    claim.put("prior_authorization_number", "1234567890");
    claim.put("accept_assignment_code", "C");
    claim.put("total_charge", "118.05");
    claim.put("patient_amount_paid", "0");
    claim.put("provider_signature_on_file", "Y");
    claim.put("diagnosis_codes", Arrays.asList("309.24", "309.0"));
    claim.put("service_lines", Arrays.asList(createServiceLine()));
    return claim;
}

Map<String, Object> createClaimCreateParams() {
    Map<String, Object> claimParams = new HashMap<>();
    claimParams.put("scrub_eligibility", "false");
    claimParams.put("billing_provider", createBillingProvider());
    claimParams.put("payer", createPayer());
    claimParams.put("subscriber", createSubsciber());
    claimParams.put("dependent", createDependent());
    claimParams.put("claim", createClaim());
    return claimParams;
}

Claim claim = Claim.create(createClaimCreateParams());
```

### Query Claim objects/acknowledgments

```java
Map<String, Object> params = new HashMap<>();
params.put("submission_status", "accepted");

// returns acknowledgment information for all claims that have been submitted with the API key
Claim.Acknowledgements acknowledgements = Claim.queryAcknowledgements(params);
```

### Retrieve individual Claim object/acknowledgment

```java
// returns acknowledgment information on an individual claim identified by its reference_id
Claim.Acknowledgements acknowledgements = Claim.getAcknowledgements("8IZ9JZI2FUEDCS");
```

### Query Claim Payment Reports

```java
Map<String, Object> params = new HashMap<>();
params.put("submission_status", "accepted");

// returns payment reports for all claims that have been submitted with the API key
Claim.PaymentReports reports = Claim.queryPaymentReports(params);
```

### Retrieve individual Claim Payment Report

```java
// returns payment reports of an individual claim identified by its reference_id
Claim.PaymentReport report = Claim.getPaymentReport("8IZ9JZI2FUEDCS");

// returns payment reports of an individual claim identified by its reference_id and payment_report_id
Claim.PaymentReport report = Claim.getPaymentReport("8IZ9JZI2FUEDCS", "UP4OCS4PUY455");
```

## Payment Status

### Reference

[https://reference.eligible.com/#payment-status](https://reference.eligible.com/#payment-status)

### Retrieve Payment status

```java
Map<String, Object> params = new HashMap<>();
params.put("payer_id", "00001");
params.put("provider_npi", "0123456789");
params.put("provider_tax_id", "111223333");
params.put("member_id", "ZZZ445554301");
params.put("member_first_name", "IDA");
params.put("member_last_name", "FRANKLIN");
params.put("member_dob", "1701-12-12");
params.put("payer_control_number", "123123123");
params.put("charge_amount", "125.00");
params.put("start_date", "2010-06-15");
params.put("end_date", "2010-06-15");
params.put("trace_number", "BHUYTOK98IK");

PaymentStatus status = PaymentStatus.retrieve(params);
```

## Full Examples

EligibleExample.java

```java

import com.eligible.model.Coverage;
import com.eligible.model.Plan;
import com.eligible.net.RequestOptions;
import com.eligible.net.RequestOptions.RequestOptionsBuilder;

import java.util.HashMap;
import java.util.Map;

public class EligibleExample {

    public static void main(String[] args) throws Exception {
        RequestOptions requestOptions = new RequestOptionsBuilder()
                                                    .setApiKey("YOUR-SECRET-KEY")
                                                    .setTest(true)
                                                    .build();

        Map<String, Object> allParams = new HashMap<String, Object>();
        allParams.put("payer_id", "00001");
        allParams.put("provider_last_name", "Doe");
        allParams.put("provider_first_name", "John");
        allParams.put("provider_npi", "0123456789");
        allParams.put("member_id", "ZZZ445554301");
        allParams.put("member_first_name", "IDA");
        allParams.put("member_last_name", "FRANKLIN");
        allParams.put("member_dob", "1701-12-12");
        allParams.put("service_type", "30");

        Coverage coverage = Coverage.all(allParams, requestOptions);
        System.out.println(coverage);
        System.out.println(coverage.dump());            // dump contains raw parameters.

        Map<String, ?> planMap = coverage.get("plan");  // get method returns raw parameters
        System.out.println(planMap);
        System.out.println(planMap.get("dates"));

        Plan planObject = coverage.getPlan();           // get<> methods return Object representation
        System.out.println(planObject);
        System.out.println(planObject.dump());
        System.out.println(planObject.get("dates"));
        System.out.println(planObject.getDates());      // OK, you get the idea now :D
    }
}
```

See [EligibleTest.java](https://github.com/eligible/eligible-java/blob/master/src/test/java/com/eligible/EligibleTest.java) for more examples.

## Testing

Note: Gradle must be installed to run tests.

To run all tests, execute `./gradlew test`. You can run particular tests by passing `-Dtest.single=Class#method` -- for example, `-Dtest.single=DeserializerTest`.
