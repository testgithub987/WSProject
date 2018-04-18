# import json
#  
# with open('TestSuite.json') as json_data:
#     d = json.load(json_data)
#     print(d)



import json
from pprint import pprint
 


with open('TestSuite_new.json') as json_file:
    filedata = json_file.read()
    print type(filedata)
    print filedata
json_testdata = json.loads(filedata)
count =0
lst = []
for i in range(0, json_testdata.__len__()):
    print json_testdata[i]
    if json_testdata[i]['EXECUTE'].lower() == "yes" :
        count = count+1
        lst.append(count)
for k in range(0, lst.__len__()):
    print lst[k]
        
for i in range(0, json_testdata.__len__()):
    if json_testdata[i]['EXECUTE'].lower() == "yes" :
        print "Testcase Descrition --> : " , json_testdata[i].get("TC_DESCRIPTION")
        str_tc_description = str(json_testdata[i].get("TC_DESCRIPTION"))
        print "Testcase ID --> : " + json_testdata[i].get("TC_ID")                          
        str_tc_id = str(json_testdata[i].get("TC_ID"))
        str_tc_methodname = str(json_testdata[i].get("Method_Name"))
        print "Method Name : " , str_tc_methodname
        str_tc_requestbody = str(json_testdata[i].get("Reguest_Body"))
        print  "Request Body : " , str_tc_requestbody
        str_tc_requestheaders = str(json_testdata[i].get("Request_Headers"))
        print  "Request Headers : " , str_tc_requestheaders
        str_tc_endpointurl = str(json_testdata[i].get("EndPoint_URL"))
        print  "End Point URL : " , str_tc_endpointurl
        str_tc_expectedstatuscode =  str(json_testdata[i].get("Expected_Status_Code"))
        print  "Expected Status Code : " , str_tc_expectedstatuscode
        str_tc_expectedelementpath =  str(json_testdata[i].get("Expected_ElementPath"))
        print  "Expected Element Path in JSon Response : " , str_tc_expectedelementpath
        str_tc_expectedvalue =  str(json_testdata[i].get("Expected_Value"))
        print  "Expected Value : " , str_tc_expectedvalue  
        print "##"*40

# for i in range(0, data1.__len__()):
#     if data1[i]['execute'].lower() == "yes" :
#         for j in range(0, data1[i]["Testcase_Testdata"].__len__()):
#             testdata = data1[i]["Testcase_Testdata"][j]
#             print ""*40
#             print "******"*40
#             print ""*40
#             print "Testcase Descrition --> : " , testdata.get("TC_DESCRIPTION")
#             str_tc_description = str(testdata.get("TC_DESCRIPTION"))
#             print "Testcase ID --> : " + testdata.get("TC_ID")                          
#             str_tc_id = str(testdata.get("TC_ID"))
#             str_tc_methodname = str(testdata.get("Method_Name"))
#             print "Method Name : " , str_tc_methodname
#             str_tc_requestbody = str(testdata.get("Reguest_Body"))
#             print  "Request Body : " , str_tc_requestbody
#             str_tc_requestheaders = str(testdata.get("Request_Headers"))
#             print  "Request Headers : " , str_tc_requestheaders
#             str_tc_endpointurl = str(testdata.get("EndPoint_URL"))
#             print  "End Point URL : " , str_tc_endpointurl
#             str_tc_expectedstatuscode =  str(testdata.get("Expected_Status_Code"))
#             print  "Expected Status Code : " , str_tc_expectedstatuscode
#             str_tc_expectedelementpath =  str(testdata.get("Expected_ElementPath"))
#             print  "Expected Element Path in JSon Response : " , str_tc_expectedelementpath
#             str_tc_expectedvalue =  str(testdata.get("Expected_Value"))
#             print  "Expected Value : " , str_tc_expectedvalue
  
  
  
            
            
            
#             for k, v in data1[i]["Testcase_Testdata"][j].iteritems():
#                 print "Testcase Descrition --> : " , testdata.get("TC_DESCRIPTION"));
#                 System.out.println("Testcase ID --> : " + testdata.get("TC_ID"));                           
#                 str_tc_description =  testdata.get("TC_DESCRIPTION");
#                 print k , v
        
        
        
        
#     if data1[i]["execute"] is "yes":
#         print data1[i]["Testcase_Testdata"]





    

