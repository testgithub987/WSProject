Feature: REST API testing framework using Java REST Assured or JayWay Libraries 
	Raise request(s) using REST Assured library
	Validate HTTP response code and parse JSON response using REST Assured library
	Make sure to run the intended REST API based web application as pre-condition

Background: 
	Given Set basic web application url is "https://mlrs-ws.herokuapp.com/"
Scenario: GET request example
  Given Set GET api endpoint as "tests"
  When Set HEADER param request content type as "application/json"
	And Set HEADER param response accept type as "application/json"
	And Set HEADER param response mlrs-token as "EAB1E2D218AD14C6E8836FCBEC157DBB4D687D8F4C8041174DD1115AD3D9CE8F"
	And Set HEADER param response mlrs-email as "mlrsadminlabone@gmail.com"
	And Set Query param as "empty" 
	And Raise "GET" HTTP request
  Then Valid HTTP response should be received
	And Response http code should be 200 
	And Response HEADER content type should be "application/json" 
	And Response BODY should not be null or empty 
	And Response BODY parsing for "Test" should be successful