document.addEventListener('DOMContentLoaded', () => {
    const token = localStorage.getItem('jwt');
    if (!token) { window.location.href = 'index.html'; return; }

    const logoutButton = document.getElementById('logout-button');
    const searchForm = document.getElementById('search-form');
    const searchInput = document.getElementById('doctor-name');
    const resultsDiv = document.getElementById('doctor-search-results');

    logoutButton.addEventListener('click', () => {
        localStorage.removeItem('jwt');
        window.location.href = 'index.html';
    });

    searchForm.addEventListener('submit', async (event) => {
        event.preventDefault();
        const searchTerm = searchInput.value;
        if (!searchTerm) return;

        resultsDiv.innerHTML = '<p>Searching...</p>';
        try {
            const response = await fetch(`http://localhost:8080/api/v1/doctors/search?name=${searchTerm}`, {
                headers: { 'Authorization': 'Bearer ' + token }
            });

            if (response.ok) {
                const doctors = await response.json();
                resultsDiv.innerHTML = '';
                if (doctors.length > 0) {
                    doctors.forEach(doc => {
                        const docElement = document.createElement('div');
                        docElement.classList.add('doctor-card');
                        const today = new Date().toISOString().split('T')[0];

                        docElement.innerHTML = `
                            <h3>${doc.name} - ${doc.specialty}</h3>
                            <div class="form-group">
                                <label for="date-${doc.id}">Select Appointment Date:</label>
                                <input type="date" id="date-${doc.id}" min="${today}">
                            </div>
                            <p>Available times for selected date:</p>
                        `;

                        const timesList = document.createElement('div');
                        timesList.classList.add('times-container');

                        doc.availableTimes.sort().forEach(time => {
                            const bookButton = document.createElement('button');
                            bookButton.classList.add('time-slot-button');
                            bookButton.textContent = time.substring(0, 5);
                            bookButton.onclick = () => bookAppointment(doc.id, time, `date-${doc.id}`);
                            timesList.appendChild(bookButton);
                        });
                        docElement.appendChild(timesList);
                        resultsDiv.appendChild(docElement);
                    });
                } else {
                    resultsDiv.innerHTML = '<p>No doctors found.</p>';
                }
            } else {
                resultsDiv.innerHTML = '<p>Error searching for doctors.</p>';
            }
        } catch (error) {
            resultsDiv.innerHTML = '<p>Could not connect to the server.</p>';
        }
    });

    const bookAppointment = async (doctorId, time, datePickerId) => {
        const selectedDate = document.getElementById(datePickerId).value;
        if (!selectedDate) {
            alert("Please select a date for the appointment.");
            return;
        }

        const appointmentDateTimeString = `${selectedDate}T${time}`;

        try {
            const response = await fetch('http://localhost:8080/api/v1/appointments', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + token
                },
                // --- THIS IS THE FIX ---
                // Add the 'reason' field to match the backend DTO
                body: JSON.stringify({
                    doctorId,
                    appointmentTime: appointmentDateTimeString,
                    reason: "General Check-up"
                })
            });

            if (response.ok) {
                alert('Appointment booked successfully!');
            } else {
                const errorData = await response.json();
                alert(`Failed to book appointment: ${errorData.error || 'Please try again.'}`);
            }
        } catch (error) {
            alert('Error connecting to the server.');
        }
    };
});