<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textarea code="entrepreneur.monema.form.label.text" path="text"/>
	
	<acme:form-submit test="${command == 'create'}" code="entrepreneur.monema.form.button.create"
			action="/entrepreneur/monema/create?invId=${invId}" />
			
	<jstl:if test="${command == 'show'}">
		<acme:form-submit code="entrepreneur.monema.form.button.update" action="/entrepreneur/monema/update"/>
	</jstl:if>
	<jstl:if test="${command == 'show'}">
		<acme:form-submit code="entrepreneur.monema.form.button.delete" action="/entrepreneur/monema/delete"/>
	</jstl:if>
	<jstl:if test="${command == 'update' }">
		<acme:form-submit code="entrepreneur.monema.form.button.update" action="/entrepreneur/monema/update"/>
	</jstl:if>
	<jstl:if test="${command == 'delete'}">
		<acme:form-submit code="entrepreneur.monema.form.button.delete" action="/entrepreneur/monema/delete"/>
	</jstl:if>
	<acme:form-return code="entrepreneur.monema.form.button.return"/>
</acme:form>