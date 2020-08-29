<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="bookkeeper.accountingRecord.list.label.title" path="title" width="40%" />
	<acme:list-column code="bookkeeper.accountingRecord.list.label.moment" path="moment" width="40%"/>
	<acme:list-column code="bookkeeper.accountingRecord.list.label.status" path="status" width="20%"/>
</acme:list>


