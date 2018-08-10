
package backend.api.rest.application;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.HttpURLConnection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.opencps.api.context.provider.CompanyContextProvider;
import org.opencps.api.context.provider.LocaleContextProvider;
import org.opencps.api.context.provider.ServiceContextProvider;
import org.opencps.api.context.provider.UserContextProvider;
import org.opencps.api.controller.impl.ApplicantManagementImpl;
import org.opencps.api.controller.impl.CertNumberManagementImpl;
import org.opencps.api.controller.impl.CommentManagementImpl;
import org.opencps.api.controller.impl.DataManagementImpl;
import org.opencps.api.controller.impl.DataTempManagementImpl;
import org.opencps.api.controller.impl.DeliverableTypesManagementImpl;
import org.opencps.api.controller.impl.DeliverablesLogManagementImpl;
import org.opencps.api.controller.impl.DeliverablesManagementImpl;
import org.opencps.api.controller.impl.DossierActionManagementImpl;
import org.opencps.api.controller.impl.DossierDocumentManagementImpl;
import org.opencps.api.controller.impl.DossierFileManagementImpl;
import org.opencps.api.controller.impl.DossierLogManagementImpl;
import org.opencps.api.controller.impl.DossierManagementImpl;
import org.opencps.api.controller.impl.DossierSyncManagementImpl;
import org.opencps.api.controller.impl.DossierTemplateManagementImpl;
import org.opencps.api.controller.impl.EmployeeManagementImpl;
import org.opencps.api.controller.impl.FileAttachManagementImpl;
import org.opencps.api.controller.impl.HolidayManagementImpl;
import org.opencps.api.controller.impl.JobposManagementImpl;
import org.opencps.api.controller.impl.NotificationQueueManagementImpl;
import org.opencps.api.controller.impl.NotificationTemplateImpl;
import org.opencps.api.controller.impl.NotificationTypeManagementImpl;
import org.opencps.api.controller.impl.OfficeSiteManagementImpl;
import org.opencps.api.controller.impl.OneGateControllerImpl;
import org.opencps.api.controller.impl.PaymentConfigManagementImpl;
import org.opencps.api.controller.impl.PaymentFileManagementImpl;
import org.opencps.api.controller.impl.ProcessPluginManagementImpl;
import org.opencps.api.controller.impl.RegistrationFormManagementImpl;
import org.opencps.api.controller.impl.RegistrationLogManagementImpl;
import org.opencps.api.controller.impl.RegistrationManagementImpl;
import org.opencps.api.controller.impl.RegistrationTemplatesManagementImpl;
import org.opencps.api.controller.impl.ServerConfigManagementImpl;
import org.opencps.api.controller.impl.ServiceConfigManagementImpl;
import org.opencps.api.controller.impl.ServiceInfoManagementImpl;
import org.opencps.api.controller.impl.ServiceProcessManagementImpl;
import org.opencps.api.controller.impl.SignatureManagementImpl;
import org.opencps.api.controller.impl.StatisticManagementImpl;
import org.opencps.api.controller.impl.UserInfoLogManagementImpl;
import org.opencps.api.controller.impl.UserManagementImpl;
import org.opencps.api.controller.impl.WorkTimeManagementImpl;
import org.opencps.api.controller.impl.WorkingUnitManagementImpl;
import org.opencps.dossiermgt.model.impl.DossierStatisticImpl;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import uk.org.okapibarcode.backend.Code128;
import uk.org.okapibarcode.backend.HumanReadableLocation;
import uk.org.okapibarcode.backend.QrCode;
import uk.org.okapibarcode.backend.Symbol;
import uk.org.okapibarcode.output.Java2DRenderer;

@ApplicationPath("/v2")
@Component(immediate = true, property={"jaxrs.application=true"}, service = Application.class)
public class BackendAPIRestApplication extends Application {

	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<Object>();
		
		
		// add REST endpoints (resources)
		singletons.add(new ApplicantManagementImpl());
		singletons.add(new ServiceInfoManagementImpl());
		singletons.add( new ServiceConfigManagementImpl());
		singletons.add( new DossierTemplateManagementImpl());
		singletons.add(new ServiceProcessManagementImpl());
		singletons.add(new PaymentConfigManagementImpl());
		singletons.add(new PaymentFileManagementImpl());
		singletons.add(new DossierManagementImpl());
		singletons.add(new DossierFileManagementImpl());
		singletons.add(new DossierActionManagementImpl());
		singletons.add(new DossierLogManagementImpl());
		singletons.add(new ServerConfigManagementImpl());

