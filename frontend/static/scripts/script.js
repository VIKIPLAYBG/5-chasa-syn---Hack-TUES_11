// Validate Patient Registration Form
function validatePatRegForm() {
    let user = document.getElementById("patregUser").value;
    let email = document.getElementById("patregEmail").value;
    let password = document.getElementById("patregPassword").value;
    let confirmPassword = document.getElementById("patregConfirmPassword").value;

    if (!user || !email || !password || !confirmPassword) {
        alert("Please fill in all the required fields.");
        return;
    }

    if (password !== confirmPassword) {
        alert("Passwords do not match. Please check again.");
        return;
    }

    window.location.href = "../complaints.html";  // Redirect to the complaints page after successful validation
}

// Validate Login Form
function validateLoginForm() {
    let email = document.getElementById("loginEmail").value;
    let password = document.getElementById("loginPassword").value;

    if (email && password) {
        window.location.href = '../complaints.html';  // Redirect to complaints page if login is successful
    } else {
        alert("Please fill in both email and password.");
    }
}

// Validate Psychologist Registration Form
function validateRegisterForm() {
    let firstName = document.getElementById("regFirstName").value;
    let lastName = document.getElementById("regLastName").value;
    let birthDate = document.getElementById("regBirthDate").value;
    let email = document.getElementById("regEmail").value;
    let password = document.getElementById("regPassword").value;
    let confirmPassword = document.getElementById("regConfirmPassword").value;

    if (!firstName || !lastName || !birthDate || !email || !password || !confirmPassword) {
        alert("Please fill in all the required fields.");
        return;
    }

    if (password !== confirmPassword) {
        alert("Passwords do not match. Please check again.");
        return;
    }

    // Save the psychologist's name in localStorage
    localStorage.setItem("psychologistName", firstName + " " + lastName);

    // Redirect to psychologist page after successful registration
    window.location.href = '../psychologist.html';
}

// Add a Comment
function addComment() {
    let commentText = document.getElementById("userComment").value.trim();
    if (commentText === "") {
        alert("Please, add a comment.");
        return;
    }

    let commentSection = document.getElementById("commentSection");
    let newComment = document.createElement("div");
    newComment.classList.add("comment");
    newComment.textContent = commentText + " - Anonymous user";  // Using textContent for security
    commentSection.appendChild(newComment);
    document.getElementById("userComment").value = "";  // Clear the comment input
}

// Logout function
function logout() {
    localStorage.clear();  // Clear localStorage to log the user out
    alert("You have been logged out!");
    window.location.href = "../templates/authentication/index.html";  // Redirect to the login page
}

// Add a Response to a Complaint
function addResponse(button) {
    let complaintDiv = button.parentElement;
    let textArea = complaintDiv.querySelector(".response-box");
    let responseText = textArea.value.trim();

    let profanities = ["damn", "hell", "crap", "fuck", "shit", "you idiot", "you suck", "sucker", "kys", "kill yourself"];

    function checkForProfanity(text) {
        for (let profanity of profanities) {
            const regex = new RegExp("\\b" + profanity + "\\b", "gi");
            if (regex.test(text)) {
                return true;
            }
        }
        return false;
    }

    if (checkForProfanity(responseText)) {
        alert("Warning: Your response contains inappropriate language. Please consider revising it.");
    } else if (responseText) {
        let responseDiv = document.createElement("div");
        responseDiv.classList.add("response");
        responseDiv.textContent = "You: " + responseText;  // Using textContent for security

        complaintDiv.querySelector(".responses").appendChild(responseDiv);
        textArea.value = "";  // Clear the response textarea
    } else {
        alert("Please write a response before submitting.");
    }
}
