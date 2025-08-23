document.addEventListener('DOMContentLoaded', () => {
    const token = localStorage.getItem('jwt');
    // If no token is found, redirect to the login page
    if (!token) {
        window.location.href = 'index.html';
        return;
    }

    const registerForm = document.getElementById('register-doctor-form');
    const messageElement = document.getElementById('message');
    const logoutButton = document.getElementById('logout-button');

    logoutButton.addEventListener('click', () => {
        localStorage.removeItem('jwt'); // Clear the token
        window.location.href = 'index.html'; // Redirect to login
    });

    registerForm.addEventListener('submit', async (event) => {
        event.preventDefault();

        const name = document.getElementById('name').value;
        const specialty = document.getElementById('specialty').value;
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;

        messageElement.textContent = '';
        messageElement.className = 'message';

        try {
            const response = await fetch('http://localhost:8080/api/v1/admin/doctors', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    // Include the JWT in the Authorization header
                    'Authorization': 'Bearer ' + token
                },
                body: JSON.stringify({ name, specialty, email, password }),
            });

            if (response.ok) {
                messageElement.textContent = 'Doctor registered successfully!';
                messageElement.classList.add('success');
                registerForm.reset(); // Clear the form
            } else {
                const errorData = await response.json();
                messageElement.textContent = `Error: ${errorData.message || 'Registration failed.'}`;
                messageElement.classList.add('error');
            }
        } catch (error) {
            messageElement.textContent = 'Unable to connect to the server.';
            messageElement.classList.add('error');
            console.error('Registration error:', error);
        }
    });
});