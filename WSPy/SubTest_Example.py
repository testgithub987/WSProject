import unittest,HTMLTestRunner,re

def is_alphabetized(value):
    value = value.lower()
    value = re.sub(r'[^a-z]', '', value)
    return ''.join(sorted(value)) == value


def assert_is_alphabetized(self, input):
    self.assertTrue(is_alphabetized(input), u'{} was not considered alphabetized'.format(input))


def assert_is_not_alphabetized(self, input):
    self.assertFalse(is_alphabetized(input), u'{} was considered alphabetized'.format(input))


def make_method(func, input):

    def test_input(self):
        func(self, input)

    test_input.__name__ = 'test_{func}_{input}'.format(func=func.__name__, input=input)
    return test_input


def generate(func, *inputs):
    """
    Take a TestCase and add a test method for each input
    """
    def decorator(klass):
        for input in inputs:
            test_input = make_method(func, input)
            setattr(klass, test_input.__name__, test_input)
        return klass

    return decorator


@generate(assert_is_alphabetized, '', 'a', 'aaaaa', 'ab', 'abcd', 'A-Cert', 'iOS')
@generate(assert_is_not_alphabetized, 'ba', 'aba', 'bob')
class IsAlphabetizedTestCase(unittest.TestCase):
    pass




# def make_method(input):
#     test_name = 'test_alphabetical_{}'.format(input)
# 
#     def test_input(self):
#         self.assertTrue(is_alphabetized(input), u'{} was not considered alphabetized'.format(input))
# 
#     return test_name, test_input
# 
# def add_methods(*inputs):
#     """
#     Take a TestCase and add a test method for each input
#     """
#     def decorator(klass):
#         for input in inputs:
#             test_name, test_input = make_method(input)
#             setattr(klass, test_name, test_input)
#         return klass
# 
#     return decorator
# 
# 
# @add_methods('', 'a', 'aaaaa', 'ab', 'abcd', 'A-Cert', 'iOS')
# class IsAlphabetizedTestCase(unittest.TestCase):
#     pass
            
            
            
            
if __name__ == "__main__":
      
#     obj = ExecuteTestSuite()
#     obj.test_runSuite()
#     obj.test_run_setExecutionType()
#     unittest.main()


    suite = unittest.TestLoader().loadTestsFromTestCase(IsAlphabetizedTestCase) #replace htmlreportsdemo with your class name
    unittest.TextTestRunner(verbosity=1)
    output = open("results.html","w")
    runner = HTMLTestRunner.HTMLTestRunner(stream=output,title='Test Report',description='This is for demonstrating HTMLTestResults')
    runner.run(suite)