<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HIS</title>
</head>
<body>
<h1>${error}</h1>
<h1>forgot password</h1>
<form:form action="forgotPasswordSendEmail" method="POST"
					modelAttribute="userform">
		<table>
			<tr>
				<td>Email</td>
				<td><form:input type="text" path="email" name="email"/> <font
					color='red'></font></td>

			</tr>
			
			<tr>
				<td><input type="submit" value="submit"></td>
			</tr>

		</table>

	</form:form>
</body>
</html>