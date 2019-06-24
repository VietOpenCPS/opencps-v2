package org.opencps.rest.opencps.api;

import org.opencps.rest.opencps.model.ActionConfigItem;
import org.opencps.rest.opencps.model.ActionConfigItemResult;

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
public interface ActionConfigApi  {

    @POST
    @Path("/actionconfigs")
    @Consumes({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @Produces({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @ApiOperation(value = "Thêm định nghĩa thao tác xử lý hồ sơ", tags={ "ActionConfig",  })
    public ActionConfigItem addActionConfig(ActionConfigItem body);

    @DELETE
    @Path("/actionconfigs/{id}")
    @Produces({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @ApiOperation(value = "Xoá định nghĩa thao tác xử lý hồ sơ", tags={ "ActionConfig",  })
    public void deleteActionConfig(@PathParam("id") String id);

    @GET
    @Path("/actionconfigs/{id}")
    @Consumes({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @Produces({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @ApiOperation(value = "Lấy danh sách các định nghĩa thao tác theo id Hoặc actionCode", tags={ "ActionConfig",  })
    public ActionConfigItem getActionConfigByCode(@PathParam("id") String id);

    @GET
    @Path("/actionconfigs/_search")
    @Consumes({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @Produces({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @ApiOperation(value = "Lấy danh sách các định nghĩa thao tác", tags={ "ActionConfig",  })
    public ActionConfigItemResult getActionConfigsElasticsearch(@QueryParam("q") String q);

    @PUT
    @Path("/actionconfigs/{id}")
    @Consumes({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @Produces({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @ApiOperation(value = "Cập nhật định nghĩa thao tác xử lý hồ sơ", tags={ "ActionConfig" })
    public ActionConfigItem updateActionConfig(@PathParam("id") String id, ActionConfigItem body);
}

