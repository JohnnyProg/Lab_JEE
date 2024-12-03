const baseURL = "/students";

// Fetch all students and display them in the list
async function fetchStudents() {
    const response = await fetch(baseURL);
    const students = await response.json();

    const studentList = document.getElementById('student-list');
    studentList.innerHTML = "";  // Clear current list

    students.forEach(student => {
        const listItem = document.createElement('li');
        console.log(student)
        listItem.textContent = `${student.id}.  ${student.name} ${student.surname} (Avg: ${student.average})`;
        studentList.appendChild(listItem);
    });
}

// Add a new student to the list
async function addStudent(student) {
    const response = await fetch(baseURL, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(student),
    });

    const newStudent = await response.json();
    console.log("Student added:", newStudent);
    fetchStudents(); // Refresh the list
}

// Delete a student by ID
async function deleteStudent(id) {
    const response = await fetch(`${baseURL}/${id}`, {
        method: "DELETE",
    });

    if (response.ok) {
        console.log(`Student with ID ${id} deleted`);
        fetchStudents(); // Refresh the list
    } else {
        console.error("Error deleting student");
    }
}

// Update a student's details
async function updateStudent(id, updatedStudent) {
    const response = await fetch(`${baseURL}/${id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(updatedStudent),
    });

    const updated = await response.json();
    console.log("Student updated:", updated);
    fetchStudents(); // Refresh the list
}

// Event listeners for form submissions
document.getElementById('add-student-form').addEventListener('submit', function (event) {
    event.preventDefault();
    const student = {
        name: document.getElementById('name').value,
        surname: document.getElementById('surname').value,
        average: parseFloat(document.getElementById('average').value),
    };
    addStudent(student);
});

document.getElementById('delete-student-form').addEventListener('submit', function (event) {
    event.preventDefault();
    const id = parseInt(document.getElementById('delete-id').value);
    deleteStudent(id);
});

document.getElementById('update-student-form').addEventListener('submit', function (event) {
    event.preventDefault();
    const id = parseInt(document.getElementById('update-id').value);
    const updatedStudent = {
        name: document.getElementById('update-name').value,
        surname: document.getElementById('update-surname').value,
        average: parseFloat(document.getElementById('update-average').value),
    };
    updateStudent(id, updatedStudent);
});

// Initial fetch to display students on page load
fetchStudents();