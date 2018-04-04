package org.opencps.api.controller;

import java.net.HttpURLConnection;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.opencps.api.datatempmgt.model.DataSearchModel;
import org.opencps.api.datatempmgt.model.DictCollectionTempInputModel;
import org.opencps.api.datatempmgt.model.DictCollectionTempModel;
import org.opencps.api.datatempmgt.model.DictGroupTempInputModel;
import org.opencps.api.datatempmgt.model.DictItemTempInputModel;
import org.opencps.api.datatempmgt.model.DictItemTempModel;
import org.opencps.api.datatempmgt.model.DictItemTempResults;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/temp/dictcollections")
@Api(value = "/temp/dictCollections", description = "APIs for DictCollection Temp", tags = "dictcollectiontemp")
public interface DataTempManagement {
 
	@GET
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getDictCollection(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@BeanParam DataSearchModel query);

	@GET
	@Path("/{code}")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getDictCollectionDetail(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@ApiParam(value = "code that need to be get detail", required = true) @PathParam("code") String code);

	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response addDictCollection(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@BeanParam DictCollectionTempInputModel input);

	@PUT
	@Path("/{code}")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Update DictCollection by its code", response = DictCollectionTempModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a certain DictCollection was updated", response = DictCollectionTempModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems") })
	public Response updateDictCollection(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@ApiParam(value = "code that need to be updated", required = true) @PathParam("code") String code,
			@ApiParam(value = "body of DictColeection that need to be updated", required = true)
			@BeanParam DictCollectionTempInputModel input);

	@DELETE
	@Path("/{code}")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Delete DictCollection by its code", response = DictCollectionTempModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a certain DictCollection was deleted", response = DictCollectionTempModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems") })
	public Response deleteDictCollection(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@ApiParam(value = "code that need to be deleted", required = true) @PathParam("code") String code);

	@GET
	@Path("/{code}/dataform")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get dataform of dictCollection by its code", response = String.class, notes = "Returns dataform of dictCollection")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns dataform of dictCollection", response = String.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems") })
	public Response getDataForm(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@ApiParam(value = "code that need to be get detail", required = true) @PathParam("code") String code);

	@PUT
	@Path("/{code}/dataform")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Adds dataform of dictCollection", response = String.class, notes = "Returns dictCollection was added")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns dataform of dictCollection", response = DictCollectionTempModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems") })
	public Response addDataForm(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@ApiParam(value = "code that need to be get detail", required = true) @PathParam("code") String code,
			@ApiParam(value = "alpace string of dataform", required = true) @FormParam("dataform") String dataform);
	
	@GET
	@Path("/{code}/dictgroups")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getDictgroups(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@PathParam("code") String code, @BeanParam DataSearchModel query);

	@POST
	@Path("/{code}/dictgroups")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response addDictgroups(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@PathParam("code") String code, @BeanParam DictGroupTempInputModel input);
	
	@PUT
	@Path("/{code}/dictgroups/{groupCode}")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response updateDictgroups(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@PathParam("code") String code, @PathParam("groupCode") String groupCode, @BeanParam DictGroupTempInputModel input);
	
	@DELETE
	@Path("/{code}/dictgroups/{groupCode}")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response deleteDictgroups(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@PathParam("code") String code,
			@PathParam("groupCode") String groupCode);
	
	@GET
	@Path("/{code}/dictgroups/{groupCode}/dictitems")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getDictgroupsDictItems(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@PathParam("code") String code, @PathParam("groupCode") String groupCode, @QueryParam("full") boolean full);
	
	@POST
	@Path("/{code}/dictgroups/{groupCode}/dictitems")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response addDictgroupsDictItems(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@PathParam("code") String code, @PathParam("groupCode") String groupCode, @FormParam("itemCode") String itemCode);
	
	@DELETE
	@Path("/{code}/dictgroups/{groupCode}/dictitems/{itemCode}")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response deleteDictgroupsDictItems(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@PathParam("code") String code, @PathParam("groupCode") String groupCode, @PathParam("itemCode") String itemCode);
	
	@GET
	@Path("/{code}/dictitems")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get DictItem list of DictCollectin", response = DictItemTempResults.class, notes = "Get DictItem list of DictCollection")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Get DictItem list of DictCollectin", response = DictItemTempResults.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems") })
	public Response getDictItems(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@ApiParam(value = "code of DictCollection of DictItem that need to be gotten list", required = true) @PathParam("code") String code,
			@BeanParam DataSearchModel query);

	@POST
	@Path("/{code}/dictitems")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Adds DictItem", response = DictItemTempModel.class, notes = "Add dictItem of DictCollection")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Return DictItem that was added", response = DictItemTempModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems") })
	public Response addDictItems(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@ApiParam(value = "code of DictCollection of DictItem that need to be added", required = true) @PathParam("code") String code,
			@BeanParam DictItemTempInputModel input);

