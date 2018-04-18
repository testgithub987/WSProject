import requests,json,ast

class Rest:

    def getRequest(self, Host_URL, URI, str_tc_requestheaders, str_tc_expectedstatuscode):
    
        url = Host_URL + URI
        
        str_tc_requestheaders = str(str_tc_requestheaders)
        requestheaders = {}
        if str_tc_requestheaders.find("#"):
            Headers = str_tc_requestheaders.split("#")
            for i in range(0, Headers.__len__()):
                header = Headers[i].strip().split("=")                             
                requestheaders[header[0]]=header[1].strip()
        else :
            header = str_tc_requestheaders.split("=")
            
            requestheaders[header[0]] = header[1].strip()            
        response = requests.get(url=url, headers= requestheaders)
#         print "code:"+ str(response.status_code)
#         print "headers:"+ str(response.headers)
#         print "content:"+ str(response.text)
        getresponse = json.dumps(response.json())        
#         return response.status_code, getresponse
        return response.status_code, getresponse
    
    def postRequest(self, Host_URL, URI, RequestBody, str_tc_requestheaders, str_tc_expectedstatuscode):
    
        url = Host_URL + URI        
        #postrequest_body = {"status": 1001, "firstName": "StaffSamplea", "mobile": "7760257251", "lastName": "MLRS", "role": 1003, "gender": "M", "email": "asdf@gmail.com"})
        if type(RequestBody) == unicode:           
            RequestBody = ast.literal_eval(RequestBody)      
        str_tc_requestheaders = str(str_tc_requestheaders)
        requestheaders = {}
        if str_tc_requestheaders.find("#"):
            Headers = str_tc_requestheaders.split("#")
            for i in range(0, Headers.__len__()):
                header = Headers[i].strip().split("=")                             
                requestheaders[header[0]]=header[1].strip()
        else :
            header = str_tc_requestheaders.split("=")
            requestheaders[header[0]] = header[1].strip()
        response = requests.post(url=url, json=RequestBody, headers= requestheaders)
        postresponse = json.dumps(response.json())
        return response.status_code, postresponse
    
    def putRequest(self, Host_URL, URI, RequestBody, str_tc_requestheaders, str_tc_expectedstatuscode):
    
        url = Host_URL + URI        
        if type(RequestBody) == unicode:           
            RequestBody = ast.literal_eval(RequestBody)
        str_tc_requestheaders = str(str_tc_requestheaders)
        requestheaders = {}
        if str_tc_requestheaders.find("#"):
            Headers = str_tc_requestheaders.split("#")
            for i in range(0, Headers.__len__()):
                header = Headers[i].strip().split("=")                             
                requestheaders[header[0]]=header[1].strip()
        else :
            header = str_tc_requestheaders.split("=")
            requestheaders[header[0]] = header[1].strip() 
        
        response = requests.put(url=url, json = RequestBody, headers= requestheaders)
        putresponse = json.dumps(response.json())
        return response.status_code, putresponse
    
    def deleteRequest(self, Host_URL, URI, RequestBody, str_tc_requestheaders, str_tc_expectedstatuscode):
    
        url = Host_URL + URI
        if type(RequestBody) == unicode:           
            RequestBody = ast.literal_eval(RequestBody)       
        str_tc_requestheaders = str(str_tc_requestheaders)
        requestheaders = {}
        if str_tc_requestheaders.find("#"):
            Headers = str_tc_requestheaders.split("#")
            for i in range(0, Headers.__len__()):
                header = Headers[i].strip().split("=")                             
                requestheaders[header[0]]=header[1].strip()
        else :
            header = str_tc_requestheaders.split("=")
            requestheaders[header[0]] = header[1].strip() 
        
        response = requests.delete(url=url, json = RequestBody, headers= requestheaders)
        deleteresponse = json.dumps(response.json())
        return response.status_code, deleteresponse
        
