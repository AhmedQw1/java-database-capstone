document.addEventListener('DOMContentLoaded', () => {
    const registerForm = document.getElementById('register-form');
    const messageElement = document.getElementById('message');

    registerForm.addEventListener('submit', async (event) => {
        event.preventDefault();

        const name = document.getElementById('name').value;
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;

        try {
            const response = await fetch('http://localhost:8080/api/v1/auth/register/patient', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ name, email, password }),
            });

            if (response.ok) {
                messageElement.className = 'message success';
                messageElement.textContent = 'Registration successful! You can now log in.';
                registerForm.reset();
            } else {
                messageElement.className = 'message error';
                messageElement.textContent = 'Registration failed. Please try again.';
            }
        } catch (error) {
            messageElement.className = 'message error';
            messageElement.textContent = 'Unable to connect to the server.';
        }
    });
});