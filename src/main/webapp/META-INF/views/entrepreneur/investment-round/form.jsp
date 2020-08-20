<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="entrepreneur.investmentRound.form.label.ticker" path="ticker"/>
	<acme:form-textbox code="entrepreneur.investmentRound.form.label.title" path="title"/>
	<acme:form-moment code="entrepreneur.investmentRound.form.label.moment" path="moment"/>
	<acme:form-textbox code="entrepreneur.investmentRound.form.label.kind" path="kind"/>
	<acme:form-textarea code="entrepreneur.investmentRound.form.label.description" path="description"/>
	<acme:form-money code="entrepreneur.investmentRound.form.label.amount" path="amount"/>
	<acme:form-url code="entrepreneur.investmentRound.form.label.moreInfo" path="moreInfo"/>
	
	<acme:form-return code="authenticated.investmentRound.form.button.return"/>
</acme:form>