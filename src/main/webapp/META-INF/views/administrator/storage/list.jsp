<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.storage.list.label.authenticated" path="authenticated" width="20%" />
	<acme:list-column code="administrator.storage.list.label.firmName" path="firmName" width="20%"/>
	<acme:list-column code="administrator.storage.list.label.responsibilityStatement" path="responsibilityStatement" width="60%"/>
</acme:list>


