package com.eligible.model;

import java.util.List;

/**
 * Created by ankitdimania on 18/09/15.
 */
public class FinancialFlows extends EligibleObject {
    List<FinancialFlow> inNetwork;
    List<FinancialFlow> outNetwork;


    public List<FinancialFlow> getInNetwork() {
        return inNetwork;
    }

    public List<FinancialFlow> getOutNetwork() {
        return outNetwork;
    }
}
