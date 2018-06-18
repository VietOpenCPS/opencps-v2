package io.swagger.api;

import io.swagger.model.ActionConfigItem;

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

@Path("/")
@Api(value = "/", description = "")
public interface ActionConfigApi  {

    @POST
    @Path("/actionconfig")
    @Consumes({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @Produces({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @ApiOperation(value = "Thêm định nghĩa thao tác xử lý hồ sơ", tags={ "ActionConfig",  })
    public ActionConfigItem addActionConfig(ActionConfigItem body);

    @DELETE
    @Path("/actionconfig/{actionCode}")
    @Produces({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @ApiOperation(value = "Xoá định nghĩa thao tác xử lý hồ sơ", tags={ "ActionConfig",  })
    public void deleteActionConfig(@PathParam("actionCode") String actionCode);

    @GET
    @Path("/actionconfig/{actionCode}")
    @Consumes({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @Produces({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @ApiOperation(value = "Lấy danh sách các định nghĩa thao tác theo actionCode", tags={ "ActionConfig",  })
    public ActionConfigItem getActionConfigByCode(@PathParam("actionCode") String actionCode);

    @GET
    @Path("/actionconfig/_search")
    @Consumes({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @Produces({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @ApiOperation(value = "Lấy danh sách các định nghĩa thao tác", tags={ "ActionConfig",  })
    public List<ActionConfigItem> getActionConfigs(@QueryParam("q") String q);

    @PUT
    @Path("/actionconfig/{actionCode}")
    @Consumes({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @Produces({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @ApiOperation(value = "Cập nhật định nghĩa thao tác xử lý hồ sơ", tags={ "ActionConfig" })
    public ActionConfigItem updateActionConfig(@PathParam("actionCode") String actionCode, ActionConfigItem body);
}

