<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="bookkeeper.accountingRecord.form.label.title" path="title"/>
	<jstl:if test="${ command != 'create' }">
		<acme:form-moment code="bookkeeper.accountingRecord.form.label.moment" path="moment" readonly="true"/>
	</jstl:if>
	<acme:form-textarea code="bookkeeper.accountingRecord.form.label.body" path="body"/>
	<acme:form-select code="bookkeeper.accountingRecord.form.label.status" path="status">
        <acme:form-option code="bookkeeper.accountingRecord.form.label.status.draft" value="DRAFT" selected="${status == 'DRAFT'}"/>
        <acme:form-option code="bookkeeper.accountingRecord.form.label.status.published" value="PUBLISHED" selected="${status == 'PUBLISHED'}"/>
    </acme:form-select>
	
	<acme:form-submit test="${command == 'create'}" code="bookkeeper.accountingRecord.form.button.create" 
		action="/bookkeeper/accounting-record/create?invId=${invId}"/>

	
	<jstl:if test="${command == 'show' && status == 'DRAFT'}">
		<acme:form-submit code="bookkeeper.accountingRecord.form.button.update" action="/bookkeeper/accounting-record/update"/>
	</jstl:if>
	<jstl:if test="${command == 'update' }">
		<acme:form-submit code="entrepreneur.investmentRound.form.button.update" action="/bookkeeper/accounting-record/update"/>
	</jstl:if>
	
	<acme:form-return code="bookkeeper.accountingRecord.form.button.return"/>
	
</acme:form>