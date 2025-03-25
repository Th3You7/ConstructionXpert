<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 3/24/2025
  Time: 10:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <div class="bg-white rounded-[16px] mt-6 overflow-hidden">
            <div class="w-full">
                <div
                        class="flex flex-wrap justify-between items-center gap-4 p-6 border-b border-gray-100"
                >
                    <h2 class="text-2xl font-bold text-[#0c0726] basis-full">
                        User&nbsp;&nbsp;&gt;&nbsp;&nbsp;<c:if test="${requestScope.task == null}">Add</c:if><c:if test="${requestScope.user != null}">Edit</c:if> Form
                    </h2>
                    <c:if test="${requestScope.user != null}">
                    <form
                            class="flex flex-col max-w-[500px] w-[500px] mx-auto my-[32px] items-start justify-center"
                            action="edit"
                            method="post"
                    >
                        <input hidden="hidden" name="id" value="${requestScope.user.userId}">
                        </c:if>
                        <c:if test="${requestScope.user == null}">
                        <form
                                class="flex flex-col max-w-[500px] w-[500px] mx-auto my-[32px] items-start justify-center"
                                action="add"
                                method="post"
                        >
                            </c:if>
                            <div
                                    class="gap-8 self-stretch w-full flex flex-col items-start"
                            >


                                    <select
                                            class="h-[50px] w-full px-4 py-2 bg-white rounded-[40px] text-[#7b7b7b] border border-gray-200"
                                            name="role" >
                                        <c:if test="${requestScope.user == null}">
                                            <option value="<c:out value="${requestScope.type}" />" selected>
                                                <c:out value="${requestScope.type}" />
                                            </option>
                                        </c:if>
                                        <c:if test="${requestScope.user != null}">
                                            <option value="<c:out value="${requestScope.user.role}" />" selected>
                                                <c:out value="${requestScope.user.role}" />
                                            </option>
                                        </c:if>

                                    </select>

                                        <c:if test="${requestScope.type != 'SUPPLIER'}">
                                            <select name="taskId"
                                                    class="h-[50px] w-full px-4 py-2 bg-white rounded-[40px] text-[#7b7b7b] border border-gray-200"
                                            >
                                                <c:forEach var="task" items="${requestScope.tasks}">
                                                    <option value="<c:out value="${task.id}" />">
                                                        <c:out value="${task.title}" />
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </c:if>

                                <input
                                        class="h-[50px] w-full px-4 py-2 bg-white rounded-[40px] text-[#7b7b7b] border border-gray-200"
                                        placeholder="First Name"
                                        name="firstName"
                                        value="${requestScope.user.firstName}"
                                />

                                <input
                                        class="h-[50px] w-full px-4 py-2 bg-white rounded-[40px] text-[#7b7b7b] border border-gray-200"
                                        placeholder="Last Name"
                                        name="lastName"
                                        value="${requestScope.user.lastName}"
                                />

                                <input
                                        class="h-[50px] w-full px-4 py-2 bg-white rounded-[40px] text-[#7b7b7b] border border-gray-200"
                                        placeholder="Email"
                                        name="email"
                                        type="email"
                                        value="${requestScope.user.email}"
                                />
                                        <c:if test="${requestScope.user == null}">
                                            <input
                                                    class="h-[50px] w-full px-4 py-2 bg-white rounded-[40px] text-[#7b7b7b] border border-gray-200"
                                                    placeholder="Password"
                                                    name="password"
                                                    type="password"
                                                    value="${requestScope.user.password}"
                                            />
                                        </c:if>


                                <button
                                        type="submit"
                                        class="h-[50px] w-full bg-[#0c0726] text-white rounded-[40px] font-normal hover:bg-[#1a1642] transition-colors"
                                >
                                    <c:if test="${requestScope.task != null}" >
                                        Edit
                                    </c:if>
                                    <c:if test="${requestScope.task == null}" >
                                        Add
                                    </c:if> Task

                                </button>
                            </div>
                        </form>
                </div>
            </div>
        </div>
</body>
</html>
