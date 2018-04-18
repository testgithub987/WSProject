import HTMLTestRunner
import unittest
class htmlreportsdemo(unittest.TestCase):
    """ class description
    """
    def test_pass(self):
        """ test description
        """
        a =7
        b= 5
        self.assertGreaterEqual(a, b, "Greater")

    def test_fail(self):
        """ test description
        """
        self.fail()


suite = unittest.TestLoader().loadTestsFromTestCase(htmlreportsdemo) #replace htmlreportsdemo with your class name
unittest.TextTestRunner(verbosity=2)
output = open("results.html","w")
runner = HTMLTestRunner.HTMLTestRunner(stream=output,title='Test Report',description='This is for demonstrating HTMLTestResults')
runner.run(suite)