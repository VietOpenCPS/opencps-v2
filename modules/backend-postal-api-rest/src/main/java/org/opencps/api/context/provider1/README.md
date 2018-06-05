# JAX-RS @Context

### JAX-RS Context annotation supports injecting the following parameters in methods:
| Type        | Description           |
| ------------- |:-------------:|
| javax.ws.rs.core.Application	| Provides access to metadata information on the JAX-RS application.| 
| javax.ws.rs.core.UriInfo	| Provides access to application and request URI information.| 
| javax.ws.rs.core.Request	| Provides access to the request used for the method.| 
| javax.ws.rs.core.HttpHeaders	| Provides access to the HTTP header information for the request.| 
| javax.ws.rs.core.SecurityContext	| Provides access to the security-related information for the request.| 
| javax.ws.rs.ext.Providers	| Provides runtime lookup of provider instances.| 

### We add some methods in @Context

| Tables        | Description           |
| ------------- |:-------------:|
| com.liferay.portal.kernel.model.User		| The locale associated with the request.| 
| com.liferay.portal.kernel.model.Company	| The Liferay Company associated with the request. | 
| java.util.Locale	| The locale associated with the request.| 
| ServiceContext	| The ServiceContext associated with the request. | 

These providers were store in `org.opencps.api.context.provider` package.

#### How to use?

```
@GET
@Path("/getcontext")
@Produces(MediaType.APPLICATION_JSON)
public String getStuff(@Context Application app, @Context UriInfo uriInfo, @Context Request request, @Context HttpHeaders httpHeaders, @Context SecurityContext securityContext, @Context Providers providers, @Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext) {
    //TODO: Implement here
}
```