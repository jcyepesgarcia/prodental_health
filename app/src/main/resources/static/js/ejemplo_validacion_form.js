document.querySelector('.nickName').focus();

const form = document.getElementById('add_new_paciente');
const submitButton = document.getElementById('btn-add-new-paciente');

let timeout = null;//Se crea esta variable para agregar un tiempo de espera en el evento 'keydown' con el método setTimeout(), y que el evento no recoja de uno en uno los carácteres digitados por el ususario.

//El objeto errors contiene los diferentes atributos name, de cada uno de los elementos input contenidos en las etiquetas div con clase .form-box.
let errors = {
    nombre: true,
    apellido: true,
    cedula: true,
    email: true,
    fechaIngreso: true,
    calle: true,
    numero: true,
    localidad: true,
    provincia: true,
}

const mailFormatRegex = /^[^@]+@\w+(\.\w+)+\w$/;//Expresión regular para la validación de estructura de un email.

document.querySelectorAll('.form-box').forEach((form_box) => {

    let boxInput = form_box.querySelector('input');

    boxInput.addEventListener('keydown', () => {

        clearTimeout(timeout);
        
        timeout = setTimeout(() => {
            validation(form_box, boxInput);
        }, 500);//El usuario tiene un tiempo de espera de 0.5 seg una vez se detiene al digitar, antes de que el evento se reinicie.
    });

});

//La función valida si el ususario ingresa información en los input. Si no lo hace agrega la clase .form-error al div form_box. Si lo hace agrega la clase .form-success al div form_box. 
validation = (form_box, boxInput) =>{

    if(boxInput.value==''){
        showErrorOrSuccess(true, form_box, boxInput);
    }else{
        showErrorOrSuccess(false, form_box, boxInput);
    };//Validación genérica si el usuario deja un ítem sin diligenciar.

    if(boxInput.name=='email'){
        if(!boxInput.value.match(mailFormatRegex)){
            showErrorOrSuccess(true, form_box, boxInput);
        }else{
            showErrorOrSuccess(false, form_box, boxInput);
        }
    };//Validación de la estructura de un email.

    sumitController();
}

//Función de errores y validación. Agrega o elimina las clases .form-success y .form-error dependiendo del caso evaluado.
showErrorOrSuccess = (check, form_box, boxInput) => {
    if(check){
        form_box.classList.remove('form-success');
        form_box.classList.add('form-error');
        errors[boxInput.name] = true;

    }else{
        form_box.classList.remove('form-error');
        form_box.classList.add('form-success');
        errors[boxInput.name] = false;
    }
}

//Desabilita el botón sumit, dependiendo de si la validación del input es correcta o no.
sumitController = () =>{
    console.log(errors);
    if(errors.nombre || errors.apellido || errors.cedula || errors.email || errors.fechaIngreso || errors.calle || errors.numero || errors.localidad || errors.provincia){
        submitButton.toggleAttribute('disabled', true);
    }else{
        submitButton.toggleAttribute('disabled', false);
    }
}


//Este código es para mostrar por consola los datos ingresados al formulario. 
form.addEventListener('submit', (event) => {
    
    // event.preventDefault();//Evita el comportamiento por defecto del botón sumit
    
    const formData = new FormData(event.target);//Encapsulamiento de todos los datos del formulario en una variable a través de la clase FormData.
    
    console.log([...formData]);//Muestra los datos por consola en un array.
    
    for(let [key, value] of formData.entries()){
        console.log(`${key}: ${value}`);
    }//Muestra los datos por consola de manera más amigable.
    
});