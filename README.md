# Student Management System

Student ko enrol, grade, search aur report kar sakte hai. Data students.txt me save hota hai.

## How to Run
javac Main.java
java Main

## Class Hierarchy
- StudentOperations (Interface) - displayDetails() aur calculateGrade() ka rule
- Student (Abstract Class) - id, name, age, course, marks rakhta hai
- RegularStudent (Child Class) - Student ko extend karta hai, grade calculate karta hai
- FileHandler - students.txt ko read/write karta hai
- StudentManager - enrol, grade, search, report ka logic
- Main - menu aur user input
