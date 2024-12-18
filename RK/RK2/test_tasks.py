import unittest
from tasks import first_task, second_task, third_task


class TestTasks(unittest.TestCase):
    def test_first_task(self):
        one_to_many = [("Emelyanov D.B.", 2, "Group B"), ("Semenov E.Y", 6, "Group F")]
        self.assertEqual(first_task(one_to_many), [("Emelyanov D.B.", 2, "Group B"), ("Semenov E.Y", 6, "Group F")])

    def test_second_task(self):
        one_to_many = [("Emelyanov D.B.", 2, "Group B"), ("Semenov E.Y", 6, "Group F"), ("Ivanov P.A.", 3, "Group C")]
        self.assertEqual(second_task(one_to_many), [("Group B", 1), ("Group C", 1), ("Group F", 1)])

    def test_third_task(self):
        many_to_many = [("Emelyanov D.B.", 1, "Group A"), ("Semenov E.Y", 2, "Group B"), ("Orlov V.M.", 8, "Group H")]
        self.assertEqual(third_task(many_to_many, 'em'), [('Semenov E.Y', 'Group B')])


if __name__ == '__main__':
    unittest.main()
