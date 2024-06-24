document.addEventListener('DOMContentLoaded', function() {
    const checkboxes = document.querySelectorAll('.respuesta-checkbox');

    checkboxes.forEach(checkbox => {
        checkbox.addEventListener('change', function() {
            if (this.checked) {
                // Obtener el ID de la pregunta actual
                const questionId = this.getAttribute('data-question-id');

                // Deseleccionar todos los demás checkboxes de la misma pregunta
                checkboxes.forEach(otherCheckbox => {
                    if (otherCheckbox !== this && otherCheckbox.getAttribute('data-question-id') === questionId) {
                        otherCheckbox.checked = false;
                    }
                });
            }
        });
    });
});
