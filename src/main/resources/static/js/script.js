//Despliegue del menu principal
let listElements = document.querySelectorAll('.lista-item, .lista-item-2');

listElements.forEach(listElement => {
	listElement.addEventListener('click', () => {
		listElement.classList.toggle('arrow');
		let menu = listElement.nextElementSibling;
		menu.classList.toggle('active');
	});
});

//ejecutar funcion con click
document.getElementById("btn-open").addEventListener("click", open_close_menu);

//creacion de variables
var side_menu = document.getElementById("menu-principal");
var btn_open = document.getElementById("btn-open");
var body = document.getElementById("body");
var lista = document.getElementById("lista-close");
var contenido = document.getElementById("contenedor-move");

//evento para mostrar y ocultar menu
function open_close_menu() {
	body.classList.toggle("body-move");
	side_menu.classList.toggle("menu-principal-move");
	lista.classList.toggle("lista-close");
	contenido.classList.toggle("contenedor-move");
	body.classList.toggle("moved");
}

function confirmarOcultar() {
	return confirm('¿Estás seguro de que deseas eliminar esta entrada?');
}

function confirmarCorrecta() {
	return confirm('¿Estás seguro de que deseas definir esta respuesta como correcta?');
}


function closeNotification(notificationId) {
    var notification = document.getElementById(notificationId);
    notification.style.display = "none";
}




const form = document.getElementById('archivoForm');
const archivoInput = document.getElementById('archivo');
const csrfToken = document.querySelector('input[name="_csrf"]').value;

form.addEventListener('change', () => {
  const archivo = archivoInput.files[0];

  if (!archivo) {
    return; // No se seleccionó ningún archivo
  }

  const formData = new FormData();
  formData.append('archivo', archivo);

  const url = form.action; // Obtiene la URL del action del formulario

  fetch(url, {
    method: 'POST',
    body: formData,
    headers: {
      'X-CSRF-TOKEN': csrfToken
    }
  })
  .then(response => {
    if (!response.ok) {
      throw new Error(`Error al subir el archivo: ${response.statusText}`);
    }
    console.log('Archivo subido correctamente');
    window.location.reload();
  })
  .catch(error => {
    console.error('Error:', error);
  });
});



