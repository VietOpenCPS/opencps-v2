package org.opencps.usermgt.lifecycle;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
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

import org.opencps.usermgt.action.util.HttpUtil;
import org.opencps.usermgt.action.util.UserMgtConfigUtil;
import org.opencps.usermgt.constants.CommonTerm;
import org.opencps.usermgt.service.TrackClientLocalServiceUtil;
import org.opencps.usermgt.service.UserLoginLocalServiceUtil;
import org.osgi.service.component.annotations.Component;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceResolver;
import org.springframework.mobile.device.LiteDeviceResolver;

@Component(immediate = true, property = { "key=login.events.post" }, service = LifecycleAction.class)
public class OpenCPSLoginAction extends Action {
	@Override
	public void run(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        _log.info("### Start Post Login Action ######################");
        if (UserMgtConfigUtil.isTrackClientEnable()) {
			String completeUrl = (String) request.getAttribute(CommonTerm.LOGIN_ACTION_CURRENT_COMPLETE_URL);
			String sessionId = request.getSession() != null ? request.getSession().getId() : StringPool.BLANK;
	        String clientIP = HttpUtil.getPublicIP(request);
	        String cityName = StringPool.BLANK;
	        String latitude = StringPool.BLANK;
	        String longitude = StringPool.BLANK;
        	
			try {
//	        	InputStream geoStream = this.getClass().getResourceAsStream("/GeoLite2-City.mmdb");
				File database = new File(UserMgtConfigUtil.getGeoDBPath());
				DatabaseReader dbReader = new DatabaseReader.Builder(database).build();
				InetAddress ipAddress = InetAddress.getByName(clientIP);
		        CityResponse cityResponse = dbReader.city(ipAddress);
		         
		        cityName = cityResponse.getCity().getName();
		        latitude = 
		        		cityResponse.getLocation().getLatitude().toString();
		        longitude = 
		        		cityResponse.getLocation().getLongitude().toString();
		        
			} catch (IOException e) {
				_log.debug(e);
			} catch (GeoIp2Exception e) {
				_log.debug(e);
			}
	        Calendar c = Calendar.getInstance();
	        int year = c.get(Calendar.YEAR);
	        int month = c.get(Calendar.MONTH) + 1;
	        int day = c.get(Calendar.DAY_OF_MONTH);
	        Date now = new Date();
	        boolean isMobile = false;
	        boolean isDesktop = false;
	        boolean isTablet = false;
	        
	        DeviceResolver resolver = new LiteDeviceResolver();
	        
	        Device currentDevice = resolver.resolveDevice(request);
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
			Long userId = request.getAttribute(CommonTerm.LOGIN_ACTION_USER_ID) != null ? (Long) request.getAttribute(CommonTerm.LOGIN_ACTION_USER_ID) : 0;
			
			User user = UserLocalServiceUtil.fetchUser(userId);
			TrackClientLocalServiceUtil.updateTrackClient(0, sessionId, completeUrl, year, month, day, now, null, clientIP, StringPool.BLANK, cityName, StringPool.BLANK, latitude, longitude, 0, isDesktop, isMobile, isTablet, userId, user.getScreenName());
        }
		if (UserMgtConfigUtil.isTrackUserEnable()) {
			Long userId = request.getAttribute(CommonTerm.LOGIN_ACTION_USER_ID) != null ? (Long) request.getAttribute(CommonTerm.LOGIN_ACTION_USER_ID) : 0;
			
			User user = UserLocalServiceUtil.fetchUser(userId);
			String sessionId = request.getSession() != null ? request.getSession().getId() : StringPool.BLANK;
	        String clientIP = HttpUtil.getPublicIP(request);
	        

			try {
				UserLoginLocalServiceUtil.updateUserLogin(user.getCompanyId(), user.getGroupId(), userId,
						user.getFullName(), new Date(), new Date(), 0l, sessionId, 0, null, clientIP);
			} catch (SystemException e) {
				_log.debug(e);
			} catch (PortalException e) {
				_log.debug(e);
			}
		}

        _log.info("### End Post Login Action ######################");
	}

	private static Log _log = LogFactoryUtil.getLog(OpenCPSLoginAction.class);
}
