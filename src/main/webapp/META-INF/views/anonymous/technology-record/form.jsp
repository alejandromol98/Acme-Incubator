<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="anonymous.technologyRecord.form.label.title" path="title"/>
	<acme:form-textbox code="anonymous.technologyRecord.form.label.activitySector" path="activitySector"/>
	<acme:form-textbox code="anonymous.technologyRecord.form.label.investor" path="investor"/>
	<acme:form-textarea code="anonymous.technologyRecord.form.label.description" path="description"/>
	<acme:form-url code="anonymous.technologyRecord.form.label.website" path="website"/>
	<acme:form-textbox code="anonymous.technologyRecord.form.label.email" path="email"/>
	<acme:form-select code="anonymous.technologyRecord.form.label.source" path="source" >
		<acme:form-option code="anonymous.technologyRecord.form.label.status.openSource" value="Open_Source" selected="${source == 'Open_Source'}"/>
        <acme:form-option code="anonymous.technologyRecord.form.label.status.closedSource" value="Closed_Source" selected="${source == 'Closed_Source'}"/>
    </acme:form-select>
	<acme:form-integer code="anonymous.technologyRecord.form.label.rate" path="rate"/>
	
	<acme:form-return code="anonymous.technologyRecord.form.button.return"/>
</acme:form>