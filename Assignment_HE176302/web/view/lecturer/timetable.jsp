<%-- 
    Document   : timetable
    Created on : Feb 29, 2024, 10:17:39 PM
    Author     : kieuthanhtheanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Time Table</title>
    </head>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        form {
            margin-bottom: 20px;
        }

        label {
            margin-right: 10px;
        }

        select, input[type="submit"] {
            margin-right: 20px;
            padding: 5px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 10px;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        a {
            color: #007bff;
            text-decoration: none;
        }


        a:hover {
            text-decoration: underline;
        }
    </style>
    <body>
        <button class="home" onclick="goToHomePage()">Home</button>
        <form id="time" action="timetable" method="GET">
            <input type="hidden" name="id" value="${param.id}"/>
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
            <c:forEach items = "${requestScope.slots}" var="slot">
                <tr>
                    <td>${slot.tname}<br/>${slot.start}-${slot.end}</td>
                        <c:forEach items="${requestScope.dates}" var="d">
                        <td>
                            <c:forEach items="${requestScope.sessions}" var="ses">
                                <c:if test="${(ses.date eq d) and (ses.slot.tid eq slot.tid)}">
                                    ${ses.groupStudent.gname}
                                    <br/>
                                    ${ses.groupStudent.subject.subcode}
                                    <br/>at ${ses.room.rname}
                                    <a href="attend?seid=${ses.seid}"> 
                                        <br/>
                                        <c:if test="${ses.isAttend}">
                                            Edit Attend
                                        </c:if>
                                        <c:if test="${!ses.isAttend}">
                                            Take Attendance
                                        </c:if>
                                    </a>
                                </c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>

        </table>

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
    </body>
</html>
