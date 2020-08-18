<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.challenge.form.label.title" path="title"/>
	<acme:form-moment code="administrator.challenge.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="administrator.challenge.form.label.description" path="description"/>
	
	<acme:form-textarea code="administrator.challenge.form.label.goalRookie" path="goalRookie"/>
	<acme:form-money code="administrator.challenge.form.label.rewardRookie" path="rewardRookie"/>
	<acme:form-textarea code="administrator.challenge.form.label.goalAverage" path="goalAverage"/>
	<acme:form-money code="administrator.challenge.form.label.rewardAverage" path="rewardAverage"/>
	<acme:form-textarea code="administrator.challenge.form.label.goalExpert" path="goalExpert"/>
	<acme:form-money code="administrator.challenge.form.label.rewardExpert" path="rewardExpert"/>
		
	
	<acme:form-submit test="${command == 'create'}" 
		code="administrator.challenge.form.button.create" 
		action="/administrator/challenge/create"/>	
	
	
	<acme:form-return code="administrator.challenge.form.button.return"/>
</acme:form>