<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textarea code="investor.monema.form.label.text" path="text"/>
	
	<acme:form-return code="investor.monema.form.button.return"/>
</acme:form>