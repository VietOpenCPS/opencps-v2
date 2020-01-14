<%@ include file="init.jsp" %>

<%
	SessionCounter counter = null;

	int curVisitor = 1;
	
	long totalVisitor = 1;
	try{
		counter = (SessionCounter) session.getAttribute("counter");
		
		curVisitor = counter.getActiveSessionNumber();
		
// 		boolean incrementCount = (Boolean) session.getAttribute("incrementCount");

// 		if (incrementCount) {
// 			Date now = new Date();
// 			now = SessionTrackingUtil.roundDate(now);

// 			SessionTracking sessionTracking = SessionTrackingLocalServiceUtil.addTracking(now,
// 					themeDisplay.getScopeGroupId(), themeDisplay.getCompanyId());

// 		}

// 		totalVisitor = SessionTrackingLocalServiceUtil.countTotal(themeDisplay.getScopeGroupId());

	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<div class="visitor-tracking-wraper">
	<div class="current-visitor-online">
		<span class="tracking-label"><liferay-ui:message key="number-of-user-onlile"/> </span>
		<span class="tracking-value"><%=curVisitor %></span>
	</div>
	<!-- 
		<div class="total-visitor">
			<span class="tracking-label"><liferay-ui:message key="total-visitor"/> </span>
			<span class="tracking-value">totalVisitor</span>
		</div>
	 -->
</div>


