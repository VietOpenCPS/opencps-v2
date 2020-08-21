package org.opencps.usermgt.lifecycle;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.opencps.backend.usermgt.service.util.ConfigConstants;
import org.opencps.usermgt.action.util.HttpUtil;
import org.opencps.usermgt.action.util.UserMgtConfigUtil;
import org.opencps.usermgt.constants.CommonTerm;
import org.opencps.usermgt.model.TrackClient;
import org.opencps.usermgt.model.UserLogin;
import org.opencps.usermgt.service.TrackClientLocalServiceUtil;
import org.opencps.usermgt.service.TrackClientStatisticLocalServiceUtil;
import org.opencps.usermgt.service.UserLoginLocalServiceUtil;
import org.opencps.usermgt.service.UserTrackPathLocalServiceUtil;
import org.osgi.service.component.annotations.Component;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceResolver;
import org.springframework.mobile.device.LiteDeviceResolver;

@Component(immediate = true, property = { "key=servlet.service.events.post" }, service = LifecycleAction.class)
public class OpenCPSTraceAction extends Action {

	@Override
	public void run(HttpServletRequest request, HttpServletResponse response) {
        _log.info("### Start Post Trace Action ######################");
		String completeUrl = (String) request.getAttribute(CommonTerm.LOGIN_ACTION_CURRENT_COMPLETE_URL);

		String sessionId = request.getSession() != null ? request.getSession().getId() : StringPool.BLANK;
        String clientIP = HttpUtil.getPublicIP(request);
        String cityName = StringPool.BLANK;
        String latitude = StringPool.BLANK;
        String longitude = StringPool.BLANK;
        boolean isMobile = false;
        boolean isDesktop = false;
        boolean isTablet = false;

        DeviceResolver resolver = new LiteDeviceResolver();
        Device currentDevice = resolver.resolveDevice(request);
        if (currentDevice != null) {
			if(currentDevice.isMobile()) {
				/* Mobile */
				isMobile = true;
			}
			if(currentDevice.isTablet()) {
				/* Tablet */
				isTablet = true;
			}
			if(currentDevice.isNormal()) {
				/* Desktop */
				isDesktop = true;
			}
		}
        
		try {
//        	InputStream geoStream = this.getClass().getResourceAsStream("/GeoLite2-City.mmdb");
			File database = new File(UserMgtConfigUtil.getGeoDBPath());
			if (database != null) {
				DatabaseReader dbReader = new DatabaseReader.Builder(database).build();
				InetAddress ipAddress = InetAddress.getByName(clientIP);
				if (dbReader != null && ipAddress != null) {
					CityResponse cityResponse = dbReader.city(ipAddress);
					if (cityResponse != null) {
						cityName = cityResponse.getCity().getName();
						latitude =
								cityResponse.getLocation().getLatitude().toString();
						longitude =
								cityResponse.getLocation().getLongitude().toString();
					}
				}
			}
	        
		} catch (IOException | GeoIp2Exception e) {
			_log.debug(e);
		}
		Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        Date now = new Date();
        
		if (UserMgtConfigUtil.isTrackClientEnable()) {
			TrackClient previousPage = TrackClientLocalServiceUtil.findPreviousPage(sessionId);
			if (previousPage != null && previousPage.getUrl().equals(completeUrl))
			{
					long timeOnPage = now.getTime() - previousPage.getVisitDate().getTime();
					previousPage.setLeaveDate(now);
					previousPage.setTimeOnPage(timeOnPage);

					TrackClientLocalServiceUtil.updateTrackClient(previousPage);
			}
			else
			{
				if (previousPage != null)
				{
					long timeOnPage = now.getTime() - previousPage.getVisitDate().getTime();
					previousPage.setLeaveDate(now);
					previousPage.setTimeOnPage(timeOnPage);

					TrackClientLocalServiceUtil.updateTrackClient(previousPage);
				}

				c.setTime(now);
				int timeIncrease = ConfigConstants.OPENCPS_DEFAULT_TIME_LEAVE_INCREMENT;
				c.add(Calendar.SECOND , timeIncrease);
				Date leaveDate = c.getTime();

				TrackClientLocalServiceUtil
					.updateTrackClient(0,sessionId,completeUrl,year,month,day,now,leaveDate,clientIP,StringPool.BLANK,cityName,
						StringPool.BLANK,latitude,longitude,5,isDesktop,isMobile,isTablet);
				TrackClientStatisticLocalServiceUtil.updateStatisticTotal(completeUrl,year,month,day,cityName,isDesktop,isMobile,isTablet);
			}
		}
		
		if (UserMgtConfigUtil.isTrackUserEnable()) {
			long userId = request.getAttribute(CommonTerm.LOGIN_ACTION_USER_ID) != null ? (Long) request.getAttribute(CommonTerm.LOGIN_ACTION_USER_ID) : 0;

			completeUrl = (String) request.getAttribute(CommonTerm.LOGIN_ACTION_CURRENT_COMPLETE_URL);

			sessionId = request.getSession() != null ? request.getSession().getId() : StringPool.BLANK;
			try {
				UserLogin userLogin = UserLoginLocalServiceUtil.fetchByU_S(userId, sessionId);
				if (userLogin != null) {
					userLogin.setHits(userLogin.getHits() + 1);
					userLogin.setModifiedDate(new Date());
					UserLoginLocalServiceUtil.updateUserLogin(userLogin);

					UserTrackPathLocalServiceUtil.updateUserTrackPath(userLogin.getCompanyId(), 0,
							userLogin.getUserLoginId(), completeUrl, new Date());
				}
			} catch (SystemException e) {
				_log.debug(e);
			}
		}
        _log.info("### End Post Trace Action ######################");
	}

	private static final Log _log = LogFactoryUtil.getLog(OpenCPSTraceAction.class);
}
