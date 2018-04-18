import xlrd
 
#----------------------------------------------------------------------

class TestExecutionMode:
    testCasesToExecuteList = []
    
    def yes(self, worksheetObj, requestRowCount):
        
        for row_idx in range(1, requestRowCount): 
            executeFlag = worksheetObj.cell(row_idx, 1).value
            print executeFlag
            if executeFlag.lower() == "yes" :               
                self.testCasesToExecuteList.append(int(worksheetObj.cell(row_idx, 0).value))
        for i in range(1, self.testCasesToExecuteList.__len__()):
            print self.testCasesToExecuteList[i].__str__()
        return self.testCasesToExecuteList
 
    def no(self, worksheetObj, requestRowCount):
        
        for row_idx in range(1, requestRowCount): 
            executeFlag = worksheetObj.cell(row_idx, 1).value
            print executeFlag
            if executeFlag.lower() == "no" :               
                self.testCasesToExecuteList.append(int(worksheetObj.cell(row_idx, 0).value))
        for i in range(1, self.testCasesToExecuteList.__len__()):
            print self.testCasesToExecuteList[i].__str__()
        return self.testCasesToExecuteList
     
    def all(self, worksheetObj, requestRowCount): 
               
        for row_idx in range(1, requestRowCount):    # Iterate through rows
            self.testCasesToExecuteList.append(int(worksheetObj.cell(row_idx, 0).value))
        for i in range(0, self.testCasesToExecuteList.__len__()):
            print self.testCasesToExecuteList[i].__str__()
        return self.testCasesToExecuteList
     
    switcher = {
            "yes" : yes,
            "no"  : no,
            "all" : all
                }
  
    def getExecutionType(self, executeMode, worksheetObj, requestRowCount):
        # Get the function from switcher dictionary
        func = self.switcher.get(executeMode)
#         print func
        # Execute the function
        return func(self, worksheetObj, requestRowCount)
    
    def getExecutionType_new(self, path, worksheetObj, requestRowCount, executeMode):       
        pass
        
        
        
        
#         testCasesToExecuteList = []
#         # Open the workbook
#         xl_workbook = xlrd.open_workbook(path)
#          
#         # List sheet names, and pull a sheet by name
#         #
#         sheet_names = xl_workbook.sheet_names()
#         print('Sheet Names', sheet_names)
#          
#         xl_sheet = xl_workbook.sheet_by_name(sheet_names[0])
#          
#         # Or grab the first sheet by index 
#         #  (sheets are zero-indexed)
#         #
#         xl_sheet = xl_workbook.sheet_by_index(0)
#         print ('Sheet name: %s' % xl_sheet.name)
#          
#         # Pull the first row by index
#         #  (rows/columns are also zero-indexed)
#         #
#         row = xl_sheet.row(0)  # 1st row
#          
#         # Print all values, iterating through rows and columns
#         #
#         num_cols = xl_sheet.ncols   # Number of columns
#          
#          
#          
#         for row_idx in range(1, xl_sheet.nrows):    # Iterate through rows
#             testCasesToExecuteList.append(xl_sheet.cell(row_idx, 0))
#         for i in range(0, testCasesToExecuteList.__len__()):
#             print testCasesToExecuteList[i].__str__()
#         return testCasesToExecuteList 
            
            
    #         print ('-'*40)
    #         print ('Row: %s' % row_idx)   # Print row number
    #         for col_idx in range(0, num_cols):  # Iterate through column
    #             cell_obj = xl_sheet.cell(row_idx, col_idx)  # Get cell object by row, col
    #             print ('Column: [%s] cell_obj: [%s]' % (col_idx, cell_obj))   
                
                
                
        
        
    #     for i in range(0, len(testCasesToExecuteList)):
    #         print testCasesToExecuteList[i] 
    #         xl_sheet.cell(row_idx, i)
    
            
            
        # get the first worksheet
    #     first_sheet = book.sheet_by_index(0)
    
    
    def open_file(self, path):
        """
        Open and read an Excel file
        """
        book = xlrd.open_workbook(path)
     
        # print number of sheets
        print book.nsheets
     
        # print sheet names
        print book.sheet_names()
        
     
        # get the first worksheet
        first_sheet = book.sheet_by_index(0)
    #     book.sheet_by_name('Tests')
     
        # read a row
        print first_sheet.row_values(0)
     
        # read a cell
        cell = first_sheet.cell(0,0)
        print cell
        print cell.value
     
        # read a row slice
        print first_sheet.row_slice(rowx=0,
                                    start_colx=0,
                                    end_colx=2)
 
#----------------------------------------------------------------------
if __name__ == "__main__":
    path = "Test_Suite.xls"
    obj = TestExecutionMode()
#     open_file(path)
    obj.getExecutionType()