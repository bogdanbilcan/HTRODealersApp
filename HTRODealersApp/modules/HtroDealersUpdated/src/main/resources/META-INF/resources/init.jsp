<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet"%>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security"%>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util"%>

<%@ page import="com.liferay.portal.kernel.security.permission.ActionKeys"%>
<%@ page import="com.liferay.portal.kernel.model.PersistedModel"%>
<%@ page import="com.liferay.portal.kernel.model.Layout"%>
<%@ page import="com.liferay.portal.kernel.model.User"%>
<%@ page import="com.liferay.portal.kernel.model.Role"%>
<%@ page import="com.liferay.portal.kernel.model.Group"%>
<%@ page import="com.liferay.portal.kernel.model.UserGroup"%>
<%@ page import="com.liferay.portal.kernel.service.UserGroupServiceUtil"%>
<%@ page import="com.liferay.portal.kernel.dao.search.SearchContainer"%>
<%@ page import="com.liferay.portal.kernel.dao.search.RowChecker"%>
<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@ page import="com.liferay.portal.kernel.dao.search.SearchEntry"%>
<%@ page import="com.liferay.portal.kernel.security.permission.ActionKeys"%>
<%@ page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@ page import="com.liferay.portal.kernel.util.StringPool"%>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@ page import="com.liferay.portal.kernel.util.Validator"%>
<%@ page import="com.liferay.portal.kernel.util.OrderByComparator"%>
<%@ page import="com.liferay.portal.kernel.theme.ThemeDisplay"%>
<%@ page import="com.liferay.portal.kernel.exception.PortalException"%>
<%@ page import="com.liferay.portal.kernel.exception.SystemException"%>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@ page import="com.liferay.portal.kernel.log.Log"%>
<%@ page import="com.liferay.portal.kernel.log.LogFactoryUtil"%>
<%@ page import="com.liferay.portal.kernel.search.Indexer"%>
<%@ page import="com.liferay.portal.kernel.search.IndexerRegistryUtil"%>
<%@ page import="com.liferay.portal.kernel.search.SearchContext"%>
<%@ page import="com.liferay.portal.kernel.search.SearchContextFactory"%>
<%@ page import="com.liferay.portal.kernel.search.Hits"%>
<%@ page import="com.liferay.portal.kernel.search.Document"%>
<%@ page import="com.liferay.portal.kernel.search.Field"%>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>

<%@ page import="com.liferay.expando.kernel.model.ExpandoColumnConstants"%>

<%@ page import="java.util.List"%>
<%@ page import="java.util.Objects"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Arrays"%>

<%@ page import="java.time.LocalTime"%>
<%@ page import="java.time.Clock"%>
<%@ page import="java.time.ZoneId"%>

<%@ page import="java.io.Serializable"%>

<%@ page import="javax.portlet.PortletURL"%>
<%@ page import="javax.portlet.WindowState"%>

<%@ page import="com.custom.logika.htro.dealersapp.model.StocItem"%>
<%@ page import="com.custom.logika.htro.dealersapp.model.PortofoliuItem"%>
<%@ page import="com.custom.logika.htro.dealersapp.controller.service.DBConnection"%>
<%@ page import="com.custom.logika.htro.dealersapp.controller.dao.StocItemDAO"%>
<%@ page import="com.custom.logika.htro.dealersapp.controller.dao.PortofoliuItemDAO"%>

<liferay-frontend:defineObjects />
<liferay-theme:defineObjects />
<portlet:defineObjects />