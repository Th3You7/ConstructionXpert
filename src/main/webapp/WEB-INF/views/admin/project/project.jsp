<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 3/25/2025
  Time: 4:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

        <%--    Emplyers--%>
        <div class="grid grid-cols-1 lg:grid-cols-3 gap-6 mt-6">
            <div class="lg:col-span-2 bg-white rounded-[16px] overflow-hidden">
                <div class="w-full">
                    <div
                            class="flex flex-wrap justify-between items-center gap-4 p-6 border-b border-gray-100"
                    >
                        <h2 class="text-2xl font-bold text-[#0c0726]">Project Employers <span class="text-[#0c072665]">(${fn:length(requestScope.employers)})</span></h2>
<%--                        <button class="bg-red-500 text-white px-2 py-2 rounded">--%>
<%--                            <a>Delete Task</a>--%>
<%--                        </button>--%>
                    </div>
                </div>
                <div
                        class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-4 p-6"
                >
                    <c:forEach var="employer" items="${requestScope.employers}">
                        <c:set var="roleColor" value="#3e2d9b"/>
                        <c:set var="bgColor" value="#f4f3f9"/>
                        <c:set var="role" value="Member" />
                        <c:choose>
                            <c:when test="${employer.employerRole == 'MEMBER'}">
                                <c:set var="roleColor" value="#3e2d9b" />
                                <c:set var="bgColor" value="#f4f3f9"/>
                                <c:set var="role" value="Member" />

                            </c:when>
                            <c:when test="${employer.employerRole == 'RESPONSIBLE'}">

                                <c:set var="roleColor" value="#f58653" />
                                <c:set var="bgColor" value="#fff5f0" />
                                <c:set var="role" value="Responsible" />

                            </c:when>
                            <%--                                <c:when test="${assignment.employer.employerRole == 'SUPPLIER'}">--%>

                            <%--                                    <c:set var="roleColor" value="#46a977"/>--%>
                            <%--                                    <c:set var="bgColor" value="#f0f7f4"/>--%>
                            <%--                                    <c:set var="role" value="Supplier" />--%>

                            <%--                                </c:when>--%>
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
                                    <h3 class="font-semibold text-[#0c0726]"><c:out value="${employer.firstName} ${employer.lastName}" /></h3>
                                    <p class="text-sm text-gray-600 mb-2"><c:out value="${employer.email}" /></p>
                                    <span
                                            class="inline-flex items-center px-3 py-1 rounded-full text-sm"
                                            style="background-color: <c:out value="${bgColor}15" />;
                                                    color: <c:out value="${roleColor}" />">
                                                  <span
                                                          class="w-1.5 h-1.5 rounded-full mr-2"
                                                          style="background-color: <c:out value="${roleColor}" />"
                                                  ></span>
                                                     <c:out value="${role}" />
                                            </span>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

<%--            <div>--%>
<%--                <div class="bg-white rounded-2xl p-6">--%>
<%--                    <h2 class="text-xl font-semibold text-[#0c0726] mb-6">--%>
<%--                        Project Allocated Resources--%>
<%--                    </h2>--%>
<%--                    <div class="grid grid-cols-2 gap-4">--%>
<%--                        <c:forEach var="resource" items="${requestScope.allocatedResources}">--%>
<%--                            <c:set var="bgColor" value=" #3e2d9b"/>--%>
<%--                            <c:set var="name" value="Cement"/>--%>
<%--                            <c:set var="icon" value="fas fa-cube" />--%>

<%--                            <c:choose>--%>
<%--                                <c:when test="${resource.resource.type == 'CEMENT'}">--%>
<%--                                    <c:set var="bgColor" value=" #3e2d9b"/>--%>
<%--                                    <c:set var="name" value="Cement"/>--%>
<%--                                    <c:set var="icon" value="fas fa-cube" />--%>

