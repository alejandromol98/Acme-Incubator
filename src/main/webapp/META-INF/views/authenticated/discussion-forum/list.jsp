<%@page language="java" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:list>
	<acme:list-column code="authenticated.discussionForum.list.label.title" path="title" width="60%" />
	<acme:list-column code="authenticated.discussionForum.list.label.moment" path="moment" width="20%" />
	<acme:list-column code="authenticated.discussionForum.list.label.invRoundTicker" path="invRoundTicker" width="20%" />
</acme:list>

