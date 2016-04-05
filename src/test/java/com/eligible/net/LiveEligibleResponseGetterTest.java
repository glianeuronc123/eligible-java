package com.eligible.net;

import com.eligible.BaseEligibleTest;
import com.eligible.exception.EligibleException;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.eligible.net.LiveEligibleResponseGetter.createHtmlQuery;
import static com.eligible.net.LiveEligibleResponseGetter.fillParams;
import static org.junit.Assert.assertEquals;

public class LiveEligibleResponseGetterTest extends BaseEligibleTest {
    LiveEligibleResponseGetter srg;

    @Before
    public void before() {
        srg = new LiveEligibleResponseGetter();
    }

    /* Kind of hacky, but makes tests readable */
    public String encode(String s) throws UnsupportedEncodingException {
        return s.replace("[", "%5B").replace("]", "%5D");
    }

    @Test
    public void testCreateQuery() throws EligibleException, UnsupportedEncodingException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("a", "b");
        assertEquals("a=b", createHtmlQuery(params));
    }

    @Test
    public void testFillParams() throws EligibleException, UnsupportedEncodingException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("a", "b");
        params = fillParams(params, RequestOptions.getDefault());
        assertEquals("b", params.get("a"));
        assertEquals("foobar", params.get("api_key"));
        assertEquals("true", params.get("test"));
    }

    @Test
    public void testCreateQueryWithNestedParams() throws EligibleException, UnsupportedEncodingException {
        /* Use LinkedHashMap because it preserves iteration order */
        Map<String, Object> params = new LinkedHashMap<String, Object>();
        Map<String, Object> nested = new LinkedHashMap<String, Object>();
        nested.put("A", "B");
        nested.put("C", "D");
        params.put("nested", nested);
        params.put("c", "d");
        params.put("e", "f");
        assertEquals(encode("nested[A]=B&nested[C]=D&c=d&e=f"), createHtmlQuery(params));
    }

    @Test
    public void testCreateQueryWithList() throws EligibleException, UnsupportedEncodingException {
        /* Use LinkedHashMap because it preserves iteration order */
        Map<String, Object> params = new LinkedHashMap<String, Object>();
        List<String> nested = new LinkedList<String>();
        nested.add("A");
        nested.add("B");
        nested.add("C");
        params.put("nested", nested);
        params.put("a", "b");
        params.put("c", "d");
        assertEquals(encode("nested[0]=A&nested[1]=B&nested[2]=C&a=b&c=d"), createHtmlQuery(params));
    }
}