<%--                                </c:when>--%>
<%--                                <c:when test="${resource.resource.type == 'STEEL'}">--%>
<%--                                    <c:set var="bgColor" value="#0c0726"/>--%>
<%--                                    <c:set var="name" value="Steel"/>--%>
<%--                                    <c:set var="icon" value="fas fa-cog" />--%>
<%--                                </c:when>--%>
<%--                                <c:when test="${resource.resource.type == 'WOOD'}">--%>
<%--                                    <c:set var="bgColor" value="#f58653"/>--%>
<%--                                    <c:set var="name" value="Wood"/>--%>
<%--                                    <c:set var="icon" value="fas fa-tree" />--%>
<%--                                </c:when>--%>
<%--                                <c:when test="${resource.type == 'BRICKS'}">--%>
<%--                                    <c:set var="bgColor" value="#94c21f"/>--%>
<%--                                    <c:set var="name" value="Bricks"/>--%>
<%--                                    <c:set var="icon" value="fas fa-layer-group" />--%>
<%--                                </c:when>--%>
<%--                                <c:when test="${resource.resource.type == 'GLASS'}">--%>
<%--                                    <c:set var="bgColor" value="#fc5656"/>--%>
<%--                                    <c:set var="name" value="Glass"/>--%>
<%--                                    <c:set var="icon" value="fas fa-square" />--%>
<%--                                </c:when>--%>
<%--                                <c:when test="${resource.resource.type == 'PAINT'}">--%>
<%--                                    <c:set var="bgColor" value="#3e2d9b"/>--%>
<%--                                    <c:set var="name" value="Paint"/>--%>
<%--                                    <c:set var="icon" value="fas fa-cube" />--%>
<%--                                </c:when>--%>
<%--                                <c:when test="${resource.resource.type == 'ELECTRICAL'}">--%>
<%--                                    <c:set var="bgColor" value="#46a977"/>--%>
<%--                                    <c:set var="name" value="Electrical"/>--%>
<%--                                    <c:set var="icon" value="fas fa-bolt" />--%>
<%--                                </c:when>--%>
<%--                                <c:when test="${resource.resource.type == 'PLUMBING'}">--%>
<%--                                    <c:set var="bgColor" value="#3e2d9b"/>--%>
<%--                                    <c:set var="name" value="Plumbing"/>--%>
<%--                                    <c:set var="icon" value="fas fa-faucet" />--%>
<%--                                </c:when>--%>
<%--                            </c:choose>--%>
<%--                            <div--%>
<%--                                    class="flex items-center gap-4 p-3 rounded-xl transition-all hover:transform hover:scale-105"--%>
<%--                                    style="background-color: <c:out value="${bgColor}15" />"--%>
<%--                            >--%>
<%--                                <div--%>
<%--                                        class="w-10 h-10 rounded-lg flex items-center justify-center"--%>
<%--                                        style="background-color: <c:out value="${bgColor}" />"--%>
<%--                                >--%>
<%--                                    <i class="<c:out value="${icon}"/> text-white"></i>--%>
<%--                                </div>--%>
<%--                                <div>--%>
<%--                                    <p class="text-sm font-medium text-[#0c0726]"><c:out value="${name}" /></p>--%>
<%--                                    <div class="flex items-center gap-2">--%>
<%--                                        <p class="text-sm font-bold" style="color: <c:out value="${bgColor}" />"><c:out value="${resource.resource.count}" /></p>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </c:forEach>--%>

<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
        </div>

<%--        Tasks--%>
        <div class="bg-white rounded-[16px] mt-6 overflow-hidden">
            <div class="w-full">
                <div
                        class="flex flex-wrap justify-between items-center gap-4 p-6 border-b border-gray-100"
                >
                    <h2 class="text-2xl font-bold text-[#0c0726]">Project Tasks <span class="text-[#0c072665]">(${fn:length(requestScope.tasks)})</span></h2>

                </div>

                <div class="overflow-x-auto">
                    <table class="w-full border-collapse">
                        <thead>
                        <tr class="text-left border-b border-gray-200">
                            <th class="py-4 px-6 text-sm font-semibold text-gray-600">
                                ID
                            </th>
                            <th class="py-4 px-6 text-sm font-semibold text-gray-600">
                                Task Name
                            </th>
                            <th class="py-4 px-6 text-sm font-semibold text-gray-600">
                                Status
                            </th>
                            <th class="py-4 px-6 text-sm font-semibold text-gray-600">
                                Start Date
                            </th>
                            <th class="py-4 px-6 text-sm font-semibold text-gray-600">
                                End Date
                            </th>

                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="task" items="${requestScope.tasks}" >
                            <tr class="border-b border-gray-100 hover:bg-gray-50">
                                <td class="py-4 px-6 text-sm text-gray-800"><c:out value="${task.id}" /></td>
                                <td class="py-4 px-6 text-sm text-gray-800 font-medium">
                                        <%--                                        <a href="<c:out value="${task.Id}"/>">--%>

                                        <%--                                        </a>--%>
                                    <a href="/task/?id=<c:out value="${task.id}" />">
                                        <c:out value="${task.title}" />
                                    </a>
                                </td>
                                <td class="py-4 px-6">
                      <span
                              class="inline-flex items-center px-3 py-1 rounded-full text-sm"
                              style="background-color: #3e2d9b15; color: #3e2d9b"
                      >
                        <span
                                class="w-1.5 h-1.5 rounded-full mr-2"
                                style="background-color: #3e2d9b"
                        ></span>
                        <c:out value="${task.status}" />
                      </span>
                                </td>
                                <td class="py-4 px-6 text-sm text-gray-600"><c:out value="${task.startDate}" /></td>
                                <td class="py-4 px-6 text-sm text-gray-600"><c:out value="${task.endDate}" /></td>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

</main>
</body>
</html>
