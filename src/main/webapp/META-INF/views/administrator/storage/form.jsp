<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.storage.form.label.firmName" path="firmName" readonly="true"/>
	<acme:form-textarea code="administrator.storage.form.label.responsibilityStatement" path="responsibilityStatement" readonly="true"/>
	<acme:form-select code="administrator.storage.form.label.status" path="status">
        <acme:form-option code="administrator.storage.form.label.status.pending" value="PENDING" selected="${status == 'PENDING'}"/>
        <acme:form-option code="administrator.storage.form.label.status.accepted" value="ACCEPTED" selected="${status == 'ACCEPTED'}"/>
    </acme:form-select>
	
	<acme:form-submit code="administrator.storage.form.button.update" action="update"/>
	
	<acme:form-return code="administrator.storage.form.button.return"/>
	
</acme:form>