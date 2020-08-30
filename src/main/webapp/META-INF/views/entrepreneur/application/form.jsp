<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="entrepreneur.application.form.label.ticker" path="ticker" readonly="true"/>
	<acme:form-select code="entrepreneur.application.form.label.status" path="status"> 
		<acme:form-option code="entrepreneur.application.form.label.status.pending" value="PENDING" selected="${status == 'PENDING'}"/>
		<acme:form-option code="entrepreneur.application.form.label.status.accepted" value="ACCEPTED" selected="${status == 'ACCEPTED'}"/>
		<acme:form-option code="entrepreneur.application.form.label.status.rejected" value="REJECTED" selected="${status == 'REJECTED'}"/>
	</acme:form-select>
	<acme:form-moment code="entrepreneur.application.form.label.moment" path="moment" readonly="true"/>
	<acme:form-textarea code="entrepreneur.application.form.label.statement" path="statement" readonly="true"/>
	<acme:form-money code="entrepreneur.application.form.label.offer" path="offer" readonly="true"/>
	<acme:form-textbox code="entrepreneur.application.form.label.applicationInvestor" path="applicationInvestor" readonly="true"/>
	<acme:form-textarea code="entrepreneur.application.form.label.justification" path="justification" />
	<acme:menu-separator/>
	<acme:form-textbox code="entrepreneur.application.form.label.invRoundTicker" path="investmentRoundTicker" readonly="true"/>
	<acme:form-money code="entrepreneur.application.form.label.invRoundAmount" path="investmentRoundAmount" readonly="true"/>
	
	<acme:form-hidden path="id"/>
	<jstl:if test="${command == 'show' && status == 'PENDING'}">
		<acme:form-submit code="entrepreneur.application.form.button.update" action="/entrepreneur/application/update"/>
	</jstl:if>
	<jstl:if test="${command == 'update'}">
		<acme:form-submit code="entrepreneur.application.form.button.update" action="/entrepreneur/application/update"/>
	</jstl:if>	
	
	<acme:form-return code="entrepreneur.application.form.button.return"/>
</acme:form>