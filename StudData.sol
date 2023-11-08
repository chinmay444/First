// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract StudentData {
    struct Student {
        uint studentID;
        string name;
        uint age;
    }
    
    Student[] public students;

    fallback() external {
        revert("Fallback function is not allowed.");
    }

    function createStudent(uint _studentID, string memory _name, uint _age) public {
        Student memory newStudent = Student(_studentID, _name, _age);
        students.push(newStudent);
    }

    function getStudent(uint index) public view returns (uint, string memory, uint) {
        require(index < students.length, "Student not found.");
        Student memory student = students[index];
        return (student.studentID, student.name, student.age);
    }
}
