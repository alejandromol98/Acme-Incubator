<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="bookkeeper.investmentRound.form.label.ticker" path="ticker"/>
	<acme:form-checkbox code="bookkeeper.investmentRound.form.label.isFinalMode" path="isFinalMode"/>
	<acme:form-textbox code="bookkeeper.investmentRound.form.label.title" path="title"/>
	<acme:form-moment code="bookkeeper.investmentRound.form.label.moment" path="moment"/>
	<acme:form-textbox code="bookkeeper.investmentRound.form.label.kind" path="kind"/>
	<acme:form-textarea code="bookkeeper.investmentRound.form.label.description" path="description"/>
	<acme:form-money code="bookkeeper.investmentRound.form.label.amount" path="amount"/>
	<acme:form-url code="bookkeeper.investmentRound.form.label.moreInfo" path="moreInfo"/>
	
	<acme:form-hidden path="invId" />
	<acme:form-submit method="get" code="bookkeeper.accountingRecord.form.button.create"
			action="/bookkeeper/accounting-record/create?invId=${id}" />
	<acme:form-submit code="bookkeeper.investmentRound.form.label.workProgrammes" method="get" action="/authenticated/work-programme/list?id=${id}" />
	<acme:form-submit code="bookkeeper.investmentRound.form.label.accountingRecord" method="get" action="/authenticated/accounting-record/list?id=${id}" />
	
	<acme:form-return code="bookkeeper.investmentRound.form.button.return"/>
</acme:form>