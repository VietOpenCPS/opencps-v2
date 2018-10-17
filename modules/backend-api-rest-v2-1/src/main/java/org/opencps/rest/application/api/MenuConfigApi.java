package org.opencps.rest.application.api;

import org.opencps.rest.application.model.MenuConfigCountItemResults;
import org.opencps.rest.application.model.MenuConfigItem;
import org.opencps.rest.application.model.MenuConfigItemResult;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import org.apache.cxf.jaxrs.ext.multipart.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.jaxrs.PATCH;
import javax.validation.constraints.*;

import java.util.Locale; 
import javax.ws.rs.core.Context; 
import javax.ws.rs.core.HttpHeaders; 
import com.liferay.portal.kernel.model.Company; 
import com.liferay.portal.kernel.model.User; 
import com.liferay.portal.kernel.service.ServiceContext; 
@Path("/")
@Api(value = "/", description = "")
public interface MenuConfigApi  {

    @POST
    @Path("/menuconfigs")
    @Consumes({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @Produces({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @ApiOperation(value = "Thêm định nghĩa menu configs", tags={ "MenuConfig",  })
    public MenuConfigItem addMenuConfig(MenuConfigItem body);

    @DELETE
    @Path("/menuconfigs/{id}")
    @Produces({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @ApiOperation(value = "Xoá định nghĩa menu", tags={ "MenuConfig",  })
    public void deleteMenuConfig(@PathParam("id") String id);

    @GET
    @Path("/menuconfigs/{id}")
    @Consumes({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @Produces({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @ApiOperation(value = "Lấy danh sách các định nghĩa cấu hình menu theo id hoặc group", tags={ "MenuConfig",  })
    public MenuConfigItem getMenuConfigByCode(@PathParam("id") String id);

    @GET
    @Path("/menuconfigs/count")
    @Consumes({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @Produces({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @ApiOperation(value = "Lấy danh sách các định nghĩa thao tác todo counter", tags={ "MenuConfig",  })
    public MenuConfigCountItemResults getMenuConfigsCount(@QueryParam("q") String q);

    @GET
    @Path("/menuconfigs/_search")
    @Consumes({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @Produces({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @ApiOperation(value = "Lấy danh sách các định nghĩa thao tác", tags={ "MenuConfig",  })
    public MenuConfigItemResult getMenuConfigsElasticsearch(@QueryParam("q") String q);

    @GET
    @Path("/menuconfigs/todo")
    @Consumes({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @Produces({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @ApiOperation(value = "Lấy danh sách các định nghĩa thao tác todo", tags={ "MenuConfig",  })
    public MenuConfigItemResult getMenuConfigsTodo(@QueryParam("q") String q);

    @PUT
    @Path("/menuconfigs/{id}")
    @Consumes({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @Produces({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @ApiOperation(value = "Cập nhật định nghĩa menu", tags={ "MenuConfig" })
    public MenuConfigItem updateMenuConfig(@PathParam("id") String id, MenuConfigItem body);
}

