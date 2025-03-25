<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 3/23/2025
  Time: 10:49 PM
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
                                    <c:out value="${card.taskStatus}" />
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
            </div>

            <%--TABLE--%>
            <div class="bg-white rounded-[16px] mt-6 overflow-hidden">
                <div class="w-full">
                    <div
                            class="flex flex-wrap justify-between items-center gap-4 p-6 border-b border-gray-100"
                    >
                        <h2 class="text-2xl font-bold text-[#0c0726]">All Tasks <span class="text-[#0c072665]">(${fn:length(requestScope.tasks)})</span></h2>
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
                                <th class="py-4 px-6 text-sm font-semibold text-gray-600">
                                    Project
                                </th>
                                <th class="py-4 px-6 text-sm font-semibold text-gray-600">
                                    Staff
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
                                    <td class="py-4 px-6 text-sm text-gray-800"><c:out value="${task.project.title}" /></td>
                                    <td class="py-4 px-6 text-sm text-gray-800">${fn:length(task.assignments)}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>



</body>
</html>
