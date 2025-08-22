document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.getElementById('login-form');
    if (!loginForm) return;

    loginForm.addEventListener('submit', async (event) => {
        event.preventDefault();

        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;
        const errorMessage = document.getElementById('error-message');
        errorMessage.textContent = ''; // Clear previous errors

        // ALWAYS use the single, unified login endpoint
        const loginUrl = 'http://localhost:8080/api/v1/auth/login';

        let dashboardUrl = '';
        const path = window.location.pathname;

        // Determine where to redirect based on the current page
        if (path.includes('/admin/')) {
            dashboardUrl = 'dashboard.html';
        } else if (path.includes('/doctor/')) {
            dashboardUrl = 'dashboard.html';
        } else if (path.includes('/patient/')) {
            dashboardUrl = 'dashboard.html';
        }

        try {
            const response = await fetch(loginUrl, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ email, password }),
            });

            if (response.ok) {
                const data = await response.json();
                localStorage.setItem('jwt', data.jwt); // Save the token
                window.location.href = dashboardUrl; // Redirect to dashboard
            } else {
                errorMessage.textContent = 'Invalid email or password.';
            }
        } catch (error) {
            errorMessage.textContent = 'Unable to connect to the server.';
            console.error('Login error:', error);
        }
    });
});