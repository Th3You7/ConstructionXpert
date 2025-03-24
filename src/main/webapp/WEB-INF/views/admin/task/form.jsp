<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 3/23/2025
  Time: 5:47 PM
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
        <div class="bg-white rounded-[16px] mt-6 overflow-hidden">
            <div class="w-full">
                <div
                        class="flex flex-wrap justify-between items-center gap-4 p-6 border-b border-gray-100"
                >
                    <h2 class="text-2xl font-bold text-[#0c0726] basis-full">
                        TasK&nbsp;&nbsp;&gt;&nbsp;&nbsp;<c:if test="${requestScope.task == null}">Add</c:if><c:if test="${requestScope.task != null}">Edit</c:if> Form
                    </h2>
                    <c:if test="${requestScope.task != null}">
                    <form
                            class="flex flex-col max-w-[500px] w-[500px] mx-auto my-[32px] items-start justify-center"
                            action="edit"
                            method="post"
                    >
                        <input hidden="hidden" name="id" value="${requestScope.task.id}">
                        </c:if>
                        <c:if test="${requestScope.task == null}">
                        <form
                                class="flex flex-col max-w-[500px] w-[500px] mx-auto my-[32px] items-start justify-center"
                                action="add"
                                method="post"
                        >
                            </c:if>
                            <div
                                    class="gap-8 self-stretch w-full flex flex-col items-start"
                            >
                                <input
                                        class="h-[50px] w-full px-4 py-2 bg-white rounded-[40px] text-[#7b7b7b] border border-gray-200"
                                        placeholder="Start date"
                                        name="startDate"
                                        onfocus="(this.type='date')"
                                        onblur="(this.type='text')"
                                        value="${requestScope.task.startDate}"
                                />
                                <input
                                        class="h-[50px] w-full px-4 py-2 bg-white rounded-[40px] text-[#7b7b7b] border border-gray-200"
                                        placeholder="End date"
                                        name="endDate"
                                        onfocus="(this.type='date')"
                                        onblur="(this.type='text')"
                                        value="${requestScope.task.endDate}"
                                />
                               <select
                                       class="h-[50px] w-full px-4 py-2 bg-white rounded-[40px] text-[#7b7b7b] border border-gray-200"
                                       name="projectId" >
                                   <c:forEach var="project" items="${requestScope.projects}">
                                       <c:if test="${project.id == requestScope.task.project.id}">
                                           <option value="<c:out value="${project.id}" />" selected>
                                       </c:if>
                                       <c:if test="${project.id != requestScope.task.project.id}">
                                           <option value="<c:out value="${project.id}" />">
                                       </c:if>
                                           <c:out value="${project.title}" />
                                       </option>
                                   </c:forEach>
                               </select>
                                <input
                                        class="h-[50px] w-full px-4 py-2 bg-white rounded-[40px] text-[#7b7b7b] border border-gray-200"
                                        placeholder="Title"
                                        name="title"
                                        value="${requestScope.task.title}"
                                />
                                <textarea
                                        class="h-[150px] w-full px-4 py-2 bg-white rounded-[16px] text-[#7b7b7b] border border-gray-200"
                                        placeholder="Enter a description"
                                        name="description"
                                >${requestScope.task.description}</textarea>
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
