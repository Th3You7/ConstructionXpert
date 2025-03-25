<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 3/25/2025
  Time: 7:04 AM
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
<div class="bg-[#eff1f3] flex flex-row justify-center w-full min-h-screen">
    <div class="bg-[#eff1f3] w-full h-screen relative flex">
        <div class="flex items-center justify-center w-full lg:w-1/2">
            <div
                    class="flex flex-col w-[354px] items-start justify-center gap-[63px]"
            >
                <div class="flex flex-col items-start gap-4">
                    <h1
                            class="font-sans mb-1 text-5xl text-[#0c0726] tracking-normal"
                    >
                        Welcome Back
                    </h1>
                    <p class="font-sans text-base text-[#0c0726] tracking-normal">
                        Please enter your details
                    </p>
                </div>

                <form class="gap-8 self-stretch w-full flex flex-col items-start" action="/auth/login" method="post">
                    <input
                            name="email"
                            class="h-[50px] w-full px-4 py-2 bg-white rounded-[40px] text-[#7b7b7b] border border-gray-200"
                            placeholder="Enter your email"
                    />
                    <input
                            name="password"
                            class="h-[50px] w-full px-4 py-2 bg-white rounded-[40px] text-[#7b7b7b] border border-gray-200"
                            type="password"
                            placeholder="Enter your password"
                    />
                    <button
                            type="submit"
                            class="h-[50px] w-full bg-[#0c0726] text-white rounded-[40px] font-normal hover:bg-[#1a1642] transition-colors"
                    >
                        Sign In
                    </button>
                </form>
            </div>
        </div>

        <div class="w-1/2 h-full relative hidden lg:block">
            <img
                    class="absolute w-full h-full object-cover"
                    alt="Building construction"
                    src="https://images.unsplash.com/photo-1536895058696-a69b1c7ba34f?q=80&w=1935&auto=format&fit=crop"
            />
        </div>
    </div>
</div>
</body>
</html>