		singletons.add(new DataManagementImpl());
		singletons.add(new HolidayManagementImpl());
		singletons.add(new WorkTimeManagementImpl());
		singletons.add(new NotificationTemplateImpl());
		singletons.add(new NotificationTypeManagementImpl());
		singletons.add(new NotificationQueueManagementImpl());
		singletons.add(new OfficeSiteManagementImpl());
		singletons.add(new WorkingUnitManagementImpl());
		singletons.add(new JobposManagementImpl());
		singletons.add(new UserManagementImpl());
		singletons.add(new EmployeeManagementImpl());
		singletons.add(new DossierStatisticImpl());
		singletons.add(new FileAttachManagementImpl());
		singletons.add(new StatisticManagementImpl());
		singletons.add(new DeliverableTypesManagementImpl());
		//
		singletons.add(new DeliverablesManagementImpl());
		singletons.add(new DeliverablesLogManagementImpl());
		//
		singletons.add(new RegistrationTemplatesManagementImpl());
		singletons.add(new CommentManagementImpl());
		singletons.add(new RegistrationManagementImpl());
		singletons.add(new RegistrationFormManagementImpl());
		singletons.add(new RegistrationLogManagementImpl());
		singletons.add(new ProcessPluginManagementImpl());
		singletons.add(new SignatureManagementImpl());
		singletons.add(new DataTempManagementImpl());
		singletons.add(new UserInfoLogManagementImpl());
		//
		singletons.add(new CertNumberManagementImpl());
		singletons.add(new OneGateControllerImpl());
		singletons.add(new DossierDocumentManagementImpl());
		singletons.add(new DossierSyncManagementImpl());
		
		// add service provider
		singletons.add(_serviceContextProvider);
		singletons.add(_companyContextProvider);
		singletons.add(_localeContextProvider);
		singletons.add(_userContextProvider);
		
		return singletons;	
	}

	@GET
	@Path("chao")
	@Produces("text/plain")
	public String working() {
		return "It works!";
	}

	@GET
	@Path("/barcode")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })

	public Response getBarcode(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @QueryParam("value") String value) {
		try {
			Code128 barcode = new Code128();
			barcode.setFontName("Monospaced");
			barcode.setFontSize(16);
			barcode.setModuleWidth(2);
			barcode.setBarHeight(50);
			barcode.setHumanReadableLocation(HumanReadableLocation.BOTTOM);
			barcode.setContent(value);

			int width = barcode.getWidth();
			int height = barcode.getHeight();

			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
			Graphics2D g2d = image.createGraphics();
			Java2DRenderer renderer = new Java2DRenderer(g2d, 1, Color.WHITE, Color.BLACK);
			renderer.render(barcode);
			String uuid = UUID.randomUUID().toString();
			File destDir = new File("barcode");
			if (!destDir.exists()) {
				destDir.mkdir();
			}
			File file = new File("barcode/" + uuid + ".png");
			if (!file.exists()) {
				file.createNewFile();				
			}
			if (file.exists()) {
				ImageIO.write(image, "png", file);
	//			String fileType = Files.probeContentType(file.toPath());
				ResponseBuilder responseBuilder = Response.ok((Object) file);

				responseBuilder.header("Content-Disposition",
						"attachment; filename=\"" + file.getName() + "\"");
				responseBuilder.header("Content-Type", "image/png");

				return responseBuilder.build();
			} else {				
				return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build();
		}		
	}
	
	@GET
	@Path("/qrcode")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })

	public Response getQRcode(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, @QueryParam("value") String value) {
		try {
			QrCode qrcode = new QrCode();
			qrcode.setFontName("Monospaced");
			qrcode.setFontSize(16);
			qrcode.setModuleWidth(2);
			qrcode.setBarHeight(128);
			qrcode.setHumanReadableLocation(HumanReadableLocation.BOTTOM);
			qrcode.setDataType(Symbol.DataType.HIBC);
			qrcode.setContent(value);

			int width = qrcode.getWidth();
			int height = qrcode.getHeight();

			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
			Graphics2D g2d = image.createGraphics();
			Java2DRenderer renderer = new Java2DRenderer(g2d, 1, Color.WHITE, Color.BLACK);
			renderer.render(qrcode);
			String uuid = UUID.randomUUID().toString();
			File destDir = new File("barcode");
			if (!destDir.exists()) {
				destDir.mkdir();
			}
			File file = new File("barcode/" + uuid + ".png");
			if (!file.exists()) {
				file.createNewFile();				
			}
			if (file.exists()) {
				ImageIO.write(image, "png", file);
	//			String fileType = Files.probeContentType(file.toPath());
				ResponseBuilder responseBuilder = Response.ok((Object) file);

				responseBuilder.header("Content-Disposition",
						"attachment; filename=\"" + file.getName() + "\"");
				responseBuilder.header("Content-Type", "image/png");

				return responseBuilder.build();
			} else {				
				return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build();
		}		
	}
	
	@Reference
	private CompanyContextProvider _companyContextProvider;

	@Reference
	private LocaleContextProvider _localeContextProvider;

	@Reference
	private UserContextProvider _userContextProvider;

	@Reference
	private ServiceContextProvider _serviceContextProvider;


}