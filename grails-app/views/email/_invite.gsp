<%@ page contentType="text/html"%>
<body>
<h1> ${user} invited you to join topic ${topic}. Click on join to subscribe!!</h1>
<g:link url="${serverUrl}/topic/join/${topic}">Join</g:link>
</body>