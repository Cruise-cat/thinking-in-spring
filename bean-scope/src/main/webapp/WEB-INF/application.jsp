<?xml version="1.0" encoding="UTF-8" ?>
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8"
                    pageEncoding="UTF-8" />
<html>
<head>
    <link rel="stylesheet" href="<spring:theme code='styleSheet'/>" type="text/css"/>
</head>
<body>
\${applicationPerson} : ${applicationPerson}
<br>
\${applicationPerson.name} : ${applicationPerson.name}
<br>
\${applicationScope['scopedTarget.applicationPerson']} : ${applicationScope['scopedTarget.applicationPerson']}
<br>
\${applicationScope['scopedTarget.applicationPerson'].name} : ${applicationScope['scopedTarget.applicationPerson'].name}
</body>
</html>