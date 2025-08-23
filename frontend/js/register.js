document.addEventListener('DOMContentLoaded', () => {
    const registerForm = document.getElementById('register-form');
    const messageElement = document.getElementById('message');

    if (registerForm) {
        registerForm.addEventListener('submit', async (event) => {
            event.preventDefault();

            const name = document.getElementById('name').value;
            const email = document.getElementById('email').value;
            const password = document.getElementById('password').value;

            messageElement.textContent = ''; // Clear previous messages
            messageElement.className = 'message';

            try {
                const response = await fetch('http://localhost:8080/api/v1/auth/register/patient', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({ name, email, password }),
                });

                if (response.ok) {
                    messageElement.textContent = 'Registration successful! You can now log in.';
                    messageElement.classList.add('success');
                    registerForm.reset();
                } else {
                    messageElement.textContent = 'Registration failed. The email may already be in use.';
                    messageElement.classList.add('error');
                }
            } catch (error) {
                messageElement.textContent = 'Unable to connect to the server.';
                messageElement.classList.add('error');
                console.error('Registration Error:', error);
            }
        });
    }
});