package com.eligible.model;

import com.eligible.net.APIResource;
import com.eligible.net.RequestOptions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.Invokable;
import com.google.common.reflect.Parameter;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Simple test to make sure eligible-java provides consistent bindings.
 */
public class StandardizationTest {
    public Collection<Class> getAllModels() throws IOException {
        Class<Payer> chargeClass = Payer.class;
        ClassPath classPath = ClassPath.from(chargeClass.getClassLoader());
        ImmutableSet<ClassPath.ClassInfo> topLevelClasses = classPath.getTopLevelClasses(chargeClass.getPackage().getName());
        List<Class> classList = Lists.newArrayListWithExpectedSize(topLevelClasses.size());
        for (ClassPath.ClassInfo classInfo : topLevelClasses) {
            Class c = classInfo.load();
            // Skip things that aren't APIResources
            if (!APIResource.class.isAssignableFrom(c)) {
                continue;
            }
            // Skip the APIResource itself
            if (APIResource.class == c) {
                continue;
            }
            // Skip the APIResource itself
            if (EligibleCollectionAPIResource.class == c) {
                continue;
            }
            classList.add(classInfo.load());
        }
        return classList;
    }

    @Test
    public void allNonDeprecatedMethodsTakeOptions() throws IOException, NoSuchMethodException {
        for (Class aClass : getAllModels()) {
            for (Method method : aClass.getMethods()) {

                if (method.getDeclaringClass() != aClass     // Skip methods not declared on the base class.
                        || method.getName().equals("equals")     // Skip equals
                        || method.getName().startsWith("set")    // Skip setters
                        || method.getName().startsWith("get")) { // Skip getters
                    continue;
                }

                // If more than one method with the same parameter types is declared in a class, and one of these
                // methods has a return type that is more specific than any of the others, that method is returned;
                // otherwise one of the methods is chosen arbitrarily.
                Method mostSpecificMethod = aClass.getDeclaredMethod(method.getName(), method.getParameterTypes());
                Invokable<?, Object> invokable = Invokable.from(method);
                ImmutableList<Parameter> parameters = invokable.getParameters();

                if (!method.equals(mostSpecificMethod)
                        || invokable.isPrivate()                  // Skip private methods.
                        // Skip deprecated methods - we need to keep them around, but aren't asserting their type.
                        || invokable.isAnnotationPresent(Deprecated.class)
                        // Skip empty parameter lists - assume the author is using default values for the RequestOptions
                        || parameters.isEmpty()) {
                    continue;
                }

                Parameter lastParam = parameters.get(parameters.size() - 1);
                Class<?> lastParamType = lastParam.getType().getRawType();

                // Skip methods that have exactly one param which is a map.
                if (Map.class.equals(lastParamType) && parameters.size() == 1) {
                    continue;
                }


                if (String.class.equals(lastParamType) && parameters.size() == 1) {
                    switch (method.getName()) {
                            // Skip `public static Foo retrieve(String id) {...` helper methods
                        case "retrieve":
                            // Skip `public static SearchOptions searchOptions(String payerId) {...`
                        case "searchOptions":
                            // Skip `public static Acknowledgements acknowledgements(String referenceId) {...`
                        case "acknowledgements":
                            // Skip `public static PaymentReports paymentReports(String referenceId) {...`
                        case "paymentReports":
                            continue;
                        default:
                    }
                }

                assertTrue(
                        String.format("Methods on %ss like %s.%s should take a final parameter as a %s parameter.%n", APIResource.class.getSimpleName(), aClass.getSimpleName(), method.getName(), RequestOptions.class.getSimpleName()),
                        RequestOptions.class.isAssignableFrom(lastParamType));
            }
        }
    }
}
