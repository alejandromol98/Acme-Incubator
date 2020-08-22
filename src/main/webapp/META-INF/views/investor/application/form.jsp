<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="investor.application.form.label.ticker" path="ticker"/>
	<acme:form-moment code="investor.application.form.label.moment" path="moment"/>
	<acme:form-textarea code="investor.application.form.label.statement" path="statement"/>
	<acme:form-money code="investor.application.form.label.offer" path="offer"/>
	<acme:menu-separator/>
	<acme:form-textbox code="investor.application.form.label.invRoundTicker" path="investmentRoundTicker"/>
	<acme:form-money code="investor.application.form.label.invRoundAmount" path="investmentRoundAmount"/>
	
	<acme:form-return code="investor.application.form.button.return"/>
</acme:form>