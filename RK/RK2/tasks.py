from operator import itemgetter
from student import Student
from group import Group
from student_group import Student_Group

def first_task(one_to_many):
    res_1 = sorted(one_to_many, key=itemgetter(0))
    return res_1

def second_task(one_to_many):
    temp_dict = {}
    for student_name, student_id_group, group_name in one_to_many:
        temp_dict[group_name] = temp_dict.get(group_name, 0) + 1

    res_2 = [(group_name, count) for group_name, count in temp_dict.items()]
    res_2.sort(key=lambda x: (-x[1], x[0]))  # Сортировка по количеству и имени
    return res_2


def third_task(many_to_many, substring):
    result = []
    for student_name, _, group_name in many_to_many:
        if substring in student_name:  # Проверка на наличие подстроки в имени студента
            result.append((student_name, group_name))
    return result
