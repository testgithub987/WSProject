import unittest
import ExecuteTestSuite
import os
import HTMLTestRunner
 
direct = os.getcwd()
 
class MyTestSuite(unittest.TestCase):
 
    def test_Issue(self):
 
        smoke_test = unittest.TestSuite()
        smoke_test.addTests([
            unittest.defaultTestLoader.loadTestsFromTestCase(ExecuteTestSuite.ExecuteTestSuite)

        ])
 
        outfile = open(direct + "\SmokeTest.html", "w")
 
        runner1 = HTMLTestRunner.HTMLTestRunner(
            stream=outfile,
            title='Test Report',
            description='Smoke Tests'
        )
 
        runner1.run(smoke_test)
 
 
 
 
 
if __name__ == '__main__':
    unittest.main()