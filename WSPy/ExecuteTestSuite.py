from ConfigParser import SafeConfigParser
import xlrd,json
from TestExecutionMode import TestExecutionMode
from Rest import Rest
import unittest
import HTMLTestRunner
# from xlrd.sheet import ctype_text
# from SampleexcelTest import row


class ExecuteTestSuite(unittest.TestCase):
    
    parser = SafeConfigParser()
    parser.read('Config.ini')
    executeMode = parser.get('Config', 'TYPE_OF_EXECUTION')
    executeMode = executeMode.lower()
    testdatafiletype = parser.get('Config', 'TestDateFileType')
    testdatafiletype = testdatafiletype.lower()
    Host_URL = parser.get('Config', 'Host_URL')
    Host_URL = Host_URL.lower() 
    # Reading Test_Suite.xls file
    xl_workbook = xlrd.open_workbook('Test_Suite.xls')
    workbooksheet_obj = xl_workbook.sheet_by_name('Tests')
    requestRowCount = int(workbooksheet_obj.nrows)
    requestColumnCount = int(workbooksheet_obj.ncols)
    
    def xls(self):
        
        obj = TestExecutionMode()
        testCasesToExecuteList = obj.getExecutionType(self.executeMode, self.workbooksheet_obj, self.requestRowCount)
        print testCasesToExecuteList
        if testCasesToExecuteList.__len__() > 0:
            print "testCasesToExecuteList.size :" , testCasesToExecuteList.__len__()          
            for tcId in testCasesToExecuteList :
                self.str_tc_description = str(self.getValues('TC_DESCRIPTION', tcId))
                print self.str_tc_description
                self.str_tc_methodname = str(self.getValues('Method Name', tcId))
                print self.str_tc_methodname
                if (self.getValues('Reguest Body', tcId) == None) or (self.getValues('Reguest Body', tcId) == "") :
                    self.str_tc_requestbody = str(self.getValues('Reguest Body', tcId))
                else:
                    self.str_tc_requestbody = eval(json.loads(json.dumps(self.getValues('Reguest Body', tcId))))
                print self.str_tc_requestbody
                self.str_tc_requestheaders = str(self.getValues('Request Headers', tcId))
                print self.str_tc_requestheaders
                self.str_tc_endpointurl = str(self.getValues('End Point URL', tcId))
                print self.str_tc_endpointurl
                self.str_tc_expectedstatuscode = str(self.getValues('Expected Status Code', tcId))
                print self.str_tc_expectedstatuscode 
                self.str_tc_expectedelementpath = str(self.getValues('Expected Element Path & Name', tcId))
                print self.str_tc_expectedelementpath
                self.str_tc_expectedvalue = str(self.getValues('Expected Value', tcId))
                print self.str_tc_expectedvalue
                self.restMethodCall(self.str_tc_description, self.str_tc_methodname, self.str_tc_requestbody, self.str_tc_requestheaders, self.str_tc_endpointurl, self.str_tc_expectedstatuscode, self.str_tc_expectedelementpath, self.str_tc_expectedvalue)
 
    def json(self):
        
        with open('TestSuite.json') as json_file:
            data = json_file.read()
        data1 = json.loads(data)
        for i in range(0, data1.__len__()):
            if data1[i]['execute'].lower() == "yes" :
                for j in range(0, data1[i]["Testcase_Testdata"].__len__()):
                    testdata = data1[i]["Testcase_Testdata"][j]
                    print ""*40
                    print "******"*40
                    print ""*40
                    print "Testcase Descrition --> : " , testdata.get("TC_DESCRIPTION")
                    self.str_tc_description = str(testdata.get("TC_DESCRIPTION"))
                    print "Testcase ID --> : " + testdata.get("TC_ID")                          
                    self.str_tc_id = str(testdata.get("TC_ID"))
                    self.str_tc_methodname = str(testdata.get("Method_Name"))
                    print "Method Name : " , self.str_tc_methodname
                    self.str_tc_requestbody = str(testdata.get("Reguest_Body"))
                    print  "Request Body : " , self.str_tc_requestbody
                    self.str_tc_requestheaders = testdata.get("Request_Headers")
                    print  "Request Headers : " , self.str_tc_requestheaders
                    self.str_tc_endpointurl = str(testdata.get("EndPoint_URL"))
                    print  "End Point URL : " , self.str_tc_endpointurl
                    self.str_tc_expectedstatuscode =  str(testdata.get("Expected_Status_Code"))
                    print  "Expected Status Code : " , self.str_tc_expectedstatuscode
                    self.str_tc_expectedelementpath =  str(testdata.get("Expected_ElementPath"))
                    print  "Expected Element Path in JSon Response : " , self.str_tc_expectedelementpath
                    self.str_tc_expectedvalue =  str(testdata.get("Expected_Value"))
                    print  "Expected Value : " , self.str_tc_expectedvalue
                    self.restMethodCall(self.str_tc_description, self.str_tc_methodname, self.str_tc_requestbody, self.str_tc_requestheaders, self.str_tc_endpointurl, self.str_tc_expectedstatuscode, self.str_tc_expectedelementpath, self.str_tc_expectedvalue)
    def xml(self, worksheetObj, requestRowCount): 
               
        pass

    switcher = {
        "xls" : xls,
        "json"  : json,
        "xml" : xml
            }
  
    def test_run_setExecutionType(self):
        func = self.switcher.get(self.testdatafiletype)
        return func(self)
       
    def getValues(self, value, tcId):
        
        for row in range(self.workbooksheet_obj.nrows):
            for col in range(self.workbooksheet_obj.ncols):
                if self.workbooksheet_obj.cell_value(row, col) == value:
                    col1 = col
                if self.workbooksheet_obj.cell_value(row, col) == tcId:
                    row1 = row
        return self.workbooksheet_obj.cell(row1, col1).value
    
    def restMethodCall(self, str_tc_description, str_tc_methodname, str_tc_requestbody, str_tc_requestheaders, str_tc_endpointurl, str_tc_expectedstatuscode, str_tc_expectedelementpath, str_tc_expectedvalue):
        
        rest = Rest()
        print "str_tc_methodname", type(str_tc_methodname)
        if str_tc_methodname.upper() == "GET":
            getrequestresult = rest.getRequest(self.Host_URL, str_tc_endpointurl, str_tc_requestheaders, str_tc_expectedstatuscode)
            if str(getrequestresult) == str_tc_expectedvalue:
                print "GET Request Test Case Passed"
                print "Actual Response   : ", getrequestresult
                print "Expected Response : ", str_tc_expectedvalue
        if str_tc_methodname.upper() == "POST":
            postrequestresult = rest.postRequest(self.Host_URL, str_tc_endpointurl, str_tc_requestbody, str_tc_requestheaders, str_tc_expectedstatuscode)
            print "Actual Response   : ", postrequestresult
            print "Expected Response : ", str_tc_expectedvalue
            if str(postrequestresult) == str_tc_expectedvalue:
                print "POST Request Test Case Passed"
                print "Actual Response   : ", postrequestresult
                print "Expected Response : ", str_tc_expectedvalue
        if str_tc_methodname.upper() == "PUT":
            putrequestresult = rest.putRequest(self.Host_URL, str_tc_endpointurl, str_tc_requestbody, str_tc_requestheaders, str_tc_expectedstatuscode)
            if str(putrequestresult) == str_tc_expectedvalue:
                print "PUT Request Test Case Passed"
                print "Actual Response   : ", putrequestresult
                print "Expected Response : ", str_tc_expectedvalue
        if str_tc_methodname.upper() == "DELETE":
            deleterequestresult = rest.deleteRequest(self.Host_URL, str_tc_endpointurl, str_tc_requestbody, str_tc_requestheaders, str_tc_expectedstatuscode)
            if str(deleterequestresult) == str_tc_expectedvalue:
                print "DELETE Request Test Case Passed"
                print "Actual Response   : ", deleterequestresult
                print "Expected Response : ", str_tc_expectedvalue
            
        
#     def test_runSuite(self):
#         
#         self.test_run_setExecutionType()
#         self.restMethodCall()
         
#----------------------------------------------------------------------
if __name__ == "__main__":
      
#     obj = ExecuteTestSuite()
#     obj.test_runSuite()
#     obj.test_run_setExecutionType()
#     unittest.main()


    suite = unittest.TestLoader().loadTestsFromTestCase(ExecuteTestSuite) #replace htmlreportsdemo with your class name
    unittest.TextTestRunner(verbosity=1)
    output = open("results.html","w")
    runner = HTMLTestRunner.HTMLTestRunner(stream=output,title='Test Report',description='This is for demonstrating HTMLTestResults')
    runner.run(suite)
