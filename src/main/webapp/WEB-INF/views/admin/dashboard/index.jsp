<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 3/24/2025
  Time: 5:43 PM
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
            <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4 mt-8">
                <!-- Start Card -->
                <div class="bg-white rounded-2xl p-6">
                    <h2 class="text-xl font-semibold text-[#0c0726] mb-6">Projects</h2>
                    <div class="grid grid-cols-2 gap-4">
                        <c:forEach var="card" items="${requestScope.projectCards}">
                        <div
                                class="flex items-center gap-3 p-3 rounded-xl"
                                style="background-color: <c:out value="${card.color}15" />"
                        >
                            <div
                                    class="w-3 h-3 rounded-full"
                                    style="background-color:<c:out value="${card.color}" />"
                            ></div>
                            <div>
                                <p class="text-sm font-bold" style="color: <c:out value="${card.color}" />"><c:out value="${card.count}" /></p>
                                <p class="text-xs text-gray-500"><c:out value="${card.projectStatus}" /></p>
                            </div>
                        </div>
                        </c:forEach>
                    </div>
                </div>
                <!-- End Card -->
                <!-- Start Card -->
                <div class="bg-white rounded-2xl p-6">
                    <h2 class="text-xl font-semibold text-[#0c0726] mb-6">Tasks</h2>

                    <div class="grid grid-cols-2 gap-4">
                        <c:forEach var="card" items="${requestScope.taskCards}">
                            <div
                                    class="flex items-center gap-3 p-3 rounded-xl"
                                    style="background-color: <c:out value="${card.color}15" />"
                            >
                                <div
                                        class="w-3 h-3 rounded-full"
                                        style="background-color:<c:out value="${card.color}" />"
                                ></div>
                                <div>
                                    <p class="text-sm font-bold" style="color: <c:out value="${card.color}" />"><c:out value="${card.count}" /></p>
                                    <p class="text-xs text-gray-500"><c:out value="${card.taskStatus}" /></p>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <!-- End Card -->
                <!-- Start Card -->
                <div class="bg-white rounded-2xl p-6">
            <h2 class="text-xl font-semibold text-[#0c0726] mb-6">Stuff</h2>
            <div class="grid grid-cols-2 gap-4">
                <c:forEach var="card" items="${requestScope.userCards}">
                    <c:set var="role" value="Responsible" />
                    <c:choose>
                        <c:when test="${card.role == 'SUPPLIER'}">
                            <c:set var="role" value="Supplier" />
                        </c:when>
                        <c:when test="${card.role == 'EMPLOYER_RESPONSIBLE'}">
                            <c:set var="role" value="Responsible" />
                        </c:when>
                        <c:when test="${card.role == 'EMPLOYER_MEMBER'}">
                            <c:set var="role" value="Member" />
                        </c:when>
                    </c:choose>
                    <div
                            class="flex items-center gap-3 p-3 rounded-xl"
                            style="background-color: <c:out value="${card.color}15" />"
                    >
                        <div
                                class="w-3 h-3 rounded-full"
                                style="background-color:<c:out value="${card.color}" />"
                        ></div>
                        <div>
                            <p class="text-sm font-bold" style="color: <c:out value="${card.color}" />"><c:out value="${card.count}" /></p>
                            <p class="text-xs text-gray-500"><c:out value="${role}" /></p>
                        </div>
                    </div>
                </c:forEach>

            </div>
        </div>
            </div>

            <!-- assignments & resources -->
            <div class="grid grid-cols-1 lg:grid-cols-3 gap-6 mt-6">
        <div class="lg:col-span-2">
            <div class="bg-white rounded-2xl overflow-hidden">
                <div
                        class="flex justify-between items-center p-6 border-b border-gray-100"
                >
                    <h2 class="text-xl font-semibold text-[#0c0726]">
                        Latest Assignments
                    </h2>
                </div>
                <div class="overflow-x-auto">
                    <table class="w-full border-collapse">
                        <thead>
                        <tr class="text-left border-b border-gray-200">
                            <th class="py-4 px-6 text-sm font-semibold text-gray-600">
                                Assignee
                            </th>
                            <th class="py-4 px-6 text-sm font-semibold text-gray-600">
                                Task
                            </th>
                            <th class="py-4 px-6 text-sm font-semibold text-gray-600">
                                Start Date
                            </th>
                            <th class="py-4 px-6 text-sm font-semibold text-gray-600">
                                End Date
                            </th>
                            <th class="py-4 px-6 text-sm font-semibold text-gray-600">
                                Project
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="assignment" items="${requestScope.assignments}">
                                <tr class="border-b border-gray-100 hover:bg-gray-50">
                                    <td class="py-4 px-6">
                                        <div class="flex items-center gap-3">
                                            <div class="w-10 h-10 rounded-full overflow-hidden">
                                                <img
                                                        src="https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d"
                                                        class="w-full h-full object-cover"
                                                />
                                            </div>
                                            <div>
                                                <div class="font-semibold text-[#0c0726]">
                                                    <c:out value="${assignment.employer.firstName}${assignment.employer.lastName} " />
                                                </div>
                                                <div class="text-sm text-gray-500">
                                                    <c:out value="${assignment.employer.email}" />
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                    <td class="py-4 px-6 text-sm font-medium text-[#0c0726]">
                                        <c:out value="${assignment.employer.task.title}" />
                                    </td>
                                    <td class="py-4 px-6 text-sm text-gray-600">
                                        <c:out value="${assignment.startDate}" />
                                    </td>
                                    <td class="py-4 px-6 text-sm text-gray-600"><c:out value="${assignment.endDate}" /></td>
                                    <td class="py-4 px-6 text-sm font-medium text-[#0c0726]">
                                        <c:out value="${assignment.task.project.title}" />
                                    </td>
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div>
            <div class="bg-white rounded-2xl p-6">
                <h2 class="text-xl font-semibold text-[#0c0726] mb-6">
                    Current Resources
                </h2>
                <div class="grid grid-cols-2 gap-4">
                    <c:forEach var="resource" items="${requestScope.resources}">
                        <c:set var="bgColor" value=" #3e2d9b"/>
                        <c:set var="name" value="Cement"/>
                        <c:set var="icon" value="fas fa-cube" />

                        <c:choose>
                        <c:when test="${resource.type == 'CEMENT'}">
                            <c:set var="bgColor" value=" #3e2d9b"/>
                            <c:set var="name" value="Cement"/>
                            <c:set var="icon" value="fas fa-cube" />

                        </c:when>
                        <c:when test="${resource.type == 'STEEL'}">
                            <c:set var="bgColor" value="#0c0726"/>
                            <c:set var="name" value="Steel"/>
                            <c:set var="icon" value="fas fa-cog" />
                        </c:when>
                        <c:when test="${resource.type == 'WOOD'}">
                            <c:set var="bgColor" value="#f58653"/>
                            <c:set var="name" value="Wood"/>
                            <c:set var="icon" value="fas fa-tree" />
                        </c:when>
                        <c:when test="${resource.type == 'BRICKS'}">
                            <c:set var="bgColor" value="#94c21f"/>
                            <c:set var="name" value="Bricks"/>
                            <c:set var="icon" value="fas fa-layer-group" />
                        </c:when>
                        <c:when test="${resource.type == 'GLASS'}">
                            <c:set var="bgColor" value="#fc5656"/>
                            <c:set var="name" value="Glass"/>
                            <c:set var="icon" value="fas fa-square" />
                        </c:when>
                        <c:when test="${resource.type == 'PAINT'}">
                            <c:set var="bgColor" value="#3e2d9b"/>
                            <c:set var="name" value="Paint"/>
                            <c:set var="icon" value="fas fa-cube" />
                        </c:when>
                        <c:when test="${resource.type == 'ELECTRICAL'}">
                            <c:set var="bgColor" value="#46a977"/>
                            <c:set var="name" value="Electrical"/>
                            <c:set var="icon" value="fas fa-bolt" />
                        </c:when>
                        <c:when test="${resource.type == 'PLUMBING'}">
                            <c:set var="bgColor" value="#3e2d9b"/>
                            <c:set var="name" value="Plumbing"/>
                            <c:set var="icon" value="fas fa-faucet" />
                        </c:when>
                        </c:choose>
                        <div
                                class="flex items-center gap-4 p-3 rounded-xl transition-all hover:transform hover:scale-105"
                                style="background-color: <c:out value="${bgColor}15" />"
                        >
                            <div
                                    class="w-10 h-10 rounded-lg flex items-center justify-center"
                                    style="background-color: <c:out value="${bgColor}" />"
                            >
                                <i class="<c:out value="${icon}"/> text-white"></i>
                            </div>
                            <div>
                                <p class="text-sm font-medium text-[#0c0726]"><c:out value="${name}" /></p>
                                <div class="flex items-center gap-2">
                                    <p class="text-sm font-bold" style="color: <c:out value="${bgColor}" />"><c:out value="${resource.count}" /></p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                </div>
            </div>
        </div>
    </div>
    </div>
</main>
</body>
</html>
