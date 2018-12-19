package backend.feedback.action;

import java.util.LinkedHashMap;

import javax.ws.rs.NotFoundException;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

import backend.feedback.exception.NoSuchVotingException;
import backend.feedback.exception.NoSuchVotingResultException;
import backend.feedback.model.Voting;
import backend.feedback.model.VotingResult;

public interface VotingActions {

	public JSONObject getVotingList(long userId, long companyId, long groupId, String className,
			String classPK, int start, int end, ServiceContext serviceContext);

	public Voting addVote(long userId, long companyId, long groupId, String className, String classPK, String subject,
			String templateNo, String choices, boolean commentable, ServiceContext serviceContext)
			throws PortalException, SystemException;

	public VotingResult addVotingResult(long userId, long companyId, long groupId, long votingId, String email,
			String comment, String selected, ServiceContext serviceContext) throws PortalException, SystemException;

	public void removeVotingResult(long votingId, long votingResultId, ServiceContext serviceContext)
			throws NoSuchVotingResultException;

	public JSONObject getVotingResults(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext);

	public Voting updateVoting(long userId, long companyId, long groupId, long votingId, String className, String classPK,
			String subject, String templateNo, String choices, Boolean commentable, ServiceContext serviceContext)
			throws PortalException, SystemException;

	public void deleteVoting(long votingId, ServiceContext serviceContext)
			throws NotFoundException, NoSuchVotingException;

	public JSONObject getVotingResultStatistic(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext);
}
