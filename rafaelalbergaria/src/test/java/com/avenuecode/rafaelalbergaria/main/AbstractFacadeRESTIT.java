package com.avenuecode.rafaelalbergaria.main;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.runner.RunWith;

import com.eclipsesource.restfuse.Destination;
import com.eclipsesource.restfuse.HttpJUnitRunner;
import com.eclipsesource.restfuse.MediaType;
import com.eclipsesource.restfuse.Method;
import com.eclipsesource.restfuse.Response;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.HttpTest;

@RunWith(HttpJUnitRunner.class)
public class AbstractFacadeRESTIT {

    @Rule
    public Destination destination = new Destination(this, "http://localhost:21957/rafaelalbergaria/rest/product");

    @Context
    private Response response;

    @HttpTest(method = Method.POST, path = "/findAll", type = MediaType.APPLICATION_JSON, content = "{ \"name\":\"Rafael Albergaria\" }")
    public void testFindAll() throws Exception {    	
        assertThat(response.getType(), equalTo(MediaType.APPLICATION_JSON));
    }
}
