<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.bookkeeper.form.label.firmName" path="firmName" readonly="true"/>
	<acme:form-textarea code="authenticated.bookkeeper.form.label.responsibilityStatement" path="responsibilityStatement" readonly="true"/>
	
  	<acme:form-return code="authenticated.user-account.form.button.return"/>
</acme:form>
