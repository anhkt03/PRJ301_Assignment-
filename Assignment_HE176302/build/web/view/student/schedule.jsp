<%-- 
    Document   : timetable
    Created on : Mar 5, 2024, 11:05:40 AM
    Author     : kieuthanhtheanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Schedule</title>
    </head>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        button.home {
            padding: 10px;
            margin-bottom: 10px;
            cursor: pointer;
        }

        form#time {
            margin-bottom: 20px;
        }

        label,
        select {
            margin-right: 10px;
        }

        table {
            border-collapse: collapse;
            width: 100%;
        }

        th,
        td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }
    </style>
    <body>
        <button class="home" onclick="goToHomePage()">Home</button>
        <form id="time" action="schedule" method="GET">
            <input type="hidden" name="sid" value="${param.sid}"/>
            <label for="year">Year</label>
            <select id="year" name="year" onchange="updateWeeks()">
                <% 
                    int currentYear = java.time.LocalDate.now().getYear();
                    for (int i = currentYear; i >= currentYear - 4; i--) {
                %>
                <option value="<%= i %>" <% if (i == currentYear) out.print("selected"); %>><%= i %></option>
                <% } %>
            </select>
            <br/>
            <label for="week">Week</label>
            <select id="week" name="week">
                <option></option>
            </select>
            <input type="submit" onclick="submitForm()" value="View">
        </form>

        <table border="1px">
            <tr>
                <td></td>
                <c:forEach items="${requestScope.dates}" var="day">
                    <td>${day}</td>
                </c:forEach>

            </tr>
            <tr>
                <td></td>
                <td>Monday</td>
                <td>Tuesday</td>
                <td>Wednesday</td>
                <td>Thursday</td>
                <td>Friday</td>
                <td>Saturday</td>
                <td>Sunday</td>
            </tr>
            <c:forEach items="${requestScope.slots}" var="slot">
                <tr>
                    <td>${slot.tname}<br/>${slot.start}-${slot.end}</td>
                        <c:forEach items="${requestScope.dates}" var="d">
                        <td>
                            <c:forEach items="${requestScope.sches}" var="sche">
                                <c:if test="${(sche.date eq d) and (sche.slot.tid eq slot.tid)}">
                                    ${sche.groupStudent.subject.subcode}
                                    <br/>at ${sche.room.rname}
                                    <br/><c:if test="${ses.isAttend}">
                                        Attend
                                    </c:if>
                                    <c:if test="${!ses.isAttend}">
                                        Absent
                                    </c:if>
                                </c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>

        </table>
    </body>
    <script>
        function goToHomePage() {
            window.location.href = "home";
        }

        function updateWeeks() {
            var selectedYear = document.getElementById("year").value;
            var selectedWeek = document.getElementById("week").value;
            var weeksDropdown = document.getElementById("week");

            weeksDropdown.innerHTML = "";

            var startDate = new Date("01/01/" + selectedYear);
            var endDate = new Date("12/31/" + selectedYear);
            var currentDate = startDate;

            var today = new Date();

            // Get the day of the week (0 = Sunday, 1 = Monday, ..., 6 = Saturday)
            var currentDayOfWeek = today.getDay();

            // Calculate the start day of the week (assuming Sunday is the start of the week)
            var startDayOfWeek = new Date(today);
            startDayOfWeek.setDate(today.getDate() - (currentDayOfWeek - 1));

            // Calculate the end day of the week (assuming Saturday is the end of the week)
            var endDayOfWeek = new Date(today);
            endDayOfWeek.setDate(today.getDate() + (7 - currentDayOfWeek));

            while (currentDate <= endDate) {
                var monday = new Date(currentDate.setDate(currentDate.getDate() - currentDate.getDay() + (currentDate.getDay() === 0 ? -6 : 1)));
                var sunday = new Date(currentDate.setDate(currentDate.getDate() - currentDate.getDay() + 7));
                var weekLabel = formatDate(monday) + " To " + formatDate(sunday);
                var option = new Option(weekLabel, weekLabel);
                if (formatDate(monday) === formatDate(startDayOfWeek) && formatDate(sunday) === formatDate(endDayOfWeek)) {
                    option.setAttribute("selected", "selected");
                }


                weeksDropdown.options.add(option);
                currentDate.setDate(currentDate.getDate() + 7);
            }

            document.getElementById('week') = selectedWeek;
        }

        function formatDate(date) {
            var dd = String(date.getDate()).padStart(2, '0');
            var mm = String(date.getMonth() + 1).padStart(2, '0');
            var yyyy = date.getFullYear();
            return dd + '/' + mm;
        }


        window.onload = function () {
            updateWeeks();
        };
    </script>
</html>
