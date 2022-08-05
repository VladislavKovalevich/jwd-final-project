<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 25-May-22
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="by.vlad.library.entity.OrderStatus" %>
<%@ page import="by.vlad.library.entity.OrderType" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="config.pagecontent"/>

<fmt:message key="title.orders_by_user_id" var="title"/>
<fmt:message key="label.order" var="order_label"/>
<fmt:message key="label.order_type" var="order_type"/>
<fmt:message key="label.order_status" var="order_status"/>
<fmt:message key="label.created_date" var="create_date"/>
<fmt:message key="title.order_filter_panel" var="order_filter_panel"/>
<fmt:message key="label.order_type" var="order_type_label"/>
<fmt:message key="label.order_status" var="order_status_label"/>
<fmt:message key="reference.next_page" var="next"/>
<fmt:message key="reference.prev_page" var="prev"/>
<fmt:message key="button.book_filter" var="filter_btn"/>
<fmt:message key="message.empty_list" var="empty_list_header"/>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>

    <link rel="stylesheet" href="${path}/css/styles.css">
    <script src="${path}/js/script.js"></script>

    <title>${title}</title>
</head>
<header>
    <jsp:include page="../header/header.jsp"/>
</header>
<body class="background-theme">
<section class="container-fluid">
    <div class="row mx-lg-5 my-lg-5">
        <div class="col-3 mx-2">
            <jsp:include page="../fragment/navigation_account.jsp"/>
            <!--панель фильтров-->
            <div class="mt-3 white-background">
                <form method="get" class="mx-3 white-background" action="${path}/controller">
                    <input type="hidden" name="command" value="get_orders_by_user_id"/>
                    <input type="hidden" name="order_direction" value="null"/>
                    <h4 class="text-center mt-2">${order_filter_panel}</h4>
                    <div class="mt-3">
                        <h5>${order_type_label}:</h5>
                        <div class="custom-scroll-container">
                            <c:forEach var="type" items="${order_types}">
                                <label for="order_type${type.ordinal() + 1}"></label>
                                <c:choose>
                                    <c:when test="${empty filter_data['order_type']}">
                                        <input type="checkbox" id="order_type${type.ordinal() + 1}"
                                               name="order_type${type.ordinal() + 1}"/>
                                        ${type.toString()} <br/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="flag_type" value="0" scope="page"/>
                                        <c:forEach var="statusId" items="${filter_data['order_type']}">
                                            <c:if test="${(statusId == type.ordinal() + 1) and (flag_type eq 0)}">
                                                <input type="checkbox" id="order_type${type.ordinal() + 1}"
                                                       name="order_type${type.ordinal() + 1}"
                                                       checked/>
                                                ${type.toString()} <br/>
                                                <c:set var="flag_type" value="1" scope="page"/>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${flag_type eq 0}">
                                            <input type="checkbox" id="order_type${type.ordinal() + 1}"
                                                   name="order_type${type.ordinal() + 1}"/>
                                            ${type.toString()} <br/>
                                        </c:if>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="mt-3">
                        <h5>${order_status_label}:</h5>
                        <div class="custom-scroll-container">
                            <c:forEach var="status" items="${order_statuses}">
                                <label for="order_status${status.ordinal() + 1}"></label>
                                <c:choose>
                                    <c:when test="${empty filter_data['order_status']}">
                                        <input type="checkbox" id="order_status${status.ordinal() + 1}"
                                               name="order_status${status.ordinal() + 1}"/>
                                        ${status.toString()} <br/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="flag_status" value="0" scope="page"/>
                                        <c:forEach var="statusId" items="${filter_data['order_status']}">
                                            <c:if test="${(statusId == status.ordinal() + 1) and (flag_status eq 0)}">
                                                <input type="checkbox" id="order_status${status.ordinal() + 1}"
                                                       name="order_status${status.ordinal() + 1}"
                                                       checked/>
                                                ${status.toString()} <br/>
                                                <c:set var="flag_status" value="1" scope="page"/>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${flag_status eq 0}">
                                            <input type="checkbox" id="order_status${status.ordinal() + 1}"
                                            name="order_status${status.ordinal() + 1}" />
                                            ${status.toString()} <br/>
                                        </c:if>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </div>
                    </div>

                    <div class="mt-2 pb-2">
                        <button class="btn btn-primary" type="submit">${filter_btn}</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="col-8 mx-2 white-background">
            <c:choose>
                <c:when test="${empty orders}">
                    <h5 class="text-center">${empty_list_label}</h5>
                </c:when>
                <c:otherwise>
                    <div class="row" style="background-color: aliceblue">
                        <c:forEach var="order" items="${orders}">
                            <div class="col-1 my-1"></div>
                            <div class="col-10 my-1" style="background-color: #c7b39b; border-color: #333333">
                                <a class="link-secondary text-decoration-none"
                                   href="${path}/controller?command=get_books_by_order_id&order_id=${order.id}"
                                   data-toggle="tooltip">
                                    <div class="row link" style="background-color:
                                    <c:choose>
                                    <c:when test="${order.status eq 'CREATED'}">
                                            #ede8a6
                                    </c:when>
                                    <c:when test="${order.status eq 'RESERVED'}">
                                            #84d194
                                    </c:when>
                                    <c:when test="${order.status eq 'REJECTED'}">
                                            #f09792
                                    </c:when>
                                    <c:when test="${order.status eq 'ACCEPTED'}">
                                            #a1aaed
                                    </c:when>
                                    <c:when test="${order.status eq 'OVERDUE'}">
                                            #eeeeee
                                    </c:when>
                                            </c:choose>">
                                        <div class="col-12">
                                            <h4>${order_label} #${order.id}</h4>
                                            <div class="my-2 border-top border-bottom">
                                                <p>${create_date}: ${order.createdDate}</p>
                                                <p>${order_type}: ${order.type}</p>
                                                <p>${order_status}: ${order.status}</p>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <div class="col-1 my-1"></div>
                        </c:forEach>
                    </div>
                    <div class="row">
                        <div class="col-md-3 text-center me-auto">
                            <c:if test="${pagination_data['order_current_page_num'] > 1}">
                                <a href="${path}/controller?command=get_orders_by_user_id&order_page_direction=prev">${prev}</a>
                            </c:if>
                        </div>
                        <div class="col-md-2 text-center me-auto">
                                ${pagination_data['order_current_page_num']}
                        </div>
                        <div class="col-md-3 text-center me-auto">
                            <c:if test="${(pagination_data['order_current_page_num'] < pagination_data['order_pages_number'])}">
                                <a href="${path}/controller?command=get_orders_by_user_id&order_page_direction=next">${next}</a>
                            </c:if>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</section>
</body>
<script>
    $(document).ready(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });
</script>
<footer>
    <jsp:include page="../footer/footer.jsp"/>
</footer>
</html>
