<%--
  Created by IntelliJ IDEA.
  User: habib
  Date: 3/10/22
  Time: 10:32 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://habib.com/functions" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="<c:url value="/"/>">e-Shoppers</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value="/"/>">Home
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">About</a>
                </li>
                <c:choose>
                    <c:when test="${sec:isAuthenticated(pageContext.request)}">
                        <a class="nav-link" href="#" onclick="logout()">
                            Logout
                            [${sec:getCurrentUser(pageContext.request).firstName}]
                        </a>

                        <script>
                            function logout(){
                                document.getElementById("logoutForm").submit();
                            }
                        </script>

                        <form style="visibility: hidden" id="logoutForm" method="post" action="<c:url value="/logout"></c:url> ">
                        </form>

                    </c:when>
                    <c:otherwise>
                        <a class="nav-link" href="<c:url value="/login"/> ">Log In</a>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>
