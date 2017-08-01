package org.mobilink.api.controller;

import java.net.HttpURLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.headers.Header;
import org.mobilink.api.dictcollection.model.DictCollectionModel;
import org.mobilink.api.dictcollection.model.DictCollectionResults;
import org.mobilink.api.dictcollection.model.DictCollectionShortModel;
import org.mobilink.api.dictcollection.model.DictGroupModel;
import org.mobilink.api.dictcollection.model.DictGroupResults;
import org.mobilink.api.dictcollection.model.DictItemModel;
import org.mobilink.api.dictcollection.model.DictItemResults;
import org.mobilink.exception.model.ExceptionModel;

import io.swagger.annotations.*;


@Path("/dictCollections")
@Api(value="/dictCollections", description="APIs for DictCollection", tags="dictcollection")
public interface DataManagement {

	@GET
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get all DictCollection", response = DictCollectionResults.class)
	@ApiResponses (value = {
	        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a list of DictCollection", response = DictCollectionResults.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized", response = ExceptionModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found", response = ExceptionModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Access denied", response = ExceptionModel.class)
	})
	public Response getDictCollection(@Context HttpServletRequest request, @Context Header header);
	
	@GET
	@Path("/{code}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get a DictCollection detail by its code", response = DictCollectionModel.class)
	public Response getDictCollectionDetail(@Context HttpServletRequest request, @Context Header header,
			@ApiParam(value = "code that need to be get detail", required = true) @PathParam("code") String code);
	
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Add a DictCollection", response = DictCollectionModel.class)
	@ApiResponses(value = {
	        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a certain DictCollection was created", response = DictCollectionModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
	        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
	        @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems")
	})
	public Response addDictCollection(@Context HttpServletRequest request, @Context Header header, String body);

	
	@PUT
	@Path("/{code}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Update DictCollection by its code", response = DictCollectionModel.class)
	@ApiResponses(value = {
	        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a certain DictCollection was updated", response = DictCollectionModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
	        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
	        @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems")
	})
	public Response updateDictCollection(@Context HttpServletRequest request, @Context Header header,
			@ApiParam(value = "code that need to be updated", required = true) @PathParam("code") String code, 
			@ApiParam(value = "body of DictColeection that need to be updated", required = true) DictCollectionShortModel body);

	@DELETE
	@Path("/{code}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Delete DictCollection by its code", response = DictCollectionModel.class)
	@ApiResponses(value = {
	        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a certain DictCollection was deleted", response = DictCollectionModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
	        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
	        @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems")
	})
	public Response deleteDictCollection(@Context HttpServletRequest request, @Context Header header,
			@ApiParam(value = "code that need to be deleted", required = true) @PathParam("code") String code);

	@GET
	@Path("/{code}/dataform")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get dataform of dictCollection by its code", response = String.class, notes="Returns dataform of dictCollection")
	@ApiResponses(value = {
	        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns dataform of dictCollection", response = String.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
	        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
	        @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems")
	})
	public Response getDataForm(@Context HttpServletRequest request, @Context Header header,
			@ApiParam(value = "code that need to be get detail", required = true) @PathParam("code") String code);
	
	
	@POST
	@Path("/{code}/dataform")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Adds dataform of dictCollection", response = String.class, notes="Returns dictCollection was added")
	@ApiResponses(value = {
	        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns dataform of dictCollection", response = DictCollectionModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
	        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
	        @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems")
	})
	public Response addDataForm(@Context HttpServletRequest request, @Context Header header,
			@ApiParam(value = "code that need to be get detail", required = true) @PathParam("code") String code, 
			@ApiParam(value = "alpace string of dataform", required = true)  String body);

	@GET
	@Path("/{code}/dictgroups")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Adds dataform of dictCollection", response = DictGroupResults.class, notes="Returns list of DictGroups")
	@ApiResponses(value = {
	        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns list of DictGroups", response = DictGroupResults.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
	        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
	        @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems")
	})
	public Response getDictGroups(@Context HttpServletRequest request, @Context Header header,
			@ApiParam(value = "code that need to be get DictGroup", required = true) @PathParam("code") String code);

	@POST
	@Path("/{code}/dictgroups")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Adds dataform of dictCollection", response = DictGroupModel.class, notes="Returns a DictGroups was added")
	@ApiResponses(value = {
	        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a DictGroups was added", response = DictGroupModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
	        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
	        @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems")
	})
	public Response addDictGroups(@Context HttpServletRequest request, @Context Header header,
			@ApiParam(value = "code that need to be get DictGroup", required = true) @PathParam("code") String code,
			@ApiParam(value = "dictGroup content need to be added", required = true)  DictGroupModel body);
	
	@PUT
	@Path("/{code}/dictgroups/{groupCode}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Updates DictGroup of dictCollection", response = DictGroupModel.class, notes="Returns a DictGroups was added")
	@ApiResponses(value = {
	        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a DictGroups was added", response = DictGroupModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
	        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
	        @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems")
	})
	public Response updateDictGroups(@Context HttpServletRequest request, @Context Header header,
			@ApiParam(value = "code of dictCollection", required = true) @PathParam("code") String code,
			@ApiParam(value = "dictGroupCode that need to be updated", required = true) @PathParam("groupCode") String groupCode,
			@ApiParam(value = "dictGroup content need to be updated", required = true)  DictGroupModel body);
	
	@DELETE
	@Path("/{code}/dictgroups/{groupCode}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Delete DictGroup of DictCollection", response = DictGroupModel.class, notes="Returns a DictGroups was deleted")
	@ApiResponses(value = {
	        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns a DictGroups was deleted", response = DictGroupModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
	        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
	        @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems")
	})
	public Response daleteDictGroups(@Context HttpServletRequest request, @Context Header header,
			@ApiParam(value = "code of dictCollection", required = true) @PathParam("code") String code,
			@ApiParam(value = "dictGroupCode that need to be delete", required = true) @PathParam("groupCode") String groupCode);	

	@GET
	@Path("/{code}/dictgroups/{groupCode}/dictItems")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get DictItem list of DictGroup by groupCode and code (code of dictCollection)", response = DictItemResults.class, notes="Returns list of DictItem")
	@ApiResponses(value = {
	        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns list of DictItem", response = DictItemResults.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
	        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
	        @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems")
	})
	public Response getDictItem(@Context HttpServletRequest request, @Context Header header,
			@ApiParam(value = "code that need to be get DictGroup", required = true) @PathParam("code") String code,
			@ApiParam(value = "dictGroupCode that need to be deleted", required = true) @PathParam("groupCode") String groupCode);

	@POST
	@Path("/{code}/dictgroups/{groupCode}/dictItems")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get DictItem list of DictGroup by groupCode and code (code of dictCollection)", response = DictItemResults.class, notes="Returns list of DictItem")
	@ApiResponses(value = {
	        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns list of DictItem", response = DictItemResults.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
	        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
	        @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems")
	})
	public Response addDictItem(@Context HttpServletRequest request, @Context Header header,
			@ApiParam(value = "code of DictCollection of DictGroup of DictItem that need to be added", required = true) @PathParam("code") String code,
			@ApiParam(value = "dictGroupCode of DictGroup of DictItem need to be added", required = true) @PathParam("groupCode") String groupCode,
			@ApiParam(value = "DictItem content that need to be add", required = true) DictItemModel dictItemModel
	);
	
	@DELETE
	@Path("/{code}/dictgroups/{groupCode}/dictItems/{dictitemCode}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Delete DictItem", response = DictItemResults.class, notes="Returns a DictItem")
	@ApiResponses(value = {
	        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns list of DictItem", response = DictItemResults.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
	        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
	        @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems")
	})
	public Response deleteDictItem(@Context HttpServletRequest request, @Context Header header,
			@ApiParam(value = "code of DictCollection of DictGroup of DictItem that need to be deleted", required = true) @PathParam("code") String code,
			@ApiParam(value = "dictGroupCode of DictGroup of DictItem need to be added", required = true) @PathParam("groupCode") String groupCode,
			@ApiParam(value = "dictitemCode of DictItem that need to be add", required = true) @PathParam("dictitemCode") String dictitemCode
	);
	
	@GET
	@Path("/{code}/dictItems")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get DictItem list of DictCollectin", response = DictItemResults.class, notes="Get DictItem list of DictCollection")
	@ApiResponses(value = {
	        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Get DictItem list of DictCollectin", response = DictItemResults.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
	        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
	        @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems")
	})
	public Response getDictItems(@Context HttpServletRequest request, @Context Header header,
			@ApiParam(value = "code of DictCollection of DictItem that need to be gotten list", required = true) @PathParam("code") String code
	);

	
	@POST
	@Path("/{code}/dictItems")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Adds DictItem", response = DictItemModel.class, notes="Add dictItem of DictCollection")
	@ApiResponses(value = {
	        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Return DictItem that was added", response = DictItemModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
	        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
	        @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems")
	})
	public Response addDictItems(@Context HttpServletRequest request, @Context Header header,
			@ApiParam(value = "code of DictCollection of DictItem that need to be added", required = true) @PathParam("code") String code
	);
	
	
	@GET
	@Path("/{code}/dictItems/{itemCode}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get DictItem detail", response = DictItemModel.class, notes="Get DictItem detail")
	@ApiResponses(value = {
	        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Return DictItem that has itemCode", response = DictItemModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
	        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
	        @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems")
	})
	public Response getDictItemByItemCode(@Context HttpServletRequest request, @Context Header header,
			@ApiParam(value = "code of DictCollection of DictItem that need to be added", required = true) @PathParam("code") String code,
			@ApiParam(value = "itemCode of DictItemthat need to be gotten detail", required = true) @PathParam("itemCode") String itemCode
	);
	
	@PUT
	@Path("/{code}/dictItems/{itemCode}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Update DictItem by DictItemCode", response = DictItemModel.class, notes="Update DictItem by DictItemCode")
	@ApiResponses(value = {
	        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Return DictItem that was updated", response = DictItemModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
	        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
	        @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems")
	})
	public Response updateDictItemByItemCode(@Context HttpServletRequest request, @Context Header header,
			@ApiParam(value = "code of DictCollection of DictItem that need to be updated", required = true) @PathParam("code") String code,
			@ApiParam(value = "itemCode of DictItemthat need to be updated", required = true) @PathParam("itemCode") String itemCode
	);
	
	@DELETE
	@Path("/{code}/dictItems/{itemCode}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Delete DictItem by DictItemCode", response = DictItemModel.class, notes="Delete DictItem by DictItemCode")
	@ApiResponses(value = {
	        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Return DictItem that was daleted", response = DictItemModel.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
	        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
	        @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems")
	})
	public Response dateletDictItemByItemCode(@Context HttpServletRequest request, @Context Header header,
			@ApiParam(value = "code of DictCollection of DictItem that need to be deleted", required = true) @PathParam("code") String code,
			@ApiParam(value = "itemCode of DictItemthat need to be deleted", required = true) @PathParam("itemCode") String itemCode
	);
	
	@GET
	@Path("/{code}/dictItems/{itemCode}/metadata")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get metadata of DictItem", response = String.class, notes="Get metadata of DictItem")
	@ApiResponses(value = {
	        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Return Metadata of DictItem", response = String.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
	        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
	        @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems")
	})
	public Response getMetaDataOfDictItem(@Context HttpServletRequest request, @Context Header header,
			@ApiParam(value = "code of DictCollection of DictItem that need to be deleted", required = true) @PathParam("code") String code,
			@ApiParam(value = "itemCode of DictItemthat need to be deleted", required = true) @PathParam("itemCode") String itemCode
	);
	
	@POST
	@Path("/{code}/dictItems/{itemCode}/metadata")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get metadata of DictItem", response = String.class, notes="Get metadata of DictItem")
	@ApiResponses(value = {
	        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Return Metadata of DictItem", response = String.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
	        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
	        @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems")
	})
	public Response addMetaDataOfDictItem(@Context HttpServletRequest request, @Context Header header,
			@ApiParam(value = "code of DictCollection of DictItem that need to be added meatadata", required = true) @PathParam("code") String code,
			@ApiParam(value = "itemCode of DictItemthat need to be added meatadata", required = true) @PathParam("itemCode") String itemCode,
			@ApiParam(value = "metadata of DictItem that need to added", required = true) String body
	);
	
	@PUT
	@Path("/{code}/dictItems/{itemCode}/metadata/{key}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Get metadata of DictItem", response = String.class, notes="Get metadata of DictItem")
	@ApiResponses(value = {
	        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Return Metadata of DictItem", response = String.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
	        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
	        @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems")
	})
	public Response updateMetaDataOfDictItem(@Context HttpServletRequest request, @Context Header header,
			@ApiParam(value = "code of DictCollection of DictItem that need to be updated meatadata", required = true) @PathParam("code") String code,
			@ApiParam(value = "itemCode of DictItemthat need to be updated meatadata", required = true) @PathParam("itemCode") String itemCode,
			@ApiParam(value = "metadata of DictItem that need to updated", required = true) @PathParam("key") String key,
			@ApiParam(value = "metadata of DictItem that need to updated", required = true) String body
	);
	
	@DELETE
	@Path("/{code}/dictItems/{itemCode}/metadata/{key}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Delete metadata of DictItem", response = String.class, notes="Delete metadata of DictItem")
	@ApiResponses(value = {
	        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Return Metadata of DictItem was deleted", response = String.class),
	        @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
	        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
	        @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server problems")
	})
	public Response deleteMetaDataOfDictItem(@Context HttpServletRequest request, @Context Header header,
			@ApiParam(value = "code of DictCollection of DictItem that need to be deleted meatadata", required = true) @PathParam("code") String code,
			@ApiParam(value = "itemCode of DictItemthat need to be deleted meatadata", required = true) @PathParam("itemCode") String itemCode,
			@ApiParam(value = "metadata of DictItem that need to deleted", required = true) @PathParam("key") String key,
			@ApiParam(value = "metadata of DictItem that need to deleted", required = true) String body
	);
}
