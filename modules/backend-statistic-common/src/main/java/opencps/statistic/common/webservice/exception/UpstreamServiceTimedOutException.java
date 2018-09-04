/**
 * OpenCPS is the open source Core Public Services software
 * Copyright (C) 2016-present OpenCPS community
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package opencps.statistic.common.webservice.exception;

/**
 * Throw this exception to indicate that an upstream service has timed out
 * 
 * @author khoavu
 *
 */
public class UpstreamServiceTimedOutException extends Exception {

	private static final long serialVersionUID = 8346042216738426156L;

	public UpstreamServiceTimedOutException() {

	}

	public UpstreamServiceTimedOutException(String message) {
		super(message);
	}

	public UpstreamServiceTimedOutException(Throwable t) {
		super(t);
	}

	public UpstreamServiceTimedOutException(String message, Throwable t) {
		super(message, t);
	}

}
