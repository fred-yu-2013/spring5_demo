<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form:form action="/helloJspForm">
    <table>
        <tr>
            <td>Subscribe to newsletter?:</td>
            <%-- Approach 1: Property is of type java.lang.Boolean --%>
            <td><form:checkbox path="receiveNewsletter"/></td>
        </tr>

        <tr>
            <td>Interests:</td>
            <%-- Approach 2: Property is of an array or of type java.util.Collection --%>
            <td>
                Quidditch: <form:checkbox path="interests" value="Quidditch"/>
                Herbology: <form:checkbox path="interests" value="Herbology"/>
                Defence Against the Dark Arts: <form:checkbox path="interests" value="Defence Against the Dark Arts"/>
            </td>
        </tr>

        <tr>
            <td>Favourite Word:</td>
            <%-- Approach 3: Property is of type java.lang.Object --%>
            <td>
                Magic: <form:checkbox path="favouriteWord" value="Magic"/>
            </td>
        </tr>

        <tr>
            <td>Select with options</td>
            <%-- Approach 1: direct link to list --%>
            <td><form:select path="skill" items="${skills}"/></td>
        </tr>

        <tr>
            <td>House:</td>
            <td>
                <form:select path="skill2">
                    <form:option value="Gryffindor"/>
                    <form:option value="Hufflepuff"/>
                    <form:option value="Ravenclaw"/>
                    <form:option value="Slytherin"/>
                </form:select>
            </td>
        </tr>

        <tr>
            <input type="submit" value="Save Changes"/>
        </tr>
    </table>
</form:form>
</body>
</html>