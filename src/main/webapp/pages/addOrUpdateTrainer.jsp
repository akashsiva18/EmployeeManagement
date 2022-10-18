<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<body style="background-color:LightGray; font-size:20px; margin-left : 550px;">
<form:form method = "Post" modelAttribute="trainerDTO" action = "addTrainer">
    <h2><% String action = request.getParameter("flag");
        if (action.equals("addTrainer")) {
        %>Add Trainer Details<%;
        } else {
        %>Update Trainer Details<%;
        }%></h2>
    <table>
        <input type="hidden" name="method" value="<%= ((action.equals("addTrainer")) ? "addTrainer" : "updateTrainer")%>" >
        <form:select hidden="hidden" path="roleDTO.description">
            <form:option value="Trainer" selected="selected"/>
        </form:select>
        <form:input type="hidden" path="id" />
        <tr>
            <td>
                Name:
            </td>
            <td>
                <form:input  path="name" required="required"/>
            </td></tr>
        <tr><td>
            Date Of Birth:
        </td>
            <td>
                <form:input type="date" path="dateOfBirth" required="required"/>
            </td></tr>
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
            <form:input path="qualificationDTO.course" required="required"/>
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
                Training Experience:
            </td>
            <td>
                <form:input type="number" path="experience" required="required"/>
            </td>
        </tr>
    </table>
    <input type="submit" value="Submit">
    <a href="/viewTrainer" ><input type="button" value="back"></a>
</form:form>

</body>
</html>