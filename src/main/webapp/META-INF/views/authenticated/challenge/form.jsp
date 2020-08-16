<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.challenge.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.challenge.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="authenticated.challenge.form.label.description" path="description"/>
	
	<acme:form-textarea code="authenticated.challenge.form.label.goalRookie" path="goalRookie"/>
	<acme:form-money code="authenticated.challenge.form.label.rewardRookie" path="rewardRookie"/>
	<acme:form-textarea code="authenticated.challenge.form.label.goalAverage" path="goalAverage"/>
	<acme:form-money code="authenticated.challenge.form.label.rewardAverage" path="rewardAverage"/>
	<acme:form-textarea code="authenticated.challenge.form.label.goalExpert" path="goalExpert"/>
	<acme:form-money code="authenticated.challenge.form.label.rewardExpert" path="rewardExpert"/>
		
	
	<security:authorize access="hasRole('Administrator')">
		<acme:form-submit test="${command == 'show'}" code="administrator.challenge.form.button.update"
			action="/administrator/challenge/update/" />
		<acme:form-submit test="${command == 'show'}" code="administrator.challenge.form.button.delete"
			action="/administrator/challenge/delete/" />
		<acme:form-submit test="${command == 'update'}" code="administrator.challenge.form.button.update"
			action="/administrator/challenge/update/" />
		<acme:form-submit test="${command == 'delete'}" code="administrator.challenge.form.button.delete"
			action="/administrator/challenge/delete/" />
	</security:authorize>
	
	
	<acme:form-return code="authenticated.challenge.form.button.return"/>
</acme:form>