	@GET
	@Path("/{code}/dictitems/{itemCode}")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get DictItem detail", response = DictItemTempModel.class, notes = "Get DictItem detail")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Return DictItem that has itemCode", response = DictItemTempModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems") })
	public Response getDictItemByItemCode(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@ApiParam(value = "code of DictCollection of DictItem that need to be added", required = true) @PathParam("code") String code,
			@ApiParam(value = "itemCode of DictItemthat need to be gotten detail", required = true) @PathParam("itemCode") String itemCode);

	@PUT
	@Path("/{code}/dictitems/{itemCode}")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Update DictItem by DictItemCode", response = DictItemTempModel.class, notes = "Update DictItem by DictItemCode")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Return DictItem that was updated", response = DictItemTempModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems") })
	public Response updateDictItemByItemCode(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@ApiParam(value = "code of DictCollection of DictItem that need to be updated", required = true) @PathParam("code") String code,
			@ApiParam(value = "itemCode of DictItemthat need to be updated", required = true) @PathParam("itemCode") String itemCode,
			@BeanParam DictItemTempInputModel input);

	@DELETE
	@Path("/{code}/dictitems/{itemCode}")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Delete DictItem by DictItemCode", response = DictItemTempModel.class, notes = "Delete DictItem by DictItemCode")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Return DictItem that was daleted", response = DictItemTempModel.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems") })
	public Response deleteDictItemByItemCode(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@ApiParam(value = "code of DictCollection of DictItem that need to be deleted", required = true) @PathParam("code") String code,
			@ApiParam(value = "itemCode of DictItemthat need to be deleted", required = true) @PathParam("itemCode") String itemCode);

	@GET
	@Path("/{code}/dictitems/{itemCode}/metadata")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get metadata of DictItem", response = String.class, notes = "Get metadata of DictItem")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Return Metadata of DictItem", response = String.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems") })
	public Response getMetaDataOfDictItem(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@ApiParam(value = "code of DictCollection of DictItem that need to be deleted", required = true) @PathParam("code") String code,
			@ApiParam(value = "itemCode of DictItemthat need to be deleted", required = true) @PathParam("itemCode") String itemCode);
	
	@GET
	@Path("/{code}/dictitems/{itemCode}/metadata/{key}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get metadata of DictItem", response = String.class, notes = "Get metadata of DictItem")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Return Metadata of DictItem", response = String.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems") })
	public Response getMetaDataOfDictItemByKey(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@ApiParam(value = "code of DictCollection of DictItem that need to be deleted", required = true) @PathParam("code") String code,
			@ApiParam(value = "itemCode of DictItemthat need to be deleted", required = true) @PathParam("itemCode") String itemCode,
			 @PathParam("key") String key);

	@PUT
	@Path("/{code}/dictitems/{itemCode}/metadata")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get metadata of DictItem", response = String.class, notes = "Get metadata of DictItem")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Return Metadata of DictItem", response = String.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems") })
	public Response addMetaDataOfDictItem(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@ApiParam(value = "code of DictCollection of DictItem that need to be updated meatadata", required = true) @PathParam("code") String code,
			@ApiParam(value = "itemCode of DictItemthat need to be updated", required = true) @PathParam("itemCode") String itemCode,
			@BeanParam DictItemTempInputModel input);

	@PUT
	@Path("/{code}/dictitems/{itemCode}/metadata/{key}")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get metadata of DictItem", response = String.class, notes = "Get metadata of DictItem")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Return Metadata of DictItem", response = String.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems") })
	public Response updateMetaDataOfDictItem(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@ApiParam(value = "code of DictCollection of DictItem that need to be updated meatadata", required = true) @PathParam("code") String code,
			@ApiParam(value = "itemCode of DictItemthat need to be updated", required = true) @PathParam("itemCode") String itemCode,
			@BeanParam DictItemTempInputModel input);

	@DELETE
	@Path("/{code}/dictitems/{itemCode}/metadata/{key}")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Delete metadata of DictItem", response = String.class, notes = "Delete metadata of DictItem")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Return Metadata of DictItem was deleted", response = String.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems") })
	public Response deleteMetaDataOfDictItem(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@ApiParam(value = "code of DictCollection of DictItem that need to be deleted meatadata", required = true) @PathParam("code") String code,
			@ApiParam(value = "itemCode of DictItemthat need to be deleted meatadata", required = true) @PathParam("itemCode") String itemCode,
			@ApiParam(value = "metadata of DictItem that need to deleted", required = true) @PathParam("key") String key,
			@ApiParam(value = "metadata of DictItem that need to deleted", required = true) String body);
}
