package org.opencps.api.controller.impl;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.Validator;

import javax.servlet.http.HttpServletRequest;

import org.opencps.api.controller.UsersApi;
import org.opencps.api.errors.OpenCPSNotFoundException;
import org.opencps.api.model.UsersUserItem;
import org.opencps.usermgt.action.impl.UserActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-10-04T01:52:35.852Z")

@RestController
public class UsersApiController implements UsersApi {

	private static final Log _log = LogFactoryUtil.getLog(UsersApiController.class);

	private static UserActions actions = new UserActions();

	@Autowired
	private final HttpServletRequest request;

	@Autowired
	public UsersApiController(HttpServletRequest request) {
		this.request = request;
	}

	public ResponseEntity<String> getUseKeyPreferencesById(
			@ApiParam(value = "id của user", required = true) @PathVariable("id") String id,
			@ApiParam(value = "key của preferences", required = true) @PathVariable("key") String key) {
		return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
	}

	public ResponseEntity<byte[]> getUsePhotorById(
			@ApiParam(value = "id của user", required = true) @PathVariable("id") String id) {
		return new ResponseEntity<byte[]>(HttpStatus.NOT_IMPLEMENTED);
	}

	public ResponseEntity<String> getUsePreferencesById(
			@ApiParam(value = "id của user", required = true) @PathVariable("id") String id) {
		return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
	}

	public ResponseEntity<UsersUserItem> getUserById(
			@ApiParam(value = "id của user", required = true) @PathVariable("id") String id) {
		
		if (Validator.isNull(id)) {

			throw new OpenCPSNotFoundException(User.class.getName());

		} else {
			
			String userData = actions.getUserById(Long.valueOf(id));
			
			if (Validator.isNull(userData)) {
				throw new OpenCPSNotFoundException(User.class.getName());
			}
			
			return new ResponseEntity<UsersUserItem>(JSONFactoryUtil.looseDeserialize(userData, UsersUserItem.class), HttpStatus.OK);

		}

	}

	public ResponseEntity<Void> putUsePreferencesById(
			@ApiParam(value = "id của user", required = true) @PathVariable("id") String id,
			@ApiParam(value = "") @RequestBody String body) {
		return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
	}
}
