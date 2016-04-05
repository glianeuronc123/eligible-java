package com.eligible.model.enrollmentnpi;


import com.eligible.model.EligibleObject;
import com.eligible.model.Person;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = false)
public class AuthorizedSigner extends EligibleObject {

    String contactNumber;
    String email;
    String firstName;
    String lastName;
    String title;
    Signature signature;

    @Getter
    @EqualsAndHashCode(callSuper = false)
    public static class Signature extends EligibleObject {
        List<Coordinate> coordinates;
    }

    @Getter
    @EqualsAndHashCode(callSuper = false)
    public static class Coordinate extends EligibleObject {
        Integer lx;
        Integer ly;
        Integer mx;
        Integer my;
    }

}
