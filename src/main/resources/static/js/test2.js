document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('evaluacionForm');
    const submitButton = document.getElementById('boton-submit');

    form.addEventListener('submit', function(event) {
        event.preventDefault(); // Prevenir el envío automático del formulario

        let allQuestionsValid = true;
console.log("waos1");
        // Iterar sobre cada pregunta y verificar que al menos una respuesta está seleccionada
        document.querySelectorAll('[id^="pregunta-"]').forEach(function(preguntaElement) {
        console.log("waos");
            const preguntaId = preguntaElement.getAttribute('id').split('-')[1];
            const respuestasSeleccionadas = preguntaElement.querySelectorAll('input[type="checkbox"]:checked');

            if (respuestasSeleccionadas.length === 0) {
                // No se seleccionó ninguna respuesta para esta pregunta
                allQuestionsValid = false;
                // Podrías resaltar la pregunta o mostrar un mensaje de error aquí si lo deseas
                console.log(`Pregunta ${preguntaId}: Selecciona al menos una respuesta.`);
            }
        });

        if (allQuestionsValid) {
            // Si todas las preguntas tienen al menos una respuesta seleccionada, enviar el formulario
            form.submit();
        } else {
            // Si hay preguntas sin respuesta seleccionada, puedes mostrar un mensaje de error o hacer alguna acción
            console.log('Debe seleccionar al menos una respuesta por pregunta.');
            // Aquí podrías mostrar un mensaje de error visible al usuario
        }
    });
});
