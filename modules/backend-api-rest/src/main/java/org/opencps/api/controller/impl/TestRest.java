package org.opencps.api.controller.impl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/dossiertemplatesx")
public class TestRest {
	@GET
	@Path("chao")
	@Produces("text/plain")
	public String working() {
		return "It works!";
	}
}
