<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="entrepreneur.application.form.label.ticker" path="ticker"/>
	<acme:form-textbox code="entrepreneur.application.form.label.status" path="status"/>
	<acme:form-moment code="entrepreneur.application.form.label.moment" path="moment"/>
	<acme:form-textarea code="entrepreneur.application.form.label.statement" path="statement"/>
	<acme:form-money code="entrepreneur.application.form.label.offer" path="offer"/>
	<acme:form-textbox code="entrepreneur.application.form.label.applicationInvestor" path="applicationInvestor"/>
	<acme:menu-separator/>
	<acme:form-textbox code="entrepreneur.application.form.label.invRoundTicker" path="investmentRoundTicker"/>
	<acme:form-money code="entrepreneur.application.form.label.invRoundAmount" path="investmentRoundAmount"/>
	
	
	<acme:form-return code="entrepreneur.application.form.button.return"/>
</acme:form>