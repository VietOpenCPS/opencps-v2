package org.opencps.rest.opencps.api;

import org.opencps.rest.opencps.model.StepConfigItem;
import org.opencps.rest.opencps.model.StepConfigItemResult;

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
public interface StepConfigApi  {

    @POST
    @Path("/stepconfigs")
    @Consumes({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @Produces({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @ApiOperation(value = "Thêm định nghĩa step configs", tags={ "StepConfig",  })
    public StepConfigItem addStepConfig(StepConfigItem body);

    @DELETE
    @Path("/stepconfigs/{id}")
    @Produces({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @ApiOperation(value = "Xoá định nghĩa step", tags={ "StepConfig",  })
    public void deleteStepConfig(@PathParam("id") String id);

    @GET
    @Path("/stepconfigs/{id}")
    @Consumes({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @Produces({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @ApiOperation(value = "Lấy danh sách các định nghĩa cấu hình step theo id hoặc group", tags={ "StepConfig",  })
    public StepConfigItem getStepConfigByCode(@PathParam("id") String id);

    @GET
    @Path("/stepconfigs/status/{mainStatus}/{subStatus}")
    @Consumes({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @Produces({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @ApiOperation(value = "Thêm định nghĩa step configs", tags={ "StepConfig",  })
    public StepConfigItemResult getStepConfigByMainStatusAndSubStatus(@PathParam("mainStatus") String mainStatus, @PathParam("subStatus") String subStatus);

    @GET
    @Path("/stepconfigs/_search")
    @Consumes({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @Produces({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @ApiOperation(value = "Lấy danh sách các định nghĩa step", tags={ "StepConfig",  })
    public StepConfigItemResult getStepConfigsElasticsearch(@QueryParam("q") String q);

    @PUT
    @Path("/stepconfigs/{id}")
    @Consumes({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @Produces({ "application/json", "application/xml", "application/x-www-form-urlencoded" })
    @ApiOperation(value = "Cập nhật định nghĩa step", tags={ "StepConfig" })
    public StepConfigItem updateStepConfig(@PathParam("id") String id, StepConfigItem body);
}

