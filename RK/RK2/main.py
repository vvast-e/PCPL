# main.py
from student import Student
from group import Group
from student_group import Student_Group
from tasks import first_task, second_task, third_task

def main():
    students = [
        Student(1, "Emelyanov D.B.", 20, 2),
        Student(2, "Semenov E.Y", 22, 6),
        Student(3, "Dmitriev S.A", 21, 17),
        Student(4, "Pavlenko T.D.", 21, 1),
        Student(5, "Ivanov P.A.", 20, 3),
        Student(6, "Petrov I.N.", 21, 1),
        Student(7, "Sidorov O.K.", 22, 6),
        Student(8, "Orlov V.M.", 20, 2)
    ]

    groups = [
        Group(1, "Group A", 1),
        Group(2, "Group B", 2),
        Group(3, "Group C", 3),
        Group(4, "Group D", 4),
        Group(5, "Group E", 5)
    ]

    groups_students = [
        Student_Group(1, 1),
        Student_Group(2, 2),
        Student_Group(3, 3),
        Student_Group(3, 2),
        Student_Group(4, 1),
        Student_Group(5, 3),
        Student_Group(6, 4),
        Student_Group(7, 5),
        Student_Group(8, 2)
    ]

    one_to_many = [(st.name, st.id_group, gr.name)
                   for st in students
                   for gr in groups
                   if st.id_group == gr.id]

    many_to_many_temp = [(st.name, sg.student_id, gr.name)
                         for st in students
                         for sg in groups_students
                         if sg.student_id == st.id]

    many_to_many = [(student_name, student_id, gr.name)
                    for student_name, student_id, group_id in many_to_many_temp
                    for gr in groups if gr.id == group_id]

    print('Задание 1')
    print(first_task(one_to_many))

    print("\nЗадание 2")
    print(second_task(one_to_many))

    print("\nЗадание 3")
    print(third_task(many_to_many, 'ov'))

if __name__ == '__main__':
    main()