<%--
- menu.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.entities.roles.Provider,acme.entities.roles.Consumer"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.molinaBulletin.list" action="/anonymous/molina-bulletin/list"/>
	<%-- 	<acme:menu-suboption code="master.menu.anonymous.molinaBulletin.create" action="/anonymous/molina-bulletin/create"/> --%>
			<acme:menu-suboption code="master.menu.anonymous.notice.list" action="/anonymous/notice/list"/>
			<acme:menu-suboption code="master.menu.anonymous.technologyRecord.list" action="/anonymous/technology-record/list"/>
			<acme:menu-suboption code="master.menu.anonymous.toolRecord.list" action="/anonymous/tool-record/list"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.anonymous.links" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.alejandro-link" action="http://www.informatica.us.es"/>
		</acme:menu-option>	

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.notice.list" action="/administrator/notice/list"/>
			<acme:menu-suboption code="master.menu.administrator.notice.create" action="/administrator/notice/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.inquiry.create" action="/administrator/inquiry/create"/>
			<acme:menu-suboption code="master.menu.administrator.overture.create" action="/administrator/overture/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.technologyRecord.create" action="/administrator/technology-record/create"/>
			<acme:menu-suboption code="master.menu.administrator.toolRecord.create" action="/administrator/tool-record/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.challenge.create" action="/administrator/challenge/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.customisation" action="/administrator/customisation/show"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shutdown" action="/master/shutdown"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.provider" access="hasRole('Provider')">
			<acme:menu-suboption code="master.menu.provider.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.consumer" access="hasRole('Consumer')">
			<acme:menu-suboption code="master.menu.consumer.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.authenticated.notice.list" action="/authenticated/notice/list"/>
			<acme:menu-suboption code="master.menu.authenticated.technologyRecord.list" action="/authenticated/technology-record/list"/>
			<acme:menu-suboption code="master.menu.authenticated.toolRecord.list" action="/authenticated/tool-record/list"/>
			<acme:menu-suboption code="master.menu.authenticated.overture.list" action="/authenticated/overture/list"/>
			<acme:menu-suboption code="master.menu.authenticated.inquire.list" action="/authenticated/inquiry/list"/>
			<acme:menu-suboption code="master.menu.authenticated.challenge.list" action="/authenticated/challenge/list"/>
			<acme:menu-suboption code="master.menu.authenticated.investmentRound.list" action="/authenticated/investment-round/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.authenticated.discussionForum.list" action="/authenticated/discussion-forum/list-mine"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.entrepreneur" access="hasRole('Entrepreneur')">
			<acme:menu-suboption code="master.menu.entrepreneur.investmentRound.list" action="/entrepreneur/investment-round/list-mine"/>
			<acme:menu-suboption code="master.menu.entrepreneur.application.list" action="/entrepreneur/application/list-mine"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.investor" access="hasRole('Investor')">
			<acme:menu-suboption code="master.menu.investor.application.list" action="/investor/application/list-mine"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.bookkeeper" access="hasRole('Bookkeeper')">
			<acme:menu-suboption code="master.menu.bookkeeper.investmentRound.listMine" action="/bookkeeper/investment-round/list-mine"/>
			<acme:menu-suboption code="master.menu.bookkeeper.investmentRound.listNotMine" action="/bookkeeper/investment-round/list"/>
		</acme:menu-option>
		
		
	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.provider" action="/authenticated/provider/update" access="hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.consumer" action="/authenticated/consumer/update" access="hasRole('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.entrepreneur" action="/authenticated/entrepreneur/update" access="hasRole('Entrepreneur')"/>
			<acme:menu-suboption code="master.menu.user-account.investor" action="/authenticated/investor/update" access="hasRole('Investor')"/>
			<acme:menu-suboption code="master.menu.user-account.become-provider" action="/authenticated/provider/create" access="!hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.become-consumer" action="/authenticated/consumer/create" access="!hasRole('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.become-entrepreneur" action="/authenticated/entrepreneur/create" access="!hasRole('Entrepreneur')"/>
			<acme:menu-suboption code="master.menu.user-account.become-investor" action="/authenticated/investor/create" access="!hasRole('Investor')"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

