<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.investmentRound.form.label.ticker" path="ticker"/>
	<acme:form-textbox code="authenticated.investmentRound.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.investmentRound.form.label.moment" path="moment"/>
	<acme:form-textbox code="authenticated.investmentRound.form.label.kind" path="kind"/>
	<acme:form-textarea code="authenticated.investmentRound.form.label.description" path="description"/>
	<acme:form-money code="authenticated.investmentRound.form.label.amount" path="amount"/>
	<acme:form-url code="authenticated.investmentRound.form.label.moreInfo" path="moreInfo"/>
	<acme:form-textbox code="authenticated.investmentRound.form.label.entrepreneur" path="entrepreneur"/>
	
	<security:authorize access="hasRole('Investor')">
		<acme:form-hidden path="invId" />
		<acme:form-submit method="get" code="authenticated.application.form.button.create"
			action="/investor/application/create?invId=${id}" />
	</security:authorize>
	<security:authorize access="hasRole('Bookkeeper')">
		<acme:form-hidden path="invId" />
		<acme:form-submit method="get" code="authenticated.accountingRecord.form.button.create"
			action="/bookkeeper/accounting-record/create?invId=${id}" />
	</security:authorize>
	
	<!-- <acme:form-hidden path="invId" /> -->
	<acme:form-submit method="get" code="authenticated.discussionForum.form.button.create"
			action="/authenticated/discussion-forum/create?invId=${id}" />	
	
	<acme:form-hidden path="id"/>
	<acme:form-submit code="authenticated.investmentRound.form.label.workProgrammes" method="get" action="/authenticated/work-programme/list?id=${id}" />
	<acme:form-submit code="authenticated.investmentRound.form.label.accountingRecord" method="get" action="/authenticated/accounting-record/list?id=${id}" />
	
	<security:authorize access="hasRole('Investor')">
		<acme:form-submit code="entrepreneur.investmentRound.form.label.monema" method="get" action="/investor/monema/list?id=${id}" />
	</security:authorize>
	
	<acme:form-return code="authenticated.investmentRound.form.button.return"/>
</acme:form>
