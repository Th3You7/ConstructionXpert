<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 3/23/2025
  Time: 12:10 AM
  To change this template use File | Settings | File Templates.
--%>
<nav class="w-full py-4">
    <div class="flex w-full items-center justify-between flex-wrap gap-4">
        <div
                class="flex items-center justify-center px-6 py-3 bg-white rounded-[40px]"
        >
            <div
                    class="w-fit [font-family:'Inter',Helvetica] text-xl md:text-2xl text-[#0c0726]"
            >
                <span class="font-bold">Construction</span>
                <span class="font-normal">Xpert</span>
            </div>
        </div>

        <div class="hidden lg:flex items-center gap-2">
            <button
                    class="w-[60px] h-[60px] flex items-center justify-center bg-white rounded-full hover:bg-gray-50 transition-colors cursor-pointer"
            >
                <a href="/dashboard.jsp">
                    <i class="fas fa-home text-xl text-gray-700"></i>
                </a>
            </button>
            <button
                    class="w-[60px] h-[60px] flex items-center justify-center bg-white rounded-full hover:bg-gray-50 transition-colors cursor-pointer"
            >
                <a href="/project/list.jsp">
                    <i class="fas fa-folder text-xl text-gray-700"></i>
                </a>

            </button>
            <button
                    class="w-[60px] h-[60px] flex items-center justify-center bg-white rounded-full hover:bg-gray-50 transition-colors cursor-pointer"
            >
                <a href="/user/list.jsp">
                    <i class="fas fa-users text-xl text-gray-700"></i>
                </a>
            </button>
            <button
                    class="w-[60px] h-[60px] flex items-center justify-center bg-white rounded-full hover:bg-gray-50 transition-colors cursor-pointer"
            >
                <a href="/task/list.jsp">
                    <i class="fa-solid fa-thumbtack text-xl text-gray-700"></i>
                </a>
            </button>
            <button
                    class="w-[60px] h-[60px] flex items-center justify-center bg-white rounded-full hover:bg-gray-50 transition-colors cursor-pointer"
            >
                <a href="projects.jsp">
                    <i class="fas fa-home text-xl text-gray-700"></i>
                </a>

            </button>
        </div>

        <div class="flex items-center gap-3">
            <div
                    class="hidden lg:flex items-center gap-3 pl-3 pr-6 py-2 bg-white rounded-[40px]"
            >
                <button
                        class="px-3.5 py-3 rounded-[40px] border border-solid border-[#7b7b7b33] hover:bg-gray-50 transition-colors"
                >
                    <i class="fas fa-sign-out-alt text-xl text-gray-700"></i>
                </button>

                <div class="w-[50px] h-[50px] rounded-full overflow-hidden">
                    <img
                            src="https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d"
                            alt="User Profile"
                            class="w-full h-full object-cover"
                    />
                </div>

                <div class="flex flex-col items-start justify-center gap-1">
                    <div class="font-semibold text-[#0c0726] text-lg">
                        John Doe
                    </div>
                    <div class="font-normal text-[#7b7b7b] text-sm m-0">
                        adressjohn@gmail.com
                    </div>
                </div>
            </div>

            <!-- Mobile Menu Button -->

            <button
                    class="lg:hidden w-10 h-10 flex items-center justify-center bg-white rounded-full"
            >
                <i class="fas fa-times text-gray-700`"></i>
            </button>
        </div>
        <!-- Mobile nav menu  -->
        <div class="lg:hidden w-full flex-col bg-white rounded-[20px] p-4">
            <div
                    class="flex items-center gap-3 p-4 border-b border-gray-100 mb-2"
            >
                <div class="w-[50px] h-[50px] rounded-full overflow-hidden">
                    <img
                            src="https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d"
                            alt="User Profile"
                            class="w-full h-full object-cover"
                    />
                </div>
                <div class="flex flex-col">
                    <div class="font-semibold text-[#0c0726] text-lg">
                        John Doe
                    </div>
                    <div class="font-normal text-[#7b7b7b] text-sm">
                        adressjohn@gmail.com
                    </div>
                </div>
            </div>

            <button
                    class="flex items-center gap-3 w-full p-3 hover:bg-gray-50 rounded-lg transition-colors"
            >
                <i class="fas fa-home text-gray-700"></i>
                <span class="text-gray-700">Home</span>
            </button>
            <button
                    class="flex items-center gap-3 w-full p-3 hover:bg-gray-50 rounded-lg transition-colors"
            >
                <i class="fas fa-folder text-gray-700"></i>
                <span class="text-gray-700">Projects</span>
            </button>
            <button
                    class="flex items-center gap-3 w-full p-3 hover:bg-gray-50 rounded-lg transition-colors"
            >
                <i class="fas fa-home text-gray-700"></i>
                <span class="text-gray-700">Tasks</span>
            </button>
            <button
                    class="flex items-center gap-3 w-full p-3 hover:bg-gray-50 rounded-lg transition-colors"
            >
                <i class="fas fa-users text-gray-700"></i>
                <span class="text-gray-700">Stuff</span>
            </button>
            <button
                    class="flex items-center gap-3 w-full p-3 hover:bg-gray-50 rounded-lg transition-colors"
            >
                <i class="fas fa-home text-gray-700"></i>
                <span class="text-gray-700">Home</span>
            </button>

            <button
                    class="flex items-center gap-3 w-full p-3 hover:bg-gray-50 rounded-lg transition-colors mt-2 border-t border-gray-100"
            >
                <i class="fas fa-sign-out-alt text-gray-700"></i>
                <span class="text-gray-700">Logout</span>
            </button>
        </div>
    </div>
</nav>
