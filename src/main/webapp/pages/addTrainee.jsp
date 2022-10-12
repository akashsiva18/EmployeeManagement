<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>


<html>
<body style="background-color:LightGray; font-size:20px; margin-left : 550px;">

<form:form method ="Post" modelAttribute="trainee" action = "addTrainee">
    <h2><%  String action = request.getParameter("flag");
        if (action.equals("addTrainee")) {
        %>Add Trainee Details<%;
        } else {
        %>Update Trainee Details<%;
        }%></h2>

    <input type="hidden" id="flag" name="flag" value="<%=action%>" >
    <input type="hidden" id="flag" name="method" value=<%= ((action.equals("addTrainee")) ? "addTrainee" : "updateTrainee")%> >

    <form:input type="hidden" path="id" />
    <table>
        <tr>
            <td>
                Name
            </td>
            <td>
            :<form:input path="name" required="required"/>
            </td>
        </tr>
        <tr>
            <td>
                Date Of Birth:<br>
            </td>
            <td>
                <form:input type="date" path="dateOfBirth" required="required"/>
            </td>
        </tr>
        <tr>
            <td>
                Gender :
            </td>
            <td>
                <form:radiobutton path="gender" value="male" required="required"/>Male
                <form:radiobutton path="gender" value="female" required="required"/>Female
                <form:radiobutton path="gender" value="others" required="required"/>Others
            </td>
        </tr>
        <tr>
            <td>
                Qualification :
            </td>
            <td>
                <form:input path="qualification.course" required="required"/>
            </td>
        </tr>
        <tr>
            <td>
                Address :
            </td>
            <td>
                <form:input path="address" required="required"/>
            </td>
        </tr>
        <tr>
            <td>
                Mobile Number :
            </td>
            <td>
                <form:input type="number" path="mobileNumber" required="required"/>
            </td>
        </tr>
        <tr>
            <td>
                Email ID :
            </td>
            <td>
                <form:input type="email" path="emailId" required="required"/>
            </td>
        </tr>
        <tr>
            <td>
                Date of Joining:
            </td>
            <td>
                <form:input type="date" path="dateOfJoining" required="required"/>
            </td>
        </tr>
        <tr>
            <td>
                Training Period(in months):
            </td>
            <td>
                <form:input path="trainingPeriod" required="required"/>
            </td>
        </tr>
        <tr>
            <td>
        Trainers:
        </td>
        <td>
            <form:select path="trainersId">
                <c:forEach items="${trainers}"  var="trainer">
                    <form:option value="${trainer.id}">${trainer.id}.${trainer.name}</form:option>
                </c:forEach>
            </form:select>
        </td>
        </tr>
    </table>
    <br>
    <input type="submit" value="Submit">
    <a href="/viewTrainee"><input type="button" value="back"></a>
</form:form>
</body>
</html>