if __name__ == '__main__':
    
    obj = Rest()
    
#     getrequest_headers = {"Accept": "application/json", "mlrs-token":"EAB1E2D218AD14C6E8836FCBEC157DBB4D687D8F4C8041174DD1115AD3D9CE8F", "mlrs-email": "mlrsadminlabone@gmail.com"}
#     print type(getrequest_headers)
    getrequest_headers = "Accept=application/json#mlrs-token=EAB1E2D218AD14C6E8836FCBEC157DBB4D687D8F4C8041174DD1115AD3D9CE8F#mlrs-email=mlrsadminlabone@gmail.com"
    getresponse = obj.getRequest('https://mlrs-ws.herokuapp.com/', 'tests', getrequest_headers, '200')
    print "*"*20 + "Get Request Response : " +"*"*20
    print getresponse
    
#     postrequest_headers = {"Accept": "application/json", "mlrs-token":"EAB1E2D218AD14C6E8836FCBEC157DBB4D687D8F4C8041174DD1115AD3D9CE8F", "mlrs-email": "mlrsadminlabone@gmail.com"}
    postrequest_headers = "Accept=application/json#mlrs-token=EAB1E2D218AD14C6E8836FCBEC157DBB4D687D8F4C8041174DD1115AD3D9CE8F#mlrs-email=mlrsadminlabone@gmail.com"
#     postrequest_body = {"email": "asdf@gmail.com",    "firstName": "StaffSamplea",    "lastName": "MLRS",    "mobile": "7760257251",    "gender": "M",    "status": 1001,    "role": 1003}
    postrequest_body = {"status": 1001, "firstName": "StaffSamplea", "mobile": "7760257251", "lastName": "MLRS", "role": 1003, "gender": "M", "email": "asdf@gmail.com"}
    print type(postrequest_body)
    postresponse = obj.postRequest('https://mlrs-ws.herokuapp.com/', 'staff', postrequest_body, postrequest_headers, '200')
    print "*"*20 + "Post Request Response : " +"*"*20
    print postresponse   
     
#     putrequest_headers = {"Accept": "application/json", "mlrs-token":"EAB1E2D218AD14C6E8836FCBEC157DBB4D687D8F4C8041174DD1115AD3D9CE8F", "mlrs-email": "mlrsadminlabone@gmail.com"}
    putrequest_headers = "Accept=application/json#mlrs-token=EAB1E2D218AD14C6E8836FCBEC157DBB4D687D8F4C8041174DD1115AD3D9CE8F#mlrs-email=mlrsadminlabone@gmail.com"
    puttrequest_body = {"firstName":"StaffSample",  "lastName":"MLRS",  "mobile":"7760257257",  "gender":"M"}
    putresponse = obj.putRequest('https://mlrs-ws.herokuapp.com/', 'staff/asdf@gmail.com', puttrequest_body, putrequest_headers, '200')
    print "*"*20 + "Put Request Response : " +"*"*20
    print putresponse   
     
#     deleterequest_headers = {"Accept": "application/json", "mlrs-token":"EAB1E2D218AD14C6E8836FCBEC157DBB4D687D8F4C8041174DD1115AD3D9CE8F", "mlrs-email": "mlrsadminlabone@gmail.com"}
    deleterequest_headers = "Accept=application/json#mlrs-token=EAB1E2D218AD14C6E8836FCBEC157DBB4D687D8F4C8041174DD1115AD3D9CE8F#mlrs-email=mlrsadminlabone@gmail.com"
    deleterequest_body = {"firstName":"StaffSample",  "lastName":"MLRS",  "mobile":"7760257257",  "gender":"M"}
    deleteresponse = obj.deleteRequest('https://mlrs-ws.herokuapp.com/', 'staff/asdf@gmail.com', deleterequest_body, deleterequest_headers, '200')
    print "*"*20 + "Delete Request Response : " +"*"*20
    print deleteresponse   