# intall python on your laptop
# install sql with mysql workbench
# create database(admissions) in workbench
# In your VS Code Terminal, run:python -m pip install mysql-connector-python
# run file: python admission.py
import mysql.connector

conn=mysql.connector.connect(
    host="localhost",
    port=3306,
    user="root",
    password="dbms",
    database="admission_db"
)

cursor=conn.cursor()

def add_admission():
    name=input(("Enter Your Name:"))
    age=input(("Enter Your Age:"))
    gender=input(("Enter Your Gender:"))
    course=input(("Enter Course:"))
    contact=input(("Enter Phone no:"))

    sql="INSERT INTO admissions(name, age, gender, course, contact) VALUES(%s,%s,%s,%s,%s)"
    values=(name,age,gender,course,contact)
    cursor.execute(sql,values)
    conn.commit()
    print("Admission Added Successfully!")

def view_admission():
    sql="SELECT * FROM admissions"
    cursor.execute(sql)
    rows=cursor.fetchall()
#     rows = [
#     (1, 'Hari', 21, 'Male', 'BCA', '9876543210'),
#     (2, 'Priya', 20, 'Female', 'BSc', '9998887776')
#      ]
    print("")
    for row in rows:
        print(f"ID: {row[0]}, Name: {row[1]}, Age: {row[2]}, Gender: {row[3]}, Course: {row[4]}, Contact: {row[5]}")

def search_admission():
    admission_id=int(input("Enter Student Id to Search:"))
    cursor.execute("SELECT * FROM admissions WHERE id=%s",(admission_id,))
    row=cursor.fetchone()
    if row:
       print(f"ID: {row[0]}, Name: {row[1]}, Age: {row[2]}, Gender: {row[3]}, Course: {row[4]}, Contact: {row[5]}")
    else:
        print("Student Not Found!")

def update_admission():
    admission_id=int(input("Enter Admission ID to Update:"))
    name=input("Enter New Name:")
    age=int(input("Enter New Age:"))
    gender=input("Enter New Gender:")
    course=input("Enter New Course:")
    contact=input("Enter New Contact:")

    sql="UPDATE admissions SET name=%s,age=%s,gender=%s,course=%s,contact=%s WHERE id=%s"
    values=(name,age,gender,course,contact,admission_id)
    cursor.execute(sql,values)
    conn.commit()
    print("Admission Updated Successfully!")

def delete_admission():
    admission_id=int(input("Enter The Id To Delete Admission:"))
    sql="DELETE FROM admissions WHERE id=%s"
    cursor.execute(sql,(admission_id,))
    conn.commit
    print("Successfully Deleted Admission!")
def main():
    while True:
        print("\n=======Admission Management System=======")
        print("1.Add Admission")
        print("2.view All Admissions")
        print("3.Search Admission by ID")
        print("4.Update Admission")
        print("5.Delete Admission")
        print("Exit")

        choice=input("Enter your Choice:")

        if choice=="1":
            add_admission()
        elif choice=="2":
            view_admission()
        elif choice=="3":
            search_admission()
        elif choice=="4":
            update_admission()
        elif choice=="5":
            delete_admission()
        elif choice=="6":
            print("Thank you for Using System")
            break
        else:
            print("Invalid Choice.Try Again!!")

main()

cursor.close()
conn.close()
