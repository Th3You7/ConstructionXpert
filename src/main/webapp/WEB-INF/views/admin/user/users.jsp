<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 3/24/2025
  Time: 10:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--<c:set var="roleColor" value="#3e2d9b"/>--%>
<%--<c:set var="bgColor" value="#f4f3f9"/>--%>

<%--<c:forEach var="user" items="${requestScope.users}" >--%>
<%--    <c:choose>--%>
<%--        <c:when test="${requestScope.user.role == 'EMPLOYER_MEMBER'}">--%>
<%--            <c:set var="roleColor" value="#3e2d9b" />--%>
<%--            <c:set var="bgColor" value="#f4f3f9"/>--%>
<%--        </c:when>--%>
<%--        <c:when test="${requestScope.user.role == 'EMPLOYER_RESPONSIBLE'}">--%>

<%--            <c:set var="roleColor" value="#f58653" />--%>
<%--            <c:set var="bgColor" value="#fff5f0" />--%>
<%--        </c:when>--%>
<%--        <c:when test="${requestScope.user.role == 'SUPPLIER'}">--%>

<%--            <c:set var="roleColor" value="#46a977"/>--%>
<%--            <c:set var="bgColor" value="#f0f7f4"/>--%>
<%--        </c:when>--%>
<%--    </c:choose>--%>
<%--</c:forEach>--%>


<html>
<head>
    <title>ConstructionXpert</title>
    <script
            src="https://kit.fontawesome.com/13885aca87.js"
            crossorigin="anonymous"
    ></script>
    <script src="https://cdn.jsdelivr.net/npm/@tailwindcss/browser@4"></script>
</head>
<body>
<main class="bg-[#eff1f3] min-h-screen w-full pb-6">
    <div class="container mx-auto flex flex-col px-4">
        <%@ include file="../../../components/navbar.jsp" %>
        <%--Cards--%>
                <div class="flex flex-col w-full justify-center gap-2.5 py-2.5 mt-8">
                    <div
                            class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-5 gap-4 w-full"
                    >
                        <c:forEach var="card" items="${requestScope.cards}" >
                            <div
                                    class="flex flex-col bg-white rounded-2xl p-6 transition-transform hover:transform hover:scale-105"
                            >
                                <div class="flex items-center justify-between w-full mb-8">
                                    <div
                                            class="[font-family:'Inter',Helvetica] font-normal text-[#0c0726] text-2xl"
                                    >
                                        <c:out value="${card.role}" />
                                    </div>
                                    <div
                                            class="w-8 h-8 rounded-[40px] flex items-center justify-center"
                                            style="background-color: <c:out value="${card.color}" />"
                                    >
                                        <i class="<c:out value="${card.icon}" /> text-white"></i>
                                    </div>
                                </div>
                                <div
                                        class="w-full [font-family:'Inter',Helvetica] font-bold text-[#0c0726] text-[32px]"
                                >
                                    <c:out value="${card.count}" />
                                </div>
                            </div>
                        </c:forEach>
                </div>
                    <div class="bg-white rounded-[16px] mt-6 overflow-hidden">
                        <div class="w-full">
                            <div
                                    class="flex flex-wrap justify-between items-center gap-4 p-6 border-b border-gray-100"
                            >
                                <h2 class="text-2xl font-bold text-[#0c0726]">All Users <span class="text-[#0c072665]">(${fn:length(requestScope.users)})</span></h2>
                                <div class="flex items-center gap-4">
                                    <div class="relative">
                                        <form method="post" action="search">
                                            <input
                                                    name="name"
                                                    type="text"
                                                    placeholder="Search tasks..."
                                                    class="pl-10 pr-4 py-2 border border-gray-200 rounded-full w-64 focus:outline-none focus:border-gray-400"
                                            />
                                            <button type="submit">
                                                <i
                                                        class="fas fa-search absolute left-4 top-1/2 -translate-y-1/2 text-gray-400"
                                                ></i>
                                            </button>
                                        </form>

                                    </div>
                                    <button
                                            class="w-10 h-10 flex items-center justify-center bg-white rounded-full border border-gray-200 hover:bg-gray-50"

                                    >
                                        <a href="add.jsp">
                                            <i class="fas fa-plus text-gray-600"></i>
                                        </a>
                                    </button>
                                </div>
                            </div>
                            <div
                                    class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-4 p-6"
                            >
                                <c:forEach var="user" items="${requestScope.users}">
                                    <c:set var="roleColor" value="#3e2d9b"/>
                                    <c:set var="bgColor" value="#f4f3f9"/>
                                    <c:choose>
                                        <c:when test="${user.role == 'EMPLOYER_MEMBER'}">
                                            <c:set var="roleColor" value="#3e2d9b" />
                                            <c:set var="bgColor" value="#f4f3f9"/>
                                        </c:when>
                                        <c:when test="${user.role == 'EMPLOYER_RESPONSIBLE'}">

                                            <c:set var="roleColor" value="#f58653" />
                                            <c:set var="bgColor" value="#fff5f0" />
                                        </c:when>
                                        <c:when test="${user.role == 'SUPPLIER'}">

                                            <c:set var="roleColor" value="#46a977"/>
                                            <c:set var="bgColor" value="#f0f7f4"/>
                                        </c:when>
                                    </c:choose>

                                    <div
                                            class="p-4 rounded-2xl transition-transform hover:transform hover:scale-105"
                                            style="background-color: <c:out value="${bgColor}" /> "
                                    >
                                        <div class="flex items-start gap-3">
                                            <div class="w-12 h-12 rounded-full overflow-hidden">
                                                <img
                                                        src="https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d"
                                                        class="w-full h-full object-cover"
                                                />
                                            </div>
                                            <div class="flex-1">
                                                <h3 class="font-semibold text-[#0c0726]"><c:out value="${user.firstName} ${user.lastName}" /></h3>
                                                <p class="text-sm text-gray-600 mb-2"><c:out value="${user.email}" /></p>
                                                <span
                                                        class="inline-flex items-center px-3 py-1 rounded-full text-sm"
                                                        style="background-color: <c:out value="${bgColor}15" />;
                                                                color: <c:out value="${roleColor}" />">
                                                  <span
                                                          class="w-1.5 h-1.5 rounded-full mr-2"
                                                          style="background-color: <c:out value="${roleColor}" />"
                                                  ></span>
                                                     <c:out value="${user.role}" />
                                            </span>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>

                        </div>
                    </div>
    </div>
</main>
</body>
</html>
