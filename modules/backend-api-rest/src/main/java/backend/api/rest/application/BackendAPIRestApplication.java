
package backend.api.rest.application;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.HttpURLConnection;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.controller.impl.*;
import org.opencps.api.controller.util.MessageUtil;
import org.opencps.api.filter.KeyGenerator;
import org.opencps.api.filter.OpenCPSKeyGenerator;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.background.model.CountEntity;
import org.opencps.communication.model.Notificationtemplate;
import org.opencps.communication.service.NotificationtemplateLocalServiceUtil;
import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.DossierTemplateActions;
import org.opencps.dossiermgt.action.impl.DossierActionsImpl;
import org.opencps.dossiermgt.action.impl.DossierTemplateActionsImpl;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.impl.DossierStatisticImpl;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import uk.org.okapibarcode.backend.Code128;
import uk.org.okapibarcode.backend.HumanReadableLocation;
import uk.org.okapibarcode.backend.QrCode;
import uk.org.okapibarcode.backend.Symbol;
import uk.org.okapibarcode.output.Java2DRenderer;

@Component(property = {
	JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/secure/rest/v2",
	JaxrsWhiteboardConstants.JAX_RS_NAME + "=OpenCPS.restv2",
    "javax.portlet.resource-bundle=content.Language"
}, service = Application.class)
public class BackendAPIRestApplication extends Application {

	private static final Log _log = LogFactoryUtil.getLog(BackendAPIRestApplication.class);

