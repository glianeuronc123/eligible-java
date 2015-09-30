# eligible-java

Java bindings for Eligible APIs (https://eligible.com).

You can request an account at https://eligible.com/request-access



Documentation
=============

Refer to https://eligible.com/rest for full documentation on Eligible APIs, their request parameters
and expected response formats.



Requirements
============

Java 1.7 and later.


Installation
============

### Maven

Add this dependency to your project's POM:

```xml
<dependency>
  <groupId>com.eligible</groupId>
  <artifactId>eligible-java</artifactId>
  <version>1.1.0</version>
</dependency>
```

### Gradle

Add this dependency to your project's build file:

```groovy
compile "com.eligible:eligible-java:1.1.0"
```

### Others

You'll need to manually install the following JARs:

* The Eligible JAR from https://jcenter.bintray.com/com/eligible/eligible-java/
* [Google Gson](http://code.google.com/p/google-gson/) from <http://google-gson.googlecode.com/files/google-gson-2.2.4-release.zip>.

### [ProGuard](http://proguard.sourceforge.net/)

If you're planning on using ProGuard, make sure that you exclude the Eligible bindings. You can do this by adding the following to your `proguard.cfg` file:

    -keep class com.eligible.** { *; }

Usage
=====

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

Testing
=======

Note: Gradle must be installed to run tests.

To run all tests, execute `./gradlew test`. You can run particular tests by passing `-Dtest.single=Class#method` -- for example, `-Dtest.single=DeserializerTest`.

