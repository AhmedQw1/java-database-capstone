document.addEventListener('DOMContentLoaded', () => {
    const token = localStorage.getItem('jwt');
    if (!token) {
        window.location.href = 'index.html';
        return;
    }

    const logoutButton = document.getElementById('logout-button');
    const datePicker = document.getElementById('appointment-date');
    const appointmentsList = document.getElementById('appointments-list');

    logoutButton.addEventListener('click', () => {
        localStorage.removeItem('jwt');
        window.location.href = 'index.html';
    });

    // Function to fetch and display appointments
    const fetchAppointments = async (date) => {
        appointmentsList.innerHTML = '<li>Loading...</li>'; // Show loading indicator
        try {
            const response = await fetch(`http://localhost:8080/api/v1/doctors/me/appointments?date=${date}`, {
                method: 'GET',
                headers: {
                    'Authorization': 'Bearer ' + token
                }
            });

            if (response.ok) {
                const appointments = await response.json();
                appointmentsList.innerHTML = ''; // Clear list
                if (appointments.length === 0) {
                    appointmentsList.innerHTML = '<li>No appointments scheduled for this date.</li>';
                } else {
                    appointments.forEach(app => {
                        const listItem = document.createElement('li');
                        // ✅ Display patient name from AppointmentResponseDTO
                        listItem.textContent = `Time: ${new Date(app.appointmentTime).toLocaleTimeString()} - Patient: ${app.patientName} - Status: ${app.status} - Reason: ${app.reason || 'N/A'}`;
                        appointmentsList.appendChild(listItem);
                    });
                }
            } else {
                appointmentsList.innerHTML = '<li>Error loading appointments.</li>';
            }
        } catch (error) {
            appointmentsList.innerHTML = '<li>Could not connect to the server.</li>';
        }
    };

    // Event listener for the date picker
    datePicker.addEventListener('change', () => {
        if (datePicker.value) {
            fetchAppointments(datePicker.value);
        }
    });

    // Set default date to today and fetch appointments
    const today = new Date().toISOString().split('T')[0];
    datePicker.value = today;
    fetchAppointments(today);
});