	public Set<Object> getSingletons() {

		Set<Object> singletons = new HashSet<>();

		// add REST endpoints (resources)
		singletons.add(new ApplicantManagementImpl());
		singletons.add(new ServiceInfoManagementImpl());
		singletons.add(new ServiceConfigManagementImpl());
		singletons.add(new DossierTemplateManagementImpl());
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
		singletons.add(new UserInfoLogManagementImpl());
		//
		singletons.add(new CertNumberManagementImpl());
		singletons.add(new OneGateControllerImpl());
		singletons.add(new DossierDocumentManagementImpl());
		singletons.add(new DossierSyncManagementImpl());

		singletons.add(new SystemManagementImpl());
		singletons.add(new VotingManagementImpl());

		singletons.add(new DossierActionUserManagementImpl());
		singletons.add(new DefaultSignatureManagementImpl());

		singletons.add(new MenuRoleManagementImpl());
		singletons.add(new SMSManagementImpl());
		singletons.add(new BackupDataManagementImpl());

		singletons.add(new NotificationManagementImpl());
		singletons.add(new FaqManagementImpl());
		singletons.add(new CacheTestManagementImpl());
		singletons.add(new ImportDataManagementImpl());
		singletons.add(new EFormManagementImpl());
		singletons.add(new BookingManagementImpl());
		singletons.add(new AdminConfigManagementImpl());
		singletons.add(new ProxyManagementImpl());
		singletons.add(new MenuConfigManagementImpl());
		singletons.add(new JasperUtilsManagermentImpl());
		singletons.add(new DVCQGSSOManagementImpl());
		
		singletons.add(new DVCQGIManagementImpl());
		singletons.add(new ConfigCounterManagementImpl());
		singletons.add(new VGCAManagementImpl());
		
		//Test send mail
		singletons.add(new MailTestManagementImpl());
		singletons.add(new NotarizationManagementImpl());
		singletons.add(new ReportRoleManagementImpl());
		
		singletons.add(new OAIManagementImpl());
		singletons.add(new NewsBroadManagementImpl());

		//Applicant Data
		singletons.add(new ApplicantDataManagementImpl());

		singletons.add(new AccessStatisticsManagementImpl());
		singletons.add(new LGSPIntegrationManagementImpl());
		
		singletons.add(new FileItemManagementImpl());

		singletons.add(new SaveFieldPickManagementImpl());
		singletons.add(new VNPostManagementImpl());
		singletons.add(new FrequencyOfficeManagementImpl());
		singletons.add(new QLVGManagementImpl());
		singletons.add(new QLCDManagementImpl());
		singletons.add(new Mofa2ManagementImpl());
		singletons.add(new SupportSearchManagementImpl());
//		singletons.add(new DossierStatisticManagementImpl());
		
		singletons.add(this);

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
	@Path("ping")
	@Produces("text/plain")
	public String ping() {

		return "ok";
	}

	@GET
	@Path("/barcode")
	@Consumes({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})

	public Response getBarcode(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@QueryParam("value") String value, @QueryParam("font") String font,
		@QueryParam("location") String location) {

		try {
			Code128 barcode = new Code128();
			barcode.setFontName(ConstantUtils.MONOSPACED);
			barcode.setFontSize(
				Validator.isNotNull(font)
					? Integer.valueOf(font) : ConstantUtils.DEFAULT_FONT_SIZE);
			barcode.setModuleWidth(2);
			barcode.setBarHeight(50);
			if (Validator.isNotNull(location) && Boolean.valueOf(location)) {
				barcode.setHumanReadableLocation(HumanReadableLocation.NONE);
			}
			else {
				barcode.setHumanReadableLocation(HumanReadableLocation.BOTTOM);
			}

			barcode.setContent(value);

			int width = barcode.getWidth();
			int height = barcode.getHeight();

			BufferedImage image =
				new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
			Graphics2D g2d = image.createGraphics();
			Java2DRenderer renderer =
				new Java2DRenderer(g2d, 1, Color.WHITE, Color.BLACK);
			renderer.render(barcode);
			String uuid = UUID.randomUUID().toString();
			File destDir = new File(ConstantUtils.BARCODE);
			if (!destDir.exists()) {
				destDir.mkdir();
			}
			String barcodeFilename = String.format(MessageUtil.getMessage(ConstantUtils.BARCODE_FILENAME), uuid);
			
			File file = new File(barcodeFilename);
			if (!file.exists()) {
				file.createNewFile();
			}
			if (file.exists()) {
				ImageIO.write(image, ConstantUtils.PNG, file);
				// String fileType = Files.probeContentType(file.toPath());
				ResponseBuilder responseBuilder = Response.ok((Object) file);

				String fileName = String.format(MessageUtil.getMessage(ConstantUtils.ATTACHMENT_FILENAME), file.getName());
				
				responseBuilder.header(
					ConstantUtils.CONTENT_DISPOSITION,
					fileName);
				responseBuilder.header(HttpHeaders.CONTENT_TYPE, ConstantUtils.MEDIA_TYPE_PNG);

				return responseBuilder.build();
			}
			else {
				return Response.status(
					HttpURLConnection.HTTP_NO_CONTENT).build();
			}

		}
		catch (Exception e) {
			// e.printStackTrace();
			_log.debug(e);
			return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build();
		}
	}

	@GET
	@Path("/qrcode")
	@Consumes({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})

	public Response getQRcode(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@QueryParam("value") String value) {

		try {
			QrCode qrcode = new QrCode();
			qrcode.setHumanReadableLocation(HumanReadableLocation.BOTTOM);
			qrcode.setDataType(Symbol.DataType.ECI);
			qrcode.setContent(value);

			int width = qrcode.getWidth();
			int height = qrcode.getHeight();

			BufferedImage image =
				new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
			Graphics2D g2d = image.createGraphics();
			Java2DRenderer renderer =
				new Java2DRenderer(g2d, 1, Color.WHITE, Color.BLACK);
			renderer.render(qrcode);
			String uuid = UUID.randomUUID().toString();
			File destDir = new File(ConstantUtils.BARCODE);
			if (!destDir.exists()) {
				destDir.mkdir();
			}
			String barCodeFileName = String.format(MessageUtil.getMessage(ConstantUtils.BARCODE_FILENAME), uuid);
			File file = new File(barCodeFileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			if (file.exists()) {
				ImageIO.write(image, ConstantUtils.PNG, file);
				// String fileType = Files.probeContentType(file.toPath());
				ResponseBuilder responseBuilder = Response.ok((Object) file);
				String fileName = String.format(MessageUtil.getMessage(ConstantUtils.ATTACHMENT_FILENAME), file.getName());
				
				responseBuilder.header(
					ConstantUtils.CONTENT_DISPOSITION,
					fileName);
				responseBuilder.header(HttpHeaders.CONTENT_TYPE, ConstantUtils.MEDIA_TYPE_PNG);

				return responseBuilder.build();
			}
			else {
				return Response.status(
					HttpURLConnection.HTTP_NO_CONTENT).build();
			}

		}
		catch (Exception e) {
			// e.printStackTrace();
			_log.debug(e);
			return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build();
		}
	}

	@POST
	@Path("/login")
	@Consumes({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_FORM_URLENCODED
	})
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	public Response authenticateUser(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext) {

		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			// Issue a token for the user
			String token = issueToken(user.getEmailAddress());
			JSONObject result = JSONFactoryUtil.createJSONObject();
			result.put(ConstantUtils.TOKEN, token);

			// Return the token on the response
			String authorization = String.format(MessageUtil.getMessage(ConstantUtils.HTTP_HEADER_BEARER), token);
			
			return Response.ok().header(
				AUTHORIZATION, authorization).entity(
					result.toJSONString()).build();

		}
		catch (Exception e) {
			// e.printStackTrace();
			_log.debug(e);
			return Response.status(UNAUTHORIZED).build();
		}
	}

	private String issueToken(String login) {

		KeyGenerator keyGenerator = new OpenCPSKeyGenerator();

		Key key = keyGenerator.generateKey();
		String jwtToken = Jwts.builder().setSubject(login).setIssuer(
			uriInfo.getAbsolutePath().toString()).setIssuedAt(
				new Date()).setExpiration(
					toDate(LocalDateTime.now().plusMinutes(15L))).signWith(
						key, SignatureAlgorithm.HS512).compact();
		return jwtToken;

	}

	private Date toDate(LocalDateTime localDateTime) {

		return Date.from(
			localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	@GET
	@Path("/count/{className}")
	@Consumes({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})

	public Response countEntity(
		@Context HttpServletRequest request, @Context HttpHeaders header,
		@Context Company company, @Context Locale locale, @Context User user,
		@Context ServiceContext serviceContext,
		@PathParam("className") String className) {

		CountEntity result = new CountEntity();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		long countDatabase = 0;
		long countLucene = 0;

		if (Notificationtemplate.class.getName().equals(className)) {
			countDatabase =
				NotificationtemplateLocalServiceUtil.countNotificationTemplateByGroupId(
					groupId);
			LinkedHashMap<String, Object> params =
				new LinkedHashMap<String, Object>();
			DossierTemplateActions actions = new DossierTemplateActionsImpl();

			params.put(Field.GROUP_ID, String.valueOf(groupId));

			Sort[] sorts = new Sort[] {};

			JSONObject jsonData;
			try {
				jsonData = actions.getDossierTemplates(
					user.getUserId(), serviceContext.getCompanyId(), groupId,
					params, sorts, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					serviceContext);
				countLucene = jsonData.getInt(ConstantUtils.TOTAL);
			}
			catch (PortalException e) {
				_log.error(e);
			}

		}
		else if (Dossier.class.getName().equals(className)) {
			LinkedHashMap<String, Object> params =
				new LinkedHashMap<String, Object>();
			params.put(Field.GROUP_ID, String.valueOf(groupId));
			DossierActions actions = new DossierActionsImpl();

			JSONObject jsonData = actions.getDossiers(
				user.getUserId(), company.getCompanyId(), groupId, params, null,
				-1, -1, serviceContext);

			countLucene = jsonData.getLong(ConstantUtils.TOTAL);
			countDatabase =
				DossierLocalServiceUtil.countDossierByGroup(groupId);
		}

		result.setDatabase(countDatabase);
		result.setLucene(countLucene);

		return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
	}
	@Context
	private UriInfo uriInfo;

	@Reference
	private CompanyContextProvider _companyContextProvider;

	@Reference
	private LocaleContextProvider _localeContextProvider;

	@Reference
	private UserContextProvider _userContextProvider;

	@Reference
	private ServiceContextProvider _serviceContextProvider;

}