<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="investor.application.form.label.ticker" path="ticker"/>
	<jstl:if test="${ command != 'create' }">
		<acme:form-textbox code="investor.application.form.label.status" path="status" readonly="true"/>
		<acme:form-moment code="investor.application.form.label.moment" path="moment" readonly="true"/>
	</jstl:if>
	
	<acme:form-textarea code="investor.application.form.label.statement" path="statement"/>
	<acme:form-money code="investor.application.form.label.offer" path="offer"/>
	
	<jstl:if test="${ status!= 'PENDING' && command !='create'}">
		<acme:form-textarea code="investor.application.form.label.justification" path="justification"/>
	</jstl:if>
	<acme:menu-separator/>
	
	<jstl:if test="${ command != 'create' }">
		<acme:form-textbox code="investor.application.form.label.invRoundTicker" path="investmentRoundTicker" readonly="true"/>
		<acme:form-money code="investor.application.form.label.invRoundAmount" path="investmentRoundAmount" readonly="true"/>
	</jstl:if>
	
	<acme:form-submit test="${command == 'create'}" code="investor.application.form.button.create"
			action="/investor/application/create?invId=${invId}" />
	
	<acme:form-return code="investor.application.form.button.return"/>
</acme:form>