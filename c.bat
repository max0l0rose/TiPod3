curl -v -X PUT http://localhost:8080/api/v1/hello-world ^
	-H accept:*/* --cookie "XSRF-TOKEN=11ba8fdd-f51b-4573-9847-81ba97bab418" ^
	-H X-XSRF-TOKEN:11ba8fdd-f51b-4573-9847-81ba97bab418 ^
	-H Authorization:"Basic dXNlcjpwd2Q="
