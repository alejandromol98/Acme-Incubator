<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="anonymous.toolRecord.form.label.title" path="title"/>
	<acme:form-textbox code="anonymous.toolRecord.form.label.activitySector" path="activitySector"/>
	<acme:form-textbox code="anonymous.toolRecord.form.label.investor" path="investor"/>
	<acme:form-textarea code="anonymous.toolRecord.form.label.description" path="description"/>
	<acme:form-url code="anonymous.toolRecord.form.label.website" path="website"/>
	<acme:form-textarea code="anonymous.toolRecord.form.label.email" path="email"/>
	<acme:form-textarea code="anonymous.toolRecord.form.label.openSource" path="openSource"/>
	<acme:form-integer code="anonymous.toolRecord.form.label.rate" path="rate"/>
	
	<acme:form-return code="anonymous.toolRecord.form.button.return"/>
</acme:form>