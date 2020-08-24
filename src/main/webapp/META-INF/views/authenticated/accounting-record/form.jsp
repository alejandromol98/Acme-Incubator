<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.accountingRecord.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.accountingRecord.form.label.moment" path="moment"/>
	<acme:form-moment code="authenticated.accountingRecord.form.label.status" path="status"/>
	<acme:form-money code="authenticated.accountingRecord.form.label.body" path="body"/>
	<acme:form-textbox code="authenticated.accountingRecord.form.label.bookkeeper" path="bookkeeper"/>
	
	<acme:form-return code="authenticated.accountingRecord.form.button.return"/>
</acme:form